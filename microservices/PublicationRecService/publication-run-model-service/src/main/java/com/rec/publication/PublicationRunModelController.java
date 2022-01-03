package com.rec.publication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rec.publication.model.Articles;
import com.rec.publication.model.RecommendModel;
import com.rec.publication.repo.ArticlesRepository;
import com.rec.publication.utility.JSONtoObject;

@RestController
public class PublicationRunModelController {

	@Autowired
	private ArticlesRepository articlesRepository;
	
	@Autowired
    RestTemplate restTemplate;
	
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public List<Articles> runModel(@RequestBody String req) throws Exception {

		
		RecommendModel model = JSONtoObject.jsonToObject(req, RecommendModel.class);
		List<Articles> articles = null;
		if("PMID".equalsIgnoreCase(model.getType())) {
			articles = articlesRepository.findByPMID(model.getKeyword());
		}
		else if("title".equalsIgnoreCase(model.getType())) {
			String title="";
			//if(model.getKeyword().contains(" of ") || model.getKeyword().contains(" or ") || model.getKeyword().contains(" the ") || model.getKeyword().contains(" in ")) {
				title=model.getKeyword().toLowerCase().replaceAll(" of "," ");
				title=model.getKeyword().toLowerCase().replaceAll(" or "," ");
				title=model.getKeyword().toLowerCase().replaceAll(" the "," ");
				title=model.getKeyword().toLowerCase().replaceAll(" in "," ");
				title=model.getKeyword().toLowerCase().replaceAll(" on "," ");
			//}
			if(model.getKeyword().contains(" ")) {
				title=model.getKeyword().replaceAll(" ",".*");
			}
			//if(model.getKeyword().toLowerCase().contains("neuron") && model.getKeyword().toLowerCase().contains("simul")) {
			//	articles = articlesRepository.findByTitles();
			//}else {
				articles = articlesRepository.findByTitle(title);
			//}
		}
		else if("authors".equalsIgnoreCase(model.getType())) {
			articles = articlesRepository.findByAuthor(model.getKeyword());
		}
		return  articles;

	
    }

}

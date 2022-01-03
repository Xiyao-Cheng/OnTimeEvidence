package com.rec.scholar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rec.scholar.model.RecommendModel;
import com.rec.scholar.model.Scholars;
import com.rec.scholar.repo.ScholarDao;
import com.rec.scholar.utility.JSONtoObject;

@RestController
public class ScholarRunModelController {

		@Autowired
		private ScholarDao scholarDao;

		@RequestMapping(value = "/", method = RequestMethod.POST)
		public List<Scholars> loadModel(@RequestBody String request) {

			RecommendModel model = JSONtoObject.jsonToObject(request, RecommendModel.class);
	
			ArrayList<Scholars> scholars = (ArrayList<Scholars>) scholarDao.getExperts(model.getTopicName());

			for (Scholars scholar : scholars) {
				Double multiply = 1.0;
				for (String topic : model.getTopicName().split(" ")) {
					multiply = multiply * Double.valueOf(scholar.getExpertise().get(topic));
				}
				scholar.setPoints(multiply);
				
				
		        Set<Entry<String, Double>> entries = scholar.getExpertise().entrySet();

		        List<Entry<String, Double>> listOfEntries = new ArrayList<Entry<String, Double>>(entries);
		        Collections.sort(listOfEntries, valueComparator);
		        List<Entry<String, Double>> listOfEntriesNew = new ArrayList<Entry<String, Double>>();
		        int i=0;
		        for(Entry<String, Double> entry : listOfEntries){
		        	listOfEntriesNew.add(entry);
		        	if(i == 9) {
		        		break;
		        	}
		        	i = i+1;
		        	
		            
		        }
				
				scholar.setExpertiseEntry(listOfEntriesNew);
				scholar.setExpertise(null);
			}

			scholars.sort(Comparator.comparingDouble(Scholars::getPoints).reversed());

			/*
			 * ArrayList<Scholars> finalScholars = new ArrayList<Scholars>(); int index=0;
			 * for(Scholars scholar :scholars) { if(index == 10) { break; }
			 * finalScholars.add(scholar);
			 * 
			 * index =index+1; }
			 */

			return scholars;

		}

	    Comparator<Entry<String, Double>> valueComparator = new Comparator<Entry<String,Double>>() {
	    	
	    	@Override 
	    	public int compare(Entry<String, Double> e1, Entry<String, Double> e2) {
	    		Double v1 = e1.getValue(); 
	    		Double v2 = e2.getValue();
	    		return v2.compareTo(v1); 
	    	}

	    };


	}
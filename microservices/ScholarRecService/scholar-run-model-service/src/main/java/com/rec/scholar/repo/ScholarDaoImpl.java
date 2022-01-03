package com.rec.scholar.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.rec.scholar.model.Scholars;


@Repository
public class ScholarDaoImpl implements ScholarDao{
	
	@Autowired
    MongoTemplate mongoTemplate;


	public List<Scholars> getExperts(String topics){
    	
		List<Scholars> scholars=null;
		//{$and: [{'expertise.cloud': {$gte: 0}}, {'expertise.computing': {$gte: 0}}]}
		
		List<Criteria> criterias = new ArrayList<>();
		 
		for(String topic : topics.split(" ")) {
	        Criteria dynamicCriteria = Criteria.where("expertise."+topic).gt(0.0);
	        criterias.add(dynamicCriteria);
		
		}
		Criteria criteria = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));

	    Query searchQuery = new Query(criteria);

	    scholars = mongoTemplate.find(searchQuery, Scholars.class);

    	
    	return scholars;
    }

}

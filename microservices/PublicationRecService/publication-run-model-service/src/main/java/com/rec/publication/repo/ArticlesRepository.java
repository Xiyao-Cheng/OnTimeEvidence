package com.rec.publication.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.rec.publication.model.Articles;


@Repository
public interface ArticlesRepository extends MongoRepository<Articles, String> {

    @Query("{PMID:'?0'}")
	List<Articles> findByPMID(String PMID);

    //@Query("{ 'title' : { $regex: ?0 } }")
    @Query("{$or:[{'title': {'$regex': '?0', '$options': 'i'}}, {'abstractDtls': {'$regex': '?0', '$options': 'i'}}]}")
	List<Articles> findByTitle(String title);

    @Query("{'authors': {'$regex': '?0', '$options': 'i'}}")
	List<Articles> findByAuthor(String author);

    @Query("{$or:[{\"title\" : { \"$regex\" : \"neuron simul\" , \"$options\" : \"i\"}},{\"abstractDtls\" : { \"$regex\" : \"neuron simul\" , \"$options\" : \"i\"}}]}")
	List<Articles> findByTitles();
}
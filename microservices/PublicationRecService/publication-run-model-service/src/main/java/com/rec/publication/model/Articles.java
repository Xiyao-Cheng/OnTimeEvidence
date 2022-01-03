package com.rec.publication.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Articles {

	@Id
	private String Id;
	private String title;
	private String abstractDtls;
	private String PMID ;
	private List<String> authors ;
	private String Journal ;
	 
	private String author;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	public String getPMID() {
		return PMID;
	}

	public void setPMID(String pMID) {
		PMID = pMID;
	}

	

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public String getJournal() {
		return Journal;
	}

	public void setJournal(String journal) {
		Journal = journal;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAbstractDtls() {
		return abstractDtls;
	}

	public void setAbstractDtls(String abstractDtls) {
		this.abstractDtls = abstractDtls;
	}
	
	
	
}
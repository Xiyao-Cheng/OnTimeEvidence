package com.rec.scholar.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Scholars {
	
	private ArrayList<Double> coordinate;
	private HashMap<String,Double> expertise;
	private String name;
	private List<Entry<String, Double>>  expertiseEntry;
	
	private Double points;
	
	
	public ArrayList<Double> getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(ArrayList<Double> coordinate) {
		this.coordinate = coordinate;
	}
	public HashMap<String, Double> getExpertise() {
		return expertise;
	}
	public void setExpertise(HashMap<String, Double> expertise) {
		this.expertise = expertise;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPoints() {
		return points;
	}
	public void setPoints(Double points) {
		this.points = points;
	}
	public List<Entry<String, Double>> getExpertiseEntry() {
		return expertiseEntry;
	}
	public void setExpertiseEntry(List<Entry<String, Double>> expertiseEntry) {
		this.expertiseEntry = expertiseEntry;
	}
	
	

}

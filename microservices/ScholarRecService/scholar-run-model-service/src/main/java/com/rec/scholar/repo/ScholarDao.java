package com.rec.scholar.repo;

import java.util.List;

import com.rec.scholar.model.Scholars;

public interface ScholarDao {

    List<Scholars> getExperts(String topic);

}

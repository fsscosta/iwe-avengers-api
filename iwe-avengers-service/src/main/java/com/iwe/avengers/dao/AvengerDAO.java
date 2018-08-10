package com.iwe.avengers.dao;

import java.util.HashMap;
import java.util.Map;

import com.iwe.avenger.dynamodb.entity.Avenger;

public class AvengerDAO {
	
	public Map<String, Avenger> mapper = new HashMap<>();
	
	public AvengerDAO() {
		
		mapper.put("sdsa-sasa-asas-sasa", new Avenger("sdsa-sasa-asas-sasa", "Iron Man", "Tony Stark"));
		
		mapper.put("aaaa-aaaa-aaaa-aaaa", new Avenger("aaaa-aaaa-aaaa-aaaa", "Hulk", "Bruce Banner"));
	}

	public Avenger find(String id) {
		return mapper.get(id);
	}
}

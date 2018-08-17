package com.iwe.avengers.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.dynamodb.manager.DynamoDBManager;

public class AvengerDAO {
	

	private static AvengerDAO instance;
	
	private DynamoDBMapper mapper = DynamoDBManager.mapper();

	public static AvengerDAO getInstance() {
		if (instance == null) {
			instance = new AvengerDAO();
		}
		return instance;
	}

	public Avenger find(final String id) {
		final Avenger avenger = mapper.load(Avenger.class, id);
		return avenger;
	}

	public Avenger create(Avenger newAvenger) {
		
		mapper.save(newAvenger);
		
		return newAvenger;
	}

	public void remove(String id) {
		//mapper.remove(id);
	}

	public Avenger update(String id, Avenger avenger) {
		return null;
	}
}

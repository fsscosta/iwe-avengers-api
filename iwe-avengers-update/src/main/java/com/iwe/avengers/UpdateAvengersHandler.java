package com.iwe.avengers;

import java.util.NoSuchElementException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.lambda.exception.AvengerNotFoundException;
import com.iwe.avenger.lambda.response.HandlerResponse;
import com.iwe.avengers.dao.AvengerDAO;

public class UpdateAvengersHandler implements RequestHandler<Avenger, HandlerResponse> {
	
	private AvengerDAO dao = new AvengerDAO();

	@Override
	public HandlerResponse handleRequest(final Avenger avenger, final Context context) {
		
		final String id = avenger.getId();
		
		context.getLogger().log("[#] - Searching Avenger with id: " + id);
		
		try {
			dao.find(id);
		} catch (NoSuchElementException e) {
			throw new AvengerNotFoundException("[NotFound] - Avenger id: " + id + " not found");
		}
		
		context.getLogger().log("[#] - Avenger found! Updating...");
			
		final Avenger updatedAvenger = dao.create(avenger);
		
		context.getLogger().log("[#] - Successfully updated Avenger!");
		
		final HandlerResponse response = HandlerResponse.builder().setObjectBody(updatedAvenger).build();
		
		return response;
	}
}

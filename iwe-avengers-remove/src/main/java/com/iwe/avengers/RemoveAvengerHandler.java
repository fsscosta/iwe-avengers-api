package com.iwe.avengers;

import java.util.NoSuchElementException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.lambda.exception.AvengerNotFoundException;
import com.iwe.avenger.lambda.response.HandlerResponse;
import com.iwe.avengers.dao.AvengerDAO;

public class RemoveAvengerHandler implements RequestHandler<Avenger, HandlerResponse> {
	
	private AvengerDAO dao = new AvengerDAO();

	@Override
	public HandlerResponse handleRequest(final Avenger avenger, final Context context) {
		
		context.getLogger().log("[#] - Searching Avenger with id: " + avenger.getId());
		
		try {
			final Avenger avengerToRemove = dao.find(avenger.getId());
			context.getLogger().log("[#] - Avenger found! Removing...");
			dao.remove(avengerToRemove);
			context.getLogger().log("[#] - Successfully removed Avenger!");
		} catch (NoSuchElementException e) {
			throw new AvengerNotFoundException("[NotFound] - Avenger id: " + avenger.getId() + " not found");
		}
		
		final HandlerResponse response = HandlerResponse.builder().build();
		
		return response;
	}
}

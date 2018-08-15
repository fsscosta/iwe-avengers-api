package com.iwe.avengers;

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

		final String id = avenger.getId();
		
		context.getLogger().log("[#] - Removing Avenger with id: " + id);
		
		final Avenger retrivedAvenger = dao.find(id);
		
		if (retrivedAvenger == null) {
			throw new AvengerNotFoundException("[NotFound] - Avenger id: " + id + " not found");
		} else {
			dao.remove(avenger.getId());
		}
		
		final HandlerResponse response = HandlerResponse.builder()
										.setStatusCode(200)
										.setObjectBody(retrivedAvenger)
										.build();
		
		context.getLogger().log("[#] - Avenger removed! =)");
		
		return response;
	}
}

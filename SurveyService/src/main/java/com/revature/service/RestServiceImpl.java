package com.revature.service;

import java.util.List;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import com.revature.model.AssociateDto;

@Service
public class RestServiceImpl {

	// get associates by batch Id
	// get survey responses by associateId + surveyId
	// post survey responses with associateId and surveyId

	@LoadBalanced
	WebClient webClient;

	public List<AssociateDto> getAssociatesByBatchId(int batchId) throws WebClientException {

		webClient = WebClient.create("trainingservice");

		return webClient.get().uri("/batch-id/" + batchId)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).retrieve()
				.bodyToFlux(AssociateDto.class).collectList().block();

	}
}

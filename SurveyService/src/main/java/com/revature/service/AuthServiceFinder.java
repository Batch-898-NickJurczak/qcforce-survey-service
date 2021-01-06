package com.revature.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.client.WebClientException;

import com.revature.dto.TokenDto;

import reactor.core.publisher.Mono;

@Service
public class AuthServiceFinder implements AuthService {
	
	private static final String AUTH_PATH = "/auth";
	private final String BASE_URL;

	private WebClient webClient;

	public AuthServiceFinder(@Value("baseUrl") String baseUrl) {
		this.BASE_URL = baseUrl; 
	}

	/**
	 * hits the sync service endpoint with a {@link TokenDto} to get back a token {@link Mono}<{@link String}>.
	 * It then returns this {@link String} to the calling method.
	 * 
	 * @param surveyId the identifier for the distributed survey
	 * @param batchId the identifier for the specified batch receiving the emails.
	 * @param surveySubId the identifier for the specific survey submission used to generate token
	 * 
	 * @return token the JWT received from the endpoint
	 */
	@Override
	public String createToken(int surveyId, String batchId, int surveySubId) throws WebClientException{
		
		TokenDto tokenDto = new TokenDto(surveyId, batchId, surveySubId);
		
		this.webClient = WebClient.create(BASE_URL);
		
		Mono<String> token;
		
		token = this.webClient
					.post()
					.uri(AUTH_PATH)
					.body(Mono.just(tokenDto), TokenDto.class)
					.retrieve()
					.bodyToMono(String.class);

	
		return token.block();
	}

}

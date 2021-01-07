package com.revature.service;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * This service receives requests from the distribution service to create a JWT.
 */
@Service
public class AuthServiceJWT implements AuthService {
	
	/**
	 * The secret string for the JWT signature.
	 */
	@Value("${survey_service.auth_service.secret}")
	private String secret;

	public final int TIME_TO_EXPIRATION = 15 * 60 * 1000;

	/**
	 * This method creates and returns a Json Web Token given a surveyId and batchID
	 * with various claims. Sets an IAT (issued at) claim for the current time in
	 * milliseconds. Sets an EXP (expiration) claim for 15 minutes past the IAT
	 * time. Sets a surveyID claim to hold the identifier for the distributed
	 * survey. Sets a batchId claim to hole the identifier for the specified batch.
	 * Set the surveySubId claim to hold the identifier for the survey submission.
	 * 
	 * @param surveyId the identifier for the distributed survey
	 * @param batchId  the identifier for the specified batch receiving the emails.
	 */
	@Override
	public String createToken(int surveyId, String batchId, int surveySubId) {

		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		// We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setIssuedAt(now).claim("surveyId", surveyId).claim("batchId", batchId).claim("surveySubId", surveySubId)
				.signWith(signatureAlgorithm, signingKey);

		Date exp = new Date(nowMillis + TIME_TO_EXPIRATION);
		builder.setExpiration(exp);

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}
}

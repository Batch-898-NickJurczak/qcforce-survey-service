package com.revature.servicetest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.service.AuthService;
import com.revature.service.AuthServiceJWT;
import com.revature.util.InvalidBatchIdException;
import com.revature.util.InvalidSurveyIdException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;

/**
 * Tests for the methods of the AuthServiceJWT class. These methods require a
 * valid surveyId. If this condition is not met, these methods will throw an
 * exception.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AuthServiceJWT.class})
class AuthServiceJWTTest {

	@Autowired
	AuthService authService;

	/**
	 * The secret string for the JWT signature.
	 */
	@Value("${survey_service.auth_service.qcforce_token_secret}")
	private String secret;

	/**
	 * Checks AuthServiceJWT.createToken(surveyId, batchId) with a valid surveyId.
	 * If token is generated with the correct claims for surveyId, batchId, IAT, and
	 * EXP, test passes. For temporal claims (IAT, EXP), a reasonable time range is
	 * checked in lieu of exact values.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void testCreateToken_withValidParameters() throws InterruptedException {

		String batchId = "2010-Nick";
		int surveyId = 1;
		Date before = new Date(System.currentTimeMillis());
		Date beforeExp = new Date(System.currentTimeMillis() + 1000 * 60 * 14);
		java.util.concurrent.TimeUnit.SECONDS.sleep(1);
		String token = this.authService.createToken(surveyId, batchId, 1);
		java.util.concurrent.TimeUnit.SECONDS.sleep(1);
		Date after = new Date(System.currentTimeMillis());
		Date afterExp = new Date(System.currentTimeMillis() + 1000 * 60 * 16);
		try {
			Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret))
					.parseClaimsJws(token).getBody();
			assertEquals((int) claims.get("surveyId"), surveyId);
			assertEquals(claims.get("batchId"), batchId);
			assertTrue(claims.getIssuedAt().after(before));
			assertTrue(claims.getIssuedAt().before(after));
			assertTrue(claims.getExpiration().after(beforeExp));
			assertTrue(claims.getExpiration().before(afterExp));
		} catch (MalformedJwtException exception) {
			fail(exception);
		}
	}

	/**
	 * Checks AuthServiceJWT.createToken(surveyId, batchId) with an invalid
	 * surveyId. The expected behavior is the throwing of the exception
	 * InvalidSurveyIdException.
	 */
	@Test
	void testCreateToken_withInvalidSurveyId() {

		String batchId = "2010-Nick";
		int surveyId = -1;

		assertThrows(InvalidSurveyIdException.class, () -> authService.createToken(surveyId, batchId, 1));

	}

	/**
	 * Checks AuthServiceJWT.createToken(surveyId, batchId) with an invalid batchId.
	 * The expected behavior is the throwing of the exception
	 * InvalidBatchIdException.
	 */
	@Test
	void testCreateToken_withInvalidBatchId() {

		String batchId = "";
		int surveyId = 1;
		
		String string = authService.createToken(surveyId, batchId, 1);

		assertThrows(InvalidBatchIdException.class, () -> authService.createToken(surveyId, batchId, 1));

	}

}

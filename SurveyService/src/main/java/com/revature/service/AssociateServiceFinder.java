/**
 * 
 */
package com.revature.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

/**
 * @author Work From Home
 *
 */
@Service
public class AssociateServiceFinder implements AssociateService {

	/**
	 * Make a call to the Teaching Service and get all associates linked to a batch Id. 
	 * It then pulls out the associate Id and Email and stores it in a HashMap that it then returns. 
	 * 
	 * @return {@link HashMap}<{@link String}, {@link Integer}> of associate emails as keys, and associate Ids as values
	 */
	@Override
	public HashMap<String, Integer> getAssociatesByBatchId(String batchId) {
		// TODO Auto-generated method stub
		return null;
	}

}

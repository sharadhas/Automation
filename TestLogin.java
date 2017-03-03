package com.highwire.chochrane.testsuite;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.highwire.chochrane.chochranereviews.LoginPage;

/**
 * 
 * @author Sharadha Sharma
 *
 */
public class TestLogin extends LoginPage {
	boolean result;
	
	@Test
	void testForLoginToContentManagementSystem()
	{
		result=chochrane_login("SIGN_IN_LINK" ,"USERID", "PASSWORD", "LOGIN_BUTTON");
		Assert.assertTrue(result, "Login Successful");
	}

}

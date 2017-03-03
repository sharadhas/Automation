package com.highwire.chochrane.chochranereviews;

import com.highwire.cochrane.utils.BrowserFactory;
import com.highwire.cochrane.utils.TestUtil;

/**
 * 
 * @author Sharadha Sharma
 *
 */
public class LoginPage {
	
boolean result = false;
String userid, password;

	public LoginPage(){
		TestUtil.getInstance();
		userid=BrowserFactory.CONFIG.getProperty("USER_ID");
		password=BrowserFactory.CONFIG.getProperty("PASSWORD");
	}
		
	protected boolean chochrane_login(String SignInLink ,String uid, String pwd, String LoginButton) {	
	TestUtil.switchtoFrame();	
	TestUtil.clickLink(SignInLink);
	TestUtil.input(uid,userid);
	TestUtil.input(pwd,password);
	TestUtil.click(LoginButton);
	result = TestUtil.isElementPresent("EDITOR_TEXT");
	return result;
	}
}
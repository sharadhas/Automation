package com.highwire.chochrane.chochranereviews;

import org.openqa.selenium.JavascriptExecutor;

import com.highwire.cochrane.utils.TestUtil;


/**
 * 
 * @author Sharadha Sharma
 *
 */
public class TableOfContents  {
	
	boolean result = false;
	
	

	protected boolean chochrane_reviews(String chocraneReviewLink) {
		result = TestUtil.isLinkPresent(chocraneReviewLink);
		if (result == true) {
			TestUtil.clickLink(chocraneReviewLink);
		}
		return result;
	}

	protected boolean table_of_contents(String linkText) {
		result = TestUtil.isLinkPresent(linkText);
		if (result == true) {
			TestUtil.clickLink(linkText);
		}
		return result;
	}

	protected boolean browse_issues(String ChorcraneReviewsLink, String tableOfContentsLink, String issueYear2013, String issueTobeFound, String linkToBeVerified) {
		TestUtil.clickLink(ChorcraneReviewsLink);
		TestUtil.clickLink(tableOfContentsLink);
		TestUtil.clickLink(issueYear2013);
		TestUtil.click(issueTobeFound);
		
		if (TestUtil.isLinkDisplayed(linkToBeVerified) == true) {
			result = true;
		} else if(TestUtil.isLinkDisplayed(linkToBeVerified)==false){
			result = false;
		}
		return result;
	}
	
	protected boolean verifyTOCLink(String ChorcraneReviewsLink, String tableOfContentsLink, String issueYear, String issueTobeFound, String linkToBeVerified){
		TestUtil.clickLink(ChorcraneReviewsLink);
		TestUtil.clickLink(tableOfContentsLink);
		TestUtil.clickLink(issueYear);
		TestUtil.clickLink(issueTobeFound);
		TestUtil.clickLink(linkToBeVerified);
		JavascriptExecutor js = (JavascriptExecutor) TestUtil.driver;
	    Long numberOfPixels = (Long)js.executeScript("return window.pageYOffset;");
		if(numberOfPixels != 0)
			return true;
		else
			return false;
		
	}
	
	protected boolean verifyLinkWorking(String issueYear, String issueTobeFound, String linkToBeVerified){
		TestUtil.clickLink(issueYear);
		TestUtil.clickLink(issueTobeFound);
		TestUtil.clickLink(linkToBeVerified);
		JavascriptExecutor js = (JavascriptExecutor) TestUtil.driver;
	    Long numberOfPixels = (Long)js.executeScript("return window.pageYOffset;");
		if(numberOfPixels!=0)
			return true;
		else
			return false;
		
		}
	
	protected boolean verifyTOCPageHeader(String issueYear, String issueToBeFound, String headingToBeFound){
		
		TestUtil.click(issueYear);
		TestUtil.click(issueToBeFound);
		result=TestUtil.isElementPresent(headingToBeFound);
		return result;
		}
		
}
	

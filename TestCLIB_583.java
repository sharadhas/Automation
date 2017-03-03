package com.highwire.chochrane.testsuite;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.highwire.chochrane.chochranereviews.TableOfContents;
import com.highwire.cochrane.utils.TestUtil;

/**
 * 
 * @author Sharadha Sharma
 *
 */
public class TestCLIB_583 extends TableOfContents {
	boolean result;
	@BeforeClass
	public void initialize()  {
		TestUtil.getInstance();
	}

	@Test(priority = 1)
	void testTableofContents() {

		Assert.assertTrue(chochrane_reviews("CHOCHRANE_REVIEWS"), "The Chochrane Reviews link is present");
		Assert.assertTrue(table_of_contents("TABLE_OF_CONTENTS"), "The Table of Contents link is present");
	}

	@Test(priority = 2)
	void testForAbsenceOfReviewsIssue2InIssueYear2004UnderChocraneReviewsTOC() {
		try {
			boolean result = browse_issues("CHOCHRANE_REVIEWS", "TABLE_OF_CONTENTS", "ISSUE_YEAR2004", "ISSUE_2004NUM2",
					"REVIEWS");
			Assert.assertFalse(result, "Reviews link not present");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 3)
	void testForPresenceOfReviewsIssue12InIssueYear2013UnderChocraneReviewsTOC() {

		boolean result = browse_issues("CHOCHRANE_REVIEWS", "TABLE_OF_CONTENTS", "ISSUE_YEAR2013", "ISSUE_NUM12",
				"REVIEWS");
		Assert.assertTrue(result, "Reviews link present");

	}

	@Test(priority = 4)
	void testForWorkingOfReviewsLinkIssue12InIssueYear2013UnderChochraneReviewsTOC() {
		boolean result = verifyTOCLink("CHOCHRANE_REVIEWS", "TABLE_OF_CONTENTS", "ISSUE_YEAR2013", "ISSUE_NUM12",
				"REVIEWS");
		Assert.assertTrue(result, "Reviews Link Works");
	}

	@Test(priority = 5)
	void testForWorkingOfDiagnosticLinkIssue2InIssueYear2015UnderChochraneReviewsTOC() {
		boolean result = verifyTOCLink("CHOCHRANE_REVIEWS", "TABLE_OF_CONTENTS", "ISSUE_YEAR2015", "ISSUE_2015NUM2",
				"DIAGNOSTICS");
		Assert.assertTrue(result, "Diagnostic Link Works");
	}

	@Test(priority = 6)
	void testForWorkingOfInterventionIssue2InIssueYear2015UnderChochraneReviewsTOC() {
		boolean result = verifyTOCLink("CHOCHRANE_REVIEWS", "TABLE_OF_CONTENTS","ISSUE_YEAR2015", "ISSUE_2015NUM2", "INTERVENTION");
		Assert.assertTrue(result, "Intervention Link works");
	}

	@Test(priority = 7, dependsOnMethods = { "testTableofContents" })
	void testForPresenceOfPageHeader() {

		result = verifyTOCPageHeader("ISSUE_YEAR2004", "ISSUE_2004NUM4", "ISSUE_2004_ISSUE2_HEADING");
		Assert.assertTrue(result, "Page Headers Verified");
	}

}
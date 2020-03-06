package com.paralleltest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageFactory.ClickAcadamyLandingPage;
import com.paralleltest.basetest.BaseTest;
import com.paralleltest.basetest.TestUtilities;

public class ClickAcadamyHomePageTest extends BaseTest{

	@Test
	public void homePageTest() {
		
		ClickAcadamyLandingPage home = new ClickAcadamyLandingPage(getDriver(), log);
		home.openPage();
		
		//assert page url
		Assert.assertEquals(home.getCurrentUrl(), home.getPageUrl());
		
		//assert page headline
		String headline = "An Academy to Learn Earn & Shine  in your QA Career";
		Assert.assertEquals(headline, home.getPageHeadline());
		
		//assert nav-bar
		Assert.assertTrue(home.getNavBar());
		
		//click login
		home.clickLoginBtn();
		
	}
}

package com.dcirrus;

import java.util.List;

import org.testng.TestNG;
import org.testng.collections.Lists;



public class RunDcirrusAutomation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestNG testSuite = new TestNG();
		 List<String> suites = Lists.newArrayList();
		 suites.add(args[0]);
	
		    testSuite.setTestSuites(suites);
		    testSuite.run();
	
	}

}

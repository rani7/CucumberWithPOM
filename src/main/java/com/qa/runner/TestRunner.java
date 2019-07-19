package com.qa.runner;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(
		features="C:\\Users\\Ranita\\workspace\\CucumberWithTestng\\src\\main\\java\\com\\qa\\feature\\freeCRM.feature",
		glue={"com/qa/stepDefinitions"},
		format={"pretty",
				"html:target/cucumber-reports/cucumber-pretty", 
				"json:target/cucumber-reports/cucumberTestReport.json",
				"rerun:target/cucumber-reports/rerun.txt"
				}, plugin="json:target/cucumber-reports/cucumberTestReport.json"	
		)

public class TestRunner {
	
	private TestNGCucumberRunner testNGCucumberRunner;
	
	@BeforeClass(alwaysRun=true)
	public void setUpClass() throws Exception{
		testNGCucumberRunner=new TestNGCucumberRunner(this.getClass());
		
	}
	
	@Test(groups="cucumber",description="Runs cucumber feature",dataProvider="features")
	public void feature(CucumberFeatureWrapper cucumberFeature){
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}
	
	@DataProvider
	public Object[][] features(){
		return testNGCucumberRunner.provideFeatures();
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDownClass() throws Exception{
		testNGCucumberRunner.finish();
		
	}

}

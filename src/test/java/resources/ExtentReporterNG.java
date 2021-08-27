package resources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import testObjects.BaseTest;

 
public class ExtentReporterNG implements IReporter {
    private static ExtentReports extent;
    ExtentHtmlReporter htmlReporter;
 
	@Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
    	
        htmlReporter = new ExtentHtmlReporter(outputDirectory + File.separator + "AutomationReport.html");
        
        htmlReporter.config().setReportName("Test UI Automation Report");
        htmlReporter.config().setReportName("Test " + BaseTest.suiteName);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
 
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();		
                buildTestNodes(context.getFailedTests(), Status.FAIL);
                buildTestNodes(context.getSkippedTests(), Status.SKIP);
                buildTestNodes(context.getPassedTests(), Status.PASS);
                buildTestNodes(context.getFailedConfigurations(), Status.FAIL);
            }
        }
 
        extent.flush();
        File srcFile = new File(System.getProperty("user.dir") + File.separator + "screenshots");
        File targetFile = new File(outputDirectory + File.separator + "screenshots");
        if (srcFile.exists()) {
	        try {
	            FileUtils.copyDirectory(srcFile, targetFile);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        }
    }
 

    private void buildTestNodes(IResultMap tests, Status status) {
        ExtentTest test;
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
            	ArrayList<String> currentGroups = new ArrayList<String>(3);
            	String testClassName = result.getTestClass().getRealClass().getSimpleName();
            	for (String group : result.getMethod().getGroups()) {
            		currentGroups.add(group);
                }          
                
                String message = "Test " + status.toString().toLowerCase() + "ed";
 
                if (result.getThrowable() != null) {
                    message = result.getThrowable().getMessage();
                }
                String parameter = "";
                if (result.getParameters().length > 0) {
                	parameter = " | " + result.getParameters()[0].toString();
                }
                test = extent.createTest(testClassName + " | " + result.getMethod().getMethodName() + parameter);
                for (String gr : currentGroups) {
                    test.assignCategory(gr);
                }
                if (result.getStatus() == 2) {
                	try {
						test.log(status, message, MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + result.getMethod().getMethodName() + ".jpg").build());
					} catch (IOException e) {
						e.printStackTrace();
						test.log(status, message);
					}
                } else {
                	test.log(status, message);
                }
            }
        }
    }
    
}
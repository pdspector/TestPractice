package resources;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners2 implements ITestListener, IMethodInterceptor, ISuiteListener {
	
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	private	LocalDateTime now = LocalDateTime.now();
	private LocalDateTime start = null;
	private List<IMethodInstance> ordered = new ArrayList<IMethodInstance>();
	
	@Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {      
	  //This ensures tests are run in alphabetical order
	  ordered = new ArrayList<>(methods);
      ordered.sort(Comparator.comparing(o -> o.getMethod().getMethodName()));
      return ordered;
    }
    
	@Override
    public void onStart(ISuite suite) {
		System.out.println("Starting suite...");
    }
    
	@Override		
    public void onFinish(ITestContext arg0) {					
        // TODO Auto-generated method stub				
    }
	
	@Override		
    public void onFinish(ISuite suite) {					
		//Prints Start Time in Console
	  	LocalDateTime finish = LocalDateTime.now();  
		System.out.println("Finishing Suite time: " + dtf.format(finish));
		Duration diff = Duration.between(start, finish);
		System.out.println("Total execution time: " + diff.toMinutes() + " minutes.");
    }	

	@Override		
    public void onStart(ITestContext arg0) {					
        // TODO Auto-generated method stub
		start = LocalDateTime.now();
    }		

    @Override		
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {					
        // TODO Auto-generated method stub				
		System.out.println("Failing with percentage test " + arg0.getMethod().getMethodName() + "...");
    }		

    @Override		
    public void onTestFailure(ITestResult arg0) {					
        // TODO Auto-generated method stub				
		System.out.println("Failing test " + arg0.getMethod().getMethodName() + "...");
		now = LocalDateTime.now();  
		System.out.println("Finishing time: " + dtf.format(now));
    }		

    @Override		
    public void onTestSkipped(ITestResult arg0) {					
        // TODO Auto-generated method stub				
		System.out.println("Skipping test " + arg0.getMethod().getMethodName() + "...");
    }		

    @Override		
    public void onTestStart(ITestResult arg0) {					
        // TODO Auto-generated method stub	
		System.out.println("Starting test " + arg0.getMethod().getMethodName() + "...");
		now = LocalDateTime.now();  
		System.out.println("Starting time: " + dtf.format(now));
    }		

    @Override		
    public void onTestSuccess(ITestResult arg0) {					
        // TODO Auto-generated method stub				
		System.out.println("Passing test " + arg0.getMethod().getMethodName() + "...");
		now = LocalDateTime.now();
		System.out.println("Finishing time: " + dtf.format(now));
    }

}

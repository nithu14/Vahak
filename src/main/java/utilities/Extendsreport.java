package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Extendsreport {
	
	public static ExtentReports extent;
	
	public static ExtentReports extendrep() {
		//extend method
		
				extent = new ExtentReports();
				ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
				 spark.config().setReportName("Automation Results");
				 spark.config().setDocumentTitle("IMDB results");
				 extent.attachReporter(spark);
				 extent.setSystemInfo("Tester", "Nithanth Rai");
				 
				 return extent;
		
	}

}

package resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;

public class BaseMethods {

	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd HH_mm_ss");  
	private	LocalDateTime now = LocalDateTime.now();

	//This takes a screenshot and places on the screenshots folder using the fileName parameter to name the file
	public void getScreenshot(AppiumDriver<?> driver, String fileName) {
	
		File srcFile = driver.getScreenshotAs(OutputType.FILE);
		File targetFile;
		targetFile = new File(System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + fileName + ".jpg");
		if (targetFile.exists()) {
			targetFile = new File(System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + fileName + now.format(dtf) + ".jpg");
        }
        try {
            FileUtils.copyFile(srcFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(driver.getPageSource());
        
	}
	
	//This checks if the current String is JSon or not
	public boolean isJSONValid(String test) {
		boolean isValid = false;
	    try {
	        new JSONObject(test);
	        isValid = true;
	    } catch (JSONException ex) {
	        try {
	            new JSONArray(test);
		        isValid = true;
	        } catch (JSONException ex1) {
	        }
	    }
	    return isValid;
	}
	
	//Returns a DesiredCapabilities object for Android using given Parameters List
	public DesiredCapabilities getCapabilities(LinkedHashMap<String, String> caps) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		String value = "";
		Set<String> keys = caps.keySet();
		for(String key: keys){
			value = caps.get(key);
			if (value.equalsIgnoreCase("false") == false && value.equalsIgnoreCase("true") == false && isNumeric(value) == false) {
				capabilities.setCapability(key, caps.get(key));
			} else if (isNumeric(value)) {
				capabilities.setCapability(key,  Integer.valueOf(value));
			} else {
				capabilities.setCapability(key,  Boolean.parseBoolean(value));
			}
		}
		return capabilities;
	}
	
	//Check if String is numeric
	public static boolean isNumeric(String strNum) {
		@SuppressWarnings("unused")
		double d;
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        d = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	//Execute Shell Command and get output as String
	public String getShellScriptOutput(String command) throws IOException, InterruptedException {
		String output = "";
		try {	
			Runtime run = Runtime.getRuntime();
			Process pr = run.exec(command);
			BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			BufferedReader buf2 = new BufferedReader(new InputStreamReader(pr.getErrorStream()));

			long t= System.currentTimeMillis();
			int f = 10*1000;
			long end = t+f;
			while (System.currentTimeMillis() < end && (buf.readLine() != null || buf2.readLine() != null)) {
				output = output + buf.readLine();
				output = output + buf2.readLine();
			}
			pr.destroy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	
	//Execute Shell Command and get output as String
		public String getShellScriptOutput(String command, int time) throws IOException, InterruptedException {
			String output = "";
			try {	
				Runtime run = Runtime.getRuntime();
				Process pr = run.exec(command);
				BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				BufferedReader buf2 = new BufferedReader(new InputStreamReader(pr.getErrorStream()));

				long t= System.currentTimeMillis();
				int f = time*1000;
				long end = t+f;
				while (System.currentTimeMillis() < end && (buf.readLine() != null || buf2.readLine() != null)) {
					output = output + buf.readLine();
					output = output + buf2.readLine();
				}
				pr.destroy();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return output;
		}
	
	//Returns a Stacktrace String
	public String getStackTraceAsString(Throwable e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        return exceptionAsString;
	}
	
	//Returns a path according running OS
	public String getPath(String path) {
		String osPath = File.separator + "";
		String[] folders = path.split("/");
		for (int i = 0; i < folders.length; i++) {
			osPath = osPath + File.separator + folders[i];
		}
		return osPath;
	}
	
	//Convert JSONArray to String[]
	public String[] getStrArray(JSONArray jsonArray) {
		String[] list = new String[jsonArray.length()];
		for(int i = 0; i < jsonArray.length(); i++){
		    list[i] = jsonArray.getString(i);
		}
		return list;
	}
	
	//Convert JSONArray into int[]
	public int[] getIntArray(JSONArray jsonArray) {
		int[] list = new int[jsonArray.length()];
		for(int i = 0; i < jsonArray.length(); i++){
		    list[i] = jsonArray.getInt(i);
		}
		return list;
	}
	
	//Create POJO Character
    public POJOCharacter createPOJO(JSONObject obj) {
    	POJOCharacter pojo = new POJOCharacter();
    	pojo.setChar_id(obj.getInt("char_id"));
    	pojo.setName(obj.getString("name"));
    	try {
    		Date birthday = new SimpleDateFormat("MM-dd-yyyy").parse(obj.getString("birthday"));
    		pojo.setBirthday(birthday.toString());
		} catch (JSONException | ParseException e) {
			// TODO Auto-generated catch block
			pojo.setBirthday(obj.getString("birthday"));
		}
    	pojo.setOccupation(getStrArray(obj.getJSONArray("occupation")));
    	pojo.setImg(obj.getString("img"));
    	pojo.setStatus(obj.getString("status"));
    	pojo.setNickname(obj.getString("nickname"));
    	pojo.setAppearance(getIntArray(obj.getJSONArray("appearance")));
    	pojo.setPortrayed(obj.getString("portrayed"));
    	pojo.setCategory(obj.getString("category"));
    	pojo.setBetter_call_saul_appearance(getIntArray(obj.getJSONArray("better_call_saul_appearance")));
    	return pojo;
    }

}

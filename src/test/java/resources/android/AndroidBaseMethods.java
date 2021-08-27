package resources.android;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy.ByAndroidUIAutomator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class AndroidBaseMethods {
	
	public AndroidBaseMethods() {

	}
	
	//This performs the Back Key Press on Android
	public void useBackKey(AppiumDriver <?> driver) {
		((AndroidDriver <?>) driver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
	}
	
	//This is a wait to be used as core of all waits in the Android Side
	private static FluentWait<AppiumDriver<?>> fluentWait(int time, AppiumDriver<?> driver) {
		FluentWait<AppiumDriver<?>> wait = null;
			try {
				wait = new FluentWait<AppiumDriver<?>>(driver)
		                .withTimeout(Duration.ofSeconds(time))
		                .pollingEvery(Duration.ofMillis(3000))
		                .ignoring(NoSuchElementException.class)
		                .ignoring(StaleElementReferenceException.class)
		                .withMessage("Element was not found");	
			} catch (Exception e) {
				wait = null;
			}
		return wait;
    }
	
	//This is a swipe action on Android which takes the dimensions of the element where it places the press action and the amount of times (times parameter) is going to be multiplying that dimension to make the swipe
	@SuppressWarnings("rawtypes")
	public void swipeElementAndroid(MobileElement el, String dir, AndroidDriver driver, int times) {
	    // Animation default time:
	    //  - Android: 300 ms
	    //  - iOS: 200 ms
	    final int ANIMATION_TIME = 200; // ms

	    final int PRESS_TIME = 200; // ms

	    PointOption pointOptionStart, pointOptionEnd;

	    Rectangle rect = el.getRect();
	    double height = rect.height*(double) times;
	    double width = rect.width*(double) times;
	    
	    Dimension screenSize = driver.manage().window().getSize();
	    double screenHeight = screenSize.getHeight();
	    double screenWidth = screenSize.getWidth();

    	pointOptionStart = PointOption.point((int) (screenWidth / 2),
                (int) (screenHeight / 2));
    	
	    switch (dir) {
	        case "DOWN":
	            pointOptionEnd = PointOption.point((int) (screenWidth / 2),
	            		(int) ((screenHeight / 2) - height));
	            break;
	        case "UP":
	        	pointOptionEnd = PointOption.point((int) (screenWidth / 2),
	            		(int) ((screenHeight / 2) + height));
	            break;
	        case "RIGHT":
	            pointOptionEnd = PointOption.point((int) ((screenWidth / 2) - width),
	            		(int) (screenHeight / 2));
	            break;
	        case "LEFT":
	            pointOptionEnd = PointOption.point((int) ((screenWidth / 2) + width),
	            		(int) (screenHeight / 2));
	            break;
	        default:
	            throw new IllegalArgumentException("swipeElementAndroid(): dir: '" + dir + "' NOT supported");
	    }

	    try {
	        new TouchAction(driver)
	                .longPress(pointOptionStart)
	                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
	                .moveTo(pointOptionEnd)
	                .release()
	                .perform();
	    } catch (Exception e) {
	        System.err.println("swipeElementAndroid(): TouchAction FAILED\n" + e.getMessage());
	        return;
	    }

	    // always allow swipe action to complete
	    try {
	        Thread.sleep(ANIMATION_TIME);
	    } catch (InterruptedException e) {
	        // ignore
	    }
	}
	
	public void scrollTo(MobileElement element1, MobileElement element2, AppiumDriver<?> driver) {	
		Point source = element1.getCenter();
		Point target = element2.getCenter();
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence dragNDrop = new Sequence(finger, 1);
		dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0),
		                    PointerInput.Origin.viewport(), source.x, source.y));
		dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(700),
		                    PointerInput.Origin.viewport(),target.x, target.y));
		dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(dragNDrop));
	}
	
	//This is a swipe action on Android which reduces the dimension of current object to be pressed on and swipe and then multiplies it the "times" number of times to perform the swipe
	@SuppressWarnings("rawtypes")
	public void swipeElementAndroid(MobileElement el, String dir, AndroidDriver driver, int times, int reducedWidth) {
	    // Animation default time:
	    //  - Android: 300 ms
	    //  - iOS: 200 ms
	    final int ANIMATION_TIME = 200; // ms

	    final int PRESS_TIME = 200; // ms

	    PointOption pointOptionStart, pointOptionEnd;

	    Rectangle rect = el.getRect();
	    double height = rect.height*(double) times;
	    double width = rect.width*(double) times;
	    
	    Dimension screenSize = driver.manage().window().getSize();
	    double screenHeight = screenSize.getHeight();
	    double screenWidth = screenSize.getWidth();

	    pointOptionStart = PointOption.point((int) (screenWidth - reducedWidth),
                (int) (screenHeight / 2));
    	
	    switch (dir) {
	        case "DOWN":
	            pointOptionEnd = PointOption.point((int) (screenWidth / 2),
	            		(int) ((screenHeight / 2) - height));
	            break;
	        case "UP":
	        	pointOptionEnd = PointOption.point((int) (screenWidth / 2),
	            		(int) ((screenHeight / 2) + height));
	            break;
	        case "LEFT":
	            pointOptionEnd = PointOption.point((int) ((screenWidth / 2) - width),
	            		(int) (screenHeight / 2));
	            break;
	        case "RIGHT":
	            pointOptionEnd = PointOption.point((int) ((screenWidth / 2) + width),
	            		(int) (screenHeight / 2));
	            break;
	        default:
	            throw new IllegalArgumentException("swipeElementAndroid(): dir: '" + dir + "' NOT supported");
	    }

	    try {
	        new TouchAction(driver)
	                .longPress(pointOptionStart)
	                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
	                .moveTo(pointOptionEnd)
	                .release()
	                .perform();
	    } catch (Exception e) {
	        System.err.println("swipeElementAndroid(): TouchAction FAILED\n" + e.getMessage());
	        return;
	    }

	    // always allow swipe action to complete
	    try {
	        Thread.sleep(ANIMATION_TIME);
	    } catch (InterruptedException e) {
	        // ignore
	    }
	}
	
	public void isElementPresentVisibleAndClickable(By by, int time, AppiumDriver<?> driver) throws NoSuchElementException {
		 
		try {
	       fluentWait(time, driver).until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElement(by)));
	    } catch (Exception e) {
	        throw new NoSuchElementException("The element was not visible and clickable.");
	        }
	   }

	 public void isElementDisplayed(By by, int time, AppiumDriver<?> driver) throws NoSuchElementException {
		    
		 try {
	            fluentWait(time, driver).until(ExpectedConditions.visibilityOfElementLocated(by));
	        } catch (NoSuchElementException e) {
	            throw e;
	        } catch (StaleElementReferenceException e1) {
	        	
	        }
	   }
	 
	 public void isElementDisplayed(MobileElement element, int time, AppiumDriver<?> driver) throws NoSuchElementException {
		    
		 try {
	            fluentWait(time, driver).until(ExpectedConditions.visibilityOf((WebElement) element));
	        } catch (NoSuchElementException e) {
	            throw e;
	        } catch (StaleElementReferenceException e1) {
	        	
	        }
	   }
	 
	 public void isElementDisplayed(By element, int time, AppiumDriver<?> driver, boolean isWebElement) throws NoSuchElementException {
		 WebDriverWait wait = null;
		 try {
			 wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.presenceOfElementLocated(element));
	        } catch (NoSuchElementException e) {
	            throw e;
	        } catch (StaleElementReferenceException e1) {
	        	
	        }
	   }
	 
	 public void isElementPresent(By by, int time, AppiumDriver<?> driver) throws NoSuchElementException {
		 
	     try {
	         fluentWait(time, driver).until(ExpectedConditions.presenceOfElementLocated(by));
	     } catch (NoSuchElementException e) {
	         throw new NoSuchElementException("The element was not present.");
	     } catch (StaleElementReferenceException e1) {
	     }
	}
	 
	 public void isElementClickable(AndroidElement element, int time, AppiumDriver<?> driver) throws NoSuchElementException {
		 
		 try {
	        fluentWait(time, driver).until(ExpectedConditions.elementToBeClickable((MobileElement) element));
	     } catch (Exception e) {
	         throw new NoSuchElementException("The element was not visible and clickable.");
	     }
	}
	 
	 
	 public void isElementClickable(By by, int time, AppiumDriver<?> driver) throws NoSuchElementException {
		 
		 try {
	        fluentWait(time, driver).until(ExpectedConditions.elementToBeClickable((MobileElement) driver.findElement(by)));
	     } catch (Exception e) {
	         throw new NoSuchElementException("The element was not visible and clickable.");
	     }
	}
 
	//This is an attempt method to dismiss the "Low Battery" popups on AWS Device Farm failures
	public void dismissNativeAWSAlertIfPresent(AppiumDriver<?> driver) {
		try {
			Alert alert = driver.switchTo().alert();
			String alertTitle = "";
			boolean isCalendarAlert = false;
			try {
				alertTitle = driver.findElement(By.id("android:id/title")).getText();
				} catch (Exception e) {
				}
			try {
				@SuppressWarnings("unused")
				MobileElement calendar = (MobileElement) driver.findElement(By.id("android:id/datePicker"));
				isCalendarAlert = true;
				} catch (Exception e) {
				}
				if (alertTitle.contains("Cancel Capture") == false && isCalendarAlert == false) {
					try {
						driver.findElement(By.xpath("//*[@text='Do not show again']")).click();
					} catch (Exception e) {
						
					}
					try {
						driver.findElement(By.xpath("//*[@text='OK']")).click();
						System.out.println("ALERT WAS SUCCESSFULLY ACCEPTED");
					} catch (Exception e) {
						alert.accept();
						System.out.println("ALERT WAS SUCCESSFULLY ACCEPTED");
					}
				}
		} catch (NoAlertPresentException e) {
			
		} catch (InvalidElementStateException e1) {
			try {
				Alert alert = driver.switchTo().alert();
				alert.dismiss();
				System.out.println("ALERT WAS SUCCESSFULLY DISMISSED");
			} catch (Exception e2) {
				throw new RuntimeException("ALERT COULDN'T BE HANDLED" + " " + e2.toString());
			}
		}
	}
	
	public void scrollToElementByUISelector(String fromUISelector, String untilUISelector, AppiumDriver<?> driver) {
		driver.findElement(ByAndroidUIAutomator.AndroidUIAutomator("new UiScrollable(new " + fromUISelector + ".scrollable(true)).scrollIntoView(new " + untilUISelector + ")"));	
	}
	
}

package com.test2.appium;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.commons.io.FileUtils;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.screenrecording.CanRecordScreen;
import static org.testng.Assert.assertEquals;

public class Test2 {

	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
		UiAutomator2Options options = new UiAutomator2Options().setDeviceName("pixel_5").
				setAutomationName("uiautomator2").
				setApp("C:\\Users\\jdiezrodriguez\\Documents\\AutomationTesting\\FirstAppiumProjectt\\src\\test\\resources\\example.apk");
		
		URI url = new URI("http://0.0.0.0:4723");
		
		AppiumDriver driver = new AndroidDriver(url.toURL(), options);
		
		driver.findElement(AppiumBy.id("android:id/button1")).click();

		//startRecording(driver);
		//checkPresenceOfElements(driver);
		validationCheckBoxes(driver);
		//stopRecording(driver);
	}
	
	public static void checkPresenceOfElements(AppiumDriver driver) throws IOException {
		String image1 = "Screenshots" + File.separator + "PresenceOfElements" + ".png";
		boolean hamburguerMenu = driver.findElement(AppiumBy.id("at.markushi.reveal:id/action")).isDisplayed();
		boolean buttonOne = driver.findElement(AppiumBy.id("at.markushi.reveal:id/btn_1")).isDisplayed();
		boolean buttonTwo = driver.findElement(AppiumBy.id("at.markushi.reveal:id/btn_2")).isDisplayed();
		boolean buttonThree = driver.findElement(AppiumBy.id("at.markushi.reveal:id/btn_3")).isDisplayed();
		boolean slowMotionText = driver.findElement(AppiumBy.id("at.markushi.reveal:id/slow_motion")).isDisplayed();
		boolean enableReveal = driver.findElement(AppiumBy.id("at.markushi.reveal:id/enable_reveal")).isDisplayed();
		boolean enableAction = driver.findElement(AppiumBy.id("at.markushi.reveal:id/enable_actions")).isDisplayed();
				
		if(hamburguerMenu && buttonOne && buttonTwo && buttonThree && slowMotionText && enableReveal && enableAction) {
			System.out.println("Todos los elementos estan presentes");
		}
		else {
			System.out.println("Algunos elementos no estan presentes");
		}
		
		/*
		assertEquals(hamburguerMenu, true);
		assertEquals(buttonOne, true);
		assertEquals(buttonTwo, true);
		assertEquals(buttonThree, true);
		assertEquals(slowMotionText, true);
		assertEquals(enableReveal, true);
		assertEquals(enableAction, true);
		*/
		
		File file = driver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(image1));
	}
	
	public static void validationCheckBoxes(AppiumDriver driver) throws IOException, InterruptedException {
		String image2 = "Screenshots" + File.separator + "CheckCheckBoxes" + ".png";
		String image3 = "Screenshots" + File.separator + "ClickCheckBoxes" + ".png";
		WebElement slowMotionText = driver.findElement(AppiumBy.id("at.markushi.reveal:id/slow_motion"));
		WebElement enableReveal = driver.findElement(AppiumBy.id("at.markushi.reveal:id/enable_reveal"));
		WebElement enableAction = driver.findElement(AppiumBy.id("at.markushi.reveal:id/enable_actions"));
		WebElement buttonThree = driver.findElement(AppiumBy.id("at.markushi.reveal:id/btn_3"));
		WebElement hamburguerMenu = driver.findElement(AppiumBy.id("at.markushi.reveal:id/action"));
		
		String slow = slowMotionText.getAttribute("checked");
		String reveal = enableReveal.getAttribute("checked");
		String action = enableAction.getAttribute("checked");
		
		if(slow.equals("false") && reveal.equals("true") && action.equals("true")) {
			System.out.println("Slow: " + slow + " Reveal: " + reveal + " Action: " + action);
		}else if(slow.equals("true") && reveal.equals("true") && action.equals("false")) {
			System.out.println("Slow: " + slow + " Reveal: " + reveal + " Action: " + action);
		}else if(slow.equals("true") && reveal.equals("false") && action.equals("true")) {
			System.out.println("Slow: " + slow + " Reveal: " + reveal + " Action: " + action);
		}
		
		slowMotionText.click();
		
		File file2 = driver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file2, new File(image2));
		buttonThree.click();
		
		Thread.sleep(500);
		File file3 = driver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file3, new File(image3));
	}
	
	public static void startRecording(AppiumDriver driver) {
		((CanRecordScreen)driver).startRecordingScreen();
	}
	
	public static void stopRecording(AppiumDriver driver) {
		String media =((CanRecordScreen)driver).stopRecordingScreen();
		
		String dir = "Videos";
		
		File videoDir = new File(dir);
		
		if(!videoDir.exists()) {
			videoDir.mkdirs();
		}
		
		try {
			FileOutputStream stream = new FileOutputStream(videoDir + File.separator + "VideoRecorded" + ".mp4");
			stream.write(org.apache.commons.codec.binary.Base64.decodeBase64(media));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
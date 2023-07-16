package com.example.demoflipkart;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;

import com.example.demoflipkart.interfaces.GlobalVariables;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

public class DemoFlipkart {
    static long HalfMin_Timeout = 30000;
    static long OneMin_Timeout = 60000;
    static long TwoMin_Timeout = 120000;
    static long ThreeMin_Timeout = 180000;
    Context context = ApplicationProvider.getApplicationContext();
    private UiDevice device = null;
    private Timestamp time01,time2,time3,time4,time5,time6,time7,time8,time9,time10,timeWarmLaunch,timeHomeElementForWarm;

    @Before
    public void preConfiguration() {

        String testCaseName = "Demo Flipkart User Journey";

    }
    public static boolean sendEvents(String testCaseName,String eventType,String eventResult,String eventName) {
        boolean value = false;
        try {

            Log.d(GlobalVariables.tagName, "eventName "+eventName+" testCaseName "+testCaseName+" eventResult "+eventResult+" "+" eventType "+eventType);
            value = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    @After
    public void closeAppOld() {
        try {
            device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
            device.executeShellCommand("am force-stop " + GlobalVariables.appPackageName);
//            eventInitilizer.sendAllEventOfEndOfTest();
            Log.d(GlobalVariables.tagName, "Closing the application");
        } catch (Exception e) {
            System.out.println("Error in closing the app");
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void closeApp() {
        try {
            Runtime.getRuntime().exec("am force-stop " + GlobalVariables.appPackageName);
            Log.d(GlobalVariables.tagName, "Closing the application");
        } catch (Exception e) {
            Log.d(GlobalVariables.tagName, "Error in closing the app");
            e.printStackTrace();
        }
    }

    private boolean kpiCalculation(String testCaseName) {
        try {
            double total = (time10.getTime() - time3.getTime()) / 1000.0;
            double J1S1 = (time2.getTime() - time01.getTime()) / 1000.0;
            double J1S2 = (time4.getTime() - time3.getTime()) / 1000.0;
            double J1S3 = (time6.getTime() - time5.getTime()) / 1000.0;
            double J1S4 = (time8.getTime() - time7.getTime()) / 1000.0;
            double J1S5 = (time10.getTime() - time9.getTime()) / 1000.0;

            //Log.d("TestTag", "m1=" + J1S1);
            Log.d("TestTag", "m2=" + J1S2);
            Log.d("TestTag", "m3=" + J1S3);
            Log.d("TestTag", "m4=" + J1S4);
            Log.d("TestTag", "m5=" + J1S5);

            double total1 = J1S2 + J1S3 + J1S4 + J1S5;

            sendEvents(testCaseName, "appear", String.valueOf(total), "endToEndValue");
            sendEvents(testCaseName, "appear", String.valueOf(total1), "endToEndValueWithoutUserActions");
            Log.d(GlobalVariables.tagName, "E2E Journey Time With User Actions is: " + total);
            Log.d(GlobalVariables.tagName, "E2E Journey Time Without User Actions is: " + total1);
            return true;

        } catch (Exception e) {
            Log.d("TestTag", "Error in KPI Calculation "+ e);
            e.printStackTrace();
            return false;
        }
    }

    @Test
    public void demoFlipkartLaunchTests() {
        String testCaseName = "Launch Test for demo flipkart";
        try {
            assertEquals("Launching App Failed", true, coldLaunch(testCaseName));
            assertEquals("Home Element Appear Failed", true, demoFlipkartHomeElementAppear(testCaseName));
            assertEquals("Click On Search Button Failed", true, demoFlipkartClickSearchButton(testCaseName));
            assertEquals("Enter Search Text and Search a Product Failed", true, demoFlipkartSearchProduct(testCaseName));
            assertEquals("Search Result Appear Failed", true, demoFlipkartSearchResultAppear(testCaseName));
            assertEquals("Click On Search Result Failed", true, demoFlipkartClickOnSearchResult(testCaseName));
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private boolean coldLaunch(String testCaseName) {
        boolean value = false;
        System.out.println("Launching the App Activity");
        try {
            device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
//            final Intent intent = context.getPackageManager().getLaunchIntentForPackage(GlobalVariables.appPackageName);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            context.startActivity(intent);
            device.executeShellCommand("monkey -p "+GlobalVariables.appPackageName+" -c android.intent.category.LAUNCHER 1");
            time01 = new Timestamp(new Date().getTime());
            Log.d(GlobalVariables.tagName,"time01 = "+time01);
            sendEvents(testCaseName,"launchApp","Success", "coldLaunch");
            device.hasObject(By.pkg(GlobalVariables.appPackageName));
            value = true;
        } catch (Exception e) {
            sendEvents(testCaseName,"launchApp","No", "coldLaunch");
            e.printStackTrace(); }
        return value;
    }

    private boolean warmLaunch(String testCaseName) {
        boolean value = false;
        System.out.println("Launching the App Activity");
        try {
            //Clearing old logs
            Runtime.getRuntime().exec("logcat -c");
            Runtime.getRuntime().exec("logcat -c");
            Runtime.getRuntime().exec("logcat -c");
            device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
            final Intent intent = context.getPackageManager().getLaunchIntentForPackage(GlobalVariables.appPackageName);
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
            timeWarmLaunch = new Timestamp(new Date().getTime());
            Log.d(GlobalVariables.tagName,"timeWarmLaunch = "+timeWarmLaunch);
            device.hasObject(By.pkg(GlobalVariables.appPackageName));
            value = true;
        } catch (Exception e) {
            sendEvents(testCaseName,"launchApp","No", "warmLaunch");
            e.printStackTrace(); }
        return value;
    }

    private boolean demoFlipkartHomeElementAppear(String testCaseName) {
        boolean value = false;
        try {int i=0;
            UiObject object = device.findObject(new UiSelector().className("android.view.ViewGroup").clickable(true).instance(0));
            UiScrollable scrollable = new UiScrollable(new UiSelector().scrollable(true));
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime()<=OneMin_Timeout){
                if (object.exists()) {
                    time5 = new Timestamp(new Date().getTime());
                    sendEvents(testCaseName,"Appear", "Yes","homePageElementAppear");
                    value = true;
                    break;
            }
        }} catch (Exception e) {
            sendEvents(testCaseName,"Appear", "Failed","homePageElementAppear");
            e.printStackTrace();
        }
        return value;
    }
    private boolean demoFlipkartClickSearchButton(String testCaseName) {
        boolean value = false;
        try {int i=0;
            UiObject object = device.findObject(new UiSelector().className("android.view.ViewGroup").clickable(true).instance(2));
            UiScrollable scrollable = new UiScrollable(new UiSelector().scrollable(true));
            UiObject object1 = device.findObject(new UiSelector().resourceId("com.flipkart.android:id/search_icon"));
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime()<=OneMin_Timeout){
                if (object.exists()) {
                    object.click();
                    if(object1.exists()){
                        object1.click();
                    }
                    time5 = new Timestamp(new Date().getTime());
                    sendEvents(testCaseName,"Appear", "Yes","homePageElementAppear");
                    value = true;
                    break;
                }
            }} catch (Exception e) {
            sendEvents(testCaseName,"Appear", "Failed","homePageElementAppear");
            e.printStackTrace();
        }
        return value;
    }

    private boolean demoFlipkartSearchProduct(String testCaseName) {
        boolean value = false;
        try {int i=0;
            UiObject object = device.findObject(new UiSelector().className("android.widget.EditText"));
            UiScrollable scrollable = new UiScrollable(new UiSelector().scrollable(true));
            UiObject object1 = device.findObject(new UiSelector().resourceId("com.flipkart.android:id/search_icon"));
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime()<=OneMin_Timeout){
                if (object.exists()) {
                    object.setText("Samsung S23 Ultra");
                    device.pressEnter();
                    time5 = new Timestamp(new Date().getTime());
                    sendEvents(testCaseName,"Appear", "Yes","homePageElementAppear");
                    value = true;
                    break;
                }
            }} catch (Exception e) {
            sendEvents(testCaseName,"Appear", "Failed","homePageElementAppear");
            e.printStackTrace();
        }
        return value;
    }

    private boolean demoFlipkartSearchResultAppear(String testCaseName) {
        boolean value = false;
        try {int i=0;
            UiObject object = device.findObject(new UiSelector().textContains("SAMSUNG Galaxy S23 Ultra 5G (Phantom Black, 512 GB)"));
            UiScrollable scrollable = new UiScrollable(new UiSelector().scrollable(true));
            UiObject object1 = device.findObject(new UiSelector().resourceId("com.flipkart.android:id/search_icon"));
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime()<=OneMin_Timeout){
                if (object.exists()) {
                    time5 = new Timestamp(new Date().getTime());
                    sendEvents(testCaseName,"Appear", "Yes","homePageElementAppear");
                    value = true;
                    break;
                }
            }} catch (Exception e) {
            sendEvents(testCaseName,"Appear", "Failed","homePageElementAppear");
            e.printStackTrace();
        }
        return value;
    }

    private boolean demoFlipkartClickOnSearchResult(String testCaseName) {
        boolean value = false;
        try {int i=0;
            UiObject object = device.findObject(new UiSelector().textContains("SAMSUNG Galaxy S23 Ultra 5G (Phantom Black, 512 GB)"));
//            UiScrollable scrollable = new UiScrollable(new UiSelector().scrollable(true));

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime()<=OneMin_Timeout){
                if (object.exists()) {
                    object.click();
                    Thread.sleep(3000);
                    device.swipe(935,892,92,882,5);
                    Thread.sleep(3000);
                    device.swipe(935,892,92,882,5);
                    Thread.sleep(3000);
                    device.swipe(935,892,92,882,5);
                    Thread.sleep(3000);
                    device.click(535, 733);
                    Thread.sleep(10000);
                    device.pressBack();
                    Thread.sleep(3000);
                    device.swipe(935,892,92,882,5);
                    Thread.sleep(3000);
                    device.swipe(935,892,92,882,5);
                    Thread.sleep(3000);
                    device.swipe(935,892,92,882,5);
                    Thread.sleep(3000);


                    time5 = new Timestamp(new Date().getTime());
                    sendEvents(testCaseName,"Appear", "Yes","homePageElementAppear");
                    value = true;
                    break;
                }
            }} catch (Exception e) {
            sendEvents(testCaseName,"Appear", "Failed","homePageElementAppear");
            e.printStackTrace();
        }
        return value;
    }

}

package com.example.demoflipkart.interfaces;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;

import org.apache.commons.lang3.time.StopWatch;

public class ClearCache {

    UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    Context context = ApplicationProvider.getApplicationContext();
    static long timeout = 10000;
    UiScrollable scrollable = new UiScrollable(new UiSelector().scrollable(true));

    public void clearingCacheAndDataForApplication(){
        boolean value = false;
        try {
            assertEquals("Failed to open Application Settings",true,appDetailsSettings());
            assertEquals("Unable to click on Internal Storage",true,internalStorage());
            //assertEquals("Unable to click on Clear Cache",true,clearCache());
            assertEquals("Unable to click on Clear Data",true,clearData());
            Thread.sleep(2000);
            value = true;
        }catch (Exception e){
            e.printStackTrace();
            Log.d(GlobalVariables.tagName,"Error in Clearing Application Cache");
        }
        // return value;
    }

    public boolean appDetailsSettings(){
        boolean value = false;
        try {
            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + GlobalVariables.appPackageName));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            Log.d(GlobalVariables.tagName,"App Details in Settings Launched");
            value = true;
        }catch (Exception e){
            Log.d(GlobalVariables.tagName, "Unable to open App Details in Settings");
            e.printStackTrace();
        }
        return value;
    }

    public boolean internalStorage(){
        boolean value = false;
        try {
            UiScrollable scrollable = new UiScrollable(new UiSelector().scrollable(true));
            UiSelector storage = new UiSelector().textContains("Internal Storage");
            UiObject storageOption = device.findObject(storage);
            UiSelector storage1 = new UiSelector().textStartsWith("Storage");
            UiObject storageOption1 = device.findObject(storage1);
            UiSelector storage2 = new UiSelector().textContains("Computing");
            UiObject storageOption2 = device.findObject(storage2);
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            while (stopWatch.getTime()<=30000){
                if (storageOption1.exists()) {
                    storageOption1.click();
                    Log.d(GlobalVariables.tagName,"Clicked On Internal Storage");
                    value = true;
                    break;
                } else{
                    if (storageOption2.exists()) {
                        storageOption2.click();
                        Log.d(GlobalVariables.tagName, "Clicked On Internal Storage");
                        value = true;
                        break;
//                } else {scrollable.scrollIntoView(storageOption1);
//                    if (storageOption1.waitForExists(timeout)){
//                    storageOption1.click();
//                    Log.d(GlobalVariables.tagName,"Clicked On Internal Storage");
//                    value = true;
//                }
                    }else{
                        if (storageOption.exists()) {
                            storageOption.click();
                            Log.d(GlobalVariables.tagName, "Clicked On Internal Storage");
                            value = true;
                            break;}
                        else {
                            scrollable.scrollTextIntoView("Storage");
                        }
                    }}}

        }catch (Exception e){
            Log.d(GlobalVariables.tagName, "Unable to click on Internal Storage");
            e.printStackTrace();
        }
        return value;
    }

    public boolean clearCache(){
        boolean value = false;
        try {
            UiSelector cache = new UiSelector().textContains("Clear cache");
            UiObject cacheButton = device.findObject(cache);
            UiObject cacheBtn1 = device.findObject(new UiSelector().textContains("CLEAR CACHE"));

            if (cacheButton.waitForExists(timeout)) {
                cacheButton.click();
                Log.d(GlobalVariables.tagName,"Clicked On Clear Cache");
                value = true;
            }else if (cacheBtn1.waitForExists(timeout)) {
                cacheBtn1.click();
                Log.d(GlobalVariables.tagName,"Clicked On Clear Cache");
                value = true;
            }
        }catch (Exception e){
            Log.d(GlobalVariables.tagName, "Unable to click on clear cache");
            e.printStackTrace();
        }
        return value;
    }

    public boolean clearData(){
        boolean value = false;
        try {
            UiSelector cache = new UiSelector().textContains("Clear data");
            UiObject cacheButton = device.findObject(cache);
            UiObject cacheBtn1 = device.findObject(new UiSelector().textContains("CLEAR STORAGE"));
            UiObject ok = device.findObject(new UiSelector().resourceId("android:id/button1"));
            UiObject delete = device.findObject(new UiSelector().textContains("Delete"));

            if (cacheButton.waitForExists(timeout)) {
                cacheButton.click();
                if (ok.waitForExists(5000)){
                    ok.click();
                }
                Log.d(GlobalVariables.tagName,"Clicked On Clear Data");
                value = true;
            }else if (cacheBtn1.waitForExists(timeout)) {
                cacheBtn1.click();
                if (ok.waitForExists(5000)){
                    ok.click();
                }
                Log.d(GlobalVariables.tagName,"Clicked On Clear Data");
                value = true;}
        }catch (Exception e){
            Log.d(GlobalVariables.tagName, "Unable to click on clear cache");
            e.printStackTrace();
        }
        return value;
    }
}
package com.m4399.gamecentertest;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.UiWatcher;
import android.support.test.uiautomator.Until;
import android.util.Log;

import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Random;

@RunWith(AndroidJUnit4.class)
public class autoTestBase extends gameCenterStr{
    public Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    public UiDevice device = UiDevice.getInstance(instrumentation);
    //结束游戏盒进程并重启
    public void restartApp() throws IOException, InterruptedException {
        device.executeShellCommand("am force-stop com.m4399.gamecenter");
        Thread.sleep(2000);
        device.executeShellCommand("am start -n com.m4399.gamecenter/.controllers.splash.SplashActivity");
        device.wait(Until.findObject(By.res("com.m4399.gamecenter:id/rl_game_search")),10000);
    }
    //滚动到指定object
    public void scrollIntoObject(String listCls,String resID) throws UiObjectNotFoundException {
        UiScrollable uiScrollable = new UiScrollable(new UiSelector().className(listCls));
        uiScrollable.setMaxSearchSwipes(500);
        uiScrollable.scrollIntoView(new UiSelector().resourceId(resID));
    }
    //注册监听器
    public void errorHandleAdd(String name_resgister, final String res_register){
        device.registerWatcher(name_resgister, new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                if (device.hasObject(By.res(res_register))){
                    device.findObject(By.res(res_register)).clickAndWait(Until.newWindow(),10000);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i("UiWatcher","监听器已触发");
                }
                return false;
            }
        });
    }
    //找游戏tab点击
    public void tabClickHome(){
        UiObject2 tabObject = device.findObject(By.res("com.m4399.gamecenter:id/ctl_indicator"));
        int a = tabObject.getVisibleBounds().top;
        int b = tabObject.getVisibleBounds().bottom;
        int c = tabObject.getVisibleBounds().left;
        int d = tabObject.getVisibleBounds().right;
        device.click((d-c)/10,(b-a)/2+a);
        device.wait(Until.findObject(By.res("com.m4399.gamecenter:id/iv_tag_icon")),1000);
    }
    //动态tab点击
    public  void tabClickZone(){
        UiObject2 tabObject = device.findObject(By.res("com.m4399.gamecenter:id/ctl_indicator"));
        int a = tabObject.getVisibleBounds().top;
        int b = tabObject.getVisibleBounds().bottom;
        int c = tabObject.getVisibleBounds().left;
        int d = tabObject.getVisibleBounds().right;
        device.click((d-c)*3/10,(b-a)/2+a);
        device.wait(Until.findObject(By.res("com.m4399.gamecenter:id/zone_list_header_family")),1000);
    }
    //游戏圈tab点击
    public  void tabClickQuan(){
        UiObject2 tabObject = device.findObject(By.res("com.m4399.gamecenter:id/ctl_indicator"));
        int a = tabObject.getVisibleBounds().top;
        int b = tabObject.getVisibleBounds().bottom;
        int c = tabObject.getVisibleBounds().left;
        int d = tabObject.getVisibleBounds().right;
        device.click((d-c)*5/10,(b-a)/2+a);
        device.wait(Until.findObject(By.res("com.m4399.gamecenter:id/tv_post_title")),1000);
    }
    //广场tab点击
    public  void tabClickSquare(){
        UiObject2 tabObject = device.findObject(By.res("com.m4399.gamecenter:id/ctl_indicator"));
        int a = tabObject.getVisibleBounds().top;
        int b = tabObject.getVisibleBounds().bottom;
        int c = tabObject.getVisibleBounds().left;
        int d = tabObject.getVisibleBounds().right;
        device.click((d-c)*7/10,(b-a)/2+a);
        device.wait(Until.findObject(By.res("com.m4399.gamecenter:id/tv_view_all")),1000);
    }
    //我tab点击
    public void tabClickMy(){
        UiObject2 tabObject = device.findObject(By.res("com.m4399.gamecenter:id/ctl_indicator"));
        int a = tabObject.getVisibleBounds().top;
        int b = tabObject.getVisibleBounds().bottom;
        int c = tabObject.getVisibleBounds().left;
        int d = tabObject.getVisibleBounds().right;
        device.click((d-c)*9/10,(b-a)/2+a);
        device.wait(Until.findObject(By.res("com.m4399.gamecenter:id/tv_menu_logo")),1000);
    }
    //获取随机字符
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i<length;i++){
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}

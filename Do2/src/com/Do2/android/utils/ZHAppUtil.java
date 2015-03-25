package com.Do2.android.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.File;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;

import com.Do2.android.log.Logger;


/**
 * 当前程序是否后台运行
 * 当前手机是否处于睡眠
 * 当前网络是否已连接
 * 当前网络是否wifi状态
 * 当前wifi名称
 * 安装apk
 * 初始化view的高度
 * 初始化view的高度后不可见
 * 判断是否为手机
 * 获取屏幕宽度
 * 获取屏幕高度
 * 获取设备的IMEI
 * 获取设备的名称
 * 获取设备的mac地址
 * 获取当前应用的版本号
 * 获取手机系统版本
 * 获取当前network的IP
 * 获取Gprs时的IP
 * 获取WIFI时的IP
 * 收集设备信息并以Properties返回
 * 收集设备信息并以String返回
 *
 * Created with IntelliJ IDEA.
 * Author: zhou  email:657523985@qq.com
 * Date: 14-07-10
 * Time: 上午10:40
 */
public class ZHAppUtil {
    private static final String TAG = ZHAppUtil.class.getSimpleName();

    /**
     *判断当前应用程序是否后台运行
     * @param context
     * @return
     */
    public static boolean isBackground(Context context) {


        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    Logger.i(TAG, "后台程序: " + appProcess.processName);
                    return true;
                }else{
                    Logger.i(TAG, "前台程序: " + appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 判断手机是否处理睡眠
     * @param context
     * @return
     */
    public static boolean isSleeping(Context context){
        KeyguardManager kgMgr = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        boolean isSleeping = kgMgr.inKeyguardRestrictedInputMode();
        Logger.i(TAG, isSleeping ? "手机睡眠中.." : "手机未睡眠...");
        return isSleeping;
    }

    /**
     * 检查网络是否已连接
     * @param context
     * @return
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * 判断当前是否是wifi状态
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(wifiNetworkInfo.isConnected())
        {
            return true ;
        }
        return false ;
    }
    /**
     * 获取当前wifi名称
     * @param context
     * @return
     */
    public static String getWifiName(Context context)
    {	
    	if (isWifiConnected(context)) {
    		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);  
        	WifiInfo wifiInfo = wifiManager.getConnectionInfo();	//获取当前连接的wifi名称
        	
        	return wifiInfo.getSSID();
		}
    	return "";
    }
    /**
     * 安装apk
     * @param context
     * @param file
     */
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("application/vnd.android.package-archive");
        intent.setData(Uri.fromFile(file));
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    /**
     * 初始化View的高宽
     * @param view
     */
    public static void initViewWH(final View view){
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                System.out.println(view + ", width: " + view.getWidth() + "; height: " + view.getHeight());
            }
        });

    }
    /**
     * 初始化View的高宽并显示不可见
     * @param view
     */
    public static void initViewWHAndGone(final View view){
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                view.setVisibility(View.GONE);
            }
        });

    }


    /** 使用Properties来保存设备的信息和错误堆栈信息 */
    private static final String VERSION_NAME = "versionName";
    private static final String VERSION_CODE = "versionCode";
    private static final String STACK_TRACE = "STACK_TRACE";

    /**
     * 判断是否为手机
     * @author wangjie
     * @param context
     * @return
     */
    public static boolean isPhone(Context context) {
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int type = telephony.getPhoneType();
        if (type == TelephonyManager.PHONE_TYPE_NONE) {
            Logger.i(TAG, "Current device is Tablet!");
            return false;
        } else {
            Logger.i(TAG, "Current device is phone!");
            return true;
        }
    }

    /**
     * 获得设备的屏幕宽度
     * @param context
     * @return
     */
    public static int getDeviceWidth(Context context){
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return manager.getDefaultDisplay().getWidth();
    }

    /**
     * 获得设备的屏幕高度
     * @param context
     * @return
     */
    public static int getDeviceHeight(Context context){
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return manager.getDefaultDisplay().getHeight();
    }

    /**
     * 获取设备id（IMEI）
     * @author zhou
     * @param context
     * @return
     */
    public static String getDeviceIMEI(Context context) {
        String deviceId;
        if (isPhone(context)) {
            TelephonyManager telephony = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = telephony.getDeviceId();
        } else {
            deviceId = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);

        }
        Logger.i(TAG, "当前设备IMEI码: " + deviceId);
        return deviceId;
    }
    /**
     * 获取设备id（平板之类）
     * @param context
     * @return
     */
	public static String getDeviceID(Context context) {

		// 如果为手机则获取手机唯一ID，平板上获取不了则获取android唯一ID
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String ID = telephonyManager.getDeviceId();
		// 如果平板上获取ID为空或者等于0，获取andorid的唯一ID
		if (ID == null || "000000000000000".equals(ID)) {
			ID = Secure.getString(context.getContentResolver(),
					Secure.ANDROID_ID);
		}
		// Android 2.2版本以下可能会返回一个固定值
		if ("9774d56d682e549c".equals(ID)) {
			ID = "";
		}
		Logger.i(TAG, "当前设备id为（手机/平板）:" + ID);
		return ID;

	}
	/**
	 *  获取手机型号名称
	 */	
	public static String getDeviceName() {
		String DeviceName = android.os.Build.MODEL;// 获取手机型号
		Logger.i(TAG, "DeviceType:" + DeviceName);
		return DeviceName;
	
	}
    /**
     * 获取设备mac地址
     * @param context
     * @return
     */
    public static String getMacAddress(Context context) {
        String macAddress;
        WifiManager wifi = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        macAddress = info.getMacAddress();
        Logger.i(TAG, "当前mac地址: " + (null == macAddress ? "null" : macAddress));
        if(null == macAddress){
            return "";
        }
        macAddress = macAddress.replace(":", "");
        return macAddress;
    }

    /**
     * 获取当前应用程序的版本号
     * @param context
     * @return
     */
    public static String getAppVersion(Context context) {
        String version = "0";
        try {
            version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(TAG, "getAppVersion", e);
        }
        Logger.i(TAG, "该应用的版本号: " + version);
        return version;
    }
    /**
	 *  获取手机系统版本
	 */		
	public static String getOsVersion() {
		String SysVersion = android.os.Build.VERSION.RELEASE;// 获取系统版本
		Logger.i(TAG, "Sysversion：" + SysVersion);
		return SysVersion;
	}
    
	/**
	 * 获取手机拨号上网（包括CTWAP和CTNET）时由PDSN分配给手机终端的源IP地址
	 * @param context
	 * @return
	 */
	public static String getNetWorkIP(Context context) {
		String ip = "";
		if (isWifiConnected(context)) {
			ip = getWiFiIP(context);
		}else {
			ip = getGprsIP();
		}
		Logger.i(TAG, "当前网络的ip为："+ip);
		return ip;
	}
	/**
	 * 获取网络为GPRS时的IP
	 * @return
	 */
	private static String getGprsIP(){
		try {
			for(Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces()
					;en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for(Enumeration<InetAddress> enumIpAdd = intf.getInetAddresses();
						enumIpAdd.hasMoreElements();) {
					InetAddress inetAddress = enumIpAdd.nextElement();
					if(!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (Exception e) {
			Logger.e(TAG, "Error while getGprsIP", e);
		}
		return "";
	}
	/**
	 * 获取网络为WIFI时的IP	
	 * @param context
	 * @return
	 */
	public static String getWiFiIP(Context context) {
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		if (!wifiManager.isWifiEnabled()) {
			 wifiManager.setWifiEnabled(true);
		}
		WifiInfo wifiinfo = wifiManager.getConnectionInfo();
		String ip = intToIp(wifiinfo.getIpAddress());
		
		return ip;
	}
	/**
	 * 将int型的IP转成带String型
	 * @param i
	 * @return
	 */
	public static String intToIp(int i) {

		return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
				+ "." + ((i >> 24) & 0xFF);
		
	}
    /**
     * 收集设备信息
     * @param context
     */
    public static Properties collectDeviceInfo(Context context) {
        Properties mDeviceCrashInfo = new Properties();
        try {
            // Class for retrieving various kinds of information related to the
            // application packages that are currently installed on the device.
            // You can find this class through getPackageManager().
            PackageManager pm = context.getPackageManager();
            // getPackageInfo(String packageName, int flags)
            // Retrieve overall information about an application package that is installed on the system.
            // public static final int GET_ACTIVITIES
            // Since: API Level 1 PackageInfo flag: return information about activities in the package in activities.
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                // public String versionName The version name of this package,
                // as specified by the <manifest> tag's versionName attribute.
                mDeviceCrashInfo.put(VERSION_NAME, pi.versionName == null ? "not set" : pi.versionName);
                // public int versionCode The version number of this package,
                // as specified by the <manifest> tag's versionCode attribute.
                mDeviceCrashInfo.put(VERSION_CODE, pi.versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(TAG, "Error while collect package info", e);
        }
        // 使用反射来收集设备信息.在Build类中包含各种设备信息,
        // 例如: 系统版本号,设备生产商 等帮助调试程序的有用信息
        // 返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                // setAccessible(boolean flag)
                // 将此对象的 accessible 标志设置为指示的布尔值。
                // 通过设置Accessible属性为true,才能对私有变量进行访问，不然会得到一个IllegalAccessException的异常
                field.setAccessible(true);
                mDeviceCrashInfo.put(field.getName(), field.get(null));
//                if (DEBUG) {
//                    Logger.d(TAG, field.getName() + " : " + field.get(null));
//                }
            } catch (Exception e) {
                Logger.e(TAG, "Error while collect crash info", e);
            }
        }

        return mDeviceCrashInfo;
    }

    /**
     * 收集设备信息
     * @param context
     * @return
     */
    public static String collectDeviceInfoStr(Context context){
        Properties prop = collectDeviceInfo(context);
        Set deviceInfos = prop.keySet();
        StringBuilder deviceInfoStr = new StringBuilder("{\n");
        for(Iterator iter = deviceInfos.iterator(); iter.hasNext();){
            Object item = iter.next();
            deviceInfoStr.append("\t\t\t" + item + ":" + prop.get(item) + ", \n");
        }
        deviceInfoStr.append("}");
        return deviceInfoStr.toString();
    }

    /**
     * 是否有SDCard
     * @return
     */
    public static boolean haveSDCard(){
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * 隐藏软键盘
     */
    public static void hideSoftInput(Context context) {
        View view = ((Activity)context).getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    /**
     * 隐藏软键盘
     */
    public static void hideSoftInput(Context context, EditText edit) {
        edit.clearFocus();
        InputMethodManager inputmanger = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputmanger.hideSoftInputFromWindow(edit.getWindowToken(), 0);
    }
    /**
     * 显示软键盘
     */
    public static void showSoftInput(Context context, EditText edit) {
        edit.setFocusable(true);
        edit.setFocusableInTouchMode(true);
        edit.requestFocus();
        InputMethodManager inputManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(edit, 0);
    }


    /**
     * 回到home，后台运行
     * @param context
     */
    public static void goHome(Context context){
        Logger.d(TAG, "返回键回到HOME，程序后台运行...");
        Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);

        mHomeIntent.addCategory(Intent.CATEGORY_HOME);
        mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        context.startActivity(mHomeIntent);
    }




}

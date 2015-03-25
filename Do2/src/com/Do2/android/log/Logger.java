package com.Do2.android.log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.Do2.android.thread.ThreadPool;
import com.Do2.android.utils.ZHTimeUtil;


/**
 * 日志包装工具类
 * Created with IntelliJ IDEA.
 * Author: zhou  email:657523985@qq.com
 * Date: 14-07-10
 * Time: 上午10:40
 */
public class Logger {
    /**
     * 是否开启调试，发布时请把DEBUG改为false
     */
    public static boolean debug = true;
    /**
     * 是否在客户端记录用户操作
     */
    public static boolean logFile = false;

    private static String logFilePath;

    public static void initLogger(LogConfig logConfig){
        if(null == logConfig){
            return;
        }
        debug = logConfig.isDebug();
        logFile = logConfig.isLogFile();
        logFilePath = logConfig.getLogFilePath();
    }


    public static void v(String tag, String msg) {
        if(debug) {
            android.util.Log.v(tag, msg); 
        }
        if(logFile ){
            writeLog(tag, msg, null, "VERBOSE");
        }
    }
    public static void v(String tag, String msg, Throwable tr) {
        if(debug) {
            android.util.Log.v(tag, msg, tr); 
        }
        if(logFile ){
            writeLog(tag, msg,tr, "VERBOSE");
        }
    }
    public static void d(String tag, String msg) {
        if(debug) {
            android.util.Log.d(tag, msg);
        }
        if(logFile ){
            writeLog(tag, msg, null, "debug");
        }
    }
    public static void d(String tag, String msg, Throwable tr) {
        if(debug) {
            android.util.Log.d(tag, msg, tr);
        }
        if(logFile ){
            writeLog(tag, msg, tr, "debug");
        }
    }
    public static void i(String tag, String msg) {
        if(debug) {
            android.util.Log.i(tag, msg);
        }
        if(logFile ){
            writeLog(tag, msg, null, "INFO");
        }
    }
    public static void i(String tag, String msg, Throwable tr) {
        if(debug) {
            android.util.Log.i(tag, msg, tr);
        }
        if(logFile ){
            writeLog(tag, msg, tr, "INFO");
        }
    }
    public static void w(String tag, String msg) {
        if(debug) {
            android.util.Log.w(tag, msg);
        }
        if(logFile ){
            writeLog(tag, msg, null, "WARN");
        }
    }
    public static void w(String tag, String msg, Throwable tr) {
        if(debug) {
            android.util.Log.w(tag, msg, tr);
        }
        if(logFile ){
            writeLog(tag, msg, tr, "WARN");
        }
    }
    public static void w(String tag, Throwable tr) {
        if(debug) {
            android.util.Log.w(tag, tr);
        }
        if(logFile){
            writeLog(tag, "", tr, "WARN");
        }
    }
    public static void e(String tag, String msg) {
        if(debug) {
            android.util.Log.e(tag, msg);
        }
        if(logFile ){
            writeLog(tag, msg, null, "ERROR");
        }
    }
    public static void e(String tag, String msg, Throwable tr) {
        if(debug) {
            android.util.Log.e(tag, msg, tr);
        }
        if(logFile ){
            writeLog(tag, msg, tr, "ERROR");
        }
    }
    public static void e(String tag, Throwable tr) {
        if(debug) {
            android.util.Log.e(tag, "", tr);
        }
        if(logFile ){
            writeLog(tag, "", tr, "ERROR");
        }
    }

    /**
     * 记录日志线程
     * @param tag
     * @param msg
     * @param tr
     * @param priority
     */
    private static void writeLog(final String tag, final String msg, final Throwable tr, final String priority){
    	ThreadPool.execute(new Runnable() {
			
			@Override
			public void run() {
				//同步锁,一个时间内只有一个执行
				synchronized (Logger.class){
					if(!logFilePath.endsWith(File.separator)){
	                    logFilePath = logFilePath + File.separator;
	                }
	                String filename = logFilePath
	                        + ZHTimeUtil.millisToStringFilename(System.currentTimeMillis(), "yyyy-MM-dd")
	                        + ".log";
	                File logFile = new File(filename);

	                OutputStream os = null;
	                try{
	                    if(!logFile.exists()){
	                        logFile.createNewFile();
	                    }

	                    os = new FileOutputStream(logFile, true);

	                    String formatMsg = ZHTimeUtil.millisToStringDate(System.currentTimeMillis()) + "\r\n[" + priority + "][" + tag + "]: \r\n"
	                            + "User Message: " + msg + "\r\n"
	                            + (null == tr ? "" :

	                            "Throwable Message: " + tr.getMessage() + "\r\n"
	                                    + "Throwable StackTrace: " + transformStackTrace(tr.getStackTrace())
	                            )
	                            + "\r\n";

	                    os.write(formatMsg.getBytes("utf-8"));
	                    os.flush();
	                }catch(Exception ex){
	                    ex.printStackTrace();
	                }finally{
	                    if(null != os){
	                        try {
	                            os.close();
	                        } catch (IOException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                }
				}
			}
		});
    }
    /**
     * 
     * @param elements
     * @return
     */
    private static StringBuilder transformStackTrace(StackTraceElement[] elements){
        StringBuilder sb = new StringBuilder();
        for(StackTraceElement element : elements){
            sb.append(element.toString() + "\r\n");
        }
        return sb;
    }



} 

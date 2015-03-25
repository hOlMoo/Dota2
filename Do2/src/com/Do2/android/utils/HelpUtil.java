package com.Do2.android.utils;


import android.content.Context;
import android.util.TypedValue;

/**
 * 数据帮助类
 * @author zhou Email:657523985@qq.com
 * @Date 14-07-30
 * @Time 下午15:25
 */
public class HelpUtil {
	public static String TAG = HelpUtil.class.getSimpleName();
	/**
	 * 
	 * @param context
	 * @param val
	 * @return
	 */
	public static float pixelToDp(Context context, float val) {
        float density = context.getResources().getDisplayMetrics().density;
        return val * density;
    }
    
	/**
	 * 
	 * @param context
	 * @param dipValue
	 * @return
	 */
	public static int dipToPx(Context context, int dipValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, context
                .getResources().getDisplayMetrics());
    }   
	
	
}

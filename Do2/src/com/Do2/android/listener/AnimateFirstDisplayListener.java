package com.Do2.android.listener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/** 
 * 图片加载第一次显示监听器 
 * @author Administrator 
 * 
 */  
public class AnimateFirstDisplayListener extends SimpleImageLoadingListener {  
      
    static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());  

    @Override  
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {  
        if (loadedImage != null) {  
            ImageView imageView = (ImageView) view;  
            // 是否第一次显示  
            boolean firstDisplay = !displayedImages.contains(imageUri);  
            if (firstDisplay) {  
                // 图片淡入效果  
                FadeInBitmapDisplayer.animate(imageView, 500);  
                displayedImages.add(imageUri);  
            }  
        }  
    }  
}

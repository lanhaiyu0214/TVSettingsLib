/*
 * Copyright (C) Appotronics
 * Author name:
 *		Lanhaiyu
 * Author Email:
 *      306740439@qq.com
 * Create Time:
 * 		2018年6月21日 下午2:25:47 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
package com.appo.tvsettings.lib;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import android.widget.TextView;

public class ScreenAdapterUtils {
	private Context context;
	private int screenWidth;
	private int screenHeight;

	private int desinnWidth;
	private int designHeight;

	private float scaleX;
	private float scaleY;
	private float minScale;
	private float maxScale;

	private static Map<String, ScreenAdapterUtils> utils = new HashMap<String, ScreenAdapterUtils>();

	private ScreenAdapterUtils(Context context, int designWidht, int designHeight) {
		this.context = context.getApplicationContext();
		this.desinnWidth = designWidht;
		this.designHeight = designHeight;

		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);

		this.screenWidth = outMetrics.widthPixels;
		this.screenHeight = outMetrics.heightPixels;

		scaleX = 1.0f * screenWidth / designWidht;
		scaleY = 1.0f * screenHeight / designHeight;

		minScale = scaleX < scaleY ? scaleX : scaleY;
		maxScale = scaleX < scaleY ? scaleY : scaleX;
	}

	public static ScreenAdapterUtils getInstance(Context context, int designWidht, int designHeight) {
		if (utils.get(designWidht + ":" + designHeight) != null) {
			return utils.get(designWidht + ":" + designHeight);
		}

		ScreenAdapterUtils adapterUtils = new ScreenAdapterUtils(context.getApplicationContext(), designWidht, designHeight);
		utils.put(designWidht + ":" + designHeight, adapterUtils);

		return adapterUtils;
	}

	public View adapterViewByWidth(View view) {
		if (view instanceof TextView) {
			TextView tv = (TextView) view;
			tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, tv.getTextSize() * scaleX);
		}

		if (view.getLayoutParams() != null) {
			ViewGroup.LayoutParams vglp = view.getLayoutParams();
			vglp.width = (vglp.width > 0 ? (int) (vglp.width * scaleX) + 1 : vglp.width);
			vglp.height = (vglp.height > 0 ? (int) (vglp.height * scaleX) + 1 : vglp.height);
			
			if (vglp instanceof MarginLayoutParams) {
				MarginLayoutParams mglp = (MarginLayoutParams) vglp;
				mglp.leftMargin = mglp.leftMargin > 0 ? (int) (mglp.leftMargin * scaleX) + 1 : mglp.leftMargin;
				mglp.topMargin = mglp.topMargin > 0 ? (int) (mglp.topMargin * scaleX) + 1 : mglp.topMargin;
				mglp.rightMargin = mglp.rightMargin > 0 ? (int) (mglp.rightMargin * scaleX) + 1 : mglp.rightMargin;
				mglp.bottomMargin = mglp.bottomMargin > 0 ? (int) (mglp.bottomMargin * scaleX) + 1 : mglp.bottomMargin;
			}
		}

		int paddingLeft = view.getPaddingLeft();
		int paddingTop = view.getPaddingTop();
		int paddingRight = view.getPaddingRight();
		int paddingBottom = view.getPaddingBottom();
		view.setPadding(paddingLeft > 0 ? (int) (paddingLeft * scaleX) + 1 : paddingLeft, paddingTop > 0 ? (int) (paddingTop * scaleX) + 1 : paddingTop,
				paddingRight > 0 ? (int) (paddingRight * scaleX) + 1 : paddingRight, paddingBottom > 0 ? (int) (paddingBottom * scaleX) + 1 : paddingBottom);
		
		if(view instanceof ViewGroup){
			ViewGroup vg = (ViewGroup) view;
			for(int i =0;i<vg.getChildCount();i++){
				adapterViewByWidth(vg.getChildAt(i));
			}
		}
		return view;
	}
	
	public View adapterByWidth(int layoutId){
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater.inflate(layoutId, null);
		return adapterViewByWidth(view);
	}

}

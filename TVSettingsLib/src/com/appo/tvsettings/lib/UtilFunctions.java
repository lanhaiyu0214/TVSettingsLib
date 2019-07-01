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

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class UtilFunctions {
	private static String TAG="UtilFunctions";
	public static final int COLOR_NORMAL = 0xFFffffff;
	public static final int COLOR_READONLY = 0xFFA0A0A0;
	
	/*
	 * move high-light-border on the top of this view
	 */
	public static void moveHighLightBorderOnTop(ImageView border,View view, int duration){
		
		if(view==null) return;
		if(border==null) return;
		//get size
		int width=view.getWidth();
		int height=view.getHeight();
		if(width==0||height==0) return;
		
		//set border size
		LayoutParams lp = border.getLayoutParams();
		lp.width = width+border.getPaddingLeft()+border.getPaddingRight();
		lp.height = height+border.getPaddingTop()+border.getPaddingBottom();
		/*border.setLayoutParams(
				new LinearLayout.LayoutParams(
						width+border.getPaddingLeft()+border.getPaddingRight(),height+border.getPaddingTop()+border.getPaddingBottom()));*/
		//Show border
		int[] location=new int[2];
		view.getLocationInWindow(location);
		/*int relativeX_item = view.getLeft();
		int relativeY_item = view.getTop();
		
		int relativeX_listview = ((View)view.getParent()).getLeft();
		int relativeY_listview = ((View)view.getParent()).getTop();
		
		location[0] = relativeX_item + relativeX_listview;
		location[1] = relativeY_item + relativeY_listview;*/
		
		
		moveViewToSpecifiedPositon(border,location[0]-border.getPaddingLeft(),location[1]-border.getPaddingTop(),duration);
	}
	
	private static void moveViewToSpecifiedPositon(final ImageView image, float toX,
			float toY, final int duration) {
		if (image == null) {
			return;
		}
		if (duration < 0) {
			image.setVisibility(View.INVISIBLE);
		} else{
			image.setVisibility(View.VISIBLE);
		}
		ViewPropertyAnimator animator = image.animate();
		if(duration<0){
			animator.setDuration(200);
		}else{
			animator.setDuration(duration);
		}
		animator.scaleX(1);
		animator.scaleY(1);
		animator.x(toX);
		animator.y(toY);
		animator.start();
		animator.setListener(new AnimatorListener() {
			public void onAnimationStart(Animator animation) {

			}

			public void onAnimationEnd(Animator animation) {
				if(duration<0){
					image.setVisibility(View.VISIBLE);
				}	
			}

			public void onAnimationCancel(Animator animation) {

			}

			public void onAnimationRepeat(Animator animation) {

			}
		});

	}
	
	public static void startActivity(Context context,String pkg, String cls){
		Intent intent = new Intent(Intent.ACTION_MAIN);
 		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
 		ComponentName componentName =null;
 		componentName = new ComponentName(pkg, cls);
 		intent.setComponent(componentName);
 		try{
 			context.startActivity(intent);
 		}catch(Exception e){
 			e.printStackTrace();
 		}
	}
	
	
}

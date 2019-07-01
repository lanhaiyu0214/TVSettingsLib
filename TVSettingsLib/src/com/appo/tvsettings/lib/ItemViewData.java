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

import android.graphics.drawable.Drawable;


public class ItemViewData {
	
	private int iconId=0;
	private String title=null;
	private String summary=null;
	private String rightText=null;
	private int rightResouceId=0;
	private boolean showArrowIcon=false;
	private boolean readonly=false;
	private onClickListener mOnclick=null;
	private onLeftKeyClickListener mOnLeftKeyclick=null;
	private onRightKeyClickListener mOnRightKeyclick=null;
	private boolean rightIconRotate=false;
	private Drawable iconDrawable = null;
	
	@Override
	public String toString() {
		return "iconId:" + iconId +",title:" + title + ",summary:" + summary + ",rightText:" + rightText; 
	}
	private onLongClickListener mOnLongClickListener=null;
	public ItemViewData(){
		
	}
	public ItemViewData(int icon,String title, String summary, String rightText){
		this.iconId=icon;
		this.title=title;
		this.summary=summary;
		this.rightText=rightText;
	}
	
	public ItemViewData(Drawable icon,String title, String summary, String rightText){
		this.iconDrawable = icon;
		this.title=title;
		this.summary=summary;
		this.rightText=rightText;
	}
	
	public ItemViewData(int icon,String title, String summary, int resId){
		this.iconId=icon;
		this.title=title;
		this.summary=summary;
		this.rightResouceId=resId;
	}
	
	public void setIconDrawable(Drawable icon){
		iconDrawable = icon;
	}
	
	public Drawable getIconDrawable(){
		return iconDrawable;
	}
	
	public void setIconId(int id){
		 this.iconId=id;
	}
	public int getIconId(){
		return iconId;
	}
	
	public void setTitle(String newTitle){
		 this.title=newTitle;
	}
	public String getTitle(){
		return title;
	}
	
	public void setSubSummary(String newSummary){
		this.summary=newSummary;
	}
	public String getSubSummary(){
		return summary;
	}
	
	public void setRightSummary(String newSummary){
		this.rightText=newSummary;
	}
	public String getRightSummary(){
		return this.rightText;
	}
	
	public void setRightResourceId(int id){
		this.rightResouceId=id;
	}
	public int getRightResourceId(){
		return this.rightResouceId;
	}
	
	public void setArrowShow(boolean b){
		this.showArrowIcon=b;
	}
	public boolean getArrowShow(){
		return this.showArrowIcon;
	}
	
	public void setReadOnly(boolean b){
		this.readonly=b;
	}
	
	public boolean getReadOnly(){
		return this.readonly;
	}
	
	
	
	public void setOnLongClickListener(onLongClickListener listener){
		mOnLongClickListener = listener;
	}
	/**
	 *  on long click
	 * @author APPO
	 *
	 */
	public interface onLongClickListener{
		public void onLongClick();
	}
	
	public void callOnLongClick(){
		if(mOnLongClickListener!=null){
			mOnLongClickListener.onLongClick();
		}
	}
	
	/*
	 * onClick
	 */
	public interface onClickListener {
		public void onClick();

	}
	public void setOnClickListener(onClickListener o){
		this.mOnclick=o;
	}
	public void callOnClick(){
		if(mOnclick!=null){
			if(!readonly)
				mOnclick.onClick();
		}
	}
	/*
	 * on LEFT-KEY Click
	 */
	public interface onLeftKeyClickListener {
		public void onClick();

	}
	public void setOnLeftKeyClickListener(onLeftKeyClickListener o){
		this.mOnLeftKeyclick=o;
	}
	public void callOnLeftKeyClick(){
		if(mOnLeftKeyclick!=null){
			mOnLeftKeyclick.onClick();
		}
	}
	
	/*
	 * on RIGHT-KEY Click
	 */
	public interface onRightKeyClickListener {
		public void onClick();

	}
	public void setOnRightKeyClickListener(onRightKeyClickListener o){
		this.mOnRightKeyclick=o;
	}
	public void callOnRightKeyClick(){
		if(mOnRightKeyclick!=null){
			mOnRightKeyclick.onClick();
		}
	}
	/*
	 * Right-Icon Rotate state
	 */
	public boolean getRightIconRotateState(){
		return rightIconRotate;
	}
	public void setRightIconRotateState(boolean state){
		rightIconRotate=state;
	}
	
}

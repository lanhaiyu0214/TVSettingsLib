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

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListItemView extends LinearLayout {
	// inflate views
	private ImageView mImageView = null;
	private TextView  mTitleView = null;
	private TextView  mSubSummaryView = null;
	private TextView  mRightSummaryView = null;
	// get value from outside
	private String titleString;
	private int iconId;
	private String summaryString;
	
	public ListItemView(Context context) {
		super(context);
		initial(context);
	}
	public ListItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		initial(context);

		// Get the setttings in the xml config
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.EntryItemParameter, 0, 0);
		titleString=a.getString(R.styleable.EntryItemParameter_title);
		iconId = a.getResourceId(R.styleable.EntryItemParameter_icon, 0);
		summaryString = a.getString(R.styleable.EntryItemParameter_summary);
		
		// Set the layout according to the getting values
		mTitleView.setText(titleString);
		mImageView.setBackgroundResource(iconId);
		mSubSummaryView.setText(summaryString);

	}
	
	private void initial(Context context){
		
		LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		inflater.inflate(R.layout.list_view_item, this, true);
		//adapter
    	ViewAdapter1080P.adapter(this, context);
    	
		mImageView=(ImageView)findViewById(R.id.markicon);
		mTitleView=(TextView)findViewById(R.id.marktext);
		mSubSummaryView=(TextView)findViewById(R.id.summarytext);
		mRightSummaryView=(TextView)findViewById(R.id.righttext);
		
	}
	
	public void updateIcon(int id){
		mImageView.setBackgroundResource(id);
	}
	
	public void updateSubSummary(int id){
		mSubSummaryView.setText(id);
	}
	
	public void updateSubSummary(String str){
		mSubSummaryView.setText(str);
	}
	public void updateRightSummary(int id){
		mRightSummaryView.setText(id);
	}
	
	public void updateRightSummary(String str){
		mRightSummaryView.setText(str);
	}
}

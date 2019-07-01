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

import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

	private Context mContext;
	private List<ItemViewData> mSettingItmes;
	
	public ListViewAdapter(Context context,List<ItemViewData> paramItems){
		mContext = context;
		mSettingItmes=paramItems;
	}
	
	public void notifyDataChange(Context context ,List<ItemViewData> paramItems){
		mSettingItmes = paramItems;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mSettingItmes.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mSettingItmes.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder viewHolder;
		final ItemViewData itemData = mSettingItmes.get(position);
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView= new ListItemView(mContext);
			//convertView.setFocusable(true);
	        viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.markicon);
			viewHolder.mTitle = (TextView)convertView.findViewById(R.id.marktext);
			viewHolder.mSubSummary = (TextView)convertView.findViewById(R.id.summarytext);
			viewHolder.leftIcon=(ImageView)convertView.findViewById(R.id.lefticon);
			viewHolder.rightSummary= (TextView)convertView.findViewById(R.id.righttext);
			viewHolder.rightIcon=(ImageView)convertView.findViewById(R.id.righticon);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		//Set
		CustomLog.d("ListViewAdapter", itemData.toString());
		viewHolder.mImageView.setImageResource(itemData.getIconId());
		viewHolder.mTitle.setText(itemData.getTitle());
		viewHolder.mSubSummary.setText(itemData.getSubSummary());
		viewHolder.rightSummary.setText(itemData.getRightSummary());
		
		if(itemData.getIconId()==0){
			if(itemData.getIconDrawable()!=null){
				viewHolder.mImageView.setImageDrawable(itemData.getIconDrawable());
			}else{
				viewHolder.mImageView.setVisibility(View.GONE);
			}
		}else{
			viewHolder.mImageView.setVisibility(View.VISIBLE);
		}
		if(itemData.getSubSummary()!=null){
			viewHolder.mSubSummary.setVisibility(View.VISIBLE);
		}else{
			viewHolder.mSubSummary.setVisibility(View.GONE);
		}
		LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(100,LayoutParams.WRAP_CONTENT);
		params.gravity=Gravity.CENTER;
		
		if(itemData.getArrowShow()){
			viewHolder.leftIcon.setVisibility(View.VISIBLE);
			viewHolder.rightIcon.setVisibility(View.VISIBLE);
			viewHolder.leftIcon.setBackgroundResource(R.drawable.selector_listitem_left_nav);
			viewHolder.leftIcon.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v){
					itemData.callOnLeftKeyClick();
				}
			});
			viewHolder.rightIcon.setBackgroundResource(R.drawable.selector_listitem_right_nav);
			viewHolder.rightIcon.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v){
					itemData.callOnRightKeyClick();
				}
			});
			//Set right-summary witch 130dp
			int width = mContext.getResources().getDimensionPixelSize(R.dimen.list_view_right_summary_width);
			params.width=width;
			viewHolder.rightSummary.setLayoutParams(params);
			//Set right-summary size
			viewHolder.rightSummary.setTextSize(25);
		}else if(itemData.getRightResourceId()>0){
			viewHolder.leftIcon.setVisibility(View.GONE);
			viewHolder.rightIcon.setVisibility(View.VISIBLE);
			viewHolder.rightIcon.setBackgroundResource(itemData.getRightResourceId());
			//Set right-summary witch content
			params.width=LayoutParams.WRAP_CONTENT;
			viewHolder.rightSummary.setLayoutParams(params);
			//Set right-summary size
			viewHolder.rightSummary.setTextSize(20);
		}else{
			viewHolder.leftIcon.setVisibility(View.GONE);
			viewHolder.rightIcon.setVisibility(View.GONE);
			//Set right-summary witch content
			params.width=LayoutParams.WRAP_CONTENT;
			viewHolder.rightSummary.setLayoutParams(params);
			//Set right-summary size
			viewHolder.rightSummary.setTextSize(20);
		}
		if(itemData.getReadOnly()){
			viewHolder.mTitle.setTextColor(UtilFunctions.COLOR_READONLY);
		}else{
			viewHolder.mTitle.setTextColor(UtilFunctions.COLOR_NORMAL);
			viewHolder.rightSummary.setTextColor(UtilFunctions.COLOR_NORMAL);
		}
		if(itemData.getRightIconRotateState()){
			//rotate
			 Animation operatingAnim = AnimationUtils.loadAnimation(mContext, R.anim.rotate);
			 viewHolder.rightIcon.startAnimation(operatingAnim);
		}else{
			viewHolder.rightIcon.clearAnimation();
		}
		
		return convertView;
	}
	
	private class ViewHolder{
		public ImageView mImageView;
		public TextView mTitle;
		public TextView mSubSummary;
		public ImageView leftIcon;
		public TextView rightSummary;
		public ImageView rightIcon;
	}
}

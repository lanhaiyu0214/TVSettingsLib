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

import java.util.ArrayList;
import java.util.List;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.TextView;

public class TVListActivity extends Activity implements OnItemClickListener,OnItemLongClickListener{
	private String TAG="TVListFragment";
	
	private TextView titleView=null;
	private ListView listView=null;
	
	private ListViewAdapter adapter=null;
	private List<ItemViewData> subItems;
	private int select_position=0;
	
	private View lastView;
	
	private AnimatorSet mAnimator;
	private final float ITEM_ZOOM_VALUE = 1.02f;
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        CustomLog.d(TAG, "onCreate()");
        onCreateView();
    }
	
	/*
	 * Children class should override this ,to init items.
	 */
	public void initTitleAndItems(){
		
	}
	
	public void onCreateView() {
		CustomLog.d(TAG, "onCreateView()");
		View view = LayoutInflater.from(this).inflate(R.layout.fragment_main,
				null);
		setContentView(view);
		initViews(view);
		subItems = new ArrayList<ItemViewData>();
		adapter = new ListViewAdapter(this, subItems);
		listView.setAdapter(adapter);
		initTitleAndItems();

	}
    
    private void initViews(View rootview){
    	//adapter
    	ViewAdapter1080P.adapter(rootview, this);
    	titleView=(TextView)rootview.findViewById(R.id.title);
    	//titleView.setText(R.string.network_setttings);
    	//selectedBorder=(ImageView)rootview.findViewById(R.id.selectedBorder);
    	listView=(ListView)rootview.findViewById(R.id.listview);
    	listView.setOnItemClickListener(this);
    	listView.setOnItemLongClickListener(this);
    	listView.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int position, long arg3) {
				CustomLog.d(TAG, "onItemSelected position= " + position);
				if(lastView!=null){
					//zoom out
					ObjectAnimator zoomOutX = ObjectAnimator.ofFloat(lastView, "scaleX", ITEM_ZOOM_VALUE, 1f);
					ObjectAnimator zoomOutY = ObjectAnimator.ofFloat(lastView, "scaleY", ITEM_ZOOM_VALUE, 1f);
					
					mAnimator = new AnimatorSet();
			        mAnimator.playTogether(zoomOutX, zoomOutY);
			        mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
			        mAnimator.setDuration(200);
			        mAnimator.start();
				}
				
				// zoomin
				ObjectAnimator zoomInX = ObjectAnimator.ofFloat(view, "scaleX", 1f, ITEM_ZOOM_VALUE);  
				ObjectAnimator zoomInY = ObjectAnimator.ofFloat(view, "scaleY", 1f, ITEM_ZOOM_VALUE);  
				
				mAnimator = new AnimatorSet();
		        mAnimator.playTogether(zoomInX, zoomInY);
		        mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
		        mAnimator.setDuration(200);
		        mAnimator.start();
		        
		        lastView = view;
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    	listView.setOnScrollListener(mOnScrollListener);
    	listView.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View arg0, int keycode, KeyEvent event) {
				// TODO Auto-generated method stub
				//Log.d("onKey", "onKey event.getAction()="+event.getAction());
				//Log.d("onKey", "onKey keycode="+keycode);
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					ItemViewData clickdata = null;
					try {
						 clickdata=subItems.get(select_position);
					} catch (Exception e) {
						// TODO: handle exception
					}
					switch (keycode) {
					case KeyEvent.KEYCODE_DPAD_LEFT:
						if(clickdata != null)
						clickdata.callOnLeftKeyClick();
						break;
					case KeyEvent.KEYCODE_DPAD_RIGHT:
						if(clickdata != null)
						clickdata.callOnRightKeyClick();
						break;
					default:
						
						break;
					}
					
					
				}
				return false;
			}
		});
    	
    }
    private OnScrollListener mOnScrollListener = new OnScrollListener() {

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

		switch (scrollState) {
		case OnScrollListener.SCROLL_STATE_IDLE:
			// static
			break;
		case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
			//start to scroll
			//selectedBorder.setVisibility(View.GONE);
			break;
		case OnScrollListener.SCROLL_STATE_FLING:
			//flying
			break;	
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		
	}
};
    /*public void initData(){
    	
    }*/
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    	ItemViewData clickdata=subItems.get(position);
    	clickdata.callOnClick();
    }
	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		ItemViewData clickdata=subItems.get(position);
    	clickdata.callOnLongClick();
		return true;
	}
	 
	/*
	 * Interface for children class
	 */
	
	public void setTitle(int resid){
		titleView.setText(resid);
	}
	
	public void setTitle(String str){
		titleView.setText(str);
	}
	
	public void addItem(ItemViewData item){
		subItems.add(item);
	}
	
	public void addItem(int index ,ItemViewData item){
		subItems.add(index,item);
	}
	
	public void removeItem(ItemViewData item){
		subItems.remove(item);
	}
	
	public void clearItems(){
		subItems.clear();
	}
	
	public int getLength(){
		return subItems.size();
	}
	
	public ItemViewData getItemAtPositon(int position){
		return subItems.get(position);
	}
	
	public void setSelection(int position){
		listView.setSelection(position);
	}
	
	public void onDataChange(){
    	if(adapter!=null){
    		adapter.notifyDataSetChanged();
    	}
    }
	
	
   
}

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
package com.appo.tvsettings.lib.demo;

import com.appo.tvsettings.lib.CustomLog;
import com.appo.tvsettings.lib.R;
import com.appo.tvsettings.lib.ItemViewData;
import com.appo.tvsettings.lib.ItemViewData.onClickListener;
import com.appo.tvsettings.lib.TVListFragment;

public class DemoFragment extends TVListFragment {
	private String TAG="DemoSettingFragment";
	
	private DemoToogleItem item1=null;
	private ItemViewData item2 =null;
	private ItemViewData item3 =null;
	private ItemViewData item4 =null;
	private ItemViewData item5 =null;
	@Override
	public void initTitleAndItems(){
		// TODO Auto-generated method stub
		CustomLog.d(TAG, "onCreateViewFinish()");
		setTitle(R.string.projection_settings_title);
		//item1
		item1=new DemoToogleItem(this.getActivity());
		//item2
		item2 = new ItemViewData(R.drawable.ic_launcher,"item2", "subsammury", "right");
		item2.setOnClickListener(new onClickListener(){
			public void onClick(){
				
			}
		});
		//item3
		item3 = new ItemViewData(0, "item3" , "3summary", R.drawable.ic_launcher);
		item3.setOnClickListener(new onClickListener(){
			public void onClick(){
				
			}
		});
		//item4
		item4 = new ItemViewData(0, "item4" , "4summary", R.drawable.ic_launcher);
		item4.setOnClickListener(new onClickListener(){
			public void onClick(){
				
			}
		});
		item4.setRightIconRotateState(true);
		//item5
		item5 = new ItemViewData(0, "item5" , "5summary", R.drawable.ic_launcher);
		item5.setRightSummary("newSummary");
		item5.setOnClickListener(new onClickListener(){
			public void onClick(){
				
			}
		});
		addItem(item1);
		addItem(item2);
		addItem(item3);
		addItem(item4);
		addItem(item5);
		onDataChange();
		
		
	}
	
			

	
}

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

public class SimulateFragment extends TVListFragment {
	private String TAG="SimulateFragment";
	
	private ItemViewData item1=null;
	private ItemViewData item2 =null;
	private ItemViewData item3 =null;
	private ItemViewData item4 =null;
	private ItemViewData item5 =null;
	@Override
	public void initTitleAndItems(){
		// TODO Auto-generated method stub
		CustomLog.d(TAG, "initTitleAndItems()");
		setTitle("Please choose a language");
		//item1
		item1 = new ItemViewData();
		item1.setTitle("����");
		//item2
		item2 = new ItemViewData();
		item2.setTitle("English");
		item2.setRightResourceId(R.drawable.icon_sel);
		item2.setOnClickListener(new onClickListener(){
			public void onClick(){
				
			}
		});
		item3 = new ItemViewData();
		item3.setTitle("�ձ���");
		item4 = new ItemViewData();
		item4.setTitle("Deutsch");
		item5 = new ItemViewData();
		item5.setTitle("Deutsch");
		addItem(item1);
		addItem(item2);
		addItem(item3);
		addItem(item4);
		addItem(item5);
		onDataChange();
		
		
	}
	
			

	
}

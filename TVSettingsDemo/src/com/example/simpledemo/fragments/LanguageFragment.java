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
package com.example.simpledemo.fragments;

import com.appo.tvsettings.lib.ItemViewData;
import com.appo.tvsettings.lib.ItemViewData.onClickListener;
import com.appo.tvsettings.lib.R;
import com.appo.tvsettings.lib.TVListFragment;
import com.example.simpledemo.Util;

public class LanguageFragment extends TVListFragment {
	private String TAG="SimulateFragment";
	
	private ItemViewData item1=null;
	private ItemViewData item2 =null;
	private ItemViewData item3 =null;
	private ItemViewData item4 =null;
	@Override
	public void initTitleAndItems(){
		// TODO Auto-generated method stub
		setTitle("Please choose a language");
		//item1
		item1 = new ItemViewData();
		item1.setTitle("Chinese");
		item1.setOnClickListener(new onClickListener(){
			public void onClick(){
				Util.showText(getContext(), "Item1 is clicked!");
				resetIcon();
				item1.setRightResourceId(R.drawable.icon_sel);
				onDataChange();
			}
		});
		//item2
		item2 = new ItemViewData();
		item2.setTitle("English");
		item2.setRightResourceId(R.drawable.icon_sel);
		item2.setOnClickListener(new onClickListener(){
			public void onClick(){
				Util.showText(getContext(), "Item2 is clicked!");
				resetIcon();
				item2.setRightResourceId(R.drawable.icon_sel);
				onDataChange();
			}
		});
		item3 = new ItemViewData();
		item3.setTitle("Deutsch");
		item3.setOnClickListener(new onClickListener(){
			public void onClick(){
				Util.showText(getContext(), "Item3 is clicked!");
				resetIcon();
				item3.setRightResourceId(R.drawable.icon_sel);
				onDataChange();
			}
		});
		item4 = new ItemViewData();
		item4.setTitle("France");
		item4.setOnClickListener(new onClickListener(){
			public void onClick(){
				Util.showText(getContext(), "Item4 is clicked!");
				resetIcon();
				item4.setRightResourceId(R.drawable.icon_sel);
				onDataChange();
			}
		});
		addItem(item1);
		addItem(item2);
		addItem(item3);
		addItem(item4);
		onDataChange();
		
	}
	
	private	void resetIcon(){
		item1.setRightResourceId(0);
		item2.setRightResourceId(0);
		item3.setRightResourceId(0);
		item4.setRightResourceId(0);
	}

	
}

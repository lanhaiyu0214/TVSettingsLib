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
package com.example.simpledemo;

import android.content.Intent;
import android.os.Bundle;

import com.appo.tvsettings.lib.ItemViewData;
import com.appo.tvsettings.lib.ItemViewData.onClickListener;
import com.appo.tvsettings.lib.R;
import com.appo.tvsettings.lib.TVListActivity;

public class EntryActivity extends TVListActivity {

	// Items
	private ItemViewData useLibActivityItem;
	private ItemViewData useLibFragmentItem;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public void initTitleAndItems() {
		setTitle("Main page");
		initData();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	private void initData() {
		clearItems();
		useLibActivityItem = new ItemViewData(R.drawable.ic_launcher, "Extends Activity-Lib", "summury 1", null);
		useLibActivityItem.setOnClickListener(new onClickListener() {

			@Override
			public void onClick() {
				startActivity(new Intent(EntryActivity.this, ExtendsLibActivity.class));
			}
		});

		useLibFragmentItem = new ItemViewData(R.drawable.ic_launcher, "Use fragment-Lib","summury 2", null);
		useLibFragmentItem.setOnClickListener(new onClickListener() {

			@Override
			public void onClick() {
				startActivity(new Intent(EntryActivity.this, UseLibFragment.class));
			}
		});

		addItem(useLibActivityItem);
		addItem(useLibFragmentItem);

		onDataChange();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}

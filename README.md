# TVSettingsLib
This lib can help you finsh the UI of TV Settings easily. And it is a little pretty.

#### What does the lib can do ?
This lib can help you finsh the UI of TV Settings easily. And it is a little pretty.

#### How to use it?？？
   1. Import this project to eclips
   2. Compile it 
   3. reference this lib
   4. Example code:
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

import android.os.Bundle;

import com.appo.tvsettings.lib.ItemViewData;
import com.appo.tvsettings.lib.ItemViewData.onClickListener;
import com.appo.tvsettings.lib.R;
import com.appo.tvsettings.lib.TVListActivity;

public class NetWorkSettingsActivity extends TVListActivity {
	
	// Items
	private ItemViewData wifiItem;
	private ItemViewData wifiApItem;
	private ItemViewData bluetoothItem;
	private ItemViewData vpnItem;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public void initTitleAndItems(){
		setTitle(R.string.network_setttings);
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
		wifiItem = new ItemViewData(R.drawable.ic_launcher, getString(R.string.network_setttings_wireless), getString(R.string.network_setttings_wireless_summary), null);
		wifiItem.setOnClickListener(new onClickListener() {

			@Override
			public void onClick() {
				
			}
		});

		wifiApItem = new ItemViewData(R.drawable.ic_launcher, getString(R.string.network_setttings_wlan), getString(R.string.network_setttings_wlan_summary), null);
		wifiApItem.setOnClickListener(new onClickListener() {

			@Override
			public void onClick() {
				
			}
		});

		bluetoothItem = new ItemViewData(R.drawable.ic_launcher, getString(R.string.network_setttings_bluetooth), getString(R.string.network_setttings_bluetooth_summary), null);
		bluetoothItem.setOnClickListener(new onClickListener() {
			public void onClick() {
				
			}
		});
		
		vpnItem=new ItemViewData(R.drawable.ic_launcher,
    			getString(R.string.network_settings_vpn_tile),
    			null,
    			null);
    	vpnItem.setOnClickListener(new onClickListener(){
    		public void onClick(){
    			
    		}
    	});
    	addItem(wifiItem);
		addItem(wifiApItem);
		addItem(bluetoothItem);
		addItem(vpnItem);
		onDataChange();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
#### license.
Appotronics Lanhaiyu

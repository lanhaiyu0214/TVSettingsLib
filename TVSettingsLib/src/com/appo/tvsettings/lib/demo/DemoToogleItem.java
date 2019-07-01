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

import android.content.Context;

import com.appo.tvsettings.lib.AToggleItem;
import com.appo.tvsettings.lib.R;

public class DemoToogleItem extends AToggleItem {
	
	public DemoToogleItem(Context c) {
		// TODO Auto-generated constructor stub
		super(c);
		//Tile
		setTitle(c.getResources().getString(R.string.projector_brightness));
		//Title summary
		setSubSummary("YYY");
		//Right summary
		setRightSummary("xxx");
		
	}
	
	@Override
	public void toggleNegative() {
		
	}
	
	@Override
	public void togglePositive() {
		
	}
	
	

}

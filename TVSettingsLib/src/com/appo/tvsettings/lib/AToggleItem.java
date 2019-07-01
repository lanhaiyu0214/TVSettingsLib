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

public class AToggleItem extends ItemViewData {
	
	public AToggleItem(Context c) {
		// TODO Auto-generated constructor stub

		setArrowShow(true);
		setOnLeftKeyClickListener(new onLeftKeyClickListener(){
			public void onClick(){
					toggleNegative();
			}
		});
		
		setOnRightKeyClickListener(new onRightKeyClickListener(){
			public void onClick(){
					togglePositive();
			}
		});
		
	}
	
	
	public void toggleNegative() {
		//do children stuff
		
	}

	public void togglePositive() {
		//do children stuff
		
	}

	
}

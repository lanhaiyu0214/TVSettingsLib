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
import android.view.View;

public class ViewAdapter1080P {
	
	public static void adapter(View view ,Context context){
		ScreenAdapterUtils SAdapterUtils = ScreenAdapterUtils.getInstance(context, 1920, 1080);
		SAdapterUtils.adapterViewByWidth(view);
	}
	
}

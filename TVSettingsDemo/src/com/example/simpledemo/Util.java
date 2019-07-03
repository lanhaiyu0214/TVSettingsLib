package com.example.simpledemo; 

import android.content.Context;
import android.widget.Toast;
/*
 * Copyright (C) Appotronics
 * Author name:
 *		Lanhaiyu
 * Author Email:
 *      hylan@appotronics.cn
 * Create Time:
 * 		2019年7月3日 下午1:22:31 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
public class Util {
	
	public static void showText(Context c, String msg){
		Toast.makeText(c, msg, Toast.LENGTH_SHORT).show();
	}
}
 
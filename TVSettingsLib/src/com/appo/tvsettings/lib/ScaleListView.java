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
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

public class ScaleListView extends ListView {
	private final String TAG = "ScaleListView";
	
    private View selectedItem;
    private int selectIndex;
    public ScaleListView(Context context) {
        super(context);
        init();
    }

    public ScaleListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScaleListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        //��������ͼ������
        setChildrenDrawingOrderEnabled(true);
    }

    @Override
    public void onDraw(Canvas c) {
    	CustomLog.d(TAG, "onDraw()");
    	selectedItem = getSelectedView();
    	
        super.onDraw(c);
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
    	//CustomLog.d(TAG, "getChildDrawingOrder () childCount =" + childCount + ", i =" + i);
    	int result= i;
        View view =this.getChildAt(i);
        if(view == selectedItem){
        	result = childCount-1;
        	selectIndex = i;
        }else{
        	if (i == childCount - 1) {
        		result = selectIndex;
        	}
        }
        //CustomLog.d(TAG, "getChildDrawingOrder () result ="  + result);
        return result;
    }
    
}
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
import android.widget.RelativeLayout;

public class BorderRelativeLayout extends RelativeLayout {
    private final String TAG = "DrawBorderRelativeLayout";
    private BorderPainter mBorderPainter;

    public BorderRelativeLayout(Context context) {
        super(context);
    }

    public BorderRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBorderPainter = new BorderPainter(this, R.drawable.selected);

    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
    	//CustomLog.d(TAG, "onMeasure()");
        super.onMeasure(widthSpec, heightSpec);
        
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        CustomLog.d(TAG, "dispatchDraw()");
        super.dispatchDraw(canvas);
        mBorderPainter.draw(canvas);
    }

    public void onChildrenViewFocus(View v){
    	mBorderPainter.setView(v);
    }
    
    
}

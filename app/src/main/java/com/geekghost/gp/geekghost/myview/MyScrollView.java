package com.geekghost.gp.geekghost.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 作者：戈鹏
 * on 2018/1/1 19:09
 */

public class MyScrollView extends ScrollView{

    private OnScrollListener onScrollListener;

    public interface OnScrollListener{
        void onScrollListener(ScrollView who,int t);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener){
        this.onScrollListener=onScrollListener;
    }

    public MyScrollView(Context context) {
        this(context,null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        //button1.getBackground().setAlpha(128)。setAlpha()
        if(onScrollListener!=null){
            onScrollListener.onScrollListener(this,t);
        }

        super.onScrollChanged(l, t, oldl, oldt);
    }
}

package com.geekghost.gp.geekghost.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：戈鹏
 * on 2018/1/2 10:26
 */

public abstract class BaseFregment extends Fragment {

    protected View rootView;
    protected Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayout(), container, false);
        }
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }


    protected void initView() {
    }

    protected abstract int getLayout();



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder!=null){
            unbinder.unbind();
        }

    }


}

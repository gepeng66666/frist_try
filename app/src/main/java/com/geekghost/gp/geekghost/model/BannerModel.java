package com.geekghost.gp.geekghost.model;

import com.geekghost.gp.geekghost.entity.ChildListBean;
import com.geekghost.gp.geekghost.entity.ListBean;
import com.geekghost.gp.geekghost.entity.MessageBean;
import com.geekghost.gp.geekghost.entity.RetBean;
import com.geekghost.gp.geekghost.http.RetrofitUtils;
import com.geekghost.gp.geekghost.presenter.BannerPresenter;

import io.reactivex.Flowable;

/**
 * 作者：戈鹏
 * on 2018/1/3 09:21
 */

public class BannerModel implements IBannerModel{

    private BannerPresenter presenter;

    public BannerModel(BannerPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getData() {
        Flowable<MessageBean<RetBean<ListBean<ChildListBean>>>> flowable = RetrofitUtils.getInstance().getApiService().getBanner();
        presenter.getBanner(flowable);
    }
}

package com.geekghost.gp.geekghost.model;

import com.geekghost.gp.geekghost.entity.MessageBean;
import com.geekghost.gp.geekghost.entity.RetBean;
import com.geekghost.gp.geekghost.entity.SpecialListBean;
import com.geekghost.gp.geekghost.http.RetrofitUtils;
import com.geekghost.gp.geekghost.presenter.SpecialPresenter;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * 作者：戈鹏
 * on 2018/1/9 10:00
 */

public class SpecialModel  implements IMovieModel{
    private SpecialPresenter presenter;

    public SpecialModel(SpecialPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getData(Map<String, String> map) {
        Flowable<MessageBean<RetBean<SpecialListBean>>> special = RetrofitUtils.getInstance().getApiService().getSpecial(map);
        presenter.getSpecial(special);
    }

}

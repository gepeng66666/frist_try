package com.geekghost.gp.geekghost.model;

import com.geekghost.gp.geekghost.entity.CommentListBean;
import com.geekghost.gp.geekghost.entity.MessageBean;
import com.geekghost.gp.geekghost.entity.RetBean;
import com.geekghost.gp.geekghost.http.RetrofitUtils;
import com.geekghost.gp.geekghost.presenter.CommentPresenter;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * 作者：戈鹏
 * on 2018/1/4 09:24
 */

public class CommentModel implements IMovieModel{
    private CommentPresenter presenter;

    public CommentModel(CommentPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getData(Map<String, String> map) {
        Flowable<MessageBean<RetBean<CommentListBean>>> comment = RetrofitUtils.getInstance().getApiService().getComment(map);
        presenter.getComment(comment);
    }

}

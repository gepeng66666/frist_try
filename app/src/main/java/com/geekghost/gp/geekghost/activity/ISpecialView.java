package com.geekghost.gp.geekghost.activity;

import com.geekghost.gp.geekghost.entity.SpecialListBean;

import java.util.List;

/**
 * 作者：戈鹏
 * on 2018/1/2 21:49
 */

public interface ISpecialView {
    void onSpecialSuccess(List<SpecialListBean> list);
    void onSpecialFailed(Exception e);
}

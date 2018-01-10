package com.geekghost.gp.geekghost.frag.interfaces;

import com.geekghost.gp.geekghost.entity.ChildListBean;
import com.geekghost.gp.geekghost.entity.ListBean;

import java.util.List;

/**
 * 作者：戈鹏
 * on 2018/1/2 21:49
 */

public interface IBannerView {
    void OnSuccess(List<ListBean<ChildListBean>> list);
    void OnFailed(Exception e);
}

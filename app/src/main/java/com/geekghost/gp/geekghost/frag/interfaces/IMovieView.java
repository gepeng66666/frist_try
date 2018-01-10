package com.geekghost.gp.geekghost.frag.interfaces;

import com.geekghost.gp.geekghost.entity.ChildListBean;
import com.geekghost.gp.geekghost.entity.ListBean;
import com.geekghost.gp.geekghost.entity.MovieRetBean;

/**
 * 作者：戈鹏
 * on 2018/1/2 21:49
 */

public interface IMovieView {
    void OnSuccess(MovieRetBean<ListBean<ChildListBean>> ret);
    void OnFailed(Exception e);
}

package com.geekghost.gp.geekghost.frag.interfaces;

import com.geekghost.gp.geekghost.entity.CommentListBean;

import java.util.List;

/**
 * 作者：戈鹏
 * on 2018/1/2 21:49
 */

public interface ICommentView {
    void onCommentSuccess(List<CommentListBean> commentList);
    void onCommentFailed(Exception e);
}

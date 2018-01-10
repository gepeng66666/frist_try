package com.geekghost.gp.geekghost.frag.videofrag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.geekghost.gp.geekghost.Adapter.CommentRvAdapter;
import com.geekghost.gp.geekghost.Base.BaseFregment;
import com.geekghost.gp.geekghost.R;
import com.geekghost.gp.geekghost.entity.CommentListBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：戈鹏
 * on 2018/1/5 08:20
 * 电影详情页  评论 fragment
 */

public class CommentFrag extends BaseFregment {


    @BindView(R.id.comment_rv)
    RecyclerView commentRv;

    List<CommentListBean> list=new ArrayList<>();
    private CommentRvAdapter commentRvAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //注册EventBus
        EventBus.getDefault().register(this);

    }

    @Override
    protected void initView() {
        commentRvAdapter = new CommentRvAdapter(getActivity(), list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        commentRv.setLayoutManager(manager);
        commentRv.setAdapter(commentRvAdapter);
    }

    // //加载布局
    @Override
    protected int getLayout() {
        return R.layout.frag_comment;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void ononMoonStickyEvent(List<CommentListBean> commentList) {
        list.clear();
        String msg = commentList.get(0).getMsg();
        list.addAll(commentList);
        commentRvAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        //SwipeBackHelper.onDestroy(getActivity());
    }

}

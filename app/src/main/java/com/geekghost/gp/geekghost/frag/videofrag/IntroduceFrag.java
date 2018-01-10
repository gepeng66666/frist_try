package com.geekghost.gp.geekghost.frag.videofrag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.geekghost.gp.geekghost.Adapter.FoundRvAdapter;
import com.geekghost.gp.geekghost.Base.BaseFregment;
import com.geekghost.gp.geekghost.R;
import com.geekghost.gp.geekghost.activity.VideoInfoActivity;
import com.geekghost.gp.geekghost.entity.ChildListBean;
import com.geekghost.gp.geekghost.entity.ListBean;
import com.geekghost.gp.geekghost.entity.MovieRetBean;
import com.jude.swipbackhelper.SwipeBackHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：戈鹏
 * on 2018/1/5 08:11
 * 电影详情页简介fragment
 */

public class IntroduceFrag extends BaseFregment {

    @BindView(R.id.introduce_tv)
    TextView introduceTv;
    @BindView(R.id.open_tv)
    TextView openTv;
    @BindView(R.id.introduce_rv)
    RecyclerView introduceRv;

    boolean flag=true;
    private FoundRvAdapter foundRvAdapter;
    private List<ChildListBean> childList=new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //注册EventBus
        EventBus.getDefault().register(this);
        SwipeBackHelper.onCreate(getActivity());

        foundRvAdapter = new FoundRvAdapter(getActivity(), childList);
        //LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
        introduceRv.setLayoutManager(manager);
        introduceRv.setAdapter(foundRvAdapter);

        foundRvAdapter.setOnFoundRvClick(new FoundRvAdapter.onFoundRvClick() {
            @Override
            public void onFoundRvClick(int position) {
                //Toast.makeText(getActivity(),"gg"+position,Toast.LENGTH_SHORT).show();
                String dataId = childList.get(position).getDataId();
                String title = childList.get(position).getTitle();
                String pic = childList.get(position).getPic();
                Intent intent=new Intent(getActivity(), VideoInfoActivity.class);
                intent.putExtra("id",dataId);
                intent.putExtra("name",title);
                intent.putExtra("pic",pic);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
                getActivity().finish();
            }
        });
    }


    // //加载布局
    @Override
    protected int getLayout() {
        return R.layout.frag_introduce;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void ononMoonStickyEvent(MovieRetBean<ListBean<ChildListBean>> ret) {

        String des = "导演：" + ret.getDirector() + "\n" +
                "主演：" + ret.getActors() + "\n" +
                "简介：" + ret.getDescription();
        introduceTv.setText(des);

        setheight();
        //List<ChildListBean> childList = ret.getList().get(0).getChildList();
        List<ListBean<ChildListBean>> list = ret.getList();
        int size = list.size();
        List<ChildListBean> childList =null;
        if(size>1){
            childList=list.get(1).getChildList();
        }else {
            childList=list.get(0).getChildList();
        }

        this.childList.clear();
        this.childList.addAll(childList);
        foundRvAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.open_tv)
    public void onViewClicked() {
        if(flag){
            openTv.setText("收起");
        }else{
            openTv.setText("展开");
        }
        flag=!flag;
        setheight();
    }

    /**
     * 设置电影介绍的展开收回高度
     */
    private void setheight() {
        if(flag){
            //final LayoutParams lp = introduceTv.getLayoutParams();
            /*ViewGroup.LayoutParams lp = introduceTv.getLayoutParams();
            lp.width = ActionBar.LayoutParams.MATCH_PARENT;
            lp.height=78;//lp.height=LayoutParams.WRAP_CONTENT;
            introduceTv.setLayoutParams(lp);*/
            //introduceTv.setSingleLine(true);
            introduceTv.setLines(3);
            introduceTv.setEllipsize(TextUtils.TruncateAt.END);
        }else{
            //final LayoutParams lp = introduceTv.getLayoutParams();
            /*ViewGroup.LayoutParams lp = introduceTv.getLayoutParams();
            lp.width = ActionBar.LayoutParams.MATCH_PARENT;
            lp.height= ViewGroup.LayoutParams.WRAP_CONTENT;//lp.height=LayoutParams.WRAP_CONTENT;
            introduceTv.setLayoutParams(lp);*/
            introduceTv.setSingleLine(false);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        //SwipeBackHelper.onDestroy(getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        SwipeBackHelper.onPostCreate(getActivity());
    }
}

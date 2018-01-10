package com.geekghost.gp.geekghost.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geekghost.gp.geekghost.Adapter.EndLessOnScrollListener;
import com.geekghost.gp.geekghost.Adapter.SpecialRvAdapter2;
import com.geekghost.gp.geekghost.Base.BaseActivity;
import com.geekghost.gp.geekghost.R;
import com.geekghost.gp.geekghost.entity.SpecialListBean;
import com.geekghost.gp.geekghost.presenter.SpecialPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpecialListActivity extends BaseActivity implements ISpecialView {

    @BindView(R.id.special_list_rv)
    RecyclerView specialListRv;
    @BindView(R.id.special_list_swipe)
    SwipeRefreshLayout swiper;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.title_name)
    TextView titleName;

    private SpecialPresenter presenter;
    int pnum=1;
    List<SpecialListBean>  specialList = new ArrayList<>();
    private SpecialRvAdapter2 specialRvAdapter;
    private String catalogId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        catalogId = intent.getStringExtra("catalogId");
        String title = intent.getStringExtra("title");

        //Toast.makeText(this,catalogId,Toast.LENGTH_SHORT).show();
        rlBack.setVisibility(View.VISIBLE);
        titleName.setText(title);

        //设置适配器
        specialRvAdapter = new SpecialRvAdapter2(this,specialList);
        //网格布局
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        specialListRv.setLayoutManager(manager);
        specialListRv.setAdapter(specialRvAdapter);


        //请求网络
        presenter = new SpecialPresenter();
        presenter.attachView(this);
        Map<String,String> map=new HashMap<>();
        map.put("catalogId", catalogId);
        map.put("pnum",pnum+"");
        presenter.getData(map);


        //下拉刷新  TODO 需要手动关闭刷新
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新事件
                //presenter.getData();
                pnum=1;
                Map<String,String> map=new HashMap<>();
                map.put("catalogId", catalogId);
                map.put("pnum",pnum+"");
                presenter.getData(map);
            }
        });



        //上拉加载
        specialListRv.addOnScrollListener(new EndLessOnScrollListener(manager) {
            @Override
            public void onLoadMore(int currentPage) {
                pnum=pnum++;
                Map<String,String> map=new HashMap<>();
                map.put("catalogId", catalogId);
                map.put("pnum",pnum+"");
                presenter.getData(map);
            }
        });


    }





    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        finish();
        overridePendingTransition(R.anim.tran_in2, R.anim.tran_out2);
    }



    @Override
    public void onSpecialSuccess(List<SpecialListBean> list) {
        if(list!=null){
            if(pnum==1&&specialList.size()>0){
                specialList.clear();
            }
            specialList.addAll(list);
            specialRvAdapter.notifyDataSetChanged();
        }
        stopRefreshing();
    }

    @Override
    public void onSpecialFailed(Exception e) {
        Toast.makeText(SpecialListActivity.this,"无网络",Toast.LENGTH_SHORT).show();
        stopRefreshing();
    }

    /**
     * 停止下拉刷新
     */
    public void stopRefreshing() {
        if (swiper.isRefreshing()) {
            swiper.setRefreshing(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.detachView();
        }
    }


}

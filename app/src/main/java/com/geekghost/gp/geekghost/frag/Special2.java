package com.geekghost.gp.geekghost.frag;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.geekghost.gp.geekghost.Adapter.SpecialRvAdapter;
import com.geekghost.gp.geekghost.Base.BaseFregment;
import com.geekghost.gp.geekghost.R;
import com.geekghost.gp.geekghost.entity.ChildListBean;
import com.geekghost.gp.geekghost.entity.ListBean;
import com.geekghost.gp.geekghost.frag.interfaces.IBannerView;
import com.geekghost.gp.geekghost.presenter.BannerPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：戈鹏
 * on 2018/1/1 20:16
 */

public class Special2 extends BaseFregment implements IBannerView {

    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.special_rv)
    RecyclerView specialRv;
    @BindView(R.id.special_swipe_refresh)
    SwipeRefreshLayout swiper;

    private BannerPresenter presenter;

    List<ListBean<ChildListBean>> specialList=new ArrayList<>();
    private SpecialRvAdapter specialRvAdapter;


   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }*/

    @Override
    protected void initView() {
        titleName.setText("专题");

        specialRvAdapter = new SpecialRvAdapter(getActivity(), specialList);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        specialRv.setLayoutManager(manager);
        specialRv.setAdapter(specialRvAdapter);

        initNet();

        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                specialList.clear();
                //下拉刷新事件
                presenter.getData();
            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.frag_special2;
    }

    //请求数据
    private void initNet() {
        presenter = new BannerPresenter();
        presenter.attachView(this);
        presenter.getData();
    }

    @Override
    public void OnSuccess(List<ListBean<ChildListBean>> list) {
        if(list!=null){
            for (ListBean<ChildListBean> i : list) {
                String moreURL = i.getMoreURL();
                if(moreURL.equals("")||moreURL==null){

                }else{
                    specialList.add(i);
                }
            }

        }
        //刷新适配器
        specialRvAdapter.notifyDataSetChanged();
        stopRefreshing();
    }

    @Override
    public void OnFailed(Exception e) {
        Toast.makeText(getActivity(),"无网络",Toast.LENGTH_SHORT).show();
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
    public void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.detachView();
        }
    }
}

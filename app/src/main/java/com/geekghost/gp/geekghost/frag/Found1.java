package com.geekghost.gp.geekghost.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.geekghost.gp.geekghost.Adapter.FoundRvAdapter;
import com.geekghost.gp.geekghost.Base.BaseFregment;
import com.geekghost.gp.geekghost.R;
import com.geekghost.gp.geekghost.activity.VideoInfoActivity;
import com.geekghost.gp.geekghost.entity.ChildListBean;
import com.geekghost.gp.geekghost.entity.ListBean;
import com.geekghost.gp.geekghost.frag.interfaces.IBannerView;
import com.geekghost.gp.geekghost.myview.MyScrollView;
import com.geekghost.gp.geekghost.presenter.BannerPresenter;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：戈鹏
 * on 2018/1/1 20:16
 */

public class Found1 extends BaseFregment implements IBannerView{

    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.myScrollView)
    MyScrollView myScrollView;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.xbanner)
    XBanner xbanner;
    @BindView(R.id.found_rv)
    RecyclerView rv;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swiper;


    List<ChildListBean> bannerList=new ArrayList<>();
    List<String> images = new ArrayList<>();

    List<ChildListBean> niceMovie = new ArrayList<>();
    private FoundRvAdapter foundRvAdapter;
    private BannerPresenter presenter;

    boolean bannerflag=true;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //滑动监听
        ScrollListener();
        initNet();
        BannerListener();
        Refresh();

        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bannerList.clear();
                niceMovie.clear();
                images.clear();
                //下拉刷新事件
                presenter.getData();
            }
        });

        foundRvAdapter.setOnFoundRvClick(new FoundRvAdapter.onFoundRvClick() {
            @Override
            public void onFoundRvClick(int position) {
                String dataId = niceMovie.get(position).getDataId();
                String title = niceMovie.get(position).getTitle();
                String pic = niceMovie.get(position).getPic();
                Intent intent=new Intent(getActivity(), VideoInfoActivity.class);
                intent.putExtra("id",dataId);
                intent.putExtra("name",title);
                intent.putExtra("pic",pic);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
            }
        });

    }

    @Override
    protected void initView() {
        title.setVisibility(View.GONE);
        titleName.setText("精选");
        //RecyclerView
        foundRvAdapter = new FoundRvAdapter(getActivity(), niceMovie);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(manager);
        rv.setAdapter(foundRvAdapter);

        //swiperefreshlayout与scrollview冲突解决
        //在scrollview还没有到达第一条数据顶部的时候，就设置swipeRefreshLayout为不可操作状态，
        // 那么检测swipeRefreshLayout的滚动分发就不起作用了，就达到我们需要的目的了
        if (myScrollView != null) {
            myScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {
                    if (swiper != null) {
                        swiper.setEnabled(myScrollView.getScrollY() == 0);
                    }
                }
            });
        }
    }
    //加载布局
    @Override
    protected int getLayout() {
        return R.layout.frag_found1;
    }

    //滑动监听
    public void ScrollListener(){
        myScrollView.setOnScrollListener(new MyScrollView.OnScrollListener() {
            @Override
            public void onScrollListener(ScrollView who, int t) {
                if (t == 0) {
                    title.setVisibility(View.GONE);
                } else if (t > 0 && t <= 235) {
                    title.setVisibility(View.VISIBLE);
                    title.getBackground().setAlpha(t+20);
                }else{
                    title.getBackground().setAlpha(255);
                }

            }
        });
    }

    //Banner监听
    private void BannerListener() {
        xbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                //影片id
                //String dataId = bannerList.get(position).getDataId();
                String dataId = bannerList.get(position).getDataId();
                String title = bannerList.get(position).getTitle();
                String pic = bannerList.get(position).getPic();

                Intent intent=new Intent(getActivity(), VideoInfoActivity.class);
                intent.putExtra("id",dataId);
                intent.putExtra("name",title);
                intent.putExtra("pic",pic);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.tran_in, R.anim.tran_out);

            }
        });
    }

    //swiper
    private void Refresh() {

    }

    /**
     * @param list
     *
     * Banner  请求成功回调接口
     */
    @Override
    public void OnSuccess(List<ListBean<ChildListBean>> list) {
       // Log.e("Gepeng", "OnSuccess: ");
        if(list!=null){
            List<ChildListBean> childList = list.get(0).getChildList();
            for (ChildListBean i : childList) {
                String angleIcon = i.getPic();
                String dataId = i.getDataId();
                if(dataId.equals("")||dataId==null){

                }else{
                    images.add(angleIcon);
                    bannerList.add(i);
                }
            }
            //bannerList.addAll(childList);
            //banner下方数据
            List<ChildListBean> childList1 = list.get(3).getChildList();
            niceMovie.addAll(childList1);
        }
        //刷新适配器
        foundRvAdapter.notifyDataSetChanged();
        if(bannerflag){
            xbanner.setData(images,null);
            // XBanner适配数据
            xbanner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, View view, int position) {
                    Glide.with(getActivity()).load(images.get(position)).into((ImageView) view);
                }
            });
            bannerflag=!bannerflag;
        }
        stopRefreshing();
    }

    /**
     * @param e
     *
     * Banner  请求失败回调接口
     */
    @Override
    public void OnFailed(Exception e) {
        Toast.makeText(getActivity(),"无网络",Toast.LENGTH_SHORT).show();
        stopRefreshing();
    }



    @Override
    public void onResume() {
        super.onResume();
        xbanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        xbanner.stopAutoPlay();
    }

    //请求数据
    private void initNet() {
        presenter = new BannerPresenter();
        presenter.attachView(this);
        presenter.getData();
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

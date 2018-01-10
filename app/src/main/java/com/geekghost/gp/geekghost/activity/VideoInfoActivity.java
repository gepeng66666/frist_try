package com.geekghost.gp.geekghost.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.geekghost.gp.geekghost.Base.BaseActivity;
import com.geekghost.gp.geekghost.R;
import com.geekghost.gp.geekghost.entity.ChildListBean;
import com.geekghost.gp.geekghost.entity.CommentListBean;
import com.geekghost.gp.geekghost.entity.ListBean;
import com.geekghost.gp.geekghost.entity.MovieRetBean;
import com.geekghost.gp.geekghost.frag.interfaces.ICommentView;
import com.geekghost.gp.geekghost.frag.interfaces.IMovieView;
import com.geekghost.gp.geekghost.frag.videofrag.CommentFrag;
import com.geekghost.gp.geekghost.frag.videofrag.IntroduceFrag;
import com.geekghost.gp.geekghost.presenter.CommentPresenter;
import com.geekghost.gp.geekghost.presenter.MoviePresenter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoInfoActivity extends BaseActivity implements IMovieView, ICommentView {

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.iv_collect)
    ImageView ivCollect;
    @BindView(R.id.rl_collect)
    RelativeLayout rlCollect;
    @BindView(R.id.videoplayer)
    JCVideoPlayerStandard videoplayer;

    @BindView(R.id.viewpagertab)
    SmartTabLayout viewPagerTab;

    @BindView(R.id.viewpager)
    ViewPager viewPager;


    private MoviePresenter presenter;
    private String id;
    private CommentPresenter commentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_info);
        ButterKnife.bind(this);
        //注册EventBus
        //EventBus.getDefault().register(this);

        init();

    }


    private void init() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String pic = intent.getStringExtra("pic");

        videoplayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(VideoInfoActivity.this).load(pic).into(videoplayer.thumbImageView);

        rlBack.setVisibility(View.VISIBLE);
        titleName.setText(name);
        rlCollect.setVisibility(View.VISIBLE);

        //电影的网络请求
        presenter = new MoviePresenter();
        presenter.attachView(this);
        Map<String, String> map = new HashMap<>();
        map.put("mediaId", id);
        presenter.getData(map);

        //评论的网络请求
        commentPresenter = new CommentPresenter();
        commentPresenter.attachView(this);
        commentPresenter.getData(map);


        //SmartTabLayout  ViewPage
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.titleA, IntroduceFrag.class)
                .add(R.string.titleB, CommentFrag.class)
                .create());

        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);

    }


    /**
     * @param ret 电影详情  请求成功回调接口
     */
    @Override
    public void OnSuccess(MovieRetBean<ListBean<ChildListBean>> ret) {
        if (ret != null) {
            String hdurl = ret.getHDURL();
            videoplayer.setUp(hdurl , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
            //发送粘性事件
            EventBus.getDefault().postSticky(ret);
        }
    }

    /**
     * @param e 电影详情  请求失败回调接口
     */
    @Override
    public void OnFailed(Exception e) {

    }


    /**
     * @param commentList  评论  请求成功回调接口
     */
    @Override
    public void onCommentSuccess(List<CommentListBean> commentList) {
        if(commentList!=null){
            Log.e("戈鹏666666", "onCommentSuccess: "+commentList.get(0).getMsg() );
            //发送粘性事件
            EventBus.getDefault().postSticky(commentList);
        }
    }

    /**
     *
     * @param e  评论 请求失败回调接口
     */
    @Override
    public void onCommentFailed(Exception e) {

    }


    @OnClick({R.id.rl_back, R.id.iv_collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                overridePendingTransition(R.anim.tran_in2, R.anim.tran_out2);
                break;
            case R.id.iv_collect:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (videoplayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoplayer.releaseAllVideos();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.detachView();
        }
        if(commentPresenter!=null){
            commentPresenter.detachView();
        }
    }


}

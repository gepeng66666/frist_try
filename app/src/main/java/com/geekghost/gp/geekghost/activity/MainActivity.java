package com.geekghost.gp.geekghost.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekghost.gp.geekghost.Adapter.DrawerRvAdapter;
import com.geekghost.gp.geekghost.Base.BaseActivity;
import com.geekghost.gp.geekghost.R;
import com.geekghost.gp.geekghost.frag.Fancy3;
import com.geekghost.gp.geekghost.frag.Found1;
import com.geekghost.gp.geekghost.frag.My4;
import com.geekghost.gp.geekghost.frag.Special2;
import com.hjm.bottomtabbar.BottomTabBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;
    @BindView(R.id.start_img)
    ImageView startImg;
    @BindView(R.id.start_textView)
    TextView startTextView;
    @BindView(R.id.drawer_rv)
    RecyclerView drawerRv;

    int[] img = new int[]{R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g};
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();
        ButterKnife.bind(this);


        Random random = new Random();
        int i = random.nextInt(7);
        //Toast.makeText(MainActivity.this,"数"+i1,Toast.LENGTH_SHORT).show();
        startImg.setImageResource(img[i]);

        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(44, 44)
                .setFontSize(10)
                .setTabPadding(0, 3, 5)
                .setChangeColor(Color.RED, Color.DKGRAY)
                .addTabItem("精选", R.drawable.found, Found1.class)
                .addTabItem("专题", R.drawable.special, Special2.class)
                .addTabItem("发现", R.drawable.fancy, Fancy3.class)
                .addTabItem("我的", R.drawable.my, My4.class)
                .isShowDivider(false)

                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                })
                .setTabBarBackgroundResource(R.mipmap.bottom_bg)
                .setBackgroundResource(R.drawable.bg_blue);


        ObjectAnimator animator = ObjectAnimator.ofFloat(startImg, "scaleY", 1.2f, 1.4f);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(startImg, "scaleX", 1.2f, 1.4f);

        /*after(Animator anim)   将现有动画插入到传入的动画之后执行
        after(long delay)   将现有动画延迟指定毫秒后执行
        before(Animator anim)   将现有动画插入到传入的动画之前执行
        with(Animator anim)   将现有动画和传入的动画同时执行*/

        AnimatorSet animSet = new AnimatorSet();
        animSet.play(animator).with(animator1);
        animSet.setDuration(1500);
        animSet.start();

        animSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startImg.setVisibility(View.GONE);
                startTextView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        list.add("我的收藏");
        list.add("我的下载");
        list.add("福利");
        list.add("分享");
        list.add("建议反馈");
        list.add("设置");

        //foundRvAdapter = new FoundRvAdapter(getActivity(), niceMovie);
        DrawerRvAdapter drawerRvAdapter = new DrawerRvAdapter(MainActivity.this, list);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        drawerRv.setLayoutManager(manager);
        drawerRv.setAdapter(drawerRvAdapter);


    }


}

package com.geekghost.gp.geekghost.http;

import com.geekghost.gp.geekghost.entity.ChildListBean;
import com.geekghost.gp.geekghost.entity.CommentListBean;
import com.geekghost.gp.geekghost.entity.ListBean;
import com.geekghost.gp.geekghost.entity.MessageBean;
import com.geekghost.gp.geekghost.entity.MovieRetBean;
import com.geekghost.gp.geekghost.entity.RetBean;
import com.geekghost.gp.geekghost.entity.SpecialListBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * 作者：戈鹏
 * on 2018/1/2 19:49
 */

public interface ApiService {
    //首页轮播图5/*
    //http://api.svipmovie.com/front/homePageApi/homePage.do
    @GET("front/homePageApi/homePage.do")
    Flowable<MessageBean<RetBean<ListBean<ChildListBean>>>> getBanner();


    //播放电影  影片详情
    //http://api.svipmovie.com/front/videoDetailApi/videoDetail.do?mediaId=691153a0add84d0ea7e0a619a517203d
    @GET("front/videoDetailApi/videoDetail.do")
    Flowable<MessageBean<MovieRetBean<ListBean<ChildListBean>>>> getMovie(@QueryMap Map<String,String> map);

    //播放电影  影片评论
    //http://api.svipmovie.com/front/Commentary/getCommentList.do?mediaId=691153a0add84d0ea7e0a619a517203d
    @GET("front/Commentary/getCommentList.do")
    Flowable<MessageBean<RetBean<CommentListBean>>> getComment(@QueryMap Map<String,String> map);

    //专题页面  点击进入
    //http://api.svipmovie.com/front/columns/getNewsList.do?catalogId=402834815584e463015584e539700019&pnum=2
    @GET("front/columns/getNewsList.do")
    Flowable<MessageBean<RetBean<SpecialListBean>>> getSpecial(@QueryMap Map<String,String> map);


}

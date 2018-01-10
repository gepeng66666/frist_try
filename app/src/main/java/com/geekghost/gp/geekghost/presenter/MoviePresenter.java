package com.geekghost.gp.geekghost.presenter;

import com.geekghost.gp.geekghost.entity.ChildListBean;
import com.geekghost.gp.geekghost.entity.ListBean;
import com.geekghost.gp.geekghost.entity.MessageBean;
import com.geekghost.gp.geekghost.entity.MovieRetBean;
import com.geekghost.gp.geekghost.frag.interfaces.IMovieView;
import com.geekghost.gp.geekghost.model.MovieModel;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * 作者：戈鹏
 * on 2018/1/4 09:32
 */

public class MoviePresenter implements IMoviePresenter{

    private IMovieView iView;

    private DisposableSubscriber<MessageBean<MovieRetBean<ListBean<ChildListBean>>>> Subscriber;

    public void attachView(IMovieView iView){
        this.iView=iView;
    }

    public void detachView(){
        if(Subscriber!=null){
            if(!Subscriber.isDisposed()){
                Subscriber.dispose();
            }
        }
    }

    @Override
    public void getData(Map<String, String> map) {
        MovieModel movieModel = new MovieModel(this);
        movieModel.getData(map);
    }

    public void getMovie(Flowable<MessageBean<MovieRetBean<ListBean<ChildListBean>>>>  flowable){
        Subscriber = flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<MessageBean<MovieRetBean<ListBean<ChildListBean>>>>() {
                    @Override
                    public void onNext(MessageBean<MovieRetBean<ListBean<ChildListBean>>> retBeanMessageBean) {
                        if(retBeanMessageBean != null){
                            MovieRetBean<ListBean<ChildListBean>> ret = retBeanMessageBean.getRet();
                            if(ret!=null){
                                iView.OnSuccess(ret);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        iView.OnFailed(new Exception(t));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

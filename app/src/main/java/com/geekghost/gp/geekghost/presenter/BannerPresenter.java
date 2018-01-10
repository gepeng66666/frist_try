package com.geekghost.gp.geekghost.presenter;

import com.geekghost.gp.geekghost.entity.ChildListBean;
import com.geekghost.gp.geekghost.entity.ListBean;
import com.geekghost.gp.geekghost.entity.MessageBean;
import com.geekghost.gp.geekghost.entity.RetBean;
import com.geekghost.gp.geekghost.frag.interfaces.IBannerView;
import com.geekghost.gp.geekghost.model.BannerModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * 作者：戈鹏
 * on 2018/1/3 09:18
 */

public class BannerPresenter implements IBannerPresenter{
    private IBannerView iView;

    private DisposableSubscriber<MessageBean<RetBean<ListBean<ChildListBean>>>> Subscriber;

    public void attachView(IBannerView iView){
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
    public void getData() {
        BannerModel bannerModel = new BannerModel(this);
        bannerModel.getData();
    }
    //Flowable<MessageBean<RetBean<ListBean<ChildListBean>>>> flowable

    public void getBanner(Flowable<MessageBean<RetBean<ListBean<ChildListBean>>>> flowable){
        Subscriber = flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<MessageBean<RetBean<ListBean<ChildListBean>>>>() {
                    @Override
                    public void onNext(MessageBean<RetBean<ListBean<ChildListBean>>> retBeanMessageBean) {
                        if(retBeanMessageBean != null){
                            RetBean<ListBean<ChildListBean>> ret = retBeanMessageBean.getRet();
                            if(ret!=null){
                                List<ListBean<ChildListBean>> list = ret.getList();
                                iView.OnSuccess(list);
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

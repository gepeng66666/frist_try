package com.geekghost.gp.geekghost.presenter;

import com.geekghost.gp.geekghost.activity.ISpecialView;
import com.geekghost.gp.geekghost.entity.MessageBean;
import com.geekghost.gp.geekghost.entity.RetBean;
import com.geekghost.gp.geekghost.entity.SpecialListBean;
import com.geekghost.gp.geekghost.model.SpecialModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * 作者：戈鹏
 * on 2018/1/9 09:53
 */

public class SpecialPresenter implements IMoviePresenter{

    private ISpecialView iView;

    private DisposableSubscriber<MessageBean<RetBean<SpecialListBean>>> Subscriber;



    public void attachView(ISpecialView iView){
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
        SpecialModel specialModel = new SpecialModel(this);
        specialModel.getData(map);
    }

    public void getSpecial(Flowable<MessageBean<RetBean<SpecialListBean>>> flowable){
        Subscriber = flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<MessageBean<RetBean<SpecialListBean>>>() {
                    @Override
                    public void onNext(MessageBean<RetBean<SpecialListBean>> retBeanMessageBean) {
                        if(retBeanMessageBean != null){
                            List<SpecialListBean> list = retBeanMessageBean.getRet().getList();
                            if(list !=null){
                                iView.onSpecialSuccess(list);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        iView.onSpecialFailed(new Exception(t));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

package com.geekghost.gp.geekghost.presenter;

import com.geekghost.gp.geekghost.entity.CommentListBean;
import com.geekghost.gp.geekghost.entity.MessageBean;
import com.geekghost.gp.geekghost.entity.RetBean;
import com.geekghost.gp.geekghost.frag.interfaces.ICommentView;
import com.geekghost.gp.geekghost.model.CommentModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * 作者：戈鹏
 * on 2018/1/4 09:32
 */

public class CommentPresenter implements IMoviePresenter{

    private ICommentView iView;

    private DisposableSubscriber<MessageBean<RetBean<CommentListBean>>> Subscriber;

    public void attachView(ICommentView iView){
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
        CommentModel movieModel = new CommentModel(this);
        movieModel.getData(map);
    }

    public void getComment(Flowable<MessageBean<RetBean<CommentListBean>>>  flowable){
        Subscriber = flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<MessageBean<RetBean<CommentListBean>>>() {
                    @Override
                    public void onNext(MessageBean<RetBean<CommentListBean>> retBeanMessageBean) {
                        if(retBeanMessageBean != null){
                            List<CommentListBean> list = retBeanMessageBean.getRet().getList();
                            if(list!=null){
                                iView.onCommentSuccess(list);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        iView.onCommentFailed(new Exception(t));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

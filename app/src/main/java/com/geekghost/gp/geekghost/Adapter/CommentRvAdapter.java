package com.geekghost.gp.geekghost.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.geekghost.gp.geekghost.R;
import com.geekghost.gp.geekghost.entity.CommentListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：戈鹏
 * on 2018/1/7 19:57
 */

public class CommentRvAdapter extends RecyclerView.Adapter <CommentRvAdapter.ViewHolder>{

    private Context context;
    private List<CommentListBean> list = new ArrayList<>();

    public CommentRvAdapter(Context context, List<CommentListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_comment_rv, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String comment = list.get(position).getPhoneNumber() + "\n" + list.get(position).getTime() + "\n" + list.get(position).getMsg();

        holder.commentImg.setImageURI(list.get(position).getUserPic());
        holder.commentMsg.setText(comment);
        holder.commentNum.setText(list.get(position).getLikeNum()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.comment_img)
        SimpleDraweeView commentImg;
        @BindView(R.id.comment_msg)
        TextView commentMsg;
        @BindView(R.id.comment_num)
        TextView commentNum;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

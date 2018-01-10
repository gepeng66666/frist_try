package com.geekghost.gp.geekghost.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.geekghost.gp.geekghost.R;
import com.geekghost.gp.geekghost.activity.SpecialListActivity;
import com.geekghost.gp.geekghost.entity.ChildListBean;
import com.geekghost.gp.geekghost.entity.ListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：戈鹏
 * on 2018/1/8 09:31
 */

public class SpecialRvAdapter extends RecyclerView.Adapter<SpecialRvAdapter.ViewHolder> {

    private Context context;
    List<ListBean<ChildListBean>> specialList = new ArrayList<>();

    public SpecialRvAdapter(Context context, List<ListBean<ChildListBean>> specialList) {
        this.context = context;
        this.specialList = specialList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_special_rv, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String moreURL = specialList.get(position).getMoreURL();
                String catalogId = moreURL.substring(moreURL.indexOf("=")+1, moreURL.lastIndexOf("&"));
                //Log.e("戈鹏的截取字符串", "onClick: "+substring );
                Intent intent = new Intent(context, SpecialListActivity.class);
                intent.putExtra("catalogId", catalogId);
                intent.putExtra("title", specialList.get(position).getTitle());
                context.startActivity(intent);
                //context.overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
            }
        });

        Glide.with(context).load(specialList.get(position).getChildList().get(0).getPic()).into(holder.specialRvImg);
        holder.specialRvTv.setText(specialList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return specialList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.special_rv_img)
        ImageView specialRvImg;
        @BindView(R.id.special_rv_tv)
        TextView specialRvTv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}

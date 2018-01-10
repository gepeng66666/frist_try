package com.geekghost.gp.geekghost.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekghost.gp.geekghost.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：戈鹏
 * on 2018/1/3 10:47
 *
 *  3D侧拉的recyclerView 适配器
 */

public class DrawerRvAdapter extends RecyclerView.Adapter<DrawerRvAdapter.ViewHolder> {

    private Context context;
    private List<String> list = new ArrayList<>();

    public DrawerRvAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_drawer_rv, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.foundRvImg.setImageURI(list.get(position).getPic());
        holder.drawerRvTv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.drawer_rv_img)
        ImageView drawerRvImg;
        @BindView(R.id.drawer_rv_tv)
        TextView drawerRvTv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

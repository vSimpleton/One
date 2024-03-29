package oms.pomelo.one.one.main;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import oms.pomelo.one.one.bean.OneListInfo;
import oms.pomelo.one.one.view.ArticleActivity;
import oms.pomelo.one.one.view.CommentActivity;
import oms.pomelo.one.one.view.OneMainView;

/**
 * Created by Sherry on 2019/12/3
 */

public class OneListAdapter extends RecyclerView.Adapter<OneListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<OneListInfo.ContentList> lists;

    public OneListAdapter(Context mContext, ArrayList<OneListInfo.ContentList> lists) {
        this.mContext = mContext;
        this.lists = lists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OneMainView view = new OneMainView(mContext);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OneListInfo.ContentList content = lists.get(position + 1);
        holder.view.setData(content);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ArticleActivity.class);
                intent.putExtra("webUrl", content.share_url);
                intent.putExtra("type", content.type);
                mContext.startActivity(intent);
            }
        });

        holder.view.mIvLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CommentActivity.class);
                intent.putExtra("item_id", content.item_id);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lists == null ? 0 : lists.size() - 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        OneMainView view;
        public ViewHolder(OneMainView view) {
            super(view);
            this.view = view;
        }
    }

}

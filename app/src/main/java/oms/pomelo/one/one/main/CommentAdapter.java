package oms.pomelo.one.one.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import oms.pomelo.one.R;
import oms.pomelo.one.one.bean.CommentInfo;

/**
 * Created by Sherry on 2019/12/5
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private ArrayList<CommentInfo.DataBean> infos;
    private Context mContext;

    public CommentAdapter(ArrayList<CommentInfo.DataBean> infos, Context mContext) {
        this.infos = infos;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_comment, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).load(infos.get(position).getUser().getWeb_url()).into(holder.mIvUserIcon);
        holder.mTvUserName.setText(infos.get(position).getUser().getUser_name());
        holder.mTvContent.setText(infos.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return infos == null ? 0 : infos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mIvUserIcon;
        TextView mTvUserName;
        TextView mTvContent;

        public ViewHolder(@NonNull View view) {
            super(view);
            mIvUserIcon = view.findViewById(R.id.ivUserIcon);
            mTvUserName = view.findViewById(R.id.tvUserName);
            mTvContent = view.findViewById(R.id.tvContent);
        }
    }

}

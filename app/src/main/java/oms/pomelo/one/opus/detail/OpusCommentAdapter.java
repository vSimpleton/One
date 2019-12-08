package oms.pomelo.one.opus.detail;

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
import oms.pomelo.one.ui.widget.RoundedImageView;

/**
 * NAME: Sherry
 * DATE: 2019-12-08
 */
public class OpusCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<OpusCommentInfo> lists;
    private static final int EMPTY_LAYOUT = 0;
    private static final int NORMAL_LAYOUT = 1;

    public OpusCommentAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<OpusCommentInfo> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == NORMAL_LAYOUT) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_comment_opus, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.opus_empty_comment, parent, false);
            EmptyViewHolder holder = new EmptyViewHolder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder normalHolder = (ViewHolder) holder;
            OpusCommentInfo info = lists.get(position);
            if (position == 0) {
                normalHolder.mTvNewTips.setVisibility(View.VISIBLE);
            }
            Glide.with(mContext).load(info.getUser().getAvatar()).into(normalHolder.mIvAvatar);
            normalHolder.mTvUserName.setText(info.getUser().getNickname());
            normalHolder.mTvTime.setText(info.getCreated_at());
            normalHolder.mTvContent.setText(info.getContent());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (lists != null && lists.size() > 0) {
            return NORMAL_LAYOUT;
        } else {
            return EMPTY_LAYOUT;
        }
    }

    @Override
    public int getItemCount() {
        if (lists == null || lists.size() == 0) {
            return 1;
        } else {
            return lists.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView mIvAvatar;
        TextView mTvUserName;
        TextView mTvTime;
        TextView mTvContent;
        TextView mTvNewTips;

        public ViewHolder(@NonNull View view) {
            super(view);
            mIvAvatar = view.findViewById(R.id.ivAvatar);
            mTvUserName = view.findViewById(R.id.tvUserName);
            mTvTime = view.findViewById(R.id.tvTime);
            mTvContent = view.findViewById(R.id.tvContent);
            mTvNewTips = view.findViewById(R.id.tvNewTips);
        }
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {

        ImageView mIvEmptyTips;
        TextView mTvEmptyTips;

        public EmptyViewHolder(@NonNull View view) {
            super(view);
            mIvEmptyTips = view.findViewById(R.id.ivEmptyTips);
            mTvEmptyTips = view.findViewById(R.id.tvEmptyTips);
        }
    }

}

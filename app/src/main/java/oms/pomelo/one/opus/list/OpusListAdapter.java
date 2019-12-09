package oms.pomelo.one.opus.list;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * NAME: Sherry
 * DATE: 2019-12-07
 */
public class OpusListAdapter extends RecyclerView.Adapter<OpusListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<OpusInfo> lists;
    private OnOpusClickListener mListener;

    public OpusListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<OpusInfo> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        OpusView view = new OpusView(mContext);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OpusInfo info = lists.get(position);
        holder.view.setData(info);
        holder.view.setOnClickOpusListener(new OpusView.OnOpusClickListener() {
            @Override
            public void onClickOpusView() {
                if (mListener != null) {
                    mListener.onClickOpusView(info.getUuid());
                }
            }

            @Override
            public void onClickLike() {
                if (mListener != null) {
                    mListener.onClickLike(info.getUuid());
                }
            }

            @Override
            public void onClickComment() {
                if (mListener != null) {
                    mListener.onClickComment(info.getUuid());
                }
            }

            @Override
            public void onClickShare() {
                if (mListener != null) {
                    mListener.onClickShare();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lists == null ? 0 : lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        OpusView view;

        public ViewHolder(@NonNull OpusView view) {
            super(view);
            this.view = view;
        }
    }

    public void setOnClickOpusListener(OnOpusClickListener listener) {
        mListener = listener;
    }

}

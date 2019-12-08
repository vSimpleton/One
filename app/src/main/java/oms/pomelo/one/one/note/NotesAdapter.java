package oms.pomelo.one.one.note;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import oms.pomelo.one.R;
import oms.pomelo.one.one.bean.NoteInfo;

/**
 * Created by Sherry on 2019/12/4
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<NoteInfo.NoteList> notes;

    public NotesAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_one_note, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        NoteInfo.NoteList note = notes.get(position);
        Glide.with(mContext).load(note.getImg_url()).into(holder.mIvHead);
        holder.mTvContent.setText(note.getContent());
        holder.mTvAuthor.setText(note.getUser().getUser_name());
        holder.mTvDate.setText(note.getInput_date());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position, holder.mIvHead);
                }
            }
        });
    }

    public void setData(ArrayList<NoteInfo.NoteList> notes) {
        this.notes = notes;
    }

    @Override
    public int getItemCount() {
        return notes == null ? 0 : notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mIvHead;
        TextView mTvContent;
        TextView mTvAuthor;
        TextView mTvDate;
        CardView mCardView;

        public ViewHolder(@NonNull View view) {
            super(view);
            mIvHead = view.findViewById(R.id.ivHead);
            mTvContent = view.findViewById(R.id.tvContent);
            mTvAuthor = view.findViewById(R.id.tvAuthor);
            mTvDate = view.findViewById(R.id.tvDate);
            mCardView = view.findViewById(R.id.cardView);
        }
    }

    interface OnItemClickListener {
        void onItemClick(int position, ImageView img);
    }

    OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

}

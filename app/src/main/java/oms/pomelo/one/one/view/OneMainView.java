package oms.pomelo.one.one.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import oms.pomelo.one.R;
import oms.pomelo.one.one.bean.OneListInfo;
import oms.pomelo.one.utils.Utils;

/**
 * Created by Sherry on 2019/12/3
 * 首页ONE的主要列表
 */

public class OneMainView extends RelativeLayout implements View.OnClickListener {

    private Context mContext;
    private TextView mTvType;
    private TextView mTvTitle;
    private TextView mTvAuthor;
    private ImageView mIvContentImg;
    private TextView mTvContent;
    private TextView mTvTime;
    private ImageView mIvLike;
    private ImageView mIvShare;
    private TextView mTvLikeCount;

    public OneMainView(Context context) {
        this(context, null);
    }

    public OneMainView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OneMainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
        initListener();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_one_main, this, false);
        addView(view);

        mTvType = view.findViewById(R.id.tvType);
        mTvTitle = view.findViewById(R.id.tvTitle);
        mTvAuthor = view.findViewById(R.id.tvAuthor);
        mTvContent = view.findViewById(R.id.tvContent);
        mTvTime = view.findViewById(R.id.tvTime);
        mTvLikeCount = view.findViewById(R.id.tvLikeCount);
        mIvContentImg = view.findViewById(R.id.ivContentImg);
        mIvLike = view.findViewById(R.id.ivLike);
        mIvShare = view.findViewById(R.id.ivShare);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListener() {
        mIvShare.setOnClickListener(this);
        mIvLike.setOnClickListener(this);

        mIvShare.setOnTouchListener(Utils.getAlphaTouchListener());
        mIvLike.setOnTouchListener(Utils.getAlphaTouchListener());
    }

    public void setData(OneListInfo.ContentList content) {
        if (content.category.equals("1")) {
            mTvType.setText(mContext.getResources().getString(R.string.one_read));
        } else if (content.category.equals("2")) {
            mTvType.setText(mContext.getResources().getString(R.string.one_serial));
        } else if (content.category.equals("3")) {
            mTvType.setText(mContext.getResources().getString(R.string.one_answer));
        } else if (content.category.equals("4")) {
            mTvType.setText(mContext.getResources().getString(R.string.one_movie));
        } else if (content.category.equals("5")) {
            mTvType.setText(mContext.getResources().getString(R.string.one_movie));
        }
        mTvTitle.setText(content.title);
        mTvContent.setText(content.forward);
        mTvAuthor.setText("文/" + content.author.user_name);
        mTvLikeCount.setText(content.like_count + "");
        mTvTime.setText("今天");
        Glide.with(mContext).load(content.img_url).into(mIvContentImg);
    }

    @Override
    public void onClick(View v) {

    }
}

package oms.pomelo.one.opus.list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import oms.pomelo.one.R;
import oms.pomelo.one.opus.detail.OpusDetailInfo;
import oms.pomelo.one.ui.widget.OvalImageView;
import oms.pomelo.one.ui.widget.RoundedImageView;
import oms.pomelo.one.utils.Utils;

/**
 * NAME: Sherry
 * DATE: 2019-12-07
 * 动态item
 */
public class OpusView extends RelativeLayout implements View.OnClickListener {

    private Context mContext;
    private RoundedImageView mIvAvatar;
    private TextView mTvUserName;
    private TextView mTvPublishTime;
    private TextView mTvContent;
    private OvalImageView mIvContentImg;
    private ImageView mIvCtrlLike;
    private TextView mTvCtrlLikeCount;
    private ImageView mIvCtrlComment;
    private TextView mTvCtrlCmtCount;
    private ImageView mIvCtrlShare;
    private OnOpusClickListener mListener;
    private View mOpusView;

    public OpusView(Context context) {
        this(context, null);
    }

    public OpusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OpusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
        initListener();
    }

    private void initView() {
        mOpusView = LayoutInflater.from(mContext).inflate(R.layout.view_opus, this, false);
        addView(mOpusView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mIvAvatar = mOpusView.findViewById(R.id.ivAvatar);
        mTvUserName = mOpusView.findViewById(R.id.tvUserName);
        mTvPublishTime = mOpusView.findViewById(R.id.tvPublishTime);
        mTvContent = mOpusView.findViewById(R.id.tvContent);
        mIvContentImg = mOpusView.findViewById(R.id.ivContentImg);
        mIvCtrlLike = mOpusView.findViewById(R.id.ivCtrlLike);
        mTvCtrlLikeCount = mOpusView.findViewById(R.id.tvCtrlLikeCount);
        mIvCtrlComment = mOpusView.findViewById(R.id.ivCtrlComment);
        mTvCtrlCmtCount = mOpusView.findViewById(R.id.tvCtrlCmtCount);
        mIvCtrlShare = mOpusView.findViewById(R.id.ivCtrlShare);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListener() {
        mIvCtrlLike.setOnClickListener(this);
        mIvCtrlComment.setOnClickListener(this);
        mIvCtrlShare.setOnClickListener(this);
        mOpusView.setOnClickListener(this);

        mIvCtrlLike.setOnTouchListener(Utils.getScaleTouchListener());
        mIvCtrlComment.setOnTouchListener(Utils.getScaleTouchListener());
        mIvCtrlShare.setOnTouchListener(Utils.getScaleTouchListener());
    }

    @Override
    public void onClick(View view) {
        if (view == mIvCtrlLike) {
            if (mListener != null) {
                mListener.onClickLike();
            }
        } else if (view == mIvCtrlComment) {
            if (mListener != null) {
                mListener.onClickComment();
            }
        } else if (view == mIvCtrlShare) {
            if (mListener != null) {
                mListener.onClickShare();
            }
        } else if (view == mOpusView){
            if (mListener != null) {
                mListener.onClickOpusView();
            }
        }
    }

    public void setData(OpusInfo opusInfo) {
        if (opusInfo.getUser() != null) {
            Glide.with(mContext).load(opusInfo.getUser().getAvatar()).into(mIvAvatar);
            mTvUserName.setText(opusInfo.getUser().getNickname());
        } else {
            mOpusView.setVisibility(GONE);
        }
        mTvPublishTime.setText(Utils.getPublishTime(opusInfo.getCreated_at()));
        mTvContent.setText(opusInfo.getContent());
        if (opusInfo.getPictures() != null && opusInfo.getPictures().size() > 0) {
            Glide.with(mContext).load(opusInfo.getPictures().get(0).getUrl()).into(mIvContentImg);
        } else {
            mIvContentImg.setVisibility(GONE);
        }
        if (opusInfo.getLike_count() > 0) {
            mTvCtrlLikeCount.setVisibility(VISIBLE);
            mTvCtrlLikeCount.setText(String.valueOf(opusInfo.getLike_count()));
        } else {
            mTvCtrlLikeCount.setVisibility(GONE);
        }
        if (opusInfo.getComment_count() > 0) {
            mTvCtrlCmtCount.setVisibility(VISIBLE);
            mTvCtrlCmtCount.setText(String.valueOf(opusInfo.getComment_count()));
        } else {
            mTvCtrlCmtCount.setVisibility(GONE);
        }
    }

    public void setDetailData(OpusDetailInfo info) {
        mIvCtrlLike.setVisibility(GONE);
        mTvCtrlLikeCount.setVisibility(GONE);
        mIvCtrlComment.setVisibility(GONE);
        mTvCtrlCmtCount.setVisibility(GONE);
        mIvCtrlShare.setVisibility(GONE);

        if (info.getUser() != null) {
            Glide.with(mContext).load(info.getUser().getAvatar()).into(mIvAvatar);
            mTvUserName.setText(info.getUser().getNickname());
        } else {
            mOpusView.setVisibility(GONE);
        }
        mTvPublishTime.setText(Utils.getPublishTime(info.getPublished_at()));
        mTvContent.setText(info.getContent());
        if (info.getPictures() != null && info.getPictures().size() > 0) {
            Glide.with(mContext).load(info.getPictures().get(0).getUrl()).into(mIvContentImg);
        } else {
            mIvContentImg.setVisibility(GONE);
        }
    }

    public void setOnClickOpusListener(OnOpusClickListener listener) {
        mListener = listener;
    }

    interface OnOpusClickListener {

        void onClickOpusView();

        void onClickLike();

        void onClickComment();

        void onClickShare();

    }

}

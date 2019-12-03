package oms.pomelo.one.one.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import oms.pomelo.one.R;
import oms.pomelo.one.one.OneListContract;
import oms.pomelo.one.one.OneListPresenter;
import oms.pomelo.one.one.bean.OneListInfo;
import oms.pomelo.one.utils.ToastUtil;
import oms.pomelo.one.utils.Utils;

/**
 * Created by Sherry on 2019/12/2
 * ONE首页的每日内容
 */

public class OneTopView extends RelativeLayout implements View.OnClickListener {

    private Context mContext;
    private ImageView mIvTopImg;
    private TextView mTvType;
    private TextView mTvContent;
    private TextView mTvAuthor;
    private TextView mTvDiscovery;
    private ImageView mIvDairy;
    private ImageView mIvCollect;
    private ImageView mIvShare;
    private ImageView mIvLike;
    private TextView mTvLikeCount;

    public OneTopView(Context context) {
        this(context, null);
    }

    public OneTopView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OneTopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
        initListener();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.one_top_view, this, false);
        addView(view);
        mTvContent = view.findViewById(R.id.tvContent);
        mIvTopImg = view.findViewById(R.id.ivTopImg);
        mTvType = view.findViewById(R.id.tvType);
        mTvAuthor = view.findViewById(R.id.tvAuthor);
        mTvDiscovery = view.findViewById(R.id.tvDiscovery);
        mIvDairy = view.findViewById(R.id.ivDairy);
        mIvCollect = view.findViewById(R.id.ivCollect);
        mIvShare = view.findViewById(R.id.ivShare);
        mIvLike = view.findViewById(R.id.ivLike);
        mTvLikeCount = view.findViewById(R.id.tvLikeCount);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListener() {
        mTvDiscovery.setOnClickListener(this);
        mIvDairy.setOnClickListener(this);
        mIvCollect.setOnClickListener(this);
        mIvShare.setOnClickListener(this);
        mIvLike.setOnClickListener(this);

        mTvDiscovery.setOnTouchListener(Utils.getTouchBackListener(0.8f));
        mIvDairy.setOnTouchListener(Utils.getAlphaTouchListener());
        mIvCollect.setOnTouchListener(Utils.getAlphaTouchListener());
        mIvShare.setOnTouchListener(Utils.getAlphaTouchListener());
        mIvLike.setOnTouchListener(Utils.getAlphaTouchListener());
    }

    public void setData(OneListInfo listInfo) {
        OneListInfo.ContentList content = listInfo.content_list.get(0);
        mTvContent.setText(content.forward);
        Glide.with(mContext).load(content.img_url).into(mIvTopImg);
        mTvType.setText(content.title + "  " + content.pic_info);
        mTvAuthor.setText(content.words_info);
        mTvLikeCount.setText(content.like_count + "");
    }

    @Override
    public void onClick(View v) {
        if (v == mTvDiscovery) {
            ToastUtil.showTextToast(mContext, "跳转到小记广场");
        }
    }

}

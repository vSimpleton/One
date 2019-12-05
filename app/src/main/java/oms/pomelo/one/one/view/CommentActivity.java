package oms.pomelo.one.one.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import oms.pomelo.one.R;
import oms.pomelo.one.base.BaseActivity;
import oms.pomelo.one.one.bean.CommentInfo;
import oms.pomelo.one.one.bean.OneListInfo;
import oms.pomelo.one.one.main.CommentAdapter;
import oms.pomelo.one.one.main.OneListContract;
import oms.pomelo.one.one.main.OneListPresenter;
import oms.pomelo.one.utils.StatusBarUtils;
import oms.pomelo.one.utils.Utils;

public class CommentActivity extends BaseActivity implements View.OnClickListener, OneListContract.OneListView {

    private RelativeLayout baseLayout;
    private LinearLayout llMainLayout;
    private boolean isFinishAni;
    private String itemId;
    private OneListPresenter mPresenter;
    private CommentAdapter mAdapter;
    private RecyclerView mRcyComment;
    private TextView mTvCmtCount;
    private TextView mTvCancel;
    private float DownY;
    private boolean getDownY = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        StatusBarUtils.transparencyBar(this);

        initView();
        initListener();
        initData();
        initPresenter();

        baseLayout.post(new Runnable() {
            @Override
            public void run() {
                doEnterAni();
            }
        });
    }

    private void initData() {
        itemId = getIntent().getStringExtra("item_id");
    }

    public void initView() {
        llMainLayout = findViewById(R.id.llMainLayout);
        baseLayout = findViewById(R.id.baseLayout);
        mTvCmtCount = findViewById(R.id.tvCmtCount);
        mRcyComment = findViewById(R.id.rcyComment);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRcyComment.setLayoutManager(manager);
        mTvCancel = findViewById(R.id.tvCancel);

        GradientDrawable cancel = new GradientDrawable();
        cancel.setCornerRadius(Utils.dpToPx(2));
        cancel.setColor(0xfff1f1f1);
        mTvCancel.setBackground(cancel);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void initListener() {
        baseLayout.setOnClickListener(this);
//        llMainLayout.setOnClickListener(this);
//        llMainLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        if (event.getRawY() - DownY > 10) {
//                            if (!getDownY) {
//                                getDownY = true;
//                                DownY = event.getRawY();
//                            }
//                            float offsetY = event.getRawY() - DownY;
//                            llMainLayout.setTranslationY(offsetY);
//                            return true;
//                        }
//                        break;
//                    case MotionEvent.ACTION_CANCEL:
//                    case MotionEvent.ACTION_UP:
//                        getDownY = false;
//                        DownY = 0;
//                        if (llMainLayout.getTranslationY() < Utils.dpToPx(200)) {
//                            doBackAnim();
//                        } else {
//                            onBackPressed();
//                        }
//                        break;
//                }
//                return false;
//            }
//        });
    }

    private void initPresenter() {
        mPresenter = new OneListPresenter(this);
        mPresenter.attachView(this);
        mPresenter.getCommentList(itemId);
    }

    private void doBackAnim() {
        Animator animator = ObjectAnimator.ofFloat(llMainLayout, "translationY", llMainLayout.getTranslationY(), 0);
        animator.setDuration(200);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }

    @Override
    public void onClick(View v) {
        if (v == baseLayout) {
            onBackPressed();
        }
    }

    private void doEnterAni() {
        final AnimatorSet aniSet = new AnimatorSet();
        aniSet.setDuration(500);
        aniSet.setInterpolator(new AccelerateDecelerateInterpolator());
        ValueAnimator layoutAni = ValueAnimator.ofInt(0x00000000, getResources().getColor(R.color.activity_mask_bgk_color));
        layoutAni.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (int) animation.getAnimatedValue();
                baseLayout.setBackgroundColor(color);
            }
        });
        layoutAni.setEvaluator(new ArgbEvaluator());

        ObjectAnimator dialogAni = ObjectAnimator.ofFloat(llMainLayout, "translationY", llMainLayout.getHeight(), 0);
        aniSet.playTogether(layoutAni, dialogAni);
        aniSet.start();
    }

    private void doQuitAni() {
        if (isFinishAni) {
            return;
        }
        isFinishAni = true;

        final AnimatorSet aniSet = new AnimatorSet();
        aniSet.setDuration(200);
        aniSet.setInterpolator(new AccelerateInterpolator());
        ValueAnimator layoutAni = ValueAnimator.ofInt(0x80000000, 0x00000000);
        layoutAni.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (int) animation.getAnimatedValue();
                baseLayout.setBackgroundColor(color);
            }
        });
        layoutAni.setEvaluator(new ArgbEvaluator());

        ObjectAnimator dialogAni = ObjectAnimator.ofFloat(llMainLayout, "translationY", 0, llMainLayout.getHeight());
        aniSet.playTogether(layoutAni, dialogAni);
        aniSet.start();
        aniSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                finish();
                overridePendingTransition(0, 0);
            }
        });
    }

    @Override
    public void onBackPressed() {
        doQuitAni();
    }

    @Override
    public void getOneListSuccess(OneListInfo info) {

    }

    @Override
    public void getCommentListSuccess(CommentInfo info) {
        mAdapter = new CommentAdapter(info.getData(), this);
        mRcyComment.setAdapter(mAdapter);
        mTvCmtCount.setText("共" + info.getCount() + "条评论");
    }
}

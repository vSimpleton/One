package oms.pomelo.one.opus.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

import oms.pomelo.one.HeaderAndFooterWrapper;
import oms.pomelo.one.R;
import oms.pomelo.one.base.BaseActivity;
import oms.pomelo.one.opus.list.OpusView;

public class OpusDetailActivity extends BaseActivity implements View.OnClickListener, OpusDetailContract.OpusDetailView {

    private OpusView mOpusView;
    private OpusDetailInfo mInfo;
    private OpusDetailPresenter mPresenter;
    private String uuid;
    private RecyclerView mRcyComment;
    private OpusCommentAdapter mAdapter;
    private ArrayList<OpusCommentInfo> lists;

    public static void start(Context context, String uuid) {
        Intent intent = new Intent(context, OpusDetailActivity.class);
        intent.putExtra("uuid", uuid);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opus_detail);

        initView();
        initPresenter();
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRcyComment.setLayoutManager(manager);
        mAdapter = new OpusCommentAdapter(this);
        HeaderAndFooterWrapper wrapper = new HeaderAndFooterWrapper(mAdapter);
        wrapper.addHeaderView(mOpusView);
        DividerItemDecoration mItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.shape_rcy_divider)));
        mRcyComment.addItemDecoration(mItemDecoration);
        mRcyComment.setAdapter(wrapper);
    }

    private void initPresenter() {
        uuid = getIntent().getStringExtra("uuid");
        mPresenter = new OpusDetailPresenter(this);
        mPresenter.attachView(this);
        mPresenter.getOpusDetailInfo(uuid);
        mPresenter.getOpusCommentInfo(uuid);
    }

    private void initView() {
        mOpusView = new OpusView(this);
        mRcyComment = findViewById(R.id.rcyComment);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void getOpusDetailSuccess(OpusDetailInfo info) {
        if (info != null) {
            mInfo = info;
            mOpusView.setDetailData(mInfo);
        }
    }

    @Override
    public void getOpusCommentInfoSuccess(ArrayList<OpusCommentInfo> info) {
        if (info != null) {
            lists = info;
            mAdapter.setData(lists);
            mRcyComment.getAdapter().notifyDataSetChanged();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}

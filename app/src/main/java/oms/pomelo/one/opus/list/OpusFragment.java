package oms.pomelo.one.opus.list;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Objects;

import oms.pomelo.one.R;
import oms.pomelo.one.opus.detail.OpusDetailActivity;
import oms.pomelo.one.utils.ToastUtil;

/**
 * 推荐动态页
 */
public class OpusFragment extends Fragment implements OpusInfoContract.OpusInfoView {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRcyOpus;
    private OpusInfoPresenter mPresenter;
    private OpusListAdapter mAdapter;
    private Context mContext;
    private String uuid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_opus, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getContext();
        initView(view);
        initPresenter();
        initListener();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter = new OpusListAdapter(mContext);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRcyOpus.setLayoutManager(manager);
        DividerItemDecoration mItemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        mItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(mContext, R.drawable.shape_rcy_divider)));
        mRcyOpus.addItemDecoration(mItemDecoration);
        mRcyOpus.setAdapter(mAdapter);

        mAdapter.setOnClickOpusListener(new OnOpusClickListener() {
            @Override
            public void onClickOpusView(String uuid) {
                OpusDetailActivity.start(mContext, uuid);
            }

            @Override
            public void onClickLike() {
                ToastUtil.showToast(mContext, "点赞");
            }

            @Override
            public void onClickComment(String uuid) {
                OpusDetailActivity.start(mContext, uuid);
            }

            @Override
            public void onClickShare() {
                ToastUtil.showToast(mContext, "分享");
            }
        });
    }

    private void initListener() {
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.getOpusInfo();
                    }
                }).start();
            }
        });
    }

    private void initPresenter() {
        mPresenter = new OpusInfoPresenter(mContext);
        mPresenter.attachView(this);
        mPresenter.getOpusInfo();
    }

    private void initView(View view) {
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        mRcyOpus = view.findViewById(R.id.rcyOpus);
    }

    @Override
    public void getOpusInfoSuccess(ArrayList<OpusInfo> opusInfo) {
        if (mRefreshLayout != null) {
            mRefreshLayout.setRefreshing(false);
        }
        if (opusInfo != null) {
            mAdapter.setData(opusInfo);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getOpusInfoFailed(String msg) {

    }
}

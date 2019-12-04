package oms.pomelo.one.one.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Objects;

import oms.pomelo.one.HeaderAndFooterAdapter;
import oms.pomelo.one.R;
import oms.pomelo.one.one.OneListAdapter;
import oms.pomelo.one.one.OneListContract;
import oms.pomelo.one.one.OneListPresenter;
import oms.pomelo.one.one.bean.OneListInfo;
import oms.pomelo.one.utils.StatusBarUtils;
import oms.pomelo.one.utils.Utils;

public class OneFragment extends Fragment implements View.OnClickListener, OneListContract.OneListView {

    private OneListPresenter mPresenter;
    private OneTopView mTopView;
    private OneListInfo listInfo;
    private OneListAdapter mAdapter;
    private Context mContext;
    private RecyclerView mRcyContent;
    private TextView mTvDay;
    private TextView mTvDate;
    private TextView mTvMore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getContext();
        initView(view);
        initListener();
        initPresenter();
        initRecyclerView();
    }

    private void initListener() {
        mTopView.setOnClickListener(this);
    }

    private void initView(View view) {
        mTopView = new OneTopView(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTopView.setLayoutParams(params);
        mRcyContent = view.findViewById(R.id.rcyContent);
        mTvDay = view.findViewById(R.id.tvDay);
        mTvDate = view.findViewById(R.id.tvDate);
        mTvMore = view.findViewById(R.id.tvMore);
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRcyContent.setLayoutManager(manager);
        DividerItemDecoration mItemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        mItemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(mContext, R.drawable.shape_rcy_divider)));
        mRcyContent.addItemDecoration(mItemDecoration);
    }

    private void initPresenter() {
        mPresenter = new OneListPresenter(getContext());
        mPresenter.attachView(this);
        mPresenter.getOneListInfo();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void getOneListSuccess(OneListInfo info) {
        listInfo = info;
        mTopView.setData(info);
        mAdapter = new OneListAdapter(mContext, listInfo.content_list);
        HeaderAndFooterAdapter adapter = new HeaderAndFooterAdapter(mAdapter);
        adapter.addHeaderView(mTopView);
        mRcyContent.setAdapter(adapter);

        String day = info.date.substring(8, 10);
        mTvDay.setText(day);
        mTvDate.setText(Utils.getDateFormat(info.date));
        mTvMore.setText(info.weather.city_name + " · " + info.weather.climate + " · 湿度" + info.weather.humidity);
    }

    @Override
    public void getOneListFailed(String message) {

    }

    @Override
    public void onClick(View v) {
        if (v == mTopView) {

        }
    }
}

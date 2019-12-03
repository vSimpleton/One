package oms.pomelo.one.one.view;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import oms.pomelo.one.HeaderAndFooterAdapter;
import oms.pomelo.one.R;
import oms.pomelo.one.one.OneListAdapter;
import oms.pomelo.one.one.OneListContract;
import oms.pomelo.one.one.OneListPresenter;
import oms.pomelo.one.one.bean.OneListInfo;
import oms.pomelo.one.utils.Utils;

public class OneFragment extends Fragment implements View.OnClickListener, OneListContract.OneListView{

    private OneListPresenter mPresenter;
    private OneTopView mTopView;
    private OneListInfo listInfo;
    private OneListAdapter mAdapter;
    private Context mContext;
    private RecyclerView mRcyContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRcyContent.setLayoutManager(manager);
        mRcyContent.addItemDecoration(mItemDecoration);
    }

    RecyclerView.ItemDecoration mItemDecoration = new RecyclerView.ItemDecoration() {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.top = 14;
        }
    };

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

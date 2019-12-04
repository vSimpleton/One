package oms.pomelo.one.one.note;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import oms.pomelo.one.R;
import oms.pomelo.one.base.BaseActivity;
import oms.pomelo.one.one.bean.NoteInfo;
import oms.pomelo.one.utils.StatusBarUtils;
import oms.pomelo.one.utils.ToastUtil;

public class NotesActivity extends BaseActivity implements View.OnClickListener, NotesContract.NotesView {

    private ImageView mIvBack;
    private TextView mTvTitle;
    private RecyclerView mRcyNotes;
    private NotesPresenter mPresenter;
    private NotesAdapter mAdapter;
    private NoteInfo mInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        StatusBarUtils.transparencyBar(this);
        StatusBarUtils.StatusBarLightMode(this);
        initView();
        initListener();
        initData();
        initPresenter();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRcyNotes = findViewById(R.id.rcyNotes);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        mRcyNotes.setLayoutManager(manager);

        mAdapter.setOnItemClickListener(new NotesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, ImageView img) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("noteInfo", mInfo.getList().get(position));
                Intent intent = new Intent(NotesActivity.this, NoteDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(NotesActivity.this,
                        new Pair<View, String>(img, "CONTENT")).toBundle()); //使用new Pair<View, String>可添加多个共享元素
            }
        });
    }

    private void initView() {
        mIvBack = findViewById(R.id.ivLeft);
        mTvTitle = findViewById(R.id.tvTitle);
        mAdapter = new NotesAdapter(this);
    }

    private void initListener() {
        mIvBack.setOnClickListener(this);
    }

    private void initData() {
        mTvTitle.setText(getResources().getString(R.string.notes_title));
    }

    private void initPresenter() {
        mPresenter = new NotesPresenter(this);
        mPresenter.attachView(this);
        mPresenter.getNotes();
    }

    @Override
    public void onClick(View v) {
        if (v == mIvBack) {
            finish();
        }
    }

    @Override
    public void getNotesSuccess(NoteInfo info) {
        mInfo = info;
        mAdapter.setData(info.getList());
        mRcyNotes.setAdapter(mAdapter);
    }

    @Override
    public void getNotesFailed(String message) {
        toast(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}

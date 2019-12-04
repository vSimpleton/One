package oms.pomelo.one.one.note;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import oms.pomelo.one.R;
import oms.pomelo.one.base.BaseActivity;
import oms.pomelo.one.one.bean.NoteInfo;
import oms.pomelo.one.permission.DangerousPermissions;
import oms.pomelo.one.permission.PermissionsUtils;
import oms.pomelo.one.utils.StatusBarUtils;

/**
 * 小记广场详情页
 */
public class NoteDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mIvBack;
    private TextView mTvTitle;
    private ImageView mIvSave;
    private NoteInfo.NoteList mInfo;
    private ImageView mIvContent;
    private TextView mTvContent;
    private TextView mTvAddress;
    private TextView mTvAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        StatusBarUtils.transparencyBar(this);
        StatusBarUtils.StatusBarLightMode(this);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mIvBack.setOnClickListener(this);
        mIvSave.setOnClickListener(this);
    }

    private void initView() {
        mIvBack = findViewById(R.id.ivLeft);
        mTvTitle = findViewById(R.id.tvTitle);
        mIvSave = findViewById(R.id.ivRight);
        mIvContent = findViewById(R.id.ivContent);
        mTvContent = findViewById(R.id.tvContent);
        mTvAddress = findViewById(R.id.tvAddress);
        mTvAuthor = findViewById(R.id.tvAuthor);
    }

    private void initData() {
        mInfo = (NoteInfo.NoteList)getIntent().getSerializableExtra("noteInfo");
        if (mInfo != null) {
            Glide.with(this).load(mInfo.getImg_url()).into(mIvContent);
            mTvAuthor.setText(mInfo.getUser().getUser_name());
            mTvAddress.setText(mInfo.getAddr());
            mTvContent.setText(mInfo.getContent());
        }
        mIvSave.setVisibility(View.GONE);
        mTvTitle.setText("小记详情");
    }

    @Override
    public void onClick(View v) {
        if (v == mIvBack) {
            finishAfterTransition();
        } else if (v == mIvSave) {
//            PermissionsUtils.requestPermission(new String[]{DangerousPermissions.STORAGE}, this, new PermissionsUtils.OnGrantListener() {
//                @Override
//                public void onGrantedSuccess() {
//                    toast("权限已被允许");
//                }
//
//                @Override
//                public void onGrantedFailed() {
//                    toast("权限已被拒绝");
//                }
//            });
        }
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
    }
}

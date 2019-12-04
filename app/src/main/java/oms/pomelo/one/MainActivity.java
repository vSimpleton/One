package oms.pomelo.one;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import oms.pomelo.one.all.AllFragment;
import oms.pomelo.one.base.BaseActivity;
import oms.pomelo.one.me.MeFragment;
import oms.pomelo.one.one.view.OneFragment;
import oms.pomelo.one.utils.StatusBarUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    private ViewPager mViewPager;
    private RadioGroup mRgTab;
    private List<Fragment> mFragments;
    private MyFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtils.transparencyBar(this);
        StatusBarUtils.StatusBarLightMode(this);
        initView();
        initListener();
    }

    private void initView() {
        mViewPager = findViewById(R.id.viewPager);
        mRgTab = findViewById(R.id.rgTab);
        mFragments = new ArrayList<>(3);
        mFragments.add(new OneFragment());
        mFragments.add(new AllFragment());
        mFragments.add(new MeFragment());
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);
    }

    private void initListener() {
        mViewPager.addOnPageChangeListener(this);
        mRgTab.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 以下3个方法为ViewPager的监听
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        RadioButton radioButton = (RadioButton) mRgTab.getChildAt(position);
        radioButton.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * RadioGroup监听
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < group.getChildCount(); i++) {
            if (group.getChildAt(i).getId() == checkedId) {
                mViewPager.setCurrentItem(i);
                return;
            }
        }
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mList;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.mList = list;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mList == null ? null : mList.get(position);
        }

        @Override
        public int getCount() {
            return mList == null ? 0 : mList.size();
        }
    }
}

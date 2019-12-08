package oms.pomelo.one.opus.detail;

import android.content.Context;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import oms.pomelo.one.base.BaseModel;
import oms.pomelo.one.base.BaseObserver;
import oms.pomelo.one.utils.ToastUtil;

/**
 * NAME: Sherry
 * DATE: 2019-12-08
 */
public class OpusDetailPresenter extends OpusDetailContract.Presenter {

    public OpusDetailPresenter(Context context) {
        super(context);
    }

    @Override
    public void getOpusDetailInfo(String uuid) {
        getApiService().getOpusDetailInfo(uuid).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OpusDetailInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OpusDetailInfo opusDetailInfo) {
                        getMvpView().getOpusDetailSuccess(opusDetailInfo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showToast(mContext, "获取详情失败");
                    }

                    @Override
                    public void onComplete() {

                    }

                });
    }

    @Override
    public void getOpusCommentInfo(String uuid) {
        getApiService().getOpusCommentInfo(uuid).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ArrayList<OpusCommentInfo>>() {
                    @Override
                    protected void onSuccess(BaseModel<ArrayList<OpusCommentInfo>> t) {
                        getMvpView().getOpusCommentInfoSuccess(t.getData());
                    }

                    @Override
                    protected void onFailure(ArrayList<OpusCommentInfo> data, int res, String msg) {
                        ToastUtil.showToast(mContext, "获取动态详情评论失败");
                    }
                });
    }
}

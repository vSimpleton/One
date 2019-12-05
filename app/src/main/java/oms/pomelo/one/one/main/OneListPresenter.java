package oms.pomelo.one.one.main;

import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import oms.pomelo.one.base.BaseModel;
import oms.pomelo.one.base.BaseObserver;
import oms.pomelo.one.one.bean.OneListInfo;
import oms.pomelo.one.utils.ToastUtil;

/**
 * Created by Sherry on 2019/12/3
 */

public class OneListPresenter extends OneListContract.Presenter{

    public OneListPresenter(Context context) {
        super(context);
    }

    @Override
    public void getOneListInfo(String date) {
        getApiService().getOneListInfo(date).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<OneListInfo>() {
                    @Override
                    protected void onSuccess(BaseModel<OneListInfo> t) {
                        getMvpView().getOneListSuccess(t.getData());
                    }

                    @Override
                    protected void onFailure(OneListInfo data, int code, String message) {
                        getMvpView().getOneListFailed(message);
                    }

                });
    }

    @Override
    public void postLike(String id) {
        getApiService().postLike(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        getMvpView().postLikeSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showTextToast(mContext, "点赞失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

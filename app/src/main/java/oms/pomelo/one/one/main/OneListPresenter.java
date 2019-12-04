package oms.pomelo.one.one.main;

import android.content.Context;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import oms.pomelo.one.base.BaseModel;
import oms.pomelo.one.base.BaseObserver;
import oms.pomelo.one.one.bean.OneListInfo;
import oms.pomelo.one.one.main.OneListContract;

/**
 * Created by Sherry on 2019/12/3
 */

public class OneListPresenter extends OneListContract.Presenter{

    public OneListPresenter(Context context) {
        super(context);
    }

    @Override
    public void getOneListInfo() {
        getApiService().getOneListInfo().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
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
}

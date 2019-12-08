package oms.pomelo.one.opus.list;

import android.content.Context;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import oms.pomelo.one.base.BaseModel;
import oms.pomelo.one.base.BaseObserver;

/**
 * NAME: Sherry
 * DATE: 2019-12-07
 */
public class OpusInfoPresenter extends OpusInfoContract.Presenter {

    public OpusInfoPresenter(Context context) {
        super(context);
    }

    @Override
    public void getOpusInfo() {
        getApiService().getOpusInfo().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ArrayList<OpusInfo>>() {
                    @Override
                    protected void onSuccess(BaseModel<ArrayList<OpusInfo>> t) {
                        getMvpView().getOpusInfoSuccess(t.getData());
                    }

                    @Override
                    protected void onFailure(ArrayList<OpusInfo> data, int res, String msg) {
                        getMvpView().getOpusInfoFailed(msg);
                    }

//                    @Override
//                    protected void onSuccess(BaseModel<OpusInfo> t) {
//                        getMvpView().getOpusInfoSuccess(t.getData());
//                    }
//
//                    @Override
//                    protected void onFailure(OpusInfo data, int res, String msg) {
//                        getMvpView().getOpusInfoFailed(msg);
//                    }
                });
    }
}

package oms.pomelo.one.opus.list;

import android.content.Context;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import oms.pomelo.one.base.BaseModel;
import oms.pomelo.one.base.BaseObserver;
import oms.pomelo.one.utils.ToastUtil;

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
                });
    }

    @Override
    public void postOpusLike(String uuid, String action) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data; charset=utf-8"), action);
        getApiService().postOpusLike(uuid, requestBody).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    protected void onSuccess(BaseModel<Object> t) {
                        ToastUtil.showToast(mContext, t.getMsg());
                    }

                    @Override
                    protected void onFailure(Object data, int res, String msg) {
                        ToastUtil.showToast(mContext, msg);
                    }
                });
    }
}

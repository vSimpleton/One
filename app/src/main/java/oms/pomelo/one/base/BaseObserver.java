package oms.pomelo.one.base;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import oms.pomelo.one.BuildConfig;

/**
 * Created by Sherry on 2019/11/28
 * 创建Base抽象类实现Observer
 */

public abstract class BaseObserver<T> implements Observer<BaseModel<T>> {

    private static final String TAG = "BaseObserver";

    @Override
    public void onSubscribe(Disposable d) {
        Log.i(TAG, "onSubscribe: ");
    }

    @Override
    public void onNext(BaseModel<T> value) {
        if (value != null) {
            onSuccess(value);
            /**
             * 根据具体要求修改
             */
            if (value.getRes() != 0) {
                //服务器端错误
                if (BuildConfig.DEBUG) {
                    onFailure(null, value.getRes(), value.getMsg());
                }
                if (value.getRes() == 401 || value.getRes() == 403) {
                    onFailure(null, -1, "无权限操作");
                }
            } else {
                onSuccess(value);
            }
//            if (value.getRes() == 0) {
//                onSuccess(value);
//            }
        } else {
            //客户端错误
            onFailure(null, -1, "当前网络不佳，请稍后再试");
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "Throwable: " + e.getMessage());
    }

    @Override
    public void onComplete() {
        Log.e(TAG, "onComplete: " );
    }

    /**
     * 返回成功
     *
     * @param t
     */
    protected abstract void onSuccess(BaseModel<T> t);

    /**
     * 返回失败
     *
     * @param res    错误码
     * @param msg 错误信息
     */
    protected abstract void onFailure(T data, int res, String msg);
}

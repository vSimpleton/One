package oms.pomelo.one.one.main;

import android.content.Context;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import oms.pomelo.one.base.BaseModel;
import oms.pomelo.one.base.BaseObserver;
import oms.pomelo.one.one.bean.CommentInfo;
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
                        ToastUtil.showToast(mContext, "获取文章列表失败");
                    }

                });
    }

    @Override
    public void getCommentList(String itemId) {
        getApiService().getCommentList(itemId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<CommentInfo>() {
                    @Override
                    protected void onSuccess(BaseModel<CommentInfo> t) {
                        getMvpView().getCommentListSuccess(t.getData());
                    }

                    @Override
                    protected void onFailure(CommentInfo data, int res, String msg) {
                        ToastUtil.showToast(mContext, "获取评论失败");
                    }
                });
    }
}

package oms.pomelo.one.one.main;

import android.content.Context;

import oms.pomelo.one.base.BaseMvpView;
import oms.pomelo.one.base.BasePresenter;
import oms.pomelo.one.one.bean.CommentInfo;
import oms.pomelo.one.one.bean.OneListInfo;

/**
 * Created by Sherry on 2019/12/3
 */

public interface OneListContract {

    interface OneListView extends BaseMvpView {

        void getOneListSuccess(OneListInfo info);

        void getCommentListSuccess(CommentInfo info);

    }

    abstract class Presenter extends BasePresenter<OneListView> {

        public Presenter(Context context) {
            super(context);
        }

        public abstract void getOneListInfo(String date);

        public abstract void getCommentList(String itemId);
    }

}

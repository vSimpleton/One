package oms.pomelo.one.one;

import android.content.Context;

import oms.pomelo.one.base.BaseMvpView;
import oms.pomelo.one.base.BasePresenter;
import oms.pomelo.one.one.bean.OneListInfo;

/**
 * Created by Sherry on 2019/12/3
 */

public interface OneListContract {

    interface OneListView extends BaseMvpView {

        void getOneListSuccess(OneListInfo info);

        void getOneListFailed(String message);

    }

    abstract class Presenter extends BasePresenter<OneListView> {

        public Presenter(Context context) {
            super(context);
        }

        public abstract void getOneListInfo();
    }

}

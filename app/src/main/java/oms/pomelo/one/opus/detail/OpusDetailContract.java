package oms.pomelo.one.opus.detail;

import android.content.Context;

import java.util.ArrayList;

import oms.pomelo.one.base.BaseMvpView;
import oms.pomelo.one.base.BasePresenter;

/**
 * NAME: Sherry
 * DATE: 2019-12-08
 */
public interface OpusDetailContract {

    interface OpusDetailView extends BaseMvpView {
        void getOpusDetailSuccess(OpusDetailInfo info);
        void getOpusCommentInfoSuccess(ArrayList<OpusCommentInfo> info);
    }

    abstract class Presenter extends BasePresenter<OpusDetailView> {

        public Presenter(Context context) {
            super(context);
        }

        public abstract void getOpusDetailInfo(String uuid);

        public abstract void getOpusCommentInfo(String uuid);
    }

}

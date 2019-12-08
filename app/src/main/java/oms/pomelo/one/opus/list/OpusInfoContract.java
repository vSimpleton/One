package oms.pomelo.one.opus.list;

import android.content.Context;

import java.util.ArrayList;

import oms.pomelo.one.base.BaseMvpView;
import oms.pomelo.one.base.BasePresenter;

/**
 * NAME: Sherry
 * DATE: 2019-12-07
 */
public interface OpusInfoContract {

    interface OpusInfoView extends BaseMvpView {
        void getOpusInfoSuccess(ArrayList<OpusInfo> opusInfo);
        void getOpusInfoFailed(String msg);
    }

    abstract class Presenter extends BasePresenter<OpusInfoView> {

        public Presenter(Context context) {
            super(context);
        }

        public abstract void getOpusInfo();
    }

}

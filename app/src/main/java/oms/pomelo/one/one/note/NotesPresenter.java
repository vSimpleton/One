package oms.pomelo.one.one.note;

import android.content.Context;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import oms.pomelo.one.base.BaseModel;
import oms.pomelo.one.base.BaseObserver;
import oms.pomelo.one.one.bean.NoteInfo;

/**
 * Created by Sherry on 2019/12/4
 */

public class NotesPresenter extends NotesContract.Presenter {

    public NotesPresenter(Context context) {
        super(context);
    }

    @Override
    public void getNotes() {
        getApiService().getNoteInfo().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<NoteInfo>() {
                    @Override
                    protected void onSuccess(BaseModel<NoteInfo> t) {
                        getMvpView().getNotesSuccess(t.getData());
                    }

                    @Override
                    protected void onFailure(NoteInfo data, int code, String message) {
                        getMvpView().getNotesFailed(message);
                    }
                });
    }
}

package oms.pomelo.one.one.note;

import android.content.Context;
import oms.pomelo.one.base.BaseMvpView;
import oms.pomelo.one.base.BasePresenter;
import oms.pomelo.one.one.bean.NoteInfo;

/**
 * Created by Sherry on 2019/12/4
 */

public interface NotesContract {

    interface NotesView extends BaseMvpView {

        void getNotesSuccess(NoteInfo info);
        void getNotesFailed(String message);

    }

    abstract class Presenter extends BasePresenter<NotesView> {

        public Presenter(Context context) {
            super(context);
        }

        public abstract void getNotes();

    }

}

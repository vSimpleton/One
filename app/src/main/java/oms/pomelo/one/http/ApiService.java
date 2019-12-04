package oms.pomelo.one.http;

import io.reactivex.Observable;
import oms.pomelo.one.base.BaseModel;
import oms.pomelo.one.one.bean.NoteInfo;
import oms.pomelo.one.one.bean.OneListInfo;
import retrofit2.http.GET;

/**
 * Created by Sherry on 2019/12/3
 */

public interface ApiService {

    @GET(ApiHelper.GET_ONE_CONTENT_LIST)
    Observable<BaseModel<OneListInfo>> getOneListInfo();

    @GET(ApiHelper.GET_NOTE_INFO)
    Observable<BaseModel<NoteInfo>> getNoteInfo();

}

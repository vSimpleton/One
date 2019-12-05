package oms.pomelo.one.http;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import oms.pomelo.one.R;
import oms.pomelo.one.base.BaseModel;
import oms.pomelo.one.one.bean.NoteInfo;
import oms.pomelo.one.one.bean.OneListInfo;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

/**
 * Created by Sherry on 2019/12/3
 */

public interface ApiService {

//    @GET(ApiHelper.GET_ONE_CONTENT_LIST)
    @GET("channel/one/{date}/广州市")
    Observable<BaseModel<OneListInfo>> getOneListInfo(@Path("date") String date);

    @GET(ApiHelper.GET_NOTE_INFO)
    Observable<BaseModel<NoteInfo>> getNoteInfo();

    @FormUrlEncoded
    @POST(ApiHelper.POST_LIST_LIKE)
    Observable<Object> postLike(@Field("source_id") String id);

}

package oms.pomelo.one.http;

import io.reactivex.Observable;
import oms.pomelo.one.base.BaseModel;
import oms.pomelo.one.one.bean.CommentInfo;
import oms.pomelo.one.one.bean.NoteInfo;
import oms.pomelo.one.one.bean.OneListInfo;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @GET(ApiHelper.GET_COMMENT_LIST)
    Observable<BaseModel<CommentInfo>> getCommentList(@Path("id") String itemId);

}

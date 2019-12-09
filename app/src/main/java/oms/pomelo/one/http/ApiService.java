package oms.pomelo.one.http;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import oms.pomelo.one.base.BaseModel;
import oms.pomelo.one.one.bean.CommentInfo;
import oms.pomelo.one.one.bean.NoteInfo;
import oms.pomelo.one.one.bean.OneListInfo;
import oms.pomelo.one.opus.detail.OpusCommentInfo;
import oms.pomelo.one.opus.detail.OpusDetailInfo;
import oms.pomelo.one.opus.list.OpusInfo;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Sherry on 2019/12/3
 */

public interface ApiService {

    @Headers("urlName:one")
    @GET(ApiHelper.GET_ONE_CONTENT_LIST)
    Observable<BaseModel<OneListInfo>> getOneListInfo(@Path("date") String date);

    @Headers("urlName:one")
    @GET(ApiHelper.GET_NOTE_INFO)
    Observable<BaseModel<NoteInfo>> getNoteInfo();

    @Headers("urlName:one")
    @FormUrlEncoded
    @POST(ApiHelper.POST_LIST_LIKE)
    Observable<Object> postLike(@Field("source_id") String id);

    @Headers("urlName:one")
    @GET(ApiHelper.GET_COMMENT_LIST)
    Observable<BaseModel<CommentInfo>> getCommentList(@Path("id") String itemId);

    @Headers("urlName:judou")
    @GET(ApiHelper.GET_JUDOU_OPUS)
    Observable<BaseModel<ArrayList<OpusInfo>>> getOpusInfo();

    @Headers("urlName:judou")
    @GET(ApiHelper.GET_JUDOU_OPUS_DETAIL)
    Observable<OpusDetailInfo> getOpusDetailInfo(@Path("uuid") String uuid);

    @Headers("urlName:judou")
    @GET(ApiHelper.GET_JUDOU_OPUS_COMMENT)
    Observable<BaseModel<ArrayList<OpusCommentInfo>>> getOpusCommentInfo(@Path("uuid") String uuid);

    @FormUrlEncoded
    @POST(ApiHelper.POST_JUDOU_OPUS_COMMENT)
    Observable<BaseModel<Object>> postOpusComment(@Field("uuid") String uuid, @Body RequestBody requestBody);

    @Headers("urlName:judou")
    @Multipart
    @POST(ApiHelper.POST_JUDOU_OPUS_LIKE)
    Observable<BaseModel<Object>> postOpusLike(@Path("uuid") String uuid, @Part("action") RequestBody requestBody);

}

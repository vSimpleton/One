package oms.pomelo.one.http;

/**
 * NAME: Sherry
 * DATE: 2019-12-02
 */
public class ApiHelper {

    public static final String BASE_URL = "http://v3.wufazhuce.com:8000/api/";

    /**
     * 首页每日文章列表
     */
    public static final String GET_ONE_CONTENT_LIST = "channel/one/0/广州市";

    /**
     * 小记广场
     */
    public static final String GET_NOTE_INFO = "diary/square/more/0";

    /**
     * 文章收藏
     */
    public static final String POST_LIST_COLLECT = "collection/add";

    /**
     * 文章点赞
     */
    public static final String POST_LIST_LIKE = "praise/add";

    /**
     * 文章评论
     */
    public static final String GET_COMMENT_LIST = "comment/praiseandtime/essay/{id}/0";

}

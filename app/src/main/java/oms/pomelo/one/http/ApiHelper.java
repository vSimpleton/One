package oms.pomelo.one.http;

/**
 * NAME: Sherry
 * DATE: 2019-12-02
 */
public class ApiHelper {

    /**
     * ONE base_url
     */
    public static final String ONE_BASE_URL = "http://v3.wufazhuce.com:8000/api/";

    /**
     * 句读 base_url
     */
    public static final String JUDOU_BASE_URL = "https://judouapp.com/api/";

    /**
     * 首页每日文章列表
     */
    public static final String GET_ONE_CONTENT_LIST = "channel/one/{date}/广州市";

    /**
     * 小记广场
     */
    public static final String GET_NOTE_INFO = "diary/square/more/0";

    /**
     * 文章点赞
     */
    public static final String POST_LIST_LIKE = "praise/add";

    /**
     * 文章评论
     */
    public static final String GET_COMMENT_LIST = "comment/praiseandtime/essay/{id}/0";

    /**
     * 句读广场动态
     */
    public static final String GET_JUDOU_OPUS = "v5/statuses";

    /**
     * 句读广场动态详情
     */
    public static final String GET_JUDOU_OPUS_DETAIL = "v6/op/sentences/{uuid}";

    /**
     * 句读广场动态详情的评论
     */
    public static final String GET_JUDOU_OPUS_COMMENT = "v6/op/sentences/{uuid}/comments/latest";

    /**
     * 句读广场点赞
     */
    public static final String POST_JUDOU_OPUS_LIKE = "v5/sentences/{uuid}/likes";

    /**
     * 句读广场评论
     */
    public static final String POST_JUDOU_OPUS_COMMENT = "v5/sentences/{uuid}/comments";

}

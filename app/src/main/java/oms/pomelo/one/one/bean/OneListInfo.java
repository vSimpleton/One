package oms.pomelo.one.one.bean;


import java.util.List;

/**
 * NAME: Sherry
 * DATE: 2019-12-02
 * 首页One的ContentList
 */
public class OneListInfo {
    public String id;
    public Weather weather;
    public String date;
    public MenuBean menu;
    public List<ContentList> contents;

    public static class Weather {
        public String city_name; //所在城市
        public String date; //当前日期
        public String temperature; //当前温度
        public String humidity; //当前湿度
        public String climate; //天气（晴）
        public String wind_direction; //风（4级）
        public String hurricane; //风向（北风）
        public IconsBean icons;

        public static class IconsBean {
            public String day;
            public String night;
        }
    }

    /**
     * 导航项
     */
    public static class MenuBean {
        public String vol; //期数
        public List<Content> list; //题目

        public static class Content {
            public String content_type; //类型
            public String content_id; //内容id
            public String title; //题目
            public List<String> serial_list; //连载文章的id
        }
    }

    public static class ContentList {
        public String id;
        public String category;
        public int display_category;
        public String item_id;
        public String title;
        public String forward;
        public String img_url;
        public int like_count;
        public String post_date;
        public String last_update_date;
        public AuthorInfo author;
        public String video_url;
        public String audio_url;
        public int audio_platform;
        public String start_video;
        public int has_reading;
        public String volume;
        public String pic_info;
        public String words_info;
        public String subtitle;
        public int number;
        public int serial_id;
        public int movie_story_id;
        public int ad_id;
        public int ad_type;
        public String ad_pvurl;
        public String ad_linkurl;
        public String ad_makettime;
        public String ad_closetime;
        public String ad_share_cnt;
        public String ad_pvurl_vendor;
        public String content_id;
        public String content_type;
        public String content_bgcolor;
        public String share_url;
        public ShareInfo share_info;
        public ShareList share_list;
        public String orientation;
        public AnswererBean answerer;
        public String music_name;
        public String audio_platform_icon;
        public String audio_platform_name;
        public String audio_author;
        public String audio_album;
        public String cover;
        public String bg_color;
        public List<?> serial_list;
        public List<?> tag_list;

        public static class AuthorInfo {
            public String user_id;
            public String user_name;
            public String desc;
            public String wb_name;
            public String is_settled;
            public String settled_type;
            public String summary;
            public String fans_total;
            public String web_url;
        }

        public static class ShareInfo {
            public String url;
            public String image;
            public String title;
            public String content;
        }

        public static class ShareList {
            public WxBean wx;
            public WxTimelineBean wx_timeline;
            public WeiboBean weibo;
            public QqBean qq;

            public static class WxBean {
                public String title;
                public String desc;
                public String link;
                public String imgUrl;
                public String audio;
            }

            public static class WxTimelineBean {
                public String title;
                public String desc;
                public String link;
                public String imgUrl;
                public String audio;
            }

            public static class WeiboBean {
                public String title;
                public String desc;
                public String link;
                public String imgUrl;
                public String audio;
            }

            public static class QqBean {
                public String title;
                public String desc;
                public String link;
                public String imgUrl;
                public String audio;
            }
        }

        public static class AnswererBean {
            public String user_id;
            public String user_name;
            public String desc;
            public String wb_name;
            public String is_settled;
            public String settled_type;
            public String summary;
            public String fans_total;
            public String web_url;
        }
    }
}

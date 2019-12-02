package oms.pomelo.one.one.bean;

import java.util.List;

/**
 * NAME: Sherry
 * DATE: 2019-12-02
 * 小记广场
 */
public class NoteInfo {

    public List<NoteList> list;

    public static class NoteList {
        /**
         * id : 1239845
         * user_id : 10784431
         * weather : 多云
         * content : 十一月要买好看衣服，十二月有白雪和圣诞树，一月里来是新年，二月天气回暖，樱花和春天，一点一点数着过，日子也就不难熬了。
         * picture : http://image.wufazhuce.com/FrtdP3mdYaF_BCHHx-sSMf9biNjy
         * input_date : 2019-12-02 06:43:13
         * img_url : http://image.wufazhuce.com/Fv33Y05AQrG6oOYW-DgXrduJNDmc
         * addr :
         * is_public : 1
         * reviewed : 1
         * remark :
         * diary_id : 222306
         * img_url_thumb_h : 240
         * user : {"user_id":"10784431","user_name":"TenrZmriOP","web_url":"http://thirdwx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEIulV3P1JEBH2JmFA8tM7gIJ9xmJptT6KibvPrx889nwtrGWPRnqVZwggic4vNlS9X1yxibvUNaVFv6Q/132"}
         * img_url_thumb : http://image.wufazhuce.com/Fv33Y05AQrG6oOYW-DgXrduJNDmc?imageView2/2/w/320
         * praisenum : 9
         */

        public String id;
        public String user_id;
        public String weather;
        public String content;
        public String picture;
        public String input_date;
        public String img_url;
        public String addr;
        public String is_public;
        public String reviewed;
        public String remark;
        public String diary_id;
        public String img_url_thumb_h;
        public UserInfo user;
        public String img_url_thumb;
        public int praisenum;

        public static class UserInfo {
            public String user_id;
            public String user_name;
            public String web_url;

        }
    }
}

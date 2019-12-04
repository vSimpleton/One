package oms.pomelo.one.one.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * NAME: Sherry
 * DATE: 2019-12-02
 * 小记广场
 */
public class NoteInfo implements Serializable {

    private ArrayList<NoteList> list;

    public ArrayList<NoteList> getList() {
        return list;
    }

    public void setList(ArrayList<NoteList> list) {
        this.list = list;
    }

    public static class NoteList implements Serializable{
        private String id;
        private String user_id;
        private String weather;
        private String content;
        private String picture;
        private String input_date;
        private String img_url;
        private String addr;
        private String is_public;
        private String reviewed;
        private String remark;
        private String diary_id;
        private String img_url_thumb_h;
        private UserInfo user;
        private String img_url_thumb;
        private int praisenum;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getInput_date() {
            return input_date;
        }

        public void setInput_date(String input_date) {
            this.input_date = input_date;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getIs_public() {
            return is_public;
        }

        public void setIs_public(String is_public) {
            this.is_public = is_public;
        }

        public String getReviewed() {
            return reviewed;
        }

        public void setReviewed(String reviewed) {
            this.reviewed = reviewed;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getDiary_id() {
            return diary_id;
        }

        public void setDiary_id(String diary_id) {
            this.diary_id = diary_id;
        }

        public String getImg_url_thumb_h() {
            return img_url_thumb_h;
        }

        public void setImg_url_thumb_h(String img_url_thumb_h) {
            this.img_url_thumb_h = img_url_thumb_h;
        }

        public UserInfo getUser() {
            return user;
        }

        public void setUser(UserInfo user) {
            this.user = user;
        }

        public String getImg_url_thumb() {
            return img_url_thumb;
        }

        public void setImg_url_thumb(String img_url_thumb) {
            this.img_url_thumb = img_url_thumb;
        }

        public int getPraisenum() {
            return praisenum;
        }

        public void setPraisenum(int praisenum) {
            this.praisenum = praisenum;
        }

        public static class UserInfo implements Serializable{
            private String user_id;
            private String user_name;
            private String web_url;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getWeb_url() {
                return web_url;
            }

            public void setWeb_url(String web_url) {
                this.web_url = web_url;
            }
        }
    }
}

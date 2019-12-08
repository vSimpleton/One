package oms.pomelo.one.opus.detail;

import java.util.List;

/**
 * NAME: Sherry
 * DATE: 2019-12-08
 * 动态详情页
 */
public class OpusDetailInfo {
    private String uuid;
    private String content;
    private int published_at;
    private int comment_count;
    private int like_count;
    private boolean is_private;
    private boolean is_collected;
    private boolean is_liked;
    private UserBean user;
    private List<PicturesBean> pictures;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPublished_at() {
        return published_at;
    }

    public void setPublished_at(int published_at) {
        this.published_at = published_at;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public boolean isIs_private() {
        return is_private;
    }

    public void setIs_private(boolean is_private) {
        this.is_private = is_private;
    }

    public boolean isIs_collected() {
        return is_collected;
    }

    public void setIs_collected(boolean is_collected) {
        this.is_collected = is_collected;
    }

    public boolean isIs_liked() {
        return is_liked;
    }

    public void setIs_liked(boolean is_liked) {
        this.is_liked = is_liked;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<PicturesBean> getPictures() {
        return pictures;
    }

    public void setPictures(List<PicturesBean> pictures) {
        this.pictures = pictures;
    }

    public static class UserBean {
        private long uid;
        private String nickname;
        private String avatar;
        private boolean is_member;
        private Object member_expired_at;

        public long getUid() {
            return uid;
        }

        public void setUid(long uid) {
            this.uid = uid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public boolean isIs_member() {
            return is_member;
        }

        public void setIs_member(boolean is_member) {
            this.is_member = is_member;
        }

        public Object getMember_expired_at() {
            return member_expired_at;
        }

        public void setMember_expired_at(Object member_expired_at) {
            this.member_expired_at = member_expired_at;
        }
    }

    public static class PicturesBean {
        /**
         * id : 690415
         * url : https://judou.oss-cn-beijing.aliyuncs.com/images/status/2019/12/8/d61f0355-6b02-4beb-9934-47438a8a2cb7.jpg
         * color : #1c1a15
         * copyright :
         */

        private int id;
        private String url;
        private String color;
        private String copyright;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }
    }
}

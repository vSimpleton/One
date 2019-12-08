package oms.pomelo.one.opus.list;

import java.util.ArrayList;

/**
 * NAME: Sherry
 * DATE: 2019-12-07
 * 动态页实体类
 */
public class OpusInfo {

    private String uuid;
    private String timestamp;
    private String content;
    private int comment_count;
    private int like_count;
    private int created_at;
    private String published_at;
    private boolean is_private;
    private boolean is_original;
    private String share_url;
    private boolean is_liked;
    private boolean is_collected;
    private boolean is_ad;
    private UserBean user;
    private boolean is_editable;
    private ArrayList<PictureBean> pictures;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public boolean isIs_private() {
        return is_private;
    }

    public void setIs_private(boolean is_private) {
        this.is_private = is_private;
    }

    public boolean isIs_original() {
        return is_original;
    }

    public void setIs_original(boolean is_original) {
        this.is_original = is_original;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public boolean isIs_liked() {
        return is_liked;
    }

    public void setIs_liked(boolean is_liked) {
        this.is_liked = is_liked;
    }

    public boolean isIs_collected() {
        return is_collected;
    }

    public void setIs_collected(boolean is_collected) {
        this.is_collected = is_collected;
    }

    public boolean isIs_ad() {
        return is_ad;
    }

    public void setIs_ad(boolean is_ad) {
        this.is_ad = is_ad;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public boolean isIs_editable() {
        return is_editable;
    }

    public void setIs_editable(boolean is_editable) {
        this.is_editable = is_editable;
    }

    public ArrayList<PictureBean> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<PictureBean> pictures) {
        this.pictures = pictures;
    }

    public static class UserBean {
        private int uid;
        private long uid2;
        private String uuid;
        private String nickname;
        private String avatar;
        private boolean is_member;
        private Object member_expired_at;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public long getUid2() {
            return uid2;
        }

        public void setUid2(long uid2) {
            this.uid2 = uid2;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
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

    public static class PictureBean {

        private int id;
        private String url;
        private String title;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }
    }
}

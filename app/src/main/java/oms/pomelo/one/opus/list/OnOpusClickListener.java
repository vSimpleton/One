package oms.pomelo.one.opus.list;

/**
 * NAME: Sherry
 * DATE: 2019-12-07
 * OpusView点击事件监听
 */
public interface OnOpusClickListener {

    void onClickOpusView(String uuid);

    void onClickLike();

    void onClickComment(String uuid);

    void onClickShare();

}

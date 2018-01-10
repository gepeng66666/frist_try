package com.geekghost.gp.geekghost.entity;

/**
 * 作者：戈鹏
 * on 2018/1/6 08:56
 */

public class CommentListBean {

    /**
     * dataId : ff8080815ffc3a7f01602c1aa9810ecf
     * likeNum : 0
     * msg : 手机和电视都连上了，咋不能投屏呢？
     * phoneNumber : (≧▽≦)
     * time : 2017-12-06 21:53:53
     * userPic : http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJfia74qOtb5aZdc3WpQjxRibia6GkeQuJJCibR5B2q3vzicv3vTHGaO8xKomzc7cgOKW13t8Xn4iaBwv0g/0
     */

    private String dataId;
    private int likeNum;
    private String msg;
    private String phoneNumber;
    private String time;
    private String userPic;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    @Override
    public String toString() {
        return "CommentListBean{" +
                "dataId='" + dataId + '\'' +
                ", likeNum=" + likeNum +
                ", msg='" + msg + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", time='" + time + '\'' +
                ", userPic='" + userPic + '\'' +
                '}';
    }
}

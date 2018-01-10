package com.geekghost.gp.geekghost.entity;

import java.util.List;

/**
 * 作者：戈鹏
 * on 2018/1/2 20:01
 */

public class RetBean <T>{
    private List<HotSearchListBean> hotSearchList;
    private List<T> list;

    public List<HotSearchListBean> getHotSearchList() {
        return hotSearchList;
    }

    public void setHotSearchList(List<HotSearchListBean> hotSearchList) {
        this.hotSearchList = hotSearchList;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public static class HotSearchListBean {
        /**
         * refCounter : 1
         * cnname : xingjichuanyue
         * siteId : 1
         * simplename : xjcy
         * id : ff8080815a5f91db015a68a763b750d5
         * tagName : 星际穿越
         * createdtime : 2017-02-23 09:48:04
         * enname :
         */

        private int refCounter;
        private String cnname;
        private String siteId;
        private String simplename;
        private String id;
        private String tagName;
        private String createdtime;
        private String enname;

        public int getRefCounter() {
            return refCounter;
        }

        public void setRefCounter(int refCounter) {
            this.refCounter = refCounter;
        }

        public String getCnname() {
            return cnname;
        }

        public void setCnname(String cnname) {
            this.cnname = cnname;
        }

        public String getSiteId() {
            return siteId;
        }

        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }

        public String getSimplename() {
            return simplename;
        }

        public void setSimplename(String simplename) {
            this.simplename = simplename;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public String getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(String createdtime) {
            this.createdtime = createdtime;
        }

        public String getEnname() {
            return enname;
        }

        public void setEnname(String enname) {
            this.enname = enname;
        }
    }



}

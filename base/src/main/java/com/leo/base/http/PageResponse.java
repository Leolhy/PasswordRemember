package com.leo.base.http;

import java.util.List;

/**
 * Project: PasswordRemember
 * Author: Leoying
 * Date: 2022/8/10 16:19
 * Desc:
 */
public class PageResponse<T> extends BaseResponse {

    private DataBean<T> data;

    public DataBean<T> getData() {
        return data;
    }

    public void setData(DataBean<T> data) {
        this.data = data;
    }

    public static class DataBean<T> {
        /**
         * curPage : 1
         * datas : []
         * offset : 0
         * over : false
         * pageCount : 9
         * size : 15
         * total : 131
         */
        private int curPage = 0;
        private int offset = 0;
        private boolean isOver = false;
        private int pageCount = 0;
        private int size = 0;
        private int total = 0;
        private List<T> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return isOver;
        }

        public void setOver(boolean over) {
            isOver = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<T> getDatas() {
            return datas;
        }

        public void setDatas(List<T> datas) {
            this.datas = datas;
        }
    }
}

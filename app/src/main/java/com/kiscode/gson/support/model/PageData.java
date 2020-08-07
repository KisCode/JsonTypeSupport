package com.kiscode.gson.support.model;

/**
 * Description:
 * Author: KrisKeno
 * Date : 2020/8/7 10:01 PM
 **/
public class PageData<T> {

    /**
     * total : 68
     * pageSize : 20
     * pageIndex : 1
     */

    private int total;
    private int pageSize;
    private int pageIndex;
    private T data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageData{" +
                "total=" + total +
                ", pageSize=" + pageSize +
                ", pageIndex=" + pageIndex +
                ", data=" + data +
                '}';
    }
}

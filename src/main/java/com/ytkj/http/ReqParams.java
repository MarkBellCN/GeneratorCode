package com.ytkj.http;

import java.util.List;

/**
 * @Author: MarkBell
 * @Description:
 * @Date 2018/4/24
 */
public class ReqParams<T> {

    private int pageNum;
    private int pageSize=10;
    private T data;
    private List<SortItem> sortMap;
    private List<String> headerEn;
    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getHeaderEn() {
        return headerEn;
    }

    public void setHeaderEn(List<String> headerEn) {
        this.headerEn = headerEn;
    }

    public List<SortItem> getSortMap() {
        return sortMap;
    }

    public void setSortMap(List<SortItem> sortMap) {
        this.sortMap = sortMap;
    }
}

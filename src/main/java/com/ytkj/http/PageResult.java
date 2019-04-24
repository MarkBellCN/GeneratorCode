package com.ytkj.http;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * Created by lafangyuan on 2017/11/3.
 */
public class PageResult {

    private long total; // 总记录
    private List rows; //显示的记录

    public PageResult(Page page){
        this.total = page.getTotal();
        this.rows = page.getResult();
    }
    public PageResult(long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResult() {}

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}

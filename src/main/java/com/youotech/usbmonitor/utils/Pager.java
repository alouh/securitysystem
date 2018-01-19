package com.youotech.usbmonitor.utils;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author sundongyang
 * @date 2017/6/6 0006
 * @time 09:58
 * @desc Pager类封装分页信息
 * @see
 */
public class Pager<T> {

    private Integer pageSize;

    private Integer total;

    private Integer totalPage;

    private Integer currentPage;

    private List<T> rows;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }


    public Pager(Integer pageNo, Integer pageSize, List<T> rows, Integer total) {
        if (rows == null) {
            return;
        }

        //总记录数
        this.total = total;

        //每页显示多小条数据
        this.pageSize = pageSize;

        //总页数
        this.totalPage = this.total % this.pageSize == 0 ? this.total / this.pageSize : this.total / this.pageSize + 1;

        //当前第几页
        if (this.totalPage < pageNo) {
            this.currentPage = this.totalPage;
        } else {
            this.currentPage = pageNo;
        }

        //起始索引
        Integer fromIndex = this.pageSize * (this.currentPage - 1);

        //结束索引
        Integer endIndex = null;
        if (this.pageSize * this.currentPage > this.total) {
            endIndex = this.total;
        } else {
            endIndex = this.pageSize * this.currentPage;
        }

        //this.rows = rows.subList(fromIndex, endIndex);
        setRows(rows);
    }

    @SuppressWarnings("unchecked")
    public Iterator<T> iterator() {
        return (Iterator<T>) (rows == null ? Collections.emptyList().iterator() : rows.iterator());
    }

    @Override
    public String toString() {
        return "Pager{" +
                "pageSize=" + pageSize +
                ", total=" + total +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                '}';
    }

}

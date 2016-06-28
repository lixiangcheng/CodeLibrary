package com.lee.util;

import java.io.Serializable;
import java.util.List;

/**
 * ∑÷“≥¿‡
 * Created by lixiangcheng on 16/4/23.
 */
public class ResultView<T>
        implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int DEFAULT_NAVIGATOR_SIZE = 5;
    public static final int DEFAULT_PAGE_SIZE = 20;
    private int currentPage = 1;
    private int pageSize = 20;
    private int totalCount;
    private boolean havaNextPage;
    private boolean havePrePage;
    private int navigatorSize;
    private List<T> items;

    public ResultView() {
    }

    public ResultView(int totalCount, int pageSize, int currentPage) {
        this(totalCount, pageSize, currentPage, 5);
    }

    public ResultView(int totalCount, int pageSize, int currentPage, int navigatorSize) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.navigatorSize = navigatorSize;
    }

    public int getPageCount() {
        int pageCount = 0;
        if (this.pageSize != 0) {
            pageCount = this.totalCount / this.pageSize;
            if (this.totalCount % this.pageSize != 0) {
                pageCount++;
            }
        }
        return pageCount;
    }

    public int getCurrentPage() {
        this.currentPage = (this.currentPage < getPageCount() ? this.currentPage :
                getPageCount());
        this.currentPage = (this.currentPage < 1 ? 1 : this.currentPage);

        return this.currentPage;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public boolean isHavaNextPage() {
        this.havaNextPage = false;
        if ((getPageCount() > 1) && (getPageCount() > getCurrentPage())) {
            this.havaNextPage = true;
        }
        return this.havaNextPage;
    }

    public boolean isHavePrePage() {
        this.havePrePage = false;
        if ((getPageCount() > 1) && (this.currentPage > 1)) {
            this.havePrePage = true;
        }
        return this.havePrePage;
    }

    public List<T> getItems() {
        return this.items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getNavigatorSize() {
        return this.navigatorSize;
    }

    public String toString() {
        return "";
    }
}

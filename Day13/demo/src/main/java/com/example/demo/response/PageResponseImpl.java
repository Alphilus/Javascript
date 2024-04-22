package com.example.demo.response;

import java.util.List;

public class PageResponseImpl<T> implements PageResponse<T>{
    List<T> data;
    int currentPage;
    int pageSize;

    public PageResponseImpl(List<T> all, int page, int pageSize) {
        this.data = all;
        this.currentPage = page;
        this.pageSize = pageSize;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public List<T> getContent() {
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, data.size());
        return data.subList(startIndex, endIndex);
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getTotalPages() {
        return (int) Math.ceil((double) data.size() / pageSize);
    }

    @Override
    public int getTotalElements() {
        return data.size();
    }
}

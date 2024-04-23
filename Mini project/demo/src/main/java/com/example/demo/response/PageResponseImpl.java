package com.example.demo.response;

import com.example.demo.model.Product;

import java.util.Collections;
import java.util.List;

public class PageResponseImpl<T> implements PageResponse<T> {
    List<T> data;
    int currentPage;
    int pageSize;

    public PageResponseImpl(List<T> all, int page, int pageSize) {
        this.data = all;
        this.currentPage = page;
        this.pageSize = pageSize;
    }

    public PageResponseImpl(List<Product> products) {
        this.data = (List<T>) products;
        this.currentPage = 1;
        this.pageSize = products.size();
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public List<T> getContent() {
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, data.size());
        if (startIndex >= data.size()) {
            return Collections.emptyList(); // Return an empty list if the start index is out of bounds
        }
        return data.subList(startIndex, endIndex);
    }


    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getTotalPages() {
        if (pageSize <= 0) {
            return 0; // Handle the case where pageSize is zero or negative
        }
        return (int) Math.ceil((double) data.size() / pageSize);
    }

    @Override
    public int getTotalElements() {
        return data.size();
    }
}

package com.example.actvn.util;

import com.example.actvn.model.response.PagedResponse;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;

public class PagedResponseMapper {
    public static PagedResponse<T> mapper(Page page) {
        PagedResponse<T> response = new PagedResponse<>();
        response.setData(page.getContent());
        response.setPage(page.getNumber() + 1);
        response.setSize(page.getSize());
        response.setTotalPage(page.getTotalPages());
        response.setTotalElements(page.getTotalElements());
        response.setLast(page.isLast());
        return response;
    }
}

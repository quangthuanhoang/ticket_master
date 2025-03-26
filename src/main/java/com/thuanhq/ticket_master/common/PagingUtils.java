package com.thuanhq.ticket_master.common;

import com.thuanhq.ticket_master.dto.response.user.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.function.Function;

public class PagingUtils {

    public static <E, R> PageResponse<R> toPageResponse(
            Page<E> entityPage,
            Function<E, R> mapper
    ) {
        return PageResponse.<R>builder()
                .currentPage(entityPage.getNumber())
                .pageSize(entityPage.getSize())
                .totalElements(entityPage.getTotalElements())
                .totalPages(entityPage.getTotalPages())
                .data(entityPage.getContent().stream().map(mapper).toList())
                .build();
    }

    public static Pageable resolvePageable(Integer currentPage, Integer pageSize, Sort sort) {
        int page = currentPage != null ? currentPage : 0;
        int size = pageSize != null ? pageSize : Integer.MAX_VALUE;
        return PageRequest.of(page, size, sort);
    }
}

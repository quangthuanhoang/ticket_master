package com.thuanhq.ticket_master.dto.response.user;

import java.util.Collections;
import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponse<T> {
    @Builder.Default
    List<T> data = Collections.emptyList();

    int currentPage;
    int pageSize;
    int totalPages;
    long totalElements;
}

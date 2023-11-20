package com.fiap.Plant4U.services.impl;

import com.fiap.Plant4U.services.UtilsServices;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UtilsServicesImpl implements UtilsServices {

    public String createUrlGetAllCoursesByUser(Long userId, Pageable pageable) {
        return "/courses?userId=" + userId + "&page=" + pageable.getPageNumber() + "&size="
                + pageable.getPageSize() + "&sort=" + pageable.getSort().toString().replaceAll(": ", ",");
    }
}
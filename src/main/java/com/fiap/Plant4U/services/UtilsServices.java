package com.fiap.Plant4U.services;

import org.springframework.data.domain.Pageable;

public interface UtilsServices {

    String createUrlGetAllCoursesByUser(Long userId, Pageable pageable);
}
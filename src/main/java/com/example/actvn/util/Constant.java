package com.example.actvn.util;

public interface Constant {
    interface ACTIVE_FLG {
        String NOT_DELETE = "Y";
        String DELETE = "N";
    }

    interface ROLE {
        String ADMIN = "ADMIN";
        String STUDENT = "STUDENT";
        String TEACHER = "TEACHER";
    }

    interface PAGINATION {
        int DEFAULT_PAGE = 0;
        int DEFAULT_PAGE_SIZE = 20;
    }
}

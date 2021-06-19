package com.example.actvn.util;

public interface Constant {
    interface ACTIVE_FLG {
        long NOT_DELETE = 1;
        long DELETE = 0;
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

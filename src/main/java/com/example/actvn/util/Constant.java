package com.example.actvn.util;

public interface Constant {
    interface ACTIVE_FLG {
        long NOT_DELETE = 1;
        long DELETE = 0;
    }

    interface ROLE {
        String ADMIN = "ADMIN";
        long STUDENT = 2;
        long TEACHER = 1;
    }

    interface PAGINATION {
        int DEFAULT_PAGE = 0;
        int DEFAULT_PAGE_SIZE = 20;
    }

    interface SCORES {
        long DIEM_CHUYEN_CAN = 1;
        long DIEM_GIUA_KY = 2;
    }
}

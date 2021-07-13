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
        long DIEM_KET_THUC_MON = 3;
    }

    interface ParamsUtils {
        interface KET_QUA_DANH_GIA_DIEM_QUA_TRINH {
            String KHOA = "khoa";
            String HOC_KY = "hoc_ky";
            String HOC_PHAN = "hoc_phan";
            String SO_TIN_CHI = "so_tin_chi";
            String MA_HOC_PHAN = "ma_hoc_phan";
            String LOP_HOC_PHAN = "lop_hoc_phan";
            String KHOA_HOC = "khoa_hoc";
            String GIANG_VIEN_GIANG_DAY = "giang_vien_giang_day";
            String TONG_SO_SV = "tong_so_sv";
            String NGAY_THI = "ngay_thi";
            String NGAY_NOP_DIEM = "ngay_nop_diem";
        }
    }

    interface REPORT {
        String CCCD_REPORT_FOLDER = "/templates/jasper/";
        String PRODUCES_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        String PRODUCES_PDF = "application/pdf";
        String PRODUCES_xlsx = " application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        String DOT_LINE = "........................................................................................................."
                + "...............................................................................................";

        interface NAME {
            String KET_QUA_DANH_GIA_DIEM_QUA_TRINH = "ket_qua_danh_gia_diem_qua_trinh";
        }
    }
}

package com.example.actvn.repository.subject;

import com.example.actvn.entity.Subject;
import com.example.actvn.model.subjects.SubjectRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class SubjectSpecification {
    public static Specification<Subject> searchSubjects(SubjectRequest request) {
        return Specification.where(searchMa(request.getMa())
                .and(searchTen(request.getTen()))
                .and(searchChuyenNganh(request.getChuyenNganh()))
                .and(searchHocKi(request.getHocKi()))
                .and(searchSoTin(request.getSoTin()))
                .and(searchGiangVien(request.getGiangVienId()))
        );
    }

    /**
     * private String ma;
     * private String ten;
     * private String chuyenNganh;
     * private String hocKi;
     * private Integer soTin;
     * private Integer giangVienId;
     * private String giangVien;
     */
    private static Specification<Subject> searchMa(String ma) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (StringUtils.isEmpty(ma)) return null;
            return criteriaBuilder.equal(root.get("ma"), ma.trim());
        };
    }

    private static Specification<Subject> searchTen(String ten) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (StringUtils.isEmpty(ten)) return null;
            return criteriaBuilder.equal(root.get("ten"), ten.trim());
        };
    }

    private static Specification<Subject> searchChuyenNganh(String chuyenNganh) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (StringUtils.isEmpty(chuyenNganh)) return null;
            return criteriaBuilder.equal(root.get("chuyenNganh"), chuyenNganh.trim());
        };
    }

    private static Specification<Subject> searchHocKi(String hocKi) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (StringUtils.isEmpty(hocKi)) return null;
            return criteriaBuilder.equal(root.get("hocKi"), hocKi.trim());
        };
    }

    private static Specification<Subject> searchSoTin(Integer soTin) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (soTin == null || soTin != 0) return null;
            return criteriaBuilder.equal(root.get("soTin"), soTin);
        };
    }

    private static Specification<Subject> searchGiangVien(Integer giangVien) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (giangVien == null || giangVien != 0) return null;
            return criteriaBuilder.equal(root.get("giangVienId"), giangVien);
        };
    }
}

package com.example.actvn.repository.classRepository;

import com.example.actvn.entity.Lop;
import com.example.actvn.model.classModel.ClassRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class ClassSpecification {
    public static Specification<Lop> classSpecification(ClassRequest request) {
        return Specification.where(searchMaLop(request.getMa()))
                .and(searchTenLop(request.getTenLop()))
                .and(searchHieuLuc(request.getHieuLuc()))
                .and(searchKhoa(request.getKhoa()))
                .and(searchKhoaId(request.getKhoaId()))
                .and(searchChuyenNganhId(request.getChuyenNganhId()));
    }

    public static Specification<Lop> searchMaLop(String maLop) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (StringUtils.isEmpty(maLop)) return null;
            return criteriaBuilder.equal(root.get("ma"), maLop);
        };
    }

    public static Specification<Lop> searchTenLop(String tenLop) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (StringUtils.isEmpty(tenLop)) return null;
            return criteriaBuilder.equal(root.get("tenLop"), tenLop.trim());
        };
    }

    public static Specification<Lop> searchHieuLuc(String hieuLuc) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (StringUtils.isEmpty(hieuLuc)) return null;
            return criteriaBuilder.equal(root.get("hieuLuc"), hieuLuc.trim());
        };
    }
    public static Specification<Lop> searchKhoa(String khoa){
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (StringUtils.isEmpty(khoa)) return  null;
            return criteriaBuilder.equal(root.get("khoa"), khoa.trim());
        };
    }
    public static Specification<Lop> searchChuyenNganhId(Integer chuyenNganhId){
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (chuyenNganhId == null || chuyenNganhId == 0)return  null;
            return criteriaBuilder.equal(root.get("chuyenNganhId"),chuyenNganhId);
        };
    }
    public static Specification<Lop> searchKhoaId(Integer khoaId){
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (khoaId == null || khoaId==0) return null;
            return criteriaBuilder.equal(root.get("khoaId"),khoaId);
        };
    }

}

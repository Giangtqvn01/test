package com.example.actvn.repository.classroom;

import com.example.actvn.entity.Classroom;
import com.example.actvn.entity.ClassroomUser;
import com.example.actvn.model.classroom.ClassRequest;
import com.example.actvn.security.UserPrincipal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ClassSpecification {
    public static Specification<Classroom> classSpecification(UserPrincipal userPrincipal, ClassRequest request) {
        return Specification.where(searchMaClassroom(request.getMa()))
                .and(searchTenClassroom(request.getTenLop()))
                .and(searchHieuLuc(request.getHieuLuc()))
                .and(searchKhoa(request.getKhoa()))
                .and(searchKhoaId(request.getKhoaId()))
                .and(searchUserId(userPrincipal.getAccountId()))
                .and(searchChuyenNganhId(request.getChuyenNganhId()));
    }

    public static Specification<Classroom> searchMaClassroom(String maClassroom) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (StringUtils.isEmpty(maClassroom)) return null;
            return criteriaBuilder.equal(root.get("ma"), maClassroom);
        };
    }

    public static Specification<Classroom> searchTenClassroom(String tenClassroom) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (StringUtils.isEmpty(tenClassroom)) return null;
            return criteriaBuilder.equal(root.get("tenClassroom"), tenClassroom.trim());
        };
    }

    public static Specification<Classroom> searchHieuLuc(String hieuLuc) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (StringUtils.isEmpty(hieuLuc)) return null;
            return criteriaBuilder.equal(root.get("hieuLuc"), hieuLuc.trim());
        };
    }

    public static Specification<Classroom> searchKhoa(String khoa) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (StringUtils.isEmpty(khoa)) return null;
            return criteriaBuilder.equal(root.get("khoa"), khoa.trim());
        };
    }

    public static Specification<Classroom> searchChuyenNganhId(Integer chuyenNganhId) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (chuyenNganhId == null || chuyenNganhId == 0) return null;
            return criteriaBuilder.equal(root.get("chuyenNganhId"), chuyenNganhId);
        };
    }

    public static Specification<Classroom> searchKhoaId(Integer khoaId) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (khoaId == null || khoaId == 0) return null;
            return criteriaBuilder.equal(root.get("khoaId"), khoaId);
        };
    }

    public static Specification<Classroom> searchUserId(Long userId) {
        return (Specification<Classroom>) (root, criteriaQuery, criteriaBuilder) -> {
            if (userId == null || userId == 0) return null;
            Join<Classroom, ClassroomUser> classroomUserJoin = root.join("users");
            List<Predicate> predicates = new ArrayList<>();
            criteriaQuery.distinct(true);
            predicates.add(criteriaBuilder.equal(classroomUserJoin.get("id"), userId));
            Predicate predicateNameOrCd = criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
            return predicateNameOrCd;
        };
    }
}

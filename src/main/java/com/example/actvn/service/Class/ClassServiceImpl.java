package com.example.actvn.service.Class;

import com.example.actvn.entity.Lop;
import com.example.actvn.exception.BadRequestException;
import com.example.actvn.model.BaseModel;
import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.classModel.ClassRequest;
import com.example.actvn.model.classModel.CreateNewClassRequest;
import com.example.actvn.model.classModel.UpdateClassRequest;
import com.example.actvn.model.response.PagedResponse;
import com.example.actvn.repository.classRepository.ClassRepository;
import com.example.actvn.repository.classRepository.ClassSpecification;
import com.example.actvn.security.UserPrincipal;
import com.example.actvn.util.HtmlUtil;
import com.example.actvn.util.Logit;
import com.example.actvn.util.PagedResponseMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl implements ClassService {
    private static final Logit log = Logit.getInstance(ClassServiceImpl.class);
    final
    ClassRepository classRepository;

    public ClassServiceImpl(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public ResponseModel createNewClass(UserPrincipal userPrincipal, CreateNewClassRequest request) {
        ResponseModel model = new ResponseModel();
        String message;
        try {
            HtmlUtil.validateRequest(request);

            boolean existsByMa = classRepository.existsByMa(request.getMa());
            if (existsByMa) {
                message = "Ma is exist, Please input other ma!";
                BaseModel error = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                model.setDescription(message);
                model.setResponseStatus(HttpStatus.BAD_REQUEST);
                model.setData(error);
                return model;
            }

            Lop lop = new Lop();
            lop.setMa(request.getMa().trim().toUpperCase());
            lop.setTenLop(request.getTenLop().trim());
            lop.setChuyenNganhId(request.getChuyenNganhId());
            lop.setKhoaId(request.getKhoaId());
            lop.setKhoa(request.getKhoa());
            lop.setNguoiTao(userPrincipal.getUsername());
            Lop save = classRepository.save(lop);

            message = "Create new class success!";
            model.setResponseStatus(HttpStatus.OK);
            model.setData(save);
            model.setDescription(message);

            return model;
        } catch (RuntimeException e) {
            log.error(e.toString(), e);
            message = "Server error! Create new class fails.";
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            model.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            model.setData(error);
            model.setDescription(message);
            return model;
        }
    }

    @Override
    public ResponseModel updateClass(UserPrincipal userPrincipal, UpdateClassRequest request) {

        ResponseModel model = new ResponseModel();
        String message;
        try {
            Long exitMa = classRepository.findByMaAndId(request.getMa(), request.getId());
            if (exitMa != 0) {
                message = "Ma is exist, Please input other ma!";
                BaseModel error = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                model.setResponseStatus(HttpStatus.BAD_REQUEST);
                model.setDescription(message);
                model.setData(error);
                return model;
            }
            Long exitTen = classRepository.findByTenAndId(request.getTenLop(), request.getId());
            if (exitTen != 0) {
                message = "Name class is exist. Please input other name class!";
                BaseModel error = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                model.setDescription(message);
                model.setData(error);
                model.setResponseStatus(HttpStatus.BAD_REQUEST);
                return model;
            }
            Lop lop = classRepository.findById(request.getId()).orElseThrow(() -> new BadRequestException("Not find class!"));
            lop.setTenLop(request.getTenLop());
            lop.setMa(request.getMa());
            lop.setKhoa(request.getKhoa());
            lop.setKhoaId(request.getKhoaId());
            lop.setChuyenNganhId(request.getChuyenNganhId());
            lop = classRepository.save(lop);

            message = "Update class success!";
            model.setResponseStatus(HttpStatus.OK);
            model.setData(lop);
            model.setDescription(message);
            return model;
        } catch (RuntimeException e) {
            log.error(e.toString(), e);
            message = "Server error! Update class fails.";
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            model.setDescription(message);
            model.setData(error);
            model.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return model;
        }
    }

    @Override
    public ResponseModel getListClass(Integer page, Integer size, ClassRequest request) {
        String message;
        ResponseModel model = new ResponseModel();
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Lop> listLop = classRepository.findAll(ClassSpecification.classSpecification(request), pageable);
            PagedResponse<T> pagedResponse = PagedResponseMapper.mapper(listLop);
            message = "Get class list successfully!";
            model.setData(pagedResponse);
            model.setDescription(message);
            model.setResponseStatus(HttpStatus.OK);
            return model;
        } catch (RuntimeException e) {
            log.error(e.toString(), e);
            message = "Server error! Get class list fails.";
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            model.setDescription(message);
            model.setData(error);
            model.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return model;
        }
    }
}

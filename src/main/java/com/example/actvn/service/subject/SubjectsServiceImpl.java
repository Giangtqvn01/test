package com.example.actvn.service.subject;

import com.example.actvn.entity.Account;
import com.example.actvn.entity.HocPhan;
import com.example.actvn.entity.Lop;
import com.example.actvn.entity.ThoiGianHocPhan;
import com.example.actvn.exception.BadRequestException;
import com.example.actvn.model.BaseModel;
import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.response.PagedResponse;
import com.example.actvn.model.subjects.CreateSubjectRequest;
import com.example.actvn.model.subjects.SubjectRequest;
import com.example.actvn.model.subjects.UpdateSubjectRequest;
import com.example.actvn.repository.account.AccountRepository;
import com.example.actvn.repository.classRepository.ClassSpecification;
import com.example.actvn.repository.subject.SubjectSpecification;
import com.example.actvn.repository.subject.SubjectsRepository;
import com.example.actvn.security.UserPrincipal;
import com.example.actvn.util.Logit;
import com.example.actvn.util.PagedResponseMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectsServiceImpl implements SubjectService {
    private static final Logit log = Logit.getInstance(SubjectsServiceImpl.class);
    @Autowired
    private SubjectsRepository subjectsRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public ResponseModel createSubjects(CreateSubjectRequest request, UserPrincipal userPrincipal) {
        String message;
        ResponseModel responseModel = new ResponseModel();
        try {
            boolean existsByMa = subjectsRepository.existsByMa(request.getMa());
            if (existsByMa) {
                message = "Ma is exist, Please input other ma!";
                BaseModel error = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                responseModel.setDescription(message);
                responseModel.setResponseStatus(HttpStatus.BAD_REQUEST);
                responseModel.setData(error);
                return responseModel;
            }
            HocPhan hocPhan = new HocPhan();
            hocPhan.setChuyenNganh(request.getChuyenNganh());
            hocPhan.setHocKi(request.getHocKi());
            hocPhan.setTen(request.getTen());
            hocPhan.setMa(request.getMa());
            hocPhan.setGiangVienId(request.getGiangVienId());
            hocPhan.setSiSo(request.getSiSo());

            Account account = accountRepository.findById(request.getGiangVienId()).orElseThrow(() -> new BadRequestException("Not found teacher!"));
            hocPhan.setGiangVien(account);
            List<ThoiGianHocPhan> thoiGianHocPhans = request.getSubjectsTimeRequests()
                    .stream().map(createSubjectsTimeRequest -> {
                        ThoiGianHocPhan thoiGianHocPhan = new ThoiGianHocPhan();
                        thoiGianHocPhan.setNgayHoc(createSubjectsTimeRequest.getNgayHoc());
                        thoiGianHocPhan.setDiaDiem(createSubjectsTimeRequest.getDiaDiem());
                        return thoiGianHocPhan;
                    }).collect(Collectors.toList());
            hocPhan.setThoiGianHocPhans(thoiGianHocPhans);
            HocPhan save = subjectsRepository.save(hocPhan);

            message = "Create subject successfully!";
            responseModel.setResponseStatus(HttpStatus.OK);
            responseModel.setData(save);
            responseModel.setDescription(message);
            return responseModel;

        } catch (RuntimeException e) {
            message = "Service error! Error " + e;
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            responseModel.setData(error);
            responseModel.setDescription(message);
            responseModel.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseModel;
        }
    }

    @Override
    public ResponseModel updateSubjects(UpdateSubjectRequest request, UserPrincipal userPrincipal) {
        ResponseModel responseModel = new ResponseModel();
        String message;
        try {
            HocPhan hocPhan = subjectsRepository.findById(request.getId()).orElseThrow(() -> new BadRequestException("Not found subjects!"));
            Long existsByMa = subjectsRepository.getExistByMaOtherId(request.getMa().trim(), request.getId());
            if (existsByMa > 0) {
                message = "Ma is exist, Please input other ma!";
                BaseModel error = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                responseModel.setDescription(message);
                responseModel.setResponseStatus(HttpStatus.BAD_REQUEST);
                responseModel.setData(error);
                return responseModel;
            }

            hocPhan.setChuyenNganh(request.getChuyenNganh());
            hocPhan.setHocKi(request.getHocKi());
            hocPhan.setTen(request.getTen());
            hocPhan.setMa(request.getMa());
            hocPhan.setGiangVienId(request.getGiangVienId());
            hocPhan.setSiSo(request.getSiSo());

            Account account = accountRepository.findById(request.getGiangVienId()).orElseThrow(() -> new BadRequestException("Not found teacher!"));
            hocPhan.setGiangVien(account);
            List<ThoiGianHocPhan> thoiGianHocPhans = request.getSubjectsTimeRequests()
                    .stream().map(updateSubjectsTimeRequest -> {
                        ThoiGianHocPhan thoiGianHocPhan = new ThoiGianHocPhan();
                        thoiGianHocPhan.setId(updateSubjectsTimeRequest.getId());
                        thoiGianHocPhan.setNgayHoc(updateSubjectsTimeRequest.getNgayHoc());
                        thoiGianHocPhan.setDiaDiem(updateSubjectsTimeRequest.getDiaDiem());
                        return thoiGianHocPhan;
                    }).collect(Collectors.toList());
            hocPhan.setThoiGianHocPhans(thoiGianHocPhans);
            HocPhan save = subjectsRepository.save(hocPhan);

            message = "Create subject successfully!";
            responseModel.setResponseStatus(HttpStatus.OK);
            responseModel.setData(save);
            responseModel.setDescription(message);
            return responseModel;

        } catch (RuntimeException e) {
            message = "Server error! Error: " + e;
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            responseModel.setDescription(message);
            responseModel.setData(error);
            responseModel.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseModel;
        }
    }

    @Override
    public ResponseModel getSubjects(SubjectRequest request, UserPrincipal userPrincipal, Integer page, Integer size) {
        ResponseModel responseModel = new ResponseModel();
        String message;
        try {
            if (page >= 1) page = page - 1;
            Pageable pageable = PageRequest.of(page, size);
            Page<HocPhan> listLop = subjectsRepository.findAll(SubjectSpecification.searchSubjects(request), pageable);
            PagedResponse<T> pagedResponse = PagedResponseMapper.mapper(listLop);
            message = "Get subjects list successfully!";
            responseModel.setData(pagedResponse);
            responseModel.setDescription(message);
            responseModel.setResponseStatus(HttpStatus.OK);
            return responseModel;
        } catch (RuntimeException exception) {
            message = "Server error! Error: " + exception;
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            responseModel.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseModel.setDescription(message);
            responseModel.setData(error);
            return responseModel;
        }
    }
}

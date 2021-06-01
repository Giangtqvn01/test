package com.example.actvn.service.account;

import com.example.actvn.entity.*;
import com.example.actvn.exception.AppException;
import com.example.actvn.exception.BadRequestException;
import com.example.actvn.model.BaseModel;
import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.account.CreateAccountRequest;
import com.example.actvn.model.account.GetAccountRequest;
import com.example.actvn.model.account.UpdateAccountRequest;
import com.example.actvn.model.response.PagedResponse;
import com.example.actvn.repository.RoleRepository;
import com.example.actvn.repository.account.AccountRepository;
import com.example.actvn.repository.account.AccountSpecification;
import com.example.actvn.repository.classRepository.ClassRepository;
import com.example.actvn.repository.listClass.DsLopRepository;
import com.example.actvn.security.UserPrincipal;
import com.example.actvn.service.Class.ListClassRepository;
import com.example.actvn.util.*;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private static final Logit log = Logit.getInstance(AccountServiceImpl.class);
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    DsLopRepository dsLopRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ListClassRepository listClassRepository;

    @Override
    public ResponseModel getAccount(Integer page, Integer size, GetAccountRequest request) {
        HtmlUtil.validateRequest(request);
        ResponseModel model = new ResponseModel();
        String message;
        try {
            if (page >= 1) page = page - 1;
            Pageable pageable = PageRequest.of(page, size);
            Page<Account> listAccount = accountRepository.findAll(AccountSpecification.searchAccount(request), pageable);
            PagedResponse<T> response = PagedResponseMapper.mapper(listAccount);
            message = "Get account successfully!";
            model.setDescription(message);
            model.setData(response);
            model.setResponseStatus(HttpStatus.OK);
            return model;

        } catch (RuntimeException e) {
            message = "Get account fails.Error: " + e;
            log.error(e.toString(), e);
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            model.setData(error);
            model.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            model.setDescription(message);
            return model;
        }
    }

    /***
     * create account
     * @param userPrincipal account
     * @param request new account
     * @return
     */
    @Override
    public ResponseModel createAccount(UserPrincipal userPrincipal, CreateAccountRequest request) {
        String message;
        ResponseModel model = new ResponseModel();
        HtmlUtil.validateRequest(request);
        try {
            if (Constant.ROLE.STUDENT.equals(request.getRoleCd().trim())) {
                return createStudentAccount(userPrincipal, request);
            } else if (Constant.ROLE.TEACHER.equals(request.getRoleCd().trim())) {
                return createTeacherAccount(userPrincipal, request);
            } else {
                message = "Not authorized!";
                BaseModel error = new BaseModel(HttpStatus.FORBIDDEN.value(), message);
                model.setData(error);
                model.setDescription(message);
                model.setResponseStatus(HttpStatus.FORBIDDEN);
                return model;
            }
        } catch (RuntimeException e) {
            message = "Create account fails!";
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            model.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            model.setDescription(message);
            model.setData(error);
            return model;
        }
    }

    /***
     * create account teacher
     * @param userPrincipal account create information
     * @param request teacher account information
     * @return ResponseModel
     */
    private ResponseModel createTeacherAccount(UserPrincipal userPrincipal, CreateAccountRequest request) {
        ResponseModel model = new ResponseModel();
        String message;
        HtmlUtil.validateRequest(request);
        try {
            List<String> codeTeacher = accountRepository.getListMaAccountFollowRole(Constant.ROLE.TEACHER);
            Account account = new Account();
            account.setHoVaTen(request.getHoVaTen());
            account.setPassword(passwordEncoder.encode(
                    DateUtils.convertTimestampToString(request.getNgaySinh(), DateUtils.PATTERN_DDMMYYYY))
            );
            account.setRoleCd(Constant.ROLE.TEACHER);
            account.setHieuLuc(Constant.ACTIVE_FLG.NOT_DELETE);
            String maGV = generateRandomCodeTeacher(codeTeacher);
            account.setMa(maGV);
            account.setTaiKhoan(maGV);
            Role userRole = roleRepository.findByRoleCd(RoleName.TEACHER).orElseThrow(() -> new AppException("User Role not set."));
            account.setRoles(Collections.singleton(userRole));
            account.setNguoiTao(userPrincipal.getUsername());
            Account saveAccount = accountRepository.save(account);
            if (request.getLopId() != null) {
                List<DsLop> danhSachLop = listClassRepository.findByLopId(request.getLopId());
                danhSachLop.forEach(dsLop -> dsLop.setGiaoVienChuNhiemId(saveAccount.getId()));
                listClassRepository.saveAll(danhSachLop);
            }
            message = "Create account teacher successfully!";
            model.setResponseStatus(HttpStatus.OK);
            model.setDescription(message);
            model.setData(saveAccount);
            return model;
        } catch (Exception e) {
            log.error(e.toString(), e);
            throw new RuntimeException();
        }
    }

    private String generateRandomCodeTeacher(List<String> codeTeacher) {
        String maGV;
        boolean exitCodeTeacher;
        do {
            maGV = "GV" + CommonUtils.generateRandomCode(6);
            String finalMaGV = maGV;
            exitCodeTeacher = codeTeacher.stream().anyMatch(s -> s.equalsIgnoreCase(finalMaGV));
        } while (exitCodeTeacher);
        return maGV;
    }

    /***
     * create account student
     * add student in class
     * @param userPrincipal account create information
     * @param request student account information
     * @return ResponseModel
     */
    private ResponseModel createStudentAccount(UserPrincipal userPrincipal, CreateAccountRequest request) {
        String message;
        ResponseModel model = new ResponseModel();
        HtmlUtil.validateRequest(request);
        try {
            if (request.getLopId() == null || request.getLopId() <= 0) {
                message = "Class id í null!";
                BaseModel error = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                model.setDescription(message);
                model.setData(error);
                model.setResponseStatus(HttpStatus.BAD_REQUEST);
                return model;
            }

            Account account = new Account();
            account.setHoVaTen(request.getHoVaTen());
            account.setRoleCd(Constant.ROLE.STUDENT);
            account.setHieuLuc(Constant.ACTIVE_FLG.NOT_DELETE);
            account.setPassword(passwordEncoder.encode(
                    DateUtils.convertTimestampToString(request.getNgaySinh(), DateUtils.PATTERN_DDMMYYYY))
            );

            Long countStudentInClass = dsLopRepository.getCountStudentInClass(request.getLopId());
            Lop lop = classRepository.findById(request.getLopId()).orElse(null);
            if (lop == null) {
                message = "Not find class!";
                BaseModel error = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                model.setDescription(message);
                model.setData(error);
                model.setResponseStatus(HttpStatus.BAD_REQUEST);
                return model;
            }
            String maSV = lop.getMa() + countStudentInClass;
            account.setMa(maSV);
            account.setTaiKhoan(maSV);
            Role userRole = roleRepository.findByRoleCd(RoleName.STUDENT).orElseThrow(() -> new AppException("User Role not set."));
            account.setRoles(Collections.singleton(userRole));
            account.setNguoiTao(userPrincipal.getUsername());
            Account saveAccount = accountRepository.save(account);
            lop.setSinhVien(Collections.singleton(saveAccount));
            lop.setNguoiSuaCuoi(userPrincipal.getUsername());
            lop.setNgaySuaCuoi(new Timestamp(System.currentTimeMillis()));
            classRepository.save(lop);

            message = "Create account student successfully!";
            model.setDescription(message);
            model.setData(saveAccount);
            model.setResponseStatus(HttpStatus.OK);
            return model;
        } catch (Exception e) {
            log.error(e.toString(), e);
            throw new RuntimeException();
        }
    }


    /***
     * Update account
     * @param request account
     * @param userPrincipal account
     * @return ResponseModel
     */
    @Override
    public ResponseModel updateAccount(UpdateAccountRequest request, UserPrincipal userPrincipal) {
        ResponseModel model = new ResponseModel();
        String message;
        try {
            Account account = accountRepository.findById(request.getId())
                    .orElseThrow(() -> new BadRequestException("Not find account!"));
            account.setHoVaTen(request.getHoVaTen().trim());
            accountRepository.save(account);
            message = "Update account successfully!";
            BaseModel success = new BaseModel(HttpStatus.OK.value(), message);
            model.setData(success);
            model.setDescription(message);
            model.setResponseStatus(HttpStatus.OK);
            return model;
        } catch (RuntimeException e) {
            message = "Update account fails!" + e;
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            model.setData(error);
            model.setDescription(message);
            model.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return model;
        }
    }
}

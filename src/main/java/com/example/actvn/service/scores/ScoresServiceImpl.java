package com.example.actvn.service.scores;


import com.example.actvn.entity.Scores;
import com.example.actvn.model.BaseModel;
import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.scores.ReportUserScoresResponse;
import com.example.actvn.model.scores.ScoresResponse;
import com.example.actvn.model.scores.UserScoresResponse;
import com.example.actvn.repository.classroom.ClassroomUserRepository;
import com.example.actvn.repository.scores.ScoresRepository;
import com.example.actvn.security.UserPrincipal;
import com.example.actvn.util.Constant;
import com.example.actvn.util.ExcelUtils;
import com.example.actvn.util.HtmlUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ScoresServiceImpl implements ScoresService {
    @Autowired
    ScoresRepository scoresRepository;
    @Autowired
    ClassroomUserRepository classroomUserRepository;

    @Override
    public ResponseModel getScores(Long classroomId, UserPrincipal userPrincipal) {
        ResponseModel responseModel = new ResponseModel();
        String message = "";
        try {
            List<UserScoresResponse> scores = scoresRepository.getScores(classroomId, null);
            message = " Get scores successfully!";
            responseModel.setData(scores);
            responseModel.setDescription(message);
            responseModel.setResponseStatus(HttpStatus.OK);
            return responseModel;
        } catch (RuntimeException exception) {
            message = "Server error! Error: " + exception;
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            responseModel.setData(error);
            responseModel.setDescription(message);
            responseModel.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseModel;
        }
    }

    @Override
    public ResponseModel createOrUpdateScores(List<ScoresResponse> request, UserPrincipal userPrincipal) {
        ResponseModel responseModel = new ResponseModel();
        String message = "";
        HtmlUtil.validateRequest(request);
        try {
            if (Constant.ROLE.TEACHER != userPrincipal.getRoleId()) {
                message = "Not permission! ";
                BaseModel error = new BaseModel(HttpStatus.BAD_REQUEST.value(), message);
                responseModel.setDescription(message);
                responseModel.setData(error);
                responseModel.setResponseStatus(HttpStatus.BAD_REQUEST);
                return responseModel;
            }
            if (request == null || request.size() == 0) {
                message = "Update scores student successfully!";
                BaseModel success = new BaseModel(HttpStatus.OK.value(), message);
                responseModel.setDescription(message);
                responseModel.setData(success);
                responseModel.setResponseStatus(HttpStatus.OK);
                return responseModel;
            }
            List<Scores> scoresList = new ArrayList<>();
            for (ScoresResponse scoresResponse : request) {
                if (!checkTypeScores(scoresResponse.getType())) {
                    continue;
                }
                if (scoresResponse.getId() == null || scoresResponse.getId() == 0) {
                    boolean checkUserAndClassroom = classroomUserRepository.existsByClassroomIdAndUserId(scoresResponse.getClassroomId(), scoresResponse.getUserId());
                    if (checkUserAndClassroom) {
                        scoresList.add(newScores(scoresResponse));
                    }
                    continue;
                } else if (scoresResponse.getId() != null && scoresResponse.getId() != 0) {
                    Scores scores = updateScores(scoresResponse);
                    if (scores == null) {
                        continue;
                    }
                    scoresList.add(scores);
                }
            }
            scoresRepository.saveAll(scoresList);

            message = "Update score student successfully!";
            BaseModel success = new BaseModel(HttpStatus.OK.value(), message);
            responseModel.setDescription(message);
            responseModel.setData(success);
            responseModel.setResponseStatus(HttpStatus.OK);
            return responseModel;

        } catch (RuntimeException exception) {
            message = "Server error! Error: " + exception;
            BaseModel error = new BaseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
            responseModel.setDescription(message);
            responseModel.setData(error);
            responseModel.setResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseModel;
        }
    }

    @Override
    public byte[] downloadFileScoresOfStudent(Long classroomId, UserPrincipal userPrincipa) {

        List<ReportUserScoresResponse> scoresResponses = new ArrayList<>();
        try {
            List<UserScoresResponse> scores = scoresRepository.getScores(classroomId, null);
            for (UserScoresResponse response :
                    scores) {
                ReportUserScoresResponse scoresResponse = new ReportUserScoresResponse();
                scoresResponse.setHoVaTen(response.getNameUser());
                scoresResponse.setUserId(response.getUserId());
                scoresResponse.setMaSinhVien(response.getCodeUser());
                for (ScoresResponse scoresResponse1 :
                        response.getScores()) {
                    if (scoresResponse1.getType() == Constant.SCORES.DIEM_CHUYEN_CAN)
                        scoresResponse.setDiemThanhPhanMot(scoresResponse1.getPoint().toString());
                    if (scoresResponse1.getType() == Constant.SCORES.DIEM_GIUA_KY)
                        scoresResponse.setDiemThanhPhanHai(scoresResponse1.getPoint().toString());
                }
                scoresResponses.add(scoresResponse);
            }
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Workbook workbook = createFileScoresStudent(scoresResponses); // creates the workbook
            workbook.write(stream);
            workbook.close();
            return stream.toByteArray();
        } catch (RuntimeException | IOException exception) {
            System.out.println("exception " +exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ReportUserScoresResponse> getListReportUserScoresResponse(Long classroomId, UserPrincipal userPrincipal) {
        List<ReportUserScoresResponse> scoresResponses = new ArrayList<>();
        try {
            List<UserScoresResponse> scores = scoresRepository.getScores(classroomId, null);
            for (UserScoresResponse response :
                    scores) {
                ReportUserScoresResponse scoresResponse = new ReportUserScoresResponse();
                scoresResponse.setHoVaTen(response.getNameUser());
                scoresResponse.setUserId(response.getUserId());
                scoresResponse.setMaSinhVien(response.getCodeUser());
                for (ScoresResponse scoresResponse1 :
                        response.getScores()) {
                    if (scoresResponse1.getType() == Constant.SCORES.DIEM_CHUYEN_CAN)
                        scoresResponse.setDiemThanhPhanMot(scoresResponse1.getPoint().toString());
                    if (scoresResponse1.getType() == Constant.SCORES.DIEM_GIUA_KY)
                        scoresResponse.setDiemThanhPhanHai(scoresResponse1.getPoint().toString());
                }
                scoresResponses.add(scoresResponse);
            }
            return scoresResponses;
        } catch (RuntimeException exception) {
            System.out.println("exception " +exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    private Workbook createFileScoresStudent(List<ReportUserScoresResponse> scoresResponses) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Customers");
        int rowIndex = 0;
        writeHeader(sheet, rowIndex,workbook);
         rowIndex = 14;
        int i = 1;
        for (ReportUserScoresResponse scoresResponse : scoresResponses) {
            Row row = sheet.createRow(rowIndex);
            writeBook(scoresResponse, row, i);
            i++;
            rowIndex++;
        }
        writeFooter(sheet, rowIndex);
        return workbook;
    }
    private static void writeHeader(Sheet sheet, int rowIndex,Workbook workbook) {


        // Create row
        Row row = sheet.createRow(rowIndex);

        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,0,5));
        Cell cell = row.createCell(0);
        cell.setCellStyle(ExcelUtils.createStyleForTile(sheet));
        cell.setCellValue("H???C VI???N K??? THU???T M???T M??");
        

        cell = row.createCell(6);
        cell.setCellValue("C???NG H??A X?? H???I CH??? NGH??A VI???T NAM");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,6,13));
        cell.setCellStyle(ExcelUtils.createStyleForTile(sheet));
        

        rowIndex++;
        row = sheet.createRow(rowIndex);
        cell=row.createCell(0);
        cell.setCellValue("Khoa:");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,0,2));
        cell.setCellStyle(ExcelUtils.createStyleForTile(sheet));
        

        cell = row.createCell(6);
        cell.setCellValue("?????c l???p - T??? do - H???nh ph??c");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,6,13));
        cell.setCellStyle(ExcelUtils.createStyleForTileGachChan(sheet));



        rowIndex+=2;
        row = sheet.createRow(rowIndex);
        cell=row.createCell(0);
        cell.setCellValue("K???T QU??? ????NH GI?? ??I???M QU?? TR??NH");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,0,13));
        cell.setCellStyle(ExcelUtils.createStyleForTile(sheet));
        

        rowIndex++;
        row = sheet.createRow(rowIndex);
        cell=row.createCell(0);
        cell.setCellValue("H???C K??? 2 N??M H???C 2019 - 2020");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,0,13));
        cell.setCellStyle(ExcelUtils.createStyleForTile(sheet));
        


        rowIndex++;
        row = sheet.createRow(rowIndex);
        cell=row.createCell(0);
        cell.setCellValue("H???c ph???n:");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,0,1));
        

        cell=row.createCell(2);
        cell.setCellValue("K??? thu???t truy???n s??? li???u");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,2,8));
        cell.setCellStyle(ExcelUtils.createStyleTextBold(sheet));

        cell=row.createCell(9);
        cell.setCellValue("S??? TC: ");
        

        cell=row.createCell(10);
        cell.setCellValue("2");
        cell.setCellStyle(ExcelUtils.createStyleTextBold(sheet));


        cell=row.createCell(11);
        cell.setCellValue("M?? h???c ph???n: ");
        

        cell=row.createCell(12);
        cell.setCellValue("ATDVDV2");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,12,13));
        cell.setCellStyle(ExcelUtils.createStyleTextBold(sheet));


        rowIndex++;
        row = sheet.createRow(rowIndex);
        cell = row.createCell(0);
        cell.setCellValue("L???p h???c ph???n: ");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,0,1));
        

        cell = row.createCell(2);
        cell.setCellValue("K??? thu???t truy???n s??? li???u-2-19 (A14C2D1-08)");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,2,8));
        cell.setCellStyle(ExcelUtils.createStyleTextBold(sheet));


        cell = row.createCell(9);
        cell.setCellValue("Kh??a:");
        

        cell = row.createCell(10);
        cell.setCellValue("AT14");
        cell.setCellStyle(ExcelUtils.createStyleTextBold(sheet));


        rowIndex++;
        row = sheet.createRow(rowIndex);
        cell = row.createCell(0);
        cell.setCellValue("Gi???ng vi??n gi???ng d???y:");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,0,2));
        

        cell = row.createCell(3);
        cell.setCellValue("Ph???m Anh Th??");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,3,8));
        


        rowIndex++;
        row = sheet.createRow(rowIndex);
        cell = row.createCell(0);
        cell.setCellValue("T???ng s??? SV:");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,0,2));
        

        cell = row.createCell(4);
        cell.setCellValue("S??? SV d??? thi:???. V???ng??????C?? l?? do:?????????.     Kh??ng l?? do:????????????..");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,4,13));
        


        rowIndex++;
        row = sheet.createRow(rowIndex);
        cell = row.createCell(0);
        cell.setCellValue("Ng??y thi:");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,0,2));
        

        cell = row.createCell(4);
        cell.setCellValue("Ng??y n???p ??i???m:");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,4,7));
        

        rowIndex=12;
        row = sheet.createRow(rowIndex);
        cell = row.createCell(0);
        cell.setCellValue("STT");
        CellRangeAddress cellRangeAddress = new CellRangeAddress(rowIndex, rowIndex + 1, 0, 0);
        sheet.addMergedRegion(cellRangeAddress);
        CellStyle cellStyle = ExcelUtils.createStyleForHeaderTable(sheet);
        cell.setCellStyle(cellStyle);
        ExcelUtils.borderStyleMergedCell(sheet,rowIndex, rowIndex+1,0,0);

        cell = row.createCell(1);
        cell.setCellValue("M?? Sinh Vi??n");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex+1,1,1));
        cell.setCellStyle(ExcelUtils.createStyleForHeaderTable(sheet));

        cell = row.createCell(2);
        cell.setCellValue("H??? v?? t??n");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex+1,2,6));
        cell.setCellStyle(ExcelUtils.createStyleForHeaderTable(sheet));

        cell = row.createCell(7);
        cell.setCellValue("L???p");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex+1,7,7));
        cell.setCellStyle(ExcelUtils.createStyleForHeaderTable(sheet));

        cell = row.createCell(8);
        cell.setCellValue("??i???m \nth??nh \nph???n 1");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex+1,8,8));
        cell.setCellStyle(ExcelUtils.createStyleForHeaderTable(sheet));

        cell = row.createCell(9);
        cell.setCellValue("??i???m \nth??nh \nph???n 2");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex+1,9,9));
        cell.setCellStyle(ExcelUtils.createStyleForHeaderTable(sheet));

        cell = row.createCell(10);
        cell.setCellValue("??i???m qu?? tr??nh");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,10,11));
        cell.setCellStyle(ExcelUtils.createStyleForHeaderTable(sheet));

        cell = row.createCell(12);
        cell.setCellValue("Ghi ch??");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex+1,12,13));
        cell.setCellStyle(ExcelUtils.createStyleForHeaderTable(sheet));

        rowIndex=13;
        row = sheet.createRow(rowIndex);
        cell = row.createCell(10);
        cell.setCellValue("B???ng s???");
        cell.setCellStyle(ExcelUtils.createStyleForHeaderTable(sheet));

        cell = row.createCell(11);
        cell.setCellValue("B???ng ch??? ");
        cell.setCellStyle(ExcelUtils.createStyleForHeaderTable(sheet));
        ExcelUtils.autosizeColumn(sheet,13);

    }

    private static void writeFooter(Sheet sheet, int rowIndex) {
        rowIndex++;
        int COLUMN_INDEX_GIANG_VIEN_CHAM_THI = 0;
        int COLUMN_INDEX_CHU_NHIEM_BO_MON = 5;
        int COLUMN_INDEX_GIAO_VIEN_KHOA = 9;
        int COLUMN_INDEX_PHONG_DAO_TAO = 13;
        int COLUMN_INDEX_DIA_CHI = 13;
        // Create row
        Row rowNgayThang = sheet.createRow(rowIndex);
        Cell cellNgayThang = rowNgayThang.createCell(COLUMN_INDEX_DIA_CHI, CellType.STRING);
        cellNgayThang.setCellValue("H?? N???i, ng??y       th??ng       n??m     ");

        rowIndex++;
        Row row = sheet.createRow(rowIndex);
        Cell cellGiangVienChamThi = row.createCell(COLUMN_INDEX_GIANG_VIEN_CHAM_THI, CellType.STRING);
        cellGiangVienChamThi.setCellValue("GI???NG VI??N CH???M THI");

        Cell cellChuNhiemBoMon = row.createCell(COLUMN_INDEX_CHU_NHIEM_BO_MON, CellType.STRING);
        cellChuNhiemBoMon.setCellValue("CH??? NHI???M B??? M??N");

        Cell cellGiaoVienKhoa = row.createCell(COLUMN_INDEX_GIAO_VIEN_KHOA, CellType.STRING);
        cellGiaoVienKhoa.setCellValue("GI??O V??? KHOA");

        Cell cellPhongDaoTao = row.createCell(COLUMN_INDEX_PHONG_DAO_TAO, CellType.STRING);
        cellPhongDaoTao.setCellValue("PH??NG ????O T???O");

        rowIndex++;

        Row rowKiTen = sheet.createRow(rowIndex);
        Cell cellKiTenGiangVienChamThi = rowKiTen.createCell(COLUMN_INDEX_GIANG_VIEN_CHAM_THI, CellType.STRING);
        cellKiTenGiangVienChamThi.setCellValue("(K??, ghi r?? h??? t??n)");

        Cell cellKiTenChuNhiemBoMon = rowKiTen.createCell(COLUMN_INDEX_CHU_NHIEM_BO_MON, CellType.STRING);
        cellKiTenChuNhiemBoMon.setCellValue("   (K??, ghi r?? h??? t??n)");

        Cell cellKiTenGiaoVienKhoa = rowKiTen.createCell(COLUMN_INDEX_GIAO_VIEN_KHOA, CellType.STRING);
        cellKiTenGiaoVienKhoa.setCellValue("(K??, ghi r?? h??? t??n)    ");

        Cell cellKiTenPhongDaoTao = rowKiTen    .createCell(COLUMN_INDEX_PHONG_DAO_TAO, CellType.STRING);
        cellKiTenPhongDaoTao.setCellValue("(K??, ghi r?? h??? t??n)");

    }

    private static void writeBook(ReportUserScoresResponse scores, Row row, int STT) {
        int COLUMN_INDEX_STT = 0;
        int COLUMN_INDEX_MA_SV = 1;
        int COLUMN_INDEX_NAME = 2;
        int COLUMN_INDEX_CLASS = 7;
        int COLUMN_INDEX_SCORES1 = 8;
        int COLUMN_INDEX_SCORES2 = 9;
        int COLUMN_INDEX_NUMBER = 10;
        int COLUMN_INDEX_STRING = 11;
        int COLUMN_INDEX_NOTE = 12;

        Cell cell = row.createCell(COLUMN_INDEX_STT);
        cell.setCellValue(STT);

        cell = row.createCell(COLUMN_INDEX_MA_SV);
        cell.setCellValue(scores.getMaSinhVien());

        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellValue(scores.getHoVaTen());

        cell = row.createCell(COLUMN_INDEX_CLASS);
        cell.setCellValue(scores.getLop());

        cell = row.createCell(COLUMN_INDEX_SCORES1);
        cell.setCellValue(scores.getDiemThanhPhanMot());

        cell = row.createCell(COLUMN_INDEX_SCORES2);
        cell.setCellValue(scores.getDiemThanhPhanHai());

        cell = row.createCell(COLUMN_INDEX_NUMBER);
        cell.setCellValue(scores.getDiemQuaTrinhBangSo());

        cell = row.createCell(COLUMN_INDEX_STRING);
        cell.setCellValue(scores.getDiemQuaTrinhBangChu());

        cell = row.createCell(COLUMN_INDEX_NOTE);
        cell.setCellValue(scores.getGhiChu());
    }

    private Scores updateScores(ScoresResponse scoresResponse) {
        Scores scores = scoresRepository.findById(scoresResponse.getId()).orElse(null);
        if (scores == null) {
            return null;
        }
        if (scoresResponse.getUserId() != scores.getUserId()
                || scoresResponse.getClassroomId() != scores.getClassroomId()
                || scoresResponse.getType() != scores.getType()) {
            return null;
        }
        scores.setPoint(scoresResponse.getPoint());
        Date now = new Date();
        Timestamp updateNow = new Timestamp(now.getTime());
        scores.setUpdatedAt(updateNow);
        return scores;
    }

    private Scores newScores(ScoresResponse scoresResponse) {
        Scores scores = new Scores();
        scores.setUserId(scoresResponse.getUserId());
        scores.setClassroomId(scoresResponse.getClassroomId());
        scores.setType(scoresResponse.getType());
        scores.setPoint(scoresResponse.getPoint());
        return scores;
    }

    private boolean checkTypeScores(long type) {
        switch (Integer.parseInt("" + type)) {
            case 1:
            case 2:
                return true;
            default:
                return false;
        }
    }
}

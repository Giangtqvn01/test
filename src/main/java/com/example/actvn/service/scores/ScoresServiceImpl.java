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
        cell.setCellValue("HỌC VIỆN KỸ THUẬT MẬT MÃ");
        

        cell = row.createCell(6);
        cell.setCellValue("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,6,13));
        cell.setCellStyle(ExcelUtils.createStyleForTile(sheet));
        

        rowIndex++;
        row = sheet.createRow(rowIndex);
        cell=row.createCell(0);
        cell.setCellValue("Khoa:");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,0,2));
        cell.setCellStyle(ExcelUtils.createStyleForTile(sheet));
        

        cell = row.createCell(6);
        cell.setCellValue("Độc lập - Tự do - Hạnh phúc");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,6,13));
        cell.setCellStyle(ExcelUtils.createStyleForTileGachChan(sheet));



        rowIndex+=2;
        row = sheet.createRow(rowIndex);
        cell=row.createCell(0);
        cell.setCellValue("KẾT QUẢ ĐÁNH GIÁ ĐIỂM QUÁ TRÌNH");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,0,13));
        cell.setCellStyle(ExcelUtils.createStyleForTile(sheet));
        

        rowIndex++;
        row = sheet.createRow(rowIndex);
        cell=row.createCell(0);
        cell.setCellValue("HỌC KỲ 2 NĂM HỌC 2019 - 2020");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,0,13));
        cell.setCellStyle(ExcelUtils.createStyleForTile(sheet));
        


        rowIndex++;
        row = sheet.createRow(rowIndex);
        cell=row.createCell(0);
        cell.setCellValue("Học phần:");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,0,1));
        

        cell=row.createCell(2);
        cell.setCellValue("Kỹ thuật truyền số liệu");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,2,8));
        cell.setCellStyle(ExcelUtils.createStyleTextBold(sheet));

        cell=row.createCell(9);
        cell.setCellValue("Số TC: ");
        

        cell=row.createCell(10);
        cell.setCellValue("2");
        cell.setCellStyle(ExcelUtils.createStyleTextBold(sheet));


        cell=row.createCell(11);
        cell.setCellValue("Mã học phần: ");
        

        cell=row.createCell(12);
        cell.setCellValue("ATDVDV2");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,12,13));
        cell.setCellStyle(ExcelUtils.createStyleTextBold(sheet));


        rowIndex++;
        row = sheet.createRow(rowIndex);
        cell = row.createCell(0);
        cell.setCellValue("Lớp học phần: ");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,0,1));
        

        cell = row.createCell(2);
        cell.setCellValue("Kỹ thuật truyền số liệu-2-19 (A14C2D1-08)");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,2,8));
        cell.setCellStyle(ExcelUtils.createStyleTextBold(sheet));


        cell = row.createCell(9);
        cell.setCellValue("Khóa:");
        

        cell = row.createCell(10);
        cell.setCellValue("AT14");
        cell.setCellStyle(ExcelUtils.createStyleTextBold(sheet));


        rowIndex++;
        row = sheet.createRow(rowIndex);
        cell = row.createCell(0);
        cell.setCellValue("Giảng viên giảng dạy:");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,0,2));
        

        cell = row.createCell(3);
        cell.setCellValue("Phạm Anh Thư");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,3,8));
        


        rowIndex++;
        row = sheet.createRow(rowIndex);
        cell = row.createCell(0);
        cell.setCellValue("Tổng số SV:");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,0,2));
        

        cell = row.createCell(4);
        cell.setCellValue("Số SV dự thi:…. Vắng……Có lý do:……….     Không lý do:…………..");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,4,13));
        


        rowIndex++;
        row = sheet.createRow(rowIndex);
        cell = row.createCell(0);
        cell.setCellValue("Ngày thi:");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,0,2));
        

        cell = row.createCell(4);
        cell.setCellValue("Ngày nộp điểm:");
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
        cell.setCellValue("Mã Sinh Viên");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex+1,1,1));
        cell.setCellStyle(ExcelUtils.createStyleForHeaderTable(sheet));

        cell = row.createCell(2);
        cell.setCellValue("Họ và tên");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex+1,2,6));
        cell.setCellStyle(ExcelUtils.createStyleForHeaderTable(sheet));

        cell = row.createCell(7);
        cell.setCellValue("Lớp");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex+1,7,7));
        cell.setCellStyle(ExcelUtils.createStyleForHeaderTable(sheet));

        cell = row.createCell(8);
        cell.setCellValue("Điểm \nthành \nphần 1");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex+1,8,8));
        cell.setCellStyle(ExcelUtils.createStyleForHeaderTable(sheet));

        cell = row.createCell(9);
        cell.setCellValue("Điểm \nthành \nphần 2");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex+1,9,9));
        cell.setCellStyle(ExcelUtils.createStyleForHeaderTable(sheet));

        cell = row.createCell(10);
        cell.setCellValue("Điểm quá trình");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,10,11));
        cell.setCellStyle(ExcelUtils.createStyleForHeaderTable(sheet));

        cell = row.createCell(12);
        cell.setCellValue("Ghi chú");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex+1,12,13));
        cell.setCellStyle(ExcelUtils.createStyleForHeaderTable(sheet));

        rowIndex=13;
        row = sheet.createRow(rowIndex);
        cell = row.createCell(10);
        cell.setCellValue("Bằng số");
        cell.setCellStyle(ExcelUtils.createStyleForHeaderTable(sheet));

        cell = row.createCell(11);
        cell.setCellValue("Bằng chữ ");
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
        cellNgayThang.setCellValue("Hà Nội, ngày       tháng       năm     ");

        rowIndex++;
        Row row = sheet.createRow(rowIndex);
        Cell cellGiangVienChamThi = row.createCell(COLUMN_INDEX_GIANG_VIEN_CHAM_THI, CellType.STRING);
        cellGiangVienChamThi.setCellValue("GIẢNG VIÊN CHẤM THI");

        Cell cellChuNhiemBoMon = row.createCell(COLUMN_INDEX_CHU_NHIEM_BO_MON, CellType.STRING);
        cellChuNhiemBoMon.setCellValue("CHỦ NHIỆM BỘ MÔN");

        Cell cellGiaoVienKhoa = row.createCell(COLUMN_INDEX_GIAO_VIEN_KHOA, CellType.STRING);
        cellGiaoVienKhoa.setCellValue("GIÁO VỤ KHOA");

        Cell cellPhongDaoTao = row.createCell(COLUMN_INDEX_PHONG_DAO_TAO, CellType.STRING);
        cellPhongDaoTao.setCellValue("PHÒNG ĐÀO TẠO");

        rowIndex++;

        Row rowKiTen = sheet.createRow(rowIndex);
        Cell cellKiTenGiangVienChamThi = rowKiTen.createCell(COLUMN_INDEX_GIANG_VIEN_CHAM_THI, CellType.STRING);
        cellKiTenGiangVienChamThi.setCellValue("(Ký, ghi rõ họ tên)");

        Cell cellKiTenChuNhiemBoMon = rowKiTen.createCell(COLUMN_INDEX_CHU_NHIEM_BO_MON, CellType.STRING);
        cellKiTenChuNhiemBoMon.setCellValue("   (Ký, ghi rõ họ tên)");

        Cell cellKiTenGiaoVienKhoa = rowKiTen.createCell(COLUMN_INDEX_GIAO_VIEN_KHOA, CellType.STRING);
        cellKiTenGiaoVienKhoa.setCellValue("(Ký, ghi rõ họ tên)    ");

        Cell cellKiTenPhongDaoTao = rowKiTen    .createCell(COLUMN_INDEX_PHONG_DAO_TAO, CellType.STRING);
        cellKiTenPhongDaoTao.setCellValue("(Ký, ghi rõ họ tên)");

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

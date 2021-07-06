package com.example.actvn.controller.scores;

import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.scores.ScoresResponse;
import com.example.actvn.security.CurrentUser;
import com.example.actvn.security.UserPrincipal;
import com.example.actvn.service.scores.ScoresService;
import com.example.actvn.util.Logit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
public class ScoresController {
    private static final Logit log = Logit.getInstance(ScoresController.class);
    @Autowired
    ScoresService scoresService;

    @GetMapping("/get-scores")
    public ResponseEntity<?> getScores(@CurrentUser UserPrincipal userPrincipal,
                                       @RequestParam("classroom_id") Long classroomId) {
        log.info("get-scores  ", classroomId);
        long start = System.currentTimeMillis();
        ResponseModel responseModel = scoresService.getScores(classroomId, userPrincipal);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + responseModel.getResponseStatus() + " , " + responseModel.getDescription() + " ,time = " + diff);
        return new ResponseEntity<>(responseModel.getData(), responseModel.getResponseStatus());
    }
    @PostMapping("/create-and-update-scores")
    public ResponseEntity<?> getScores(@CurrentUser UserPrincipal userPrincipal,
                                       @RequestBody List<ScoresResponse> request) {
        log.info("Create or update scores of classroom ");
        long start = System.currentTimeMillis();
        ResponseModel responseModel = scoresService.createOrUpdateScores(request, userPrincipal);
        long end = System.currentTimeMillis();
        long diff = end - start;
        log.info("Code = " + responseModel.getResponseStatus() + " , " + responseModel.getDescription() + " ,time = " + diff);
        return new ResponseEntity<>(responseModel.getData(), responseModel.getResponseStatus());
    }
//    @RequestMapping(value = "/in-chi-tiei-doi-tuong-chu-y", method = RequestMethod.POST, produces = Constants.REPORT.PRODUCES_PDF)
//    @Timed
//    public ResponseEntity<byte[]> inChiTietDoiTuong(HttpServletRequest request,
//                                                    @Valid @RequestParam("id") Long id) {
//        HttpHeaders headers = new HttpHeaders();
//        try {
//            if (id == 0) {
//                return ResponseEntity.badRequest().body(null);
//            }
//            Map<String, Object> parameters = new HashMap<String, Object>();
//            Calendar cal = Calendar.getInstance();
//            parameters.put(ParamsUtils.DOI_TUONG.P_Ngay_Thang_Nam, "Hà Nội" + ", ngày " + cal.get(Calendar.DAY_OF_MONTH)
//                    + " tháng " + (cal.get(Calendar.MONTH) + 1)
//                    + " năm " + cal.get(Calendar.YEAR));
//            parameters.put(ParamsUtils.DOI_TUONG.P_Trung_Tam, "Trung tâm dữ liệu quốc gia về dân cư".toUpperCase());
//            parameters.put(ParamsUtils.DOI_TUONG.P_Cuc, "Cục cảnh sát QLHC về TTXH".toUpperCase());
//            List<InChiTietDoiTuongResponse> doiTuongResponses = new ArrayList<>();
//            String jasperPath = request.getSession().getServletContext()
//                    .getRealPath(Constants.REPORT.CCCD_REPORT_FOLDER + "ChiTietDoiTuongCanChuY.jasper");
//            String jrxmlPath = request.getSession().getServletContext()
//                    .getRealPath(Constants.REPORT.CCCD_REPORT_FOLDER + "ChiTietDoiTuongCanChuY.jrxml");
//            JasperReport jasperReport = ReportUtils.getCompiledFile(jasperPath, jrxmlPath);
//            InChiTietDoiTuongResponse response=doiTuongService.inChiTietDoiTuong(id);
//            doiTuongResponses.add(response);
//            byte[] bytes = ReportUtils.generateReportPDF(doiTuongResponses, parameters, jasperReport);
//            if (bytes == null) {
//                return ResponseEntity.badRequest().body(null);
//            }
//            headers.setContentType(MediaType.parseMediaType(Constants.REPORT.PRODUCES_PDF));
//            headers.setContentDispositionFormData(Constants.REPORT.NAME.GET_MAU_CC04_PDF, Constants.REPORT.NAME.GET_MAU_CC04_PDF);
//            return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
//        } catch (Exception e) {
//            log.error("System error: WordReportResource.getDsDonViWord(HttpServletRequest request) {} ", e);
//            return ResponseEntity.badRequest().body(null);
//        }
//    }
}

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

//    @RequestMapping(value = "/in-ket-qua-danh-gia-qua-trinh", method = RequestMethod.POST, produces = REPORT.PRODUCES_xlsx)
//    public ResponseEntity<byte[]> inKetQuaDanhGiaDiemQuaTrinh(HttpServletRequest request,
//                                                              @CurrentUser UserPrincipal userPrincipal,
//                                                              @RequestParam("classroom_id") Long classroomId) {
//        HttpHeaders headers = new HttpHeaders();
//        try {
//            Map<String, Object> parameters = new HashMap<String, Object>();
//            Calendar cal = Calendar.getInstance();
//            parameters.put(ParamsUtils.KET_QUA_DANH_GIA_DIEM_QUA_TRINH.KHOA, "Công nghệ thông tin");
//            List<ReportUserScoresResponse> scoresResponses = new ArrayList<>();
//            String jasperPath = request.getSession().getServletContext()
//                    .getRealPath(REPORT.CCCD_REPORT_FOLDER + "ket_qua_danh_gia_diem_qua_trinh.jasper");
//            String jrxmlPath = request.getSession().getServletContext()
//                    .getRealPath(REPORT.CCCD_REPORT_FOLDER + "ket_qua_danh_gia_diem_qua_trinh.jrxml");
//            JasperReport jasperReport = ReportUtils.getCompiledFile(jasperPath, jrxmlPath);
//            scoresResponses  = scoresService.inKetQuaDanhGiaDiemQuaTrinh(classroomId, userPrincipal);
//            byte[] bytes = ReportUtils.generateReportPDF(scoresResponses, parameters, jasperReport);
//            if (bytes == null) {
//                return ResponseEntity.badRequest().body(null);
//            }
//            long time = System.currentTimeMillis();
//            headers.setContentType(MediaType.parseMediaType(REPORT.PRODUCES_PDF));
//            headers.setContentDispositionFormData(REPORT.NAME.KET_QUA_DANH_GIA_DIEM_QUA_TRINH + "_" + time + ".xlsx", REPORT.NAME.KET_QUA_DANH_GIA_DIEM_QUA_TRINH + "_" + time + ".xlsx");
//            return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
//        } catch (Exception e) {
//            log.error("System error: WordReportResource.getDsDonViWord(HttpServletRequest request) {} ", e);
//            return ResponseEntity.badRequest().body(null);
//        }
//    }
}

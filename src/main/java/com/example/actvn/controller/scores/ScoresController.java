package com.example.actvn.controller.scores;

import com.example.actvn.model.ResponseModel;
import com.example.actvn.model.scores.ScoresResponse;
import com.example.actvn.security.CurrentUser;
import com.example.actvn.security.UserPrincipal;
import com.example.actvn.service.scores.ScoresService;
import com.example.actvn.util.Logit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping(value = "/download/file-scores")
    public ResponseEntity<ByteArrayResource> downloadFileScoresOfStudent( @CurrentUser UserPrincipal userPrincipal,
                                                                         @RequestParam("classroom_id") Long classroomId) {
        try {
            byte[] bytes = scoresService.downloadFileScoresOfStudent(classroomId, userPrincipal);
           if (bytes == null ){
               log.error("System error: WordReportResource.getDsDonViWord(HttpServletRequest request) {} ");
               return ResponseEntity.badRequest().body(null);
           }
           long time = System.currentTimeMillis();
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "force-download"));
            header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ket_qua_danh_gia_diem_qua_trinh_"+time+".xlsx");
            return new ResponseEntity<>(new ByteArrayResource(bytes),
                    header, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("System error: WordReportResource.getDsDonViWord(HttpServletRequest request) {} ", e);
            return ResponseEntity.badRequest().body(null);
        }
    }
}

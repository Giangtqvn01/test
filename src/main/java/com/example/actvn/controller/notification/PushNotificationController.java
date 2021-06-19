package com.example.actvn.controller.notification;


import com.example.actvn.model.notification.PushNotificationRequest;
import com.example.actvn.model.notification.PushNotificationResponse;
import com.example.actvn.service.notification.PushNotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class PushNotificationController {

    private PushNotificationService pushNotificationService;

    public PushNotificationController(PushNotificationService pushNotificationService) {
        this.pushNotificationService = pushNotificationService;
    }

    @PostMapping("/topic")
    public ResponseEntity sendNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotificationWithoutData(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @PostMapping("/token")
    public ResponseEntity sendTokenNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotificationToToken(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @PostMapping("/data")
    public ResponseEntity sendDataNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotification(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @PostMapping("/data/customdatawithtopic")
    public ResponseEntity sendDataNotificationCustom(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotificationCustomDataWithTopic(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }
    @PostMapping("/data/customdatawithtopicjson")
    public ResponseEntity sendDataNotificationCustomWithSpecificJson(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotificationCustomDataWithTopicWithSpecificJson(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    public void sendAutomaticNotification(){
        PushNotificationRequest request = new PushNotificationRequest();
        request.setTopic("global");
        pushNotificationService.sendPushNotificationCustomDataWithTopicWithSpecificJson(request);
    }
}
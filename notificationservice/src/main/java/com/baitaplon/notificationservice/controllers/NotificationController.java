package com.baitaplon.notificationservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class NotificationController {

    @Autowired
    private JavaMailSender emailSender;

    @PostMapping("/notify")
    public void sendMessage(@RequestBody List<String> list) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("huongdichvu19@gmail.com");
        message.setTo(list.get(0));
        message.setSubject(list.get(1));
        message.setText(list.get(2));
        emailSender.send(message);
    }

}

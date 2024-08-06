package com.akashit.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils1 {

    @Autowired
    private JavaMailSender mailSender;

    public void sendHelloWorldEmail(String to) {
        try {
            MimeMessage mimeMsg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMsg);
            helper.setSubject("Hello World");
            helper.setText("Hello World from Spring Boot!", true);
            helper.setTo(to);
            mailSender.send(mimeMsg);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        }
    }
}

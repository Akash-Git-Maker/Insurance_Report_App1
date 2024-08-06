package com.akashit.controller;

import com.akashit.util.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akashit.util.EmailUtils1;

@RestController
public class EmailController {

    @Autowired
    private EmailUtils emailUtils;

    @GetMapping("sendEmail")
    public String sendEmail() {
        emailUtils.sendEmail("sendemails06@gmail.com"
, "AkashIT", "Test Body", null);
        return "Sent";
    }
}
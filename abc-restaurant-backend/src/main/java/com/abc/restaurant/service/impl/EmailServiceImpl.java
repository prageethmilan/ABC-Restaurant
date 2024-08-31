package com.abc.restaurant.service.impl;

import com.abc.restaurant.constant.EmailTemplate;
import com.abc.restaurant.dto.UserDTO;
import com.abc.restaurant.service.EmailService;
import com.abc.restaurant.util.EmailSender;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

@Service
@Transactional
@Log4j2
public class EmailServiceImpl implements EmailService {
//    private final EmailSender emailSender;
//    private final EmailTemplate emailTemplate;
//
//    @Autowired
//    public EmailServiceImpl(EmailSender emailSender, EmailTemplate emailTemplate) {
//        this.emailSender = emailSender;
//        this.emailTemplate = emailTemplate;
//    }
//
//    @Override
//    public void sendUserOTPEmail(UserDTO userDTO, int otp) throws MessagingException {
//        try {
//
//            emailSender.sendSimpleEmail(userDTO.getEmail(),
//                    EmailTemplate.SEND_USER_PASSWORD_RESET_OTP_EMAIL_SUBJECT,
//                    emailTemplate.sendUserOTPTemplate(otp));
//
//        } catch (Exception e) {
//            throw e;
//        }
//    }
}

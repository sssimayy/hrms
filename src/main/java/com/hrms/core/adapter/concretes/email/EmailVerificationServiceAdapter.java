package com.hrms.core.adapter.concretes.email;

import com.hrms.core.adapter.abstracts.email.EmailVerificationService;
import org.springframework.stereotype.Service;


@Service
public class EmailVerificationServiceAdapter implements EmailVerificationService {

    @Override
    public String sendEmailVerificationCode() {
        return "Verification code is sended. Please check your mail box.";
    }

}

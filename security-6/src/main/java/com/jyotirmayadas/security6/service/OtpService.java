package com.jyotirmayadas.security6.service;

import com.jyotirmayadas.security6.entities.Otp;
import com.jyotirmayadas.security6.reposiories.OtpRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class OtpService {

    private final OtpRepository otpRepository;

    public OtpService(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    public Otp generateOtp(String username) {
        Otp otp = new Otp();
        otp.setUsername(username);
        otp.setOtp(code());

        return otpRepository.save(otp);
    }

    private String code() {
        return String.valueOf(new SecureRandom().nextInt(9999) + 1000);
    }

}

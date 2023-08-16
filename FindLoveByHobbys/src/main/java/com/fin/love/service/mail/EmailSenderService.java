package com.fin.love.service.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.web.session.SimpleRedirectInvalidSessionStrategy;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }
    public String sendEmail(String toEmail) {
        String key = createKey();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dahanyu0@gmail.com");
        message.setTo(toEmail);
        message.setText(makeBoby(key));
        message.setSubject("취밋 아이디 찾기 인증번호 발송");

        mailSender.send(message);

        log.info("mail Sent successfully...");

        return key;
    }

    private String makeBoby(String randomNumber) {
        return """
                안녕하세요 취밋입니다.
                
                화면으로 돌아가 인증번호를 입력해주세요.
                
                인증번호: 
                """
                + randomNumber +
                """
                입니다.
                
                감사합니다.
                """;
    }
}

package com.example.fqw.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSender {

    private final JavaMailSender mailSender;

    public void sendEmail(EmailMessage emailMessage) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(EmailMessage.SENDER);
        mailMessage.setTo(emailMessage.addressee()); //кому -> можно лист эмеилов обработать
        mailMessage.setSubject(EmailMessage.HEADER); //заголовок
        mailMessage.setText(emailMessage.text());

        mailSender.send(mailMessage);
    }

}

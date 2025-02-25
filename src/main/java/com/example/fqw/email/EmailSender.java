package com.example.fqw.email;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmailSender {

    JavaMailSender mailSender;

    public void sendEmail(EmailMessage emailMessage) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(EmailMessage.SENDER);
        mailMessage.setTo(emailMessage.addressee()); //кому -> можно лист эмеилов обработать
        mailMessage.setSubject(EmailMessage.HEADER); //заголовок
        mailMessage.setText(emailMessage.text());

        mailSender.send(mailMessage);
    }

}

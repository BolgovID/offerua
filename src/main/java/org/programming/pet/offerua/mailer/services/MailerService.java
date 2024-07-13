package org.programming.pet.offerua.mailer.services;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.programming.pet.offerua.mailer.exception.SendMessageException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailerService {
    public final JavaMailSender mailSender;

    public void sendMimeMessage(String sendTo, String subject, String emailTemplate) {
        var mimeMessage = mailSender.createMimeMessage();
        var mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setText(emailTemplate, true);
            mimeMessageHelper.setTo(sendTo);
            mimeMessageHelper.setSubject(subject);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Error while sending email to {}: ", sendTo, e);
            throw new SendMessageException();
        }
    }
}
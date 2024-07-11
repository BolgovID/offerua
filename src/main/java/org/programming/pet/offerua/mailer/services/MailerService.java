package org.programming.pet.offerua.mailer.services;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.mailer.MailerInternalService;
import org.programming.pet.offerua.mailer.exception.SendMessageException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailerService implements MailerInternalService {
    public final JavaMailSender mailSender;
    private final EmailTemplateEngine emailTemplateEngine;

    @Override
    public void sendVerificationAccountEmail(String frontEndUrl, String sendTo, String encodedToken) {
        var mimeMessage = mailSender.createMimeMessage();
        var mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        var verificationLink = frontEndUrl + "/auth/verify?data=" + encodedToken;
        var processedString = emailTemplateEngine.applyStylesForVerificationEmail(verificationLink);

        try {
            mimeMessageHelper.setText(processedString, true);
            mimeMessageHelper.setTo(sendTo);
            mimeMessageHelper.setSubject("Verify your email address");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new SendMessageException();
        }
    }

    @Override
    public void sendResetPasswordEmail(String frontEndUrl, String email, String encodedToken) {
        var mimeMessage = mailSender.createMimeMessage();
        var mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        var restoreLink = frontEndUrl + "/auth/restore?data=" + encodedToken;
        var processedString = emailTemplateEngine.applyStylesForRestorePasswordEmail(restoreLink);

        try {
            mimeMessageHelper.setText(processedString, true);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("Restore your password");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new SendMessageException();
        }
    }
}
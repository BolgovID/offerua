package org.programming.pet.offerua.mailer.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
public class EmailTemplateEngine {
    private final TemplateEngine templateEngine;

    public static final String MAIL_VERIFICATION_TEMPLATE_NAME = "mail-verification.html";
    public static final String RESTORE_PASSWORD_TEMPLATE_NAME = "restore-password.html";
    public static final String LINK_TEMPLATE_KEY = "redirectLink";

    public String applyStylesForVerificationEmail(String verificationLink) {
        var context = new Context();
        context.setVariable(LINK_TEMPLATE_KEY, verificationLink);
        return templateEngine.process(MAIL_VERIFICATION_TEMPLATE_NAME, context);
    }

    public String applyStylesForRestorePasswordEmail(String restoreLink) {
        var context = new Context();
        context.setVariable(LINK_TEMPLATE_KEY, restoreLink);
        return templateEngine.process(RESTORE_PASSWORD_TEMPLATE_NAME, context);
    }
}
package org.programming.pet.offerua.mailer.services;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.rabbit.message.EmailRedirectMessage;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
public class EmailTemplateEngine {
    private final TemplateEngine templateEngine;

    public static final String MAIL_VERIFICATION_TEMPLATE_NAME = "mail-verification.html";
    public static final String RESTORE_PASSWORD_TEMPLATE_NAME = "restore-password.html";

    public String toRestorePasswordTemplate(EmailRedirectMessage emailContent) {
        var context = prepareTemplateContext(emailContent);
        return templateEngine.process(RESTORE_PASSWORD_TEMPLATE_NAME, context);
    }

    public String toVerifiedEmailTemplate(EmailRedirectMessage emailContent) {
        var context = prepareTemplateContext(emailContent);
        return templateEngine.process(MAIL_VERIFICATION_TEMPLATE_NAME, context);
    }

    private Context prepareTemplateContext(EmailRedirectMessage emailContent) {

        var context = new Context();
        context.setVariable("redirectTo", emailContent.redirectTo());
        context.setVariable("firstName", emailContent.firstName());
        return context;
    }
}
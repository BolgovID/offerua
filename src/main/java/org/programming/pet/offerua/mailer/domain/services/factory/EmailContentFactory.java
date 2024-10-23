package org.programming.pet.offerua.mailer.domain.services.factory;

import lombok.RequiredArgsConstructor;
import org.programming.pet.offerua.common.rabbit.message.EmailRedirectMessage;
import org.programming.pet.offerua.mailer.domain.model.SimpleEmailContent;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
public class EmailContentFactory {
    private final TemplateEngine templateEngine;

    public static final String MAIL_VERIFICATION_TEMPLATE_NAME = "mail-verification.html";
    public static final String RESTORE_PASSWORD_TEMPLATE_NAME = "restore-password.html";


    public SimpleEmailContent createVerifyEmailContent(EmailRedirectMessage message) {
        var emailTemplateContent = verifyEmailContentAsString(message);
        return new SimpleEmailContent("Verify your email address", emailTemplateContent);
    }

    public SimpleEmailContent createRestorePasswordEmailContent(EmailRedirectMessage message) {
        var emailTemplateContent = restorePasswordEmailContentAsString(message);
        return new SimpleEmailContent("Restore your password", emailTemplateContent);
    }

    private String restorePasswordEmailContentAsString(EmailRedirectMessage emailContent) {
        var context = prepareTemplateContext(emailContent);
        return templateEngine.process(RESTORE_PASSWORD_TEMPLATE_NAME, context);
    }

    private String verifyEmailContentAsString(EmailRedirectMessage emailContent) {
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

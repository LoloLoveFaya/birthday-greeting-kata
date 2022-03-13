package com.tdd.app.domain;

public class MailBuilder {
    public String recipient;

    public String content;

    public String subject;

    public MailBuilder() {
    }

    public MailBuilder setRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public MailBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public MailBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Mail build() {
        return new Mail(this);
    }

}

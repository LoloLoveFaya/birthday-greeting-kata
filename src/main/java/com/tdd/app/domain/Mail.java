package com.tdd.app.domain;

public class Mail {

    public String recipient;

    public String content;

    public String subject;

    public Mail(MailBuilder mailBuilder) {
        this.recipient = mailBuilder.recipient;
        this.content = mailBuilder.content;
        this.subject = mailBuilder.subject;
    }
}

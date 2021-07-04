package com.tdd.app.domain;

public class Mail {

    public String recipient;

    public String content;

    public String subject;

    public Mail(String recipient, String content, String subject) {
        this.recipient = recipient;
        this.content = content;
        this.subject = subject;
    }
}

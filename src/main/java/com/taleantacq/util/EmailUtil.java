package com.taleantacq.util;

import java.io.File;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taleantacq.domain.Mail;
import com.taleantacq.serviceImpl.EmailService;

public final class EmailUtil {
	
	@Autowired
	private EmailService emailService;

	public void sendEmail(String fileName, String email) throws MessagingException {
		File validatedFile=new File(fileName);
		System.out.println("Email sent to === "+email+" contain filename ===================== " +validatedFile);
		Mail mail = new Mail();
		mail.setFrom("no-reply@memorynotfound.com");
        mail.setTo(email);
        mail.setSubject("Sending Email Attachment Configuration Example");
        mail.setContent("This tutorial demonstrates how to send an email with attachment using Spring Framework.");
		emailService.sendSimpleMail(mail, validatedFile);
	}
}

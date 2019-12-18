/**
 * 
 */
package com.taleantacq.serviceImpl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.taleantacq.domain.Mail; 

/**
 * @author yadak
 *
 */

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender emailSender;

public void sendSimpleMail(Mail mail,File file) throws MessagingException {
	
	MimeMessage mimeMessage = emailSender.createMimeMessage();
	
	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	
	helper.setSubject(mail.getSubject());
	helper.setFrom(mail.getFrom());
	helper.setTo(mail.getTo());
	helper.setText(mail.getContent());
	
	helper.addAttachment("Failed_Candidate_Error_Details.xlsx", file);
	emailSender.send(mimeMessage);
	
}

}

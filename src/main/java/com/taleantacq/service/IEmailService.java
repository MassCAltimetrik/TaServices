/**
 * 
 */
package com.taleantacq.service;

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

public interface IEmailService {
	

public void sendSimpleMail(Mail mail,File file) throws MessagingException ;

}

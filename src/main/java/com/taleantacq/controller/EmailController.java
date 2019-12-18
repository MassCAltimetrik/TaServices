package com.taleantacq.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.taleantacq.domain.CandidateResponse;
import com.taleantacq.domain.EmailRequest;
import com.taleantacq.service.ICandidateService;

@Controller
public class EmailController {

	@Autowired
	ICandidateService candidateServiceImpl;
	@Autowired
	private JavaMailSender emailSender;
	
    @GetMapping("/email/{taId}")
    public ModelAndView doLogin(@PathVariable("taId")Integer taID, Model model ) throws SQLException {
    	CandidateResponse rs= candidateServiceImpl.getCandidateDetails(taID,0);
    	model.addAttribute("data", rs.getCandidateList());
    	model.addAttribute("taid", taID);
        return new ModelAndView("email");
    }
    
    @PostMapping("/email")
	@ResponseBody
	public String sendEmails(@RequestBody EmailRequest emailRequest) throws IOException, MessagingException {
		String emailids = emailRequest.getEmailIDs();
		String emailAddress[] = emailids.substring(0,emailids.length()-1).split(",");
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		
		helper.setSubject(emailRequest.getEmailSubject());
		helper.setFrom("altitalent-in@altimetrik.com");
		helper.setText(emailRequest.getEmailBody());
		
		for(String emailId :emailAddress) {
			helper.setTo(emailId);
			emailSender.send(mimeMessage);
		}
    	return "Success";
	}
}
package com.taleantacq.util;
//
//import com.twilio.Twilio;
//import com.twilio.rest.ipmessaging.v1.service.channel.Message;
//import com.twilio.type.PhoneNumber;

public class SMSUtil {
	private final String TWILIO_NUMBER = "+12078020164";
	private static final String ACCOUNT_SID = "ACeabf66bd5fde78c0b1e468ef79dfdb9f";
	private static final String AUTH_ID = "4f7508865dea50946594e5c9a158ec8e";
	
	/*static {
	      Twilio.init(ACCOUNT_SID, AUTH_ID);
	   }

	public void sendSMS(String number,String smsBody) {
		Message message = Message.creator(
			    new PhoneNumber(number),
			    new PhoneNumber(TWILIO_NUMBER),
			    "Sample Twilio SMS using Java")
			.create();
	}*/
}


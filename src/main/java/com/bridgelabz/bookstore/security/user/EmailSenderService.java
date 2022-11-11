package com.bridgelabz.bookstore.security.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderService {
	@Autowired
    JavaMailSender javaMailSender;
	
	public void sendEmail(String toEmail, String subject, String body ) {
	
	SimpleMailMessage message=new SimpleMailMessage();
	message.setFrom("vatareamruta615@gmail.com");
	message.setTo(toEmail);
	message.setText(body);
	message.setSubject(subject);
	javaMailSender.send(message);
	System.out.println("Mail sent to the User...!");
		
	}
}

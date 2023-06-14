package fr.helpad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import fr.helpad.entity.EmailDetails;

@Service
public class EmailServiceImpl implements EmailService{
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	public String sendSimpleMail(EmailDetails details)
    {
 
        // Try block to check for exceptions
        try {
        	
            // Creating a simple mail message
            SimpleMailMessage mailMessage
                = new SimpleMailMessage();
 
            // Setting up necessary details
            mailMessage.setFrom(details.getFrom());
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
 
            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }
 
        // Catch block to handle the exceptions
        catch (Exception e) {
        	throw e;
            //return "Error while Sending Mail";
        }
    }
 
}

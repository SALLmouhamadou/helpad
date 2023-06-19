package fr.helpad.service;

import fr.helpad.entity.EmailDetails;

public interface EmailService {
	String sendSimpleMail(EmailDetails details);
}

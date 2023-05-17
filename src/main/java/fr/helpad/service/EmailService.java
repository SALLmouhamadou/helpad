package fr.helpad.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;

public class EmailService {
	 @Autowired
	    private JavaMailSender mailSender;

	    public void sendEmail(String to, String subject, String text) throws Exception {
	        String userId = "me";
	        String from = "salllmouha10@gmail.com";

	        // Créer un flux d'autorisation OAuth 2.0
	        AuthorizationCodeFlow flow = getAuthorizationCodeFlow();

	        // Obtenir les informations d'identification de l'utilisateur
	        Credential credential = flow.loadCredential(userId);
	        if (credential == null) {
	            // Demander une autorisation si les informations d'identification n'existent pas
	            String authorizationUrl = flow.newAuthorizationUrl().setRedirectUri("http://localhost:8080/oauth2callback").build();
	            System.out.println("Ouvrez le lien suivant dans votre navigateur :");
	            System.out.println(authorizationUrl);
	            System.out.println("Copiez le code d'autorisation après l'authentification :");
	            // L'utilisateur doit saisir le code d'autorisation dans la console
	            String authorizationCode = System.console().readLine();
	            TokenResponse tokenResponse = flow.newTokenRequest(authorizationCode).setRedirectUri("http://localhost:8080/oauth2callback").execute();
	            credential = flow.createAndStoreCredential(tokenResponse, userId);
	        }

	        // Créer une session de messagerie
	        Session session = Session.getDefaultInstance(new Properties(), null);

	        // Créer un objet MimeMessage
	        MimeMessage mimeMessage = new MimeMessage(session);
	        mimeMessage.setFrom(new InternetAddress(from));
	        mimeMessage.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
	        mimeMessage.setSubject(subject);
	        mimeMessage.setText(text);

	        // Convertir l'objet MimeMessage en chaîne encodée Base64
	        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	        mimeMessage.writeTo(buffer);
	        byte[] bytes = buffer.toByteArray();
	        @SuppressWarnings("deprecation")
			String encodedEmail = Base64.encodeBase64URLSafeString(bytes);

	        // Envoyer l'e-mail via l'API Gmail
	        Gmail service = getGmailService(credential);
	        Message message = new Message();
	        message.setRaw(encodedEmail);
	        service.users().messages().send(userId, message).execute();
	    }
	    private AuthorizationCodeFlow getAuthorizationCodeFlow() throws Exception {
	        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
	        JsonFactory jsonFactory = new JacksonFactory();
	        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(getClass().getResourceAsStream("/client_secrets.json")));
	        return new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, clientSecrets, Collections.singleton(GmailScopes.GMAIL_SEND))
	                .setAccessType("offline")
	                .setApprovalPrompt("force")
	                .build();
	    }

	    private Gmail getGmailService(Credential credential) throws Exception {
	        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
	        JsonFactory jsonFactory = new JacksonFactory();
	        Gmail service = new Gmail.Builder(httpTransport, jsonFactory, credential)
	                .setApplicationName("Spring Boot Email")
	                .build();
	        return service;
	    }
}

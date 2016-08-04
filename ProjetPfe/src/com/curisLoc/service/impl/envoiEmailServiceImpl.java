package com.curisLoc.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.curisLoc.model.UserClinique;
import com.curisLoc.model.UserMedecin;
import com.curisLoc.service.envoiEmailService;

@Service
public class envoiEmailServiceImpl implements envoiEmailService {

	@Override
	public void envoiEmail(String sujet, String texte,List<File> attachement,
			String... recipients) {
		
	try {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setProtocol("smtp");
		mailSender.setUsername("jihan.ferfess17@gmail.com");
		mailSender.setPassword("jihansihamnihal");
		mailSender.setDefaultEncoding("utf-8");
		
		Properties properties = new Properties();
		properties.setProperty("username", "jihan.ferfess17@gmail.com");
		properties.setProperty("password", "jihansihamnihal");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.transport.protocol", "smtp");
		mailSender.setJavaMailProperties(properties);
		
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, false);
		helper.setFrom("jihan.ferfess17@gmail.com");
		helper.setSubject(sujet);
		helper.setText(texte, true);
		//helper.addTo("jihan.ferfess17@gmail.com");
		for(String recipient: recipients ){
			helper.addTo(recipient);
		}
		
		mailSender.send(msg);
		
		System.out.println("envoi email...");
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}


	@Override
	@Async
	public void envoiEmailRegisterUserClinique(UserClinique uc, String password) {
		String sujet="Registration de l'utilisateur";
		String texte= takeHTMLEmail("ressource/email_sendTo_userClinique.html");
		
		texte = texte.replace("{clinique.nom}",uc.getNom());
		texte = texte.replace("{clinique.login}",uc.getLogin());
		texte = texte.replace("{clinique.password}",password);
		
		envoiEmail(sujet, texte,null, uc.getEmail());
	
	}


	public String takeHTMLEmail(String url) {
		InputStream is = getClass().getResourceAsStream(url);
		BufferedInputStream bis = new BufferedInputStream(is);
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		int result;
		try {
			result = bis.read();
			while (result != -1) {
				byte b = (byte) result;
				buf.write(b);
				result = bis.read();
			}
			return buf.toString("UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}


	@Override
	@Async
	public void envoiEmailRegisterUserMedecin(UserMedecin um, String password) {
		String sujet="Registration de l'utilisateur";
		String texte= takeHTMLEmail("ressource/email_sendTo_userClinique.html");
		
		texte = texte.replace("{clinique.nom}",um.getNom());
		texte = texte.replace("{clinique.login}",um.getLogin());
		texte = texte.replace("{clinique.password}",password);
		
		envoiEmail(sujet, texte,null, um.getEmail());
		
	}

}

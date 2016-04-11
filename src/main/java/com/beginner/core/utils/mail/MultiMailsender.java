package com.beginner.core.utils.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class MultiMailsender {

	/**
	 * 发送邮件给多个接收者
	 * @param mailInfo 带发送邮件的信息
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static boolean sendMailtoMultiReceiver(MailSenderInfo mailInfo) {
		MyAuthenticator authenticator = null;
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		Session sendMailSession = Session.getInstance(mailInfo.getProperties(), authenticator);
		try {
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address[] tos = null;
			String[] receivers = mailInfo.getReceivers();
			if (receivers != null) {
				// 为每个邮件接收者创建一个地址
				tos = new InternetAddress[receivers.length + 1];
				tos[0] = new InternetAddress(mailInfo.getToAddress());
				for (int i = 0; i < receivers.length; i++) {
					tos[i + 1] = new InternetAddress(receivers[i]);
				}
			} else {
				tos = new InternetAddress[1];
				tos[0] = new InternetAddress(mailInfo.getToAddress());

			}
			// 将所有接收者地址都添加到邮件接收者属性中
			mailMessage.setRecipients(Message.RecipientType.TO, tos);
			mailMessage.setSubject(MimeUtility.encodeText(mailInfo.getSubject(), "UTF-8", "B"));
			mailMessage.setSentDate(new Date());
			// 设置邮件内容
			Multipart mainPart = new MimeMultipart();
			BodyPart html = new MimeBodyPart();
			html.setContent(mailInfo.getContent(), "text/html; charset=UTF-8");
			mainPart.addBodyPart(html);
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	/**
	 * 发送带抄送的邮件
	 * @param mailInfo 待发送邮件的消息
	 * @return
	 */
	public static boolean sendMailtoMultiCC(MailSenderInfo mailInfo) {
		MyAuthenticator authenticator = null;
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		Session sendMailSession = Session.getInstance(mailInfo.getProperties(), authenticator);
		try {
			Message mailMessage = new MimeMessage(sendMailSession);

			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 获取抄送者信息
			String[] ccs = mailInfo.getCcs();
			if (ccs != null) {
				// 为每个邮件接收者创建一个地址
				Address[] ccAdresses = new InternetAddress[ccs.length];
				for (int i = 0; i < ccs.length; i++) {
					ccAdresses[i] = new InternetAddress(ccs[i]);
				}
				// 将抄送者信息设置到邮件信息中，注意类型为Message.RecipientType.CC 
				mailMessage.setRecipients(Message.RecipientType.CC, ccAdresses);
			}
			mailMessage.setSubject(MimeUtility.encodeText(mailInfo.getSubject(), "UTF-8", "B"));
			mailMessage.setSentDate(new Date());
			// 设置邮件内容
			Multipart mainPart = new MimeMultipart();
			BodyPart html = new MimeBodyPart();
			html.setContent(mailInfo.getContent(), "text/html; charset=UTF-8");
			mainPart.addBodyPart(html);
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return false;
	}
}

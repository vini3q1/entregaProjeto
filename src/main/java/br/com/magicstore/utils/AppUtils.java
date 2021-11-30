package br.com.magicstore.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class AppUtils {

	private static final String PATTERN_YYYY_MM_DD_HH_MM_SS_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	private static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyyMMddHHmmss";
	private static final String PATTERN_DDMMYYYYHHMMSS = "ddmmyyyyHHMMss";
	private static final String PATTERN_DDMMYYYY = "ddmmyyyy";

	private static final DateTimeFormatter FORMATTER_YYYY_MM_DD_HH_MM_SS_DEFAULT = DateTimeFormatter
			.ofPattern(PATTERN_YYYY_MM_DD_HH_MM_SS_DEFAULT);
	private static final DateTimeFormatter FORMATTER_YYYY_MM_DD_HH_MM_SS = DateTimeFormatter
			.ofPattern(PATTERN_YYYY_MM_DD_HH_MM_SS);
	private static final DateTimeFormatter FORMATTER_DDMMYYYYHHMMSS = DateTimeFormatter
			.ofPattern(PATTERN_DDMMYYYYHHMMSS);
	private static final DateTimeFormatter FORMATTER_DDMMYYYY = DateTimeFormatter.ofPattern(PATTERN_DDMMYYYY);

	public static Date toDate(LocalDate localDate) {
		Date dataRetorno = null;
		try {
			if (localDate != null) {
				dataRetorno = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			}
		} catch (Exception e) {
		}
		return dataRetorno;
	}

	public static Date toDate(LocalDateTime localDate) {
		Date dataRetorno = null;
		try {
			if (localDate != null) {
				dataRetorno = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
			}
		} catch (Exception e) {
		}
		return dataRetorno;
	}

	public static LocalDate toLocalDate(Date date) {
		LocalDate dataRetorno = null;
		try {
			if (date != null) {
				dataRetorno = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			}
		} catch (Exception e) {
		}
		return dataRetorno;
	}

	public static LocalDateTime toLocalDateTime(Date date) {
		LocalDateTime dataRetorno = null;
		try {
			if (date != null) {
				dataRetorno = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
			}
		} catch (Exception e) {
		}
		return dataRetorno;
	}

	public static Date getDateNow() {
		return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static String converterDatePatternYyyyMmDdHhMmSsDefault(Date date) {
		String dataRetorno = null;
		try {
			if (date != null) {
				LocalDateTime dateTime = toLocalDateTime(date);
				if (dateTime != null) {
					dataRetorno = dateTime.format(FORMATTER_YYYY_MM_DD_HH_MM_SS_DEFAULT);
				}
			}
		} catch (Exception e) {
		}
		return dataRetorno;
	}

	public static String converterDatePatternYyyyMmDdHhMmSs(Date date) {
		String dataRetorno = null;
		try {
			if (date != null) {
				LocalDateTime dateTime = toLocalDateTime(date);
				if (dateTime != null) {
					dataRetorno = dateTime.format(FORMATTER_YYYY_MM_DD_HH_MM_SS);
				}
			}
		} catch (Exception e) {
		}
		return dataRetorno;
	}

	public static Date converterDatePatternDdMmYyyyHhMmSs(String date) {
		Date dataRetorno = null;
		try {
			if (date != null) {
				dataRetorno = toDate(LocalDateTime.parse(date, FORMATTER_DDMMYYYYHHMMSS));
			}
		} catch (Exception e) {
		}
		return dataRetorno;
	}

	public static Date converterDatePatternYyyyMmDdHhMmSs(String date) {
		Date dataRetorno = null;
		try {
			if (date != null) {
				dataRetorno = toDate(LocalDateTime.parse(date, FORMATTER_YYYY_MM_DD_HH_MM_SS_DEFAULT));
			}
		} catch (Exception e) {
		}
		return dataRetorno;
	}

	public static Date converterDatePatternDdMmYyyy(String date) {
		Date dataRetorno = null;
		try {
			if (date != null) {
				dataRetorno = toDate(LocalDateTime.parse(date, FORMATTER_DDMMYYYY));
			}
		} catch (Exception e) {
		}
		return dataRetorno;
	}

	public static String gerarCodigoPedido() {

		String caracteres = "";
		StringBuilder builder = null;

		caracteres = "0123456789";

		builder = new StringBuilder(20);
		builder.append("#MS2021_");
		for (int i = 0; i < 10; i++) {
			int index = (int) (caracteres.length() * Math.random());
			builder.append(caracteres.charAt(index));
		}

		return builder.toString();

	}
	
	public static String gerarCodigoAleatorio() {

		String caracteres = "";
		StringBuilder builder = null;

		caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ@#*0123456789";

		builder = new StringBuilder(30);
		for (int i = 0; i < 30; i++) {
			int index = (int) (caracteres.length() * Math.random());
			builder.append(caracteres.charAt(index));
		}

		return builder.toString();

	}

	public static String encryptPass(String pass) {
		return Base64.getEncoder().encodeToString(pass.getBytes());
	}

	public static void envioEmail(String email, String emailSender, String senha, String texto) {

		String host = "smtp.gmail.com";
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(emailSender, senha);

			}

		});

		// Used to debug SMTP issues
		session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(emailSender));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

			// Set Subject: header field
			message.setSubject("MAGIC STORE - Loja Virtual");

			// Now set the actual message
			message.setText(texto);

			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

}

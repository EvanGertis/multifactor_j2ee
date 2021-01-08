package com.jsp_myblog.user.command;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.jsp_myblog.user.dao.UserDao;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;

public class UserSignupCommand implements UserCommand {
	final GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator(); 
	static final Logger logger = Logger.getLogger(UserSignupCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {	
		BasicConfigurator.configure();
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		GoogleAuthenticatorKey googleAuthenticatorKey = googleAuthenticator.createCredentials();
		String secretKey = googleAuthenticatorKey.getKey();
		
		String qrCodeUrl = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL(null, email, googleAuthenticatorKey);
		logger.debug(secretKey);
		logger.debug(qrCodeUrl);
		UserDao dao = new UserDao();
		
		try {
			byte[] qrCode = createQRCode(qrCodeUrl, 400, 400);
			
			response.setContentType("image/png");
	        OutputStream outputStream = response.getOutputStream();
	        outputStream.write(qrCode);
	        outputStream.flush();
	        outputStream.close();
		}
		catch(Exception e) {
			
		}
		
		try {
			dao.signup(id, password, name, email, secretKey);
			return "signup_success.jsp";
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "signup_view.user";
	}
	
	public static byte[] createQRCode(String barCodeUrl, int height, int width)
	        throws WriterException, IOException {
		try { 
			BitMatrix bitMatrix = new MultiFormatWriter().encode(barCodeUrl, BarcodeFormat.QR_CODE,
		            width, height);
		     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		     MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
		     return byteArrayOutputStream.toByteArray();
		}
		catch(Exception e) {
			return null;
		}
	}
}

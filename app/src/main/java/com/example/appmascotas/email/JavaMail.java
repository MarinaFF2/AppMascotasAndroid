package com.example.appmascotas.email;

import android.app.Activity;
import android.content.Context;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {
    //Variables
    private Context mActivity;
    private Message messege;
    private String mEmail;
    private String mSubject;
    private String mMessage;

    private String remitente ="tiendapelis2021@gmail.com";
    private String pwd = "aplicacionPelis20";

    public JavaMail(Activity mActivity, String mEmail, String mSubject, String mMessage) {
        this.mActivity = mActivity;
        this.mEmail = mEmail.trim(); //quitamos cualquier espacio que pueda tener el correo
        this.mSubject = mSubject.trim();
        this.mMessage = mMessage;

        //ejecutamos la clase
        execute();
    }

    public Message getMessege() {
        return messege;
    }

    public void execute(){
        //iniciamos las porpiedades
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //host
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true"); //autor
        props.put("mail.smtp.starttls.enable", "true");//TLS
        props.put("mail.smtp.port", "587"); //puerto

        //iniciamos la sesion
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente,pwd);
            }
        });


        try {
            //iniciaciamos el contenido del email
            messege = new MimeMessage(session);

            //direccion del remitente
            messege.setFrom(new InternetAddress(remitente));
            //Adding recipient email
            messege.addRecipient(Message.RecipientType.TO, new InternetAddress(mEmail));
            //añadimos el asunto del mensaje
            messege.setSubject(mSubject);
            //añadimos el mensaje
            messege.setText(mMessage);
            //enviamos email
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}


package com.huellitas.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServicio {
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Async
    public void enviarMail ( String to, String mensaje){
    
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        
        mailMessage.setFrom("majo.boero@gmail.com");
        mailMessage.setTo(to);
        mailMessage.setSubject("Información contacto Adopción");
        mailMessage.setText(mensaje);
        
        javaMailSender.send(mailMessage);
    
        
    }
}

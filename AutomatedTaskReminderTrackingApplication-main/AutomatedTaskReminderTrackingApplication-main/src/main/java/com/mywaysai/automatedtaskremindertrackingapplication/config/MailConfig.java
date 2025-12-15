//package com.mywaysai.automatedtaskremindertrackingapplication.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//
//@Configuration
//public class MailConfig {
//	
// @Bean
// public JavaMailSender javaMailSender(org.springframework.core.env.Environment env) {
//     JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//    // mailSender.setHost(env.getProperty("spring.mail.host"));
//     mailSender.setPort(Integer.parseInt(env.getProperty("spring.mail.port", "25")));
////     mailSender.setUsername(env.getProperty("spring.mail.username"));
////     mailSender.setPassword(env.getProperty("spring.mail.password"));
////     
//		mailSender.setHost("smtp.gmail.com");
//		mailSender.setPort(587);
//		mailSender.setUsername("madhuritakekar1@gmail.com");
//		mailSender.setPassword("jdyognactlhitvli"); // your Gmail App Password
//
//     
//     
//
//     Properties props = mailSender.getJavaMailProperties();
//     props.put("mail.transport.protocol", "smtp");
//     props.put("mail.smtp.auth", env.getProperty("spring.mail.properties.mail.smtp.auth", "true"));
//     props.put("mail.smtp.starttls.enable", env.getProperty("spring.mail.properties.mail.smtp.starttls.enable", "true"));
//     props.put("mail.debug", "false");
//     return mailSender;
// }
//}
package com.mywaysai.automatedtaskremindertrackingapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


import java.util.Properties;

@Configuration

public class MailConfig {

    @Bean
    @Primary
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("sachikandi1008@gmail.com");
        mailSender.setPassword("yrka omkz huzw nmko"); //

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.connectiontimeout", "8081");
        props.put("mail.smtp.timeout", "8081");

        return mailSender;
    }
}

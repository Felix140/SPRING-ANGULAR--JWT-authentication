package com.auth.jwt.backend.config;

import java.util.Arrays;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//Questa classe servira per creare il filtro CORS delle richieste FE
@Configuration
@EnableWebMvc
public class WebConfig {
	
	@Bean
	public FilterRegistrationBean corsFilter() {
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		
		 // Questo permete al BE di ricevere gli HEADERS che contengono le info di AUTENTICAZIONE
		corsConfiguration.setAllowCredentials(true);
		
		corsConfiguration.addAllowedOrigin ("http://localhost:4200"); // url FRONTEND
		
		// Qui setto gli HEADERS che il BE dovra accettare
		corsConfiguration.setAllowedHeaders(Arrays.asList(
				HttpHeaders.AUTHORIZATION,
				HttpHeaders.CONTENT_TYPE,
				HttpHeaders.ACCEPT
				));
		
		// Qui setto le REQUEST da accettare		
		corsConfiguration.setAllowedMethods (Arrays.asList(
				HttpMethod.GET.name(),
				HttpMethod.POST.name(),
				HttpMethod.PUT.name(),
				HttpMethod.DELETE.name()));
		
		// Qui setto il tempo in cui il CORS config viene accettato: 30 minuti
		corsConfiguration.setMaxAge(3600L);
		
		// Qui applico la CONFIGURAZIONE a TUTTE LE ROTTE!
		source.registerCorsConfiguration("/**", corsConfiguration);

		// Posiziono il BEAN corrente alla posizione piu bassa...
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		// ...per essere eseguito prima di ogni altro BEAN!
		bean.setOrder(-102);
		
		// infine ritorna il bean
		return bean;
	}

}

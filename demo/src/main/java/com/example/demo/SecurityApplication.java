package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@SpringBootApplication // класс конфигурации, запускает автоматическую настройку и сканирование компонентов
@EnableOAuth2Sso
@RestController // Применив ее, добавляются аннотации @Controller, а так же @ResponseBody(указывает, что возвращаемое значение метода должно быть привязано к телу ответа)
				// применяется ко всем методам.
public class SecurityApplication extends WebSecurityConfigurerAdapter {

	@RequestMapping("/user")
	public Principal user(Principal principal){ //Principal - данный интерфейс представляет собой абстрактное понятие,
												// которое может быть использовано для представлеия сущности, а так же login id
		return principal;
	}

	protected void configure(HttpSecurity http) throws Exception{
		http
				.antMatcher("/**") // адрес начинается с /
				.authorizeRequests()
					.antMatchers("/", "/login**", "/webjars**") //разрешаем запросы на домашнюю страницу и страницу login
					.permitAll()
				.anyRequest().authenticated(); // остальные запросы требуют аутентификации
	}

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
}

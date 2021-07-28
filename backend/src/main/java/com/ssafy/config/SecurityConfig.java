package com.ssafy.config;

import com.ssafy.common.auth.CustomOAuth2Provider;
import com.ssafy.common.auth.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomOAuth2UserService customOAuth2UserService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.headers().frameOptions().disable()
//				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
					.authorizeRequests()
					.antMatchers( "/", "/login/**").permitAll()
					.anyRequest().authenticated()
				.and()
					.oauth2Login()
//						.loginPage("/login")
//						.authorizationEndpoint().baseUri("login/oauth2/autherization")
//					.and()
						.defaultSuccessUrl("/loginSuccess")
					 	.failureUrl("/loginFailure")
						.userInfoEndpoint()
						.userService(customOAuth2UserService);
	}


	@Bean
	public ClientRegistrationRepository clientRegistrationRepository(OAuth2ClientProperties oAuth2ClientProperties
			, @Value("${spring.security.oauth2.client.registration.kakao.client-id}") String kakaoClientId
			, @Value("${spring.security.oauth2.client.registration.kakao.client-secret}") String kakaoClientSecret) {
//		List<ClientRegistration> registrations = oAuth2ClientProperties.getRegistration().keySet().stream()
//														 .map(client -> getRegistration(oAuth2ClientProperties, client))
//														 .filter(Objects::nonNull)
//														 .collect(Collectors.toList());
		List<ClientRegistration> registrations = new ArrayList<>();

		System.out.println("====> getRegistrationRepository");
		registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao")
								//rest client id
								  .clientId(kakaoClientId)
								//client secret
								  .clientSecret(kakaoClientSecret)
								//temp uri(사용x)
								  .jwkSetUri("temp")
								  .build());

		return new InMemoryClientRegistrationRepository(registrations);
	}
}
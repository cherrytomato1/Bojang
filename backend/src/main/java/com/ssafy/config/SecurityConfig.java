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
						.defaultSuccessUrl("/loginSuccess")
					 	.failureUrl("/loginFailure")
						.userInfoEndpoint()
						.userService(customOAuth2UserService);
	}


	@Bean
	public ClientRegistrationRepository clientRegistrationRepository(/*OAuth2ClientProperties oAuth2ClientProperties*/
			@Value("${spring.security.oauth2.client.registration.kakao.client-id}") String kakaoClientId
			, @Value("${spring.security.oauth2.client.registration.kakao.client-secret}") String kakaoClientSecret) {
//		List<ClientRegistration> registrations = oAuth2ClientProperties.getRegistration().keySet().stream()
//														 .map(client -> getRegistration(oAuth2ClientProperties, client))
//														 .filter(Objects::nonNull)
//														 .collect(Collectors.toList());
		List<ClientRegistration> registrations = new ArrayList<>();

		System.out.println("====> getRegistrationRepository");
		registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao")
								  .clientId(kakaoClientId)
								  .clientSecret(kakaoClientSecret) //필요없는 값인데 null이면 실행이 안되도록 설정되어 있음
								  .jwkSetUri("test") //필요없는 값인데 null이면 실행이 안되도록 설정되어 있음
								  .build());

		return new InMemoryClientRegistrationRepository(registrations);
	}

	private ClientRegistration getRegistration(OAuth2ClientProperties clientProperties, String client) {
		if ("google".equals(client)) {
			OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("google");
			return CommonOAuth2Provider.GOOGLE.getBuilder(client)
						   .clientId(registration.getClientId())
						   .clientSecret(registration.getClientSecret())
						   .scope("email", "profile")
						   .build();
		}
		if ("facebook".equals(client)) {
			OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("facebook");
			return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
						   .clientId(registration.getClientId())
						   .clientSecret(registration.getClientSecret())
						   .userInfoUri("https://graph.facebook.com/me?fields=id,name,email,link")
						   .scope("email")
						   .build();
		}
		return null;
	}
}
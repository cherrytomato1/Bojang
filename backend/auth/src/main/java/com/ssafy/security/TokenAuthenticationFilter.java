package com.ssafy.security;

import com.ssafy.api.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

	/*
		헤더 파싱 String 및 토큰 Header 정의
	 */
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";

	@Autowired
	private TokenProvider tokenProvider;

	@Autowired
	private UserDetailService userDetailService;

	/*
		토큰 인증 필터 메서드
		requst header에서 토큰을 받아와 인증을 수행
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		try {
			//jwt 토큰 받아오기
			String jwt = getJwtFromRequest(request);

			//토큰 정보가 있으면서, validate 되지 않았을 경우
			if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
				//회원의 OAUTH 인증 ID를 받아옴
				String authId = tokenProvider.getOAuthIdFromToken(jwt);

				//UserDetails 정보를 OAUTH ID로 받기
				UserDetails userDetails = userDetailService.loadUserById(authId);
				//UserDetails로 athentication 설정
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
				//authentication에 request 정보 담기
				authentication
					.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				//securitycontext 에 인증 정보 담기
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
//			response.addHeader("Accept-Patch", "*");
//			response.addHeader("Access-Control-Allow-Headers", "Content-Type");
//			response.addHeader("Access-Control-Allow-Origin", "*");
//			response.addHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,OPTIONS,PATCH");
//			System.out.println("set Headers");
		} catch (Exception ex) {
			log.error("Could not set user authentication in security context", ex);
			System.out.println("thrown error");
		}

		filterChain.doFilter(request, response);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(HEADER_STRING);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
}

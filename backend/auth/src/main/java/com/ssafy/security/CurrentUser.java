package com.ssafy.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//현재 인증된 principal 참조
@AuthenticationPrincipal
public @interface CurrentUser {

}
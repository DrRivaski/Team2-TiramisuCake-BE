package com.softeer.backend.global.annotation.argumentresolver;

import com.softeer.backend.global.annotation.AuthInfo;
import com.softeer.backend.global.common.entity.JwtClaimsDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthInfoArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(AuthInfo.class) !=null
                && parameter.getParameterType().equals(Integer.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory)  {
        HttpServletRequest req = (HttpServletRequest) webRequest.getNativeRequest();
        JwtClaimsDto jwtClaimsDto = (JwtClaimsDto) req.getAttribute("jwtClaims");
        return jwtClaimsDto.getId();
    }
}
package com.hij.book.springboot.config.auth;

import com.hij.book.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter){//
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
        return isLoginUserAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user");
    }
    //resolveArgument
    //파라미터에 전달할 객체를 생성한다.
    //여기서는 세션에서 객체를 가져온다.
}

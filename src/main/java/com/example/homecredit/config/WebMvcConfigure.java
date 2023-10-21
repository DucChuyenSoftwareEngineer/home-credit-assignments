package com.example.homecredit.config;

import com.example.homecredit.advice.HomeCreditHandlerInterceptor;
import com.example.homecredit.common.HomeCreditConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfigure implements WebMvcConfigurer {

    private final HomeCreditHandlerInterceptor homeCreditHandlerInterceptor;

    private final String activeProfile;

    public WebMvcConfigure(HomeCreditHandlerInterceptor homeCreditHandlerInterceptor,@Value("${spring.profiles.active}") String activeProfile) {
        this.homeCreditHandlerInterceptor = homeCreditHandlerInterceptor;
        this.activeProfile = activeProfile;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (HomeCreditConstant.PROFILE_PRO.equals(activeProfile)){
            registry.addInterceptor(homeCreditHandlerInterceptor);
        }
    }
}

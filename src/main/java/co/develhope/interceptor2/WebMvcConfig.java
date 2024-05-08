package co.develhope.interceptor2;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

public class WebMvcConfig implements WebMvcConfigurer{

    private final MonthInterceptor monthInterceptor;

    public WebMvcConfig(MonthInterceptor monthInterceptor) {
        this.monthInterceptor = monthInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(monthInterceptor).addPathPatterns("/months");
    }

}

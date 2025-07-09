package kr.co.wikibook.gallery.common;

import kr.co.wikibook.gallery.account.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration //빈등록
public class WebMvcConfiguration implements WebMvcConfigurer {
    private final String uploadPath;
    //생성자생성
    public WebMvcConfiguration(@Value("${constants.file.directory}") String uploadPath) {
        this.uploadPath = uploadPath;
        log.info("uploadPath:{}", uploadPath);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
//        pic으로 시작되는 요청이 들어다면
//        classpath:와 연결  >  file:  외부경로와 연동 바꿈 (file :  <이런식의 띄어쓰면안됨 붙여써야함!)
        registry.addResourceHandler("/pic/**")
                .addResourceLocations("file:"+  uploadPath);
    }






    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");
    }
}

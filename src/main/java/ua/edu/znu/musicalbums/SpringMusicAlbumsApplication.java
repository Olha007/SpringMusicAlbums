package ua.edu.znu.musicalbums;

import jakarta.servlet.DispatcherType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import ua.edu.znu.musicalbums.controller.filter.AuthFilter;

@Slf4j
@SpringBootApplication
public class SpringMusicAlbumsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringMusicAlbumsApplication.class, args);
    }
    /**
     * We register the AuthFilter as a Spring bean.
     *
     * @return AuthFilter registration bean
     */
    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        FilterRegistrationBean<AuthFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new AuthFilter());
        registration.addUrlPatterns("/home", "/logout", "/albumassign",
                "/albums", "/albumEditForm", "/albumEdit", "/albumRemove", "/albumAddForm", "/albumAdd",
                "/genres", "/genreEditForm", "/genreEdit", "/genreRemove", "/genreAddForm", "/genreAdd",
                "/artists", "/artistEditForm", "/artistEdit", "/artistRemove", "/artistAddForm", "/artistAdd",
                "/groups", "/groupEditForm", "/groupEdit", "/groupRemove", "/groupAddForm", "/groupAdd",
                "/songs", "/songEditForm", "/songEdit", "/songRemove", "/songAddForm", "/songAdd");
        registration.setDispatcherTypes(DispatcherType.FORWARD, DispatcherType.REQUEST);
        log.info("AuthFilter registered");
        return registration;
    }
}

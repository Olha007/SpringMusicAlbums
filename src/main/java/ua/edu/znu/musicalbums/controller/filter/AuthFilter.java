package ua.edu.znu.musicalbums.controller.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import ua.edu.znu.musicalbums.model.User;

import java.io.IOException;
@Slf4j
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession(false);
        if (session == null) {
            log.info("AuthFilter: session is null");
            resp.sendRedirect("/login");
        } else {
            String sessionID = session.getId();
            System.out.println("AuthFilter: sessionID = " + sessionID);
            User user = (User) session.getAttribute("user");
            if (user == null) {
                session.invalidate();
                log.info("AuthFilter: user is null");
                resp.sendRedirect("/login");
            } else {
                log.info("AuthFilter: user is {}", user.getUsername());
                chain.doFilter(req, resp);
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}


package dev.rinesarusinovci.online_quizzes.security;


import dev.rinesarusinovci.online_quizzes.dto.UserDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Configuration
public class SimpleSessionCookieFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //skip the filter for static resources
        if (request.getRequestURI().startsWith("/assets")) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            if (request.getRequestURI().startsWith("/login") || request.getRequestURI().startsWith("/register")) {
                filterChain.doFilter(request, response);
                return;
            }
            response.sendRedirect("/login?returnUrl=" + request.getRequestURI());
            return;
        }

        if (request.getRequestURI().startsWith("/login") || request.getRequestURI().startsWith("/register")) {
            response.sendRedirect("/");
            return;
        }

        UserDto userDto = (UserDto)session.getAttribute("user");

//        // validimi i path-it sipas ROLE
//        if(userDto.getRole().equals("ADMIN")) {
//            AdminFilter.doFilter(request, response, filterChain);
//            return;
//        } else if(userDto.getRole().equals("CUSTOMER")) {
//            UserFilter.doFilter(request, response, filterChain);
//            return;
//        }

        filterChain.doFilter(request, response);
    }
}

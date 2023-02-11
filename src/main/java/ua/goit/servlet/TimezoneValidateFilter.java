package ua.goit.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.TimeZone;

@WebFilter("/time")
public class TimezoneValidateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String timezone = servletRequest.getParameter("timezone");
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (timezone == null || timezone.isEmpty() || TimeZone.getTimeZone(timezone) == null) {
            response.setStatus(400);
            response.getWriter().write("Invalid timezone");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
package by.epam.javatraining.restaurant.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class AdministratorFilter implements Filter {
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}

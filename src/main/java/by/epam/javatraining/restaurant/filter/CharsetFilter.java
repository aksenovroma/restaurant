package by.epam.javatraining.restaurant.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {
    private static final Logger LOGGER = Logger.getRootLogger();
    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");
        if (encoding == null) encoding = "UTF-8";
        LOGGER.trace("CharsetFilter init");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        next.doFilter(request, response);
    }

    public void destroy() {
        LOGGER.trace("CharsetFilter destroy");
    }
}

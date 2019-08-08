package by.epam.javatraining.restaurant.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class CharsetFilter implements Filter {
    private static final String CONTENT_TYPE = "text/html; charset=";
    private static final Logger LOGGER = Logger.getRootLogger();
    private String encoding;

    public void init(FilterConfig config) {
        encoding = config.getInitParameter(getConst(PAR_REQUEST_ENCODING));
        if (encoding == null) {
            encoding = getConst(FILTER_ENCODING);
        }
        LOGGER.trace(getConst(LOG_CHARSET_FILTER_INIT));
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }

        response.setContentType(CONTENT_TYPE + getConst(FILTER_ENCODING));
        response.setCharacterEncoding(getConst(FILTER_ENCODING));

        next.doFilter(request, response);
    }

    public void destroy() {
        LOGGER.trace(getConst(LOG_CHARSET_FILTER_DESTROY));
    }
}

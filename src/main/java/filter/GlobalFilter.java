package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GlobalFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;

        if (!request.getRequestURI().contains(".jsp")){
            filterChain.doFilter(servletRequest,servletResponse);
        }

        if (request.getSession().getAttribute("existUser") == null && !request.getRequestURI().contains("login.jsp")){
            HttpServletResponse response = (HttpServletResponse)servletResponse;
            response.sendRedirect("/login.jsp");
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}

package work.douzi.report;

import com.auth0.jwt.interfaces.Claim;
import org.springframework.stereotype.Component;
import work.douzi.report.util.JwtUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author Ryan
 * @date 2021/6/1
 */
@Component
@WebFilter(filterName = "JwtFilter", urlPatterns = "/*")
public class JwtFilter implements Filter {
    private final String[] publicUrl = {"/user/login"};
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        String url = request.getRequestURI();
        if(isAllow(url)){
            chain.doFilter(request,response);
            return;
        }
        response.setCharacterEncoding("UTF-8");
        //获取 header里的token
        final String token = request.getHeader("authorization");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request, response);
        } else {
            if (token == null) {
                response.getWriter().write("没有token！");
                return;
            }
            Map<String, Claim> userData = JwtUtil.verifyToken(token);
            if (userData == null) {
                response.getWriter().write("token不合法！");
                return;
            }
            Integer user_id = userData.get("user_id").asInt();
            String user_name = userData.get("user_name").asString();
            request.setAttribute("user_id", user_id);
            request.setAttribute("user_name", user_name);
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isAllow(String url) {
        for (String s : publicUrl) {
            if (url.indexOf(s) >= 0) {
                return true;
            }
        }
        return false;
    }
}

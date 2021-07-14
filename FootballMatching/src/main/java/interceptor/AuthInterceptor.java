package interceptor;

import annotation.BusinessUserAuth;
import annotation.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) return true;

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        UserAuth auth = handlerMethod.getMethod().getDeclaredAnnotation(UserAuth.class); // 메소드의 어노테이션
        BusinessUserAuth bAuth = handlerMethod.getMethod().getDeclaredAnnotation(BusinessUserAuth.class);

        if (auth == null || bAuth == null) { // auth annotation 이 없다면
            return true;
        } else { // auth annotation 이 있다면
            if ( jwtUtil.isValid(request.getHeader("Authorization"))){
                System.out.println("여기요 토큰 검사중 interceptior");
                return true;
            }
            else{
                throw new Exception("non valid");
            }
        }
    }
}

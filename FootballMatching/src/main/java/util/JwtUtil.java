package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${json.web.token.secret.key}")
    String secret;

    public String token_issued(String return_id){
        Map<String, Object> headers = new HashMap<String, Object>(); // header
        headers.put("typ", "JWT");
        headers.put("alg","HS256");
        Map<String, Object> payloads = new HashMap<String, Object>(); //payload
        payloads.put("id", return_id );
        Calendar calendar = Calendar.getInstance(); // singleton object java calendar
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, 24); // access token expire 24h later
        Date exp = calendar.getTime();
        return Jwts.builder().setHeader(headers).setClaims(payloads).setExpiration(exp).signWith(SignatureAlgorithm.HS256, secret.getBytes()).compact();
    }

    public String get_id(String token){
        token = token.substring(7);
        Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        String id = String.valueOf(claims.get("id", String.class));
        return id;
    }

    public boolean isValid(String token) throws Exception{
        if ( token == null) {
            throw new Exception("null임"); // 여러분들만의 Exception 객체를 만들어 계층구조를 만들어보는 것도 좋은 경험일 것 같습니다.
        }
        else if ( !token.startsWith("Bearer ") ){
            throw new Exception("Bearer 로 시작안함");
        }
        token = token.substring(7); // "Bearer " 제거
        try {
            Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e1){
            //TODO jsonwebtoken 라이브러리의 Exception 계층에 대해서 파악해봅시다.
            throw new Exception("만료됨");
        }
        catch(Throwable e2){
            //TODO jsonwebtoken 라이브러리의 Exception 계층에 대해서 파악해봅시다.
            throw new Exception("잘못됨");
        }
        return true;
    }

}

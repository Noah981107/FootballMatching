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
        System.out.println("herer");
        Map<String, Object> headers = new HashMap<String, Object>(); // header
        headers.put("typ", "JWT");
        headers.put("alg","HS256");
        System.out.println(headers);
        Map<String, Object> payloads = new HashMap<String, Object>(); //payload
        payloads.put("id", return_id);
        System.out.println(payloads);
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
            throw new Exception("null임");
        }
        else if ( !token.startsWith("Bearer ") ){
            throw new Exception("Bearer 로 시작안함");
        }
        token = token.substring(7); // "Bearer " 제거
        try {
            Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e1){
            throw new Exception("만료됨");
        }
        catch(Throwable e2){
            throw new Exception("잘못됨");
        }
        return true;
    }

}

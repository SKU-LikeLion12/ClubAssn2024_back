package likelion12.puzzle.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtUtility {

    @Value("${jwt.key}")
    private String secret = "askqwhrkjweagfjasdfasfdahsjkfhqlwkjfhbasdjkfhlqwkjefhbadskjfbalsdhfvbasdfasdfasdfasdfasdqmjhfvgjasd";

    private static final long expirationTime = 1000 * 60 * 60; // 1시간

    // JWT 생성
    public String generateToken(String memberId) {
        return "Bearer " + Jwts.builder()
                .setSubject(memberId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    // JWT 유효성 검사
    public Claims validateToken(String token) {
        try {
            // 토큰 파싱 및 클레임 반환
            return Jwts.parser()
                    .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody(); // 유효한 경우, 클레임 반환
        } catch (Exception ex) {
            throw ex;
        }
    }

    // 토큰으로 학번 추출
    public String getStudentId(String token){
        Claims claims = validateToken(token);
        return claims.getSubject();
    }

    //

    // 토큰 추출
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);        // "Bearer " 문자 이후의 토큰 부분을 반환
        }
        return null;
    }

    public static void main(String[] args) {
        JwtUtility jwtUtility = new JwtUtility();
        String token = jwtUtility.generateToken("00000000");
        System.out.println("token = " + token);
        System.out.println("getStudentId(token) = " + jwtUtility.getStudentId(token.substring(7)));
        System.out.println("asdf = " + jwtUtility.validateToken(token.substring(7)));
    }
}
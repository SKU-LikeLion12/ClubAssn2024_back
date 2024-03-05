package likelion12.puzzle.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtUtility {

    @Value("${jwt.key}")
    private String secret;

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
        System.out.println("token = " + token);
        if(token==null || !token.startsWith("Bearer ")) throw new SignatureException("");
        token = token.substring(7);
        try {
            // 토큰 파싱 및 클레임 반환
            Claims claims = Jwts.parser()
                    .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody();

            return claims; // 유효한 경우, 클레임 반환
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void main(String[] args) {
        JwtUtility jwtUtility = new JwtUtility();
        String token = jwtUtility.generateToken("test");
        System.out.println("token = " + token);
        System.out.println("jwtUtility.validateToken(token) = " + jwtUtility.validateToken(token));
    }
}
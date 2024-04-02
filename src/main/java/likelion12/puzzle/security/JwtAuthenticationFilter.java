package likelion12.puzzle.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtility jwtUtility;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = jwtUtility.resolveToken(request);

        if (token != null) {
            try {
                Claims claims = jwtUtility.validateToken(token);

                if (claims != null) {
                    // 유효한지 검사부터
                    String username = claims.getSubject();

                    // 권한 부여
                    List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));

                    // 인증 객체 생성(매번 생성하는거 맞음)
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            username, null, authorities);

                    // SecurityContext 에 인증 객체 저장
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                System.out.println("e = " + e);
                SecurityContextHolder.clearContext(); // 인증 실패 시, 컨텍스트 클리어
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return; // 필터 체인을 계속 진행하지 않고 종료
            }
        }

        filterChain.doFilter(request, response); // 다음 필터로 요청과 응답을 전달
    }
}

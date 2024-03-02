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
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                Claims claims = jwtUtility.validateToken(token);
                if (claims != null) {
                    String username = claims.getSubject(); // 혹은 사용자를 식별할 수 있는 다른 클레임
                    // JWT 클레임에서 권한 정보를 추출하는 로직 (예시)
                    // 실제로는 클레임에 맞게 권한을 설정해야 할 수 있습니다.
                    List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));

                    // 인증 객체 생성
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            username, null, authorities);

                    // SecurityContext에 인증 객체 저장
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                // 인증 실패 시 필요한 예외 처리 로직
                // 예를 들어, SecurityContext를 클리어 하거나, 적절한 HTTP 응답을 설정
                System.out.println("e = " + e);
                SecurityContextHolder.clearContext(); // 인증 실패 시, 컨텍스트 클리어
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return; // 필터 체인을 계속 진행하지 않고 종료
            }
        }

        filterChain.doFilter(request, response); // 다음 필터로 요청과 응답을 전달
    }

}

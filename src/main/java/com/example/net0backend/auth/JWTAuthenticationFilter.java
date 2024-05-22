package com.example.net0backend.auth;

import com.example.net0backend.entity.Users;
import com.example.net0backend.repository.UsersRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTProvider jwtProvider;
    private final UsersRepository usersRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        try {
            String userEmail = jwtProvider.validateTokenReturnEmail(authorizationHeader);
            Users user = usersRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(() -> user.getUserType().toString());

            // 필터에서 컨트롤러로 보낼때 담는 바구니
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

            //바구니 안에 담을 토큰 생성
            AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, authorities);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            securityContext.setAuthentication(authenticationToken);
            SecurityContextHolder.setContext(securityContext);

        } catch (Exception e) {
            // 쿠키 지우는 로직
            log.error("로그인 오류: {}", e.getMessage());
        } finally {
            filterChain.doFilter(request, response);
        }
    }
}

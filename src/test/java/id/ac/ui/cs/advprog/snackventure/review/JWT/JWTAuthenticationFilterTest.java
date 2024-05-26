package id.ac.ui.cs.advprog.snackventure.review.JWT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class JWTAuthenticationFilterTest {

    @Mock
    private JWTService jwtService;

    @InjectMocks
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoFilterInternalValidToken() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn("Bearer validToken");
        when(jwtService.validateToken("validToken")).thenReturn(true);
        when(jwtService.getUsernameFromJWT("validToken")).thenReturn("username");
        when(jwtService.getRolesFromJWT("validToken")).thenReturn(List.of("ROLE_USER"));

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        assertEquals("username", authenticationToken.getPrincipal());
        assertEquals(1, authenticationToken.getAuthorities().size());
        assertEquals(new SimpleGrantedAuthority("ROLE_USER"), authenticationToken.getAuthorities().iterator().next());

        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testDoFilterInternalInvalidToken() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn("Bearer invalidToken");
        when(jwtService.validateToken("invalidToken")).thenReturn(false);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        assertEquals(null, SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testDoFilterInternalNoToken() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn(null);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        assertEquals(null, SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testDoFilterInternalMalformedToken() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn("Bearer ");
        
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        
        assertEquals(null, SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testDoFilterInternalWithNonBearerToken() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn("NonBearer token");

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }
}

package id.ac.ui.cs.advprog.snackventure.review.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.Key;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JWTServiceTest {

    @InjectMocks
    private JWTService jwtService;

    @Value("${jwt.secret}")
    private String JWT_SECRET = "mysecretkeymysecretkeymysecretkeymysecretkey";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(jwtService, "JWT_SECRET", JWT_SECRET);
    }

    @Test
    public void testGetUsernameFromJWT() {
        String token = generateToken("username", List.of("ROLE_USER"));
        String username = jwtService.getUsernameFromJWT(token);
        assertEquals("username", username);
    }

    @Test
    public void testGetRolesFromJWT() {
        String token = generateToken("username", List.of("ROLE_USER"));
        List<String> roles = jwtService.getRolesFromJWT(token);
        assertEquals(List.of("ROLE_USER"), roles);
    }

    @Test
    public void testValidateToken() {
        String token = generateToken("username", List.of("ROLE_USER"));
        assertTrue(jwtService.validateToken(token));
    }

    @Test
    public void testValidateTokenWithInvalidToken() {
        String token = "invalid.token.here";
        assertThrows(AuthenticationCredentialsNotFoundException.class, () -> jwtService.validateToken(token));
    }

    private String generateToken(String username, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);

        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET));
        return Jwts.builder()
                .setClaims(claims)
                .signWith(key)
                .compact();
    }
}

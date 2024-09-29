package com.sistemablogspring.sistema_blog_springboot_api_rest.Configuracion.Auth;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.HashMap;

@Service
public class JwtService {
    
    private static final String SECRET_KEY = "5864asfasfafsafsjfkaflwae8468afseiuajfealfoiej9662";

    public String getToken(UserDetails userdetails){
        return getToken(new HashMap<>(), userdetails);
    }

    public String getToken(Map<String,Object> extraClaims, UserDetails userDetails){
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername()).
        setIssuedAt( new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+ 1000*60*60*24))
        .signWith(getKey(),SignatureAlgorithm.HS256).compact();
    }

    private SecretKey getKey() {
       byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
       return Keys.hmacShaKeyFor(keyBytes);
    }
    public String getUsernameFromToken(String token){
        return getClaim(token,Claims::getSubject);
    }
    
    private Date getExpiration(String token){
        return getClaim(token, Claims::getExpiration);
    }
    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }
    public Boolean isTokenValid(String token, UserDetails userDetails){
        String username= getUsernameFromToken(token);
        return( username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    private Claims getAllClaims(String token){
        return Jwts.parser().verifyWith(getKey()).build().parseClaimsJws(token).getBody();

    }
    public <T> T  getClaim(String  token,Function<Claims,T> claimsResolver){
        final Claims claims= getAllClaims(token);
        return claimsResolver.apply(claims);
    }
}

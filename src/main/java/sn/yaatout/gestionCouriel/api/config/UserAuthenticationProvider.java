package sn.yaatout.gestionCouriel.api.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import sn.yaatout.gestionCouriel.api.services.IUserService;
import sn.yaatout.gestionJournaux.model.Role;
import sn.yaatout.gestionJournaux.model.User;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserAuthenticationProvider {
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;
    private final IUserService userService;
    @PostConstruct
    protected void init() {
        // this is to avoid having the raw secret key available in the JVM
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String login) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(login)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);
    }

//    public String createToken(String email) {
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + 3600000); // 1 hour
//
//        Algorithm algorithm = Algorithm.HMAC256(secretKey);
//
//        // Obtenez les rôles de l'utilisateur à partir de l'objet user
//        User user = userService.getUserByEmail(email);
//        List<String> roles = user.getRoles().stream()
//                //.map(role -> role.getNom()) // Supposons que le nom du rôle est stocké dans l'attribut "nom"
//                .map(Role::getNom)
//                .collect(Collectors.toList());
//
//        return JWT.create()
//                .withSubject(email)
//                .withIssuedAt(now)
//                .withExpiresAt(validity)
//                .withClaim("userId", user.getId()) // Ajoutez l'id de l'utilisateur dans la charge utile
//                .withClaim("roles", roles) // Ajoutez les rôles dans la charge utile
//                .sign(algorithm);
//    }
//    public Authentication validateToken(String token) {
//        Algorithm algorithm = Algorithm.HMAC256(secretKey);
//
//        JWTVerifier verifier = JWT.require(algorithm)
//                .build();
//
//        DecodedJWT decoded = verifier.verify(token);
//
//        // Obtenez les rôles depuis la charge utile du token
//        List<String> roles = decoded.getClaim("roles").asList(String.class);
//        int userId = decoded.getClaim("userId").asInt();
//        // Utilisez les rôles pour construire l'objet Authentication
//        User user = userService.getUserByEmail(decoded.getSubject());
//
//        return new UsernamePasswordAuthenticationToken(user, null, roles.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList()));
//    }


    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        User user = userService.getUserByEmail(decoded.getSubject());

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

}

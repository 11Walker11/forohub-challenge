package com.forohub.forohub_challenge.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.forohub.forohub_challenge.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

//@Service
//public class TokenService {
//
//    @Value("${api.security.secret}")
//    private String secret;
//
//    public String generarToken(Usuario usuario) {
//        try {
//            var algoritmo = Algorithm.HMAC256(secret);
//            return JWT.create()
//                    .withIssuer("voll_med")
//                    .withSubject(usuario.getLogin())
//                    .withExpiresAt(fechaExpiracion())
//                    .sign(algoritmo);
//        } catch (JWTCreationException exception){
//            throw new RuntimeException("error al generar token jwt", exception);
//        }
//    }
//
//    public String getSubject(String tokenJWT) {
//        try {
//            var algoritmo = Algorithm.HMAC256(secret);
//            return JWT.require(algoritmo)
//                    .withIssuer("voll_med")
//                    .build()
//                    .verify(tokenJWT)
//                    .getSubject();
//        } catch (JWTVerificationException exception) {
//            throw new RuntimeException("Token JWT inválido o expirado!");
//        }
//    }
//
//    private Instant fechaExpiracion() {
//        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
//    }
//
//}

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("voll med")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al crear el token", exception);
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("El token no puede ser nulo");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // validando firma
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("voll med")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String subject = decodedJWT.getSubject();
            if (subject == null) {
                throw new RuntimeException("Subject no encontrado en el token");
            }
            return subject;
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token inválido", exception);
        }
    }

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}


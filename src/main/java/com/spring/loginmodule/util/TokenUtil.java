package com.spring.loginmodule.util;

import com.spring.loginmodule.dto.jwt.CreateTokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;

//import javax.transaction.Transactional; // javax -> jakarta
import jakarta.transaction.Transactional;

//import javax.annotation.PostConstruct;
import jakarta.annotation.PostConstruct;

//import javax.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Slf4j
@Service
@Transactional
public class TokenUtil {

    private final Base64.Decoder decoder = Base64.getUrlDecoder();

    @Value("${spring.jwt.secretKey}")
    private String SECRET;

    //30분
    private final int ACCESS_TOKEN_EXPIRE_SECONDS = 60 * 30;
    //7일
    private final int REFRESH_TOKEN_EXPIRE_DAYS = 7;
    private final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS256;
    private SecretKeySpec SECRET_KEY;

    @PostConstruct
    public void init() {
        this.SECRET_KEY = new SecretKeySpec(SECRET.getBytes(), ALGORITHM.getJcaName());
    }

    private JwtBuilder createToken(CreateTokenDto dto) {

        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setIssuer("SpringSecurityJwt")
                .claim("id", dto.getUserId())
                .claim("email", dto.getEmail())
                .claim("role", dto.getRole())
                .signWith(ALGORITHM, SECRET_KEY);
    }

    public String createAccessToken(CreateTokenDto dto) {

        Date now = new Date();

        JwtBuilder jwtBuilder = createToken(dto);

        return jwtBuilder
                .setExpiration(new Date(
                        now.getTime() + Duration.ofMinutes(ACCESS_TOKEN_EXPIRE_SECONDS).toSeconds()
                ))
                .compact();
    }

    public String getEmail(String token) throws ParseException {

        Map<String, Object> payload = parse(token);

        return (String) payload.get("email");
    }

    /**
     * JWT String 을 Map 으로 파싱해주는 함수.
     * @param token JWT String
     * @return Map<String, Object> key, value 형태의 Map 데이터
     * @throws ParseException 파싱 실패 예외처리
     */
    private Map<String, Object> parse(String token) throws ParseException {

        String[] chunks = token.split("\\.");
        String jwtBodyString = new String(decoder.decode(chunks[1]));

        JSONParser parser = new JSONParser(jwtBodyString);

        return parser.parseObject();
    }

    // Request의 Header에서 token 값을 가져옵니다.
    // "Authorization" : <Bearer> "TOKEN"
    public String getToken(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");
        return authorization.split(" ")[1];
//        try {
//
//            return authorization.split(" ")[1];
//
//        } catch (BaseException e) {
//
//            throw new BadRequestToken();
//
//        }
    }
    public void validateToken(String token) {

        checkTokenSignature(token);

        if (IsExpired(token)) {
//            throw new UnAuthorizedToken();
            throw new RuntimeException();
        }
    }

    private boolean IsExpired(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        Date expiration = claims.getExpiration();
        Date current = new Date();

        if (expiration.before(current)) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }


    private boolean checkTokenSignature(String token) {

        String[] chunks = token.split("\\.");
        String tokenWithoutSignature = chunks[0] + "." + chunks[1];
        String signature = chunks[2];

        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(ALGORITHM, SECRET_KEY);

        if (!validator.isValid(tokenWithoutSignature, signature)) {
//            throw new BadRequestToken();
            throw new RuntimeException();
        }
        return true;
    }
}

package iss.se.team4.secondhand.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtUtils {

    private String secret;
    private long expire;

    /**
     * 生成jwt token
     */
    public String generateToken(Integer userId, String role) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + expire);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 设置头部信息
            HashMap<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim("userId", userId)
                    .withClaim("role", role)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer getUserIdByToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                    .build(); // 创建验证器

            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getClaim("userId").asInt();
        }catch (Exception e){
            log.debug("validate is token error ", e);
            return null;
        }
    }

    public String getRoleByToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                    .build(); // 创建验证器

            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getClaim("role").asString();
        }catch (Exception e){
            log.debug("validate is token error ", e);
            return null;
        }
    }

    /**
     * token是否过期
     * @return  true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

}

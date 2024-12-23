package NetworkEngineeringProject.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // 使用普通字符串生成 SecretKey
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(
            "zkkkkkkkkkkkkkkkzkkkkkkkkkkkkkkk".getBytes(StandardCharsets.UTF_8)
    );

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return
     */
    public static String generateJwt(Map<String, Object> claims) {
        String compactJwt = Jwts.builder()
                .setClaims(claims) // 设置自定义 claims
                .setIssuedAt(new Date()) // 设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 设置过期时间
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256) // 使用私钥签名
                .compact(); // 生成 JWT
        return compactJwt;
    }

    /**
     * 解析JWT令牌
     * @param compactJwt JWT令牌字符串
     * @return Claims 对象
     */
    public static Claims parseJWT(String compactJwt) {
        Claims claims = Jwts.parser()
                .verifyWith(SECRET_KEY) // 使用相同的密钥验证签名
                .build()
                .parseSignedClaims(compactJwt) // 解析签名 JWT
                .getPayload(); // 获取解密后的 Claims
        return claims;
    }
}
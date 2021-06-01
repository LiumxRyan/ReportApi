package work.douzi.report.util;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ryan
 * @date 2021/6/1
 */
public class JwtUtil {

    private static final String SECRET = "my_secret";

    private static final Long EXPIRATION = 1800L;

    public static String createToken(JSONObject jsonObject) {
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)
                .withClaim("user_id", jsonObject.getIntValue("user_id"))
                .withClaim("user_name", jsonObject.getString("user_name"))
                .withExpiresAt(expireDate)
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            return null;
        }
        return jwt.getClaims();
    }

}


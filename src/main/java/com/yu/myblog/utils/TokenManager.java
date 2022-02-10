package com.yu.myblog.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.log4j.Log4j2;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.utils
 * @ClassName: TokenManager
 * @Author: 钟洪强
 * @Description: token认证工具类
 * @Date: 2022/1/25 14:54
 * @Version: 1.0
 */
@Log4j2
public class TokenManager {
    //私钥
    private static final String SECRET = "Axmk89Li3Aji9M";
    //过期时间10分钟
    private static final int expiresTime = 1000 * 60 * 5;

    public static String createToken(String userId){
        //获取加上过期时间后的时间
        Date nowDate = new Date();
        System.out.println(nowDate);
        Date expiresDate = new Date(System.currentTimeMillis()+expiresTime);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create().withHeader(map)	//请求头
                .withClaim("iss", "Service")	//签发方
                .withClaim("aud", "Client")		//接收方
                .withClaim("userId", userId) //存储信息，用户ID
                .withIssuedAt(nowDate)		//当前时间
                .withExpiresAt(expiresDate)		//过期时间
                .sign(Algorithm.HMAC256(SECRET));		//私钥
        return token;
    }

    public static String  userId(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT verify = verifier.verify(token);
        return verify.getClaim("userId").asString();
    }

    public static boolean verifyToken(String token){
        try{
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            verifier.verify(token);
            //DecodedJWT verify = verifier.verify(token);
            //return verify.getClaims();	//能返回数据集合(用于在jwt中存储一些数据)的，但是由于这一版本只需要核验token是否合法，所以只需要返回true和false；
            return true;
        }catch(Exception e){
            //log.error("认证失败=>{}",e.getMessage());
            log.error("token过期");
            return false;
        }
    }
    public static boolean verifyToken1(String token){
        try{
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            verifier.verify(token);
            //DecodedJWT verify = verifier.verify(token);
            //return verify.getClaims();	//能返回数据集合(用于在jwt中存储一些数据)的，但是由于这一版本只需要核验token是否合法，所以只需要返回true和false；
            return true;
        }catch(Exception e){
            //log.error("认证失败=>{}",e.getMessage());
            log.error("token过期");
            return false;
        }
    }
}
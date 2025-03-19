package org.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    /*
     * 生成JWT代码实现
     * */
    @Test
    public void testGenJwt() {
        Map<String, Object> claim = new HashMap<>();
        claim.put("id",10);
        claim.put("username","itheima");
        String jwt= Jwts.builder().signWith(SignatureAlgorithm.HS256, "aXRjYXNO")// 设置加密算法和密钥
                .addClaims(claim)// 添加自定义的声明(自定义信息等等)
                .setExpiration(new Date(System.currentTimeMillis() +43200000L))//设置过期时间为12小时
                .compact();// 生成JWT
        System.out.println(jwt);
    }

    /*
    * 解析JWT
    * */
    @Test
    public void testParseJwt(){
        String s = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MTAsInVzZXJuYW1lIjoiaXRoZWltYSIsImV4cCI6MTc0MTMxNjc1N30.uzvnbWUSlQ5LLjJemIj6uXHzJrq8ET94v5RxdPX-v2I";
        Claims claims = Jwts.parser().
                setSigningKey("aXRjYXNO")//指定密钥
                .parseClaimsJws(s)//解析令牌
                .getBody();//获取自定义声明信息
        System.out.println(claims);
    }

}

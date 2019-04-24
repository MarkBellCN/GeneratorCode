package com.ytkj.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author: MarkBell
 * @Description:
 * @Date 2018/4/25
 */
@Slf4j
public class UserUtils {
    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_IP = "IP";
    static final String CLAIM_KEY_SHOWNAME = "showName";
    static final String CLAIM_KEY_USERID = "userId";
    private static String secret;
    private static String tokenHeader;
    static {
        try {
            Resource resource = new ClassPathResource("/application.properties");
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            secret = properties.getProperty("jwt.secret");
            tokenHeader = properties.getProperty("jwt.header");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized  static HttpServletRequest getHttpRequest(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }
    public synchronized static String getToken(){
        HttpServletRequest request = getHttpRequest();
        String token = request.getHeader(tokenHeader);
        String url = request.getServletPath();
        log.info(url+"获取到的token:"+token);
        return token;
    }
    public synchronized  static  String getUserId(){
        String token = getToken();
        Claims claims = getClaimsFromToken(token);
        return (String) claims.get(CLAIM_KEY_USERID);
    }

    private synchronized  static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
}

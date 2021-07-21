/**  
 * <p>Title: test.java</p>  
 * <p>Description: </p>    
 * @author dongbao  
 * @date 2018年8月14日
*/

/**  
 * <p>Title: test.java</p>  
 * <p>Description: </p>    
 * @author dongbao  
 * @date 2018年8月14日
*/
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Base64; 
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.util.HashMap;
import java.util.Map;

 
 
/**
 * Json web token 签发
 *
 */
public class JWT {
 
	// 密钥key
	private static final String SECRET_KEY = "dongbao";
	
	/**
	 * 构造密钥
	 * 
	 * @return
	 */
	private static SecretKey generalKey() {
		byte[] encodeKey = Base64.decode(SECRET_KEY);
		return new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
	}
	
	/**
	 * 签发JWT
	 * 
	 * @param jti JWT的唯一身份标识,主要用来作为一次性token(允许为空)
	 * @param sub JWT所面向的用户(允许为空)
	 * @param expiredTimeAt 过期时间(当前时间ms+要过期时间ms),单位ms(允许为空)
	 * @param claims 荷载信息
	 * @return
	 */
	public static String createJWT(String jti, String sub, long expiredTimeAt, Map<String, Object> claims) {
		// 获取密钥
		SecretKey secretKey = generalKey();
		// 构建JWT,并设置签发时间,签名算法
		JwtBuilder builder = Jwts.builder()
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, secretKey);
		// 校验jti
		if(null!=jti&&""!=jti) {
			builder.setId(jti);
		}
		// 校验sub
		if(null!=sub&&""!=sub) {
			builder.setSubject(sub);
		}
		// 过期时间
		if (expiredTimeAt > 0) {
			Date expDate = new Date(expiredTimeAt);
			builder.setExpiration(expDate);
		}
		// 校验
		if (claims != null) {
			// 保存相关信息
			for (Map.Entry<String, Object> en : claims.entrySet()) {
				builder.claim(en.getKey(), en.getValue());
			}
		}
		return builder.compact();
	}
	
	/**
	 * 
	 * 解析JWT字符串
	 * 
	 * @param jwt
	 * @return claims,包括公告声明,自定义声明
	 * @throws ExpiredJwtException,SignatureException,Exception token已过期,签名校验失败,其它错误
	 */
	public static Map<String, Object> parseJWT(String jwt) {
		SecretKey secretKey = generalKey();
		try {
			Map<String, Object> claims = Jwts.parser()
					.setSigningKey(secretKey)
					.parseClaimsJws(jwt)
					.getBody();
			return claims;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", 10000);
		//String jwt = createJWT("", "", System.currentTimeMillis() + 30*60*1000, map);
		String jwt = createJWT("abc", "", System.currentTimeMillis() + 30*60*1000, map);
		System.out.println(jwt);
		
		/**
		 * 之前parseJWT(jwt)返回的是Claims对象，
		 * Claims实现了Map接口，事实上就是对Map进行的封装，所以可以直接返回Map
		 */
		Map<String, Object> claims = parseJWT(jwt);
		System.out.println(claims.get("userId"));
		System.out.println(claims.get("iat"));
		System.out.println(claims.get("exp"));
	}
 
}



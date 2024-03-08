package logica;

import java.util.HashMap;
import java.util.Map;

public class TokenBlacklist {
	private static  TokenBlacklist instance;
	private static Map<String, Long> blacklistedTokens = new HashMap<>();

	public TokenBlacklist() {
		// TODO Auto-generated constructor stub
	}

	public static TokenBlacklist getInstance() {
		 if (instance == null) {
	            instance = new TokenBlacklist();
	        }
	        return instance;
	}

	public void blacklistToken(String token, Long expirationTime) {
		cleanExpiredTokens();
		blacklistedTokens.put(token, expirationTime);
	}

	public boolean isTokenBlacklisted(String token) {
		return blacklistedTokens.containsKey(token);
	}

	private void cleanExpiredTokens() {
		long currentTime = System.currentTimeMillis() / 1000;
		blacklistedTokens.entrySet().removeIf(entry -> entry.getValue() <= currentTime);
	}

}

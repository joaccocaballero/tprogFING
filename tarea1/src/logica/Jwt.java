package logica;

import java.security.Key;
import java.util.Date;
import servidor.types.DTEmpresa;
import servidor.types.DTPostulante;
import servidor.types.DTUsuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;



public class Jwt {
	private static final String secret_Key = "6a2b5c8e1f4a7d0987654321abcdef09";

	public Jwt() {
		// TODO Auto-generated constructor stub
	}
	
	public String generateJWT(String email, String tipo_usuario) {
		long expirationTimeMillis = System.currentTimeMillis() + 3600000; // Tiempo de expiración (1 hora)
		Key key = Keys.hmacShaKeyFor(secret_Key.getBytes());

		String jwt = Jwts.builder().setSubject(email).claim("email", email).claim("tipoUsuario", tipo_usuario)
				.setExpiration(new Date(expirationTimeMillis)).signWith(key, SignatureAlgorithm.HS256).compact();

		return jwt;
	}

	public boolean validarUsuario(String jwt){
	  boolean isValid = false;
      TokenBlacklist blacklist = TokenBlacklist.getInstance();
	  if (!blacklist.isTokenBlacklisted(jwt)) {
		    try{
				Key secretKey = Keys.hmacShaKeyFor(secret_Key.getBytes());
				Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt);
				Claims claims = claimsJws.getBody();
				String correo = (String) claims.get("email");
				Fabrica factory = Fabrica.getInstance();
				IControladorUsuario ICU = factory.getIControladorUsuario();
				if (ICU.usuarioExiste(correo)){
					isValid = true;
				}
			} catch (Exception e) {
				
			}
		}
		return isValid;
    }
	
	public String tipoUsuario(String jwt){
		  String out = "invalido";
	      TokenBlacklist blacklist = TokenBlacklist.getInstance();
		  if(jwt != null) {
			  if (!blacklist.isTokenBlacklisted(jwt)) {
				    try{
						Key secretKey = Keys.hmacShaKeyFor(secret_Key.getBytes());
						Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt);
						Claims claims = claimsJws.getBody();
						String correo = (String) claims.get("email");
						Fabrica factory = Fabrica.getInstance();
						IControladorUsuario ICU = factory.getIControladorUsuario();
						if (ICU.usuarioExiste(correo)) {
							DTUsuario usuario = ICU.consultarUsuarioPorCorreo(correo);
							if (usuario instanceof DTEmpresa) {
								out = "empresa";
							} else if (usuario instanceof DTPostulante) {
								out = "postulante";
							}
						} else {
							
						}
					} catch (Exception e) {
						
					}
				}
		  }
		  else {
			  out = "visitante";
		  }
		return out;
	    }
	
	public String obtenerCorreoPorJWT(String jwt){
		  String out = "invalido";
	      TokenBlacklist blacklist = TokenBlacklist.getInstance();
		  if (!blacklist.isTokenBlacklisted(jwt)) {
			    try{
					Key secretKey = Keys.hmacShaKeyFor(secret_Key.getBytes());
					Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt);
					Claims claims = claimsJws.getBody();
					String correo = (String) claims.get("email");
					Fabrica factory = Fabrica.getInstance();
					IControladorUsuario ICU = factory.getIControladorUsuario();
					if (ICU.usuarioExiste(correo)) {
						DTUsuario usuario = ICU.consultarUsuarioPorCorreo(correo);
						out = usuario.getCorreo();
					} else {
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return out;
	    }
	
	public DTUsuario obtenerDatosDeUsuarioJWT(String jwt) {
		DTUsuario user = null;
		TokenBlacklist blacklist = TokenBlacklist.getInstance();
		if (!blacklist.isTokenBlacklisted(jwt)) {
			try {
				Key secretKey = Keys.hmacShaKeyFor(secret_Key.getBytes());
				Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt);
				Claims claims = claimsJws.getBody();
				String correo = (String) claims.get("email");
				Fabrica factory = Fabrica.getInstance();
				IControladorUsuario iconuser = factory.getIControladorUsuario();
				if (iconuser.usuarioExiste(correo)) {
					 user = iconuser.consultarUsuarioPorCorreo(correo); 
				} 

			} catch (Exception e) {
				e.printStackTrace();
			}}
		return user;		
	}
	
	public void CerrarSesion(String jwt) {
		Key secretKey = Keys.hmacShaKeyFor(secret_Key.getBytes());
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt);

        Claims claims = claimsJws.getBody();
        Date expirationDate = claims.getExpiration();
        long expirationTimeMillis = expirationDate.getTime();
        TokenBlacklist blacklist = TokenBlacklist.getInstance();
        blacklist.blacklistToken(jwt, expirationTimeMillis);
	}
}

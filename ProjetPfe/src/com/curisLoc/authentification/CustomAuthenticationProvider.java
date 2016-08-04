package com.curisLoc.authentification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.curisLoc.model.User;
import com.curisLoc.service.UserService;



@SuppressWarnings("deprecation")
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UserService usuarioServico;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken) authentication;
		String loginFornecido = userToken.getName();
		String senhaFornecida = (String) userToken.getCredentials();

		verificarPreenchimentoLoginESenha(loginFornecido, senhaFornecida);
		User details = usuarioServico.obterUsuarioPeloLogin(loginFornecido);
		verificarLoginESenha(senhaFornecida, details);
		
		return new UsernamePasswordAuthenticationToken(details, senhaFornecida, details.getAuthorities());
	}

	private void verificarLoginESenha(String senhaFornecida, User details) {
		if (details == null) {
			throw new BadCredentialsException("Ligin et/ou password invalide");
		}

		
		/*if (!senhaFornecida.equals(details.getPassword())){
			
			throw new BadCredentialsException("Ligin et/ou password invalide");
		}*/
		if (!passwordEncoder.encodePassword(senhaFornecida,null).equals(details.getPassword())) {
			throw new BadCredentialsException("Ligin et/ou password invalide");
		}
	}
	
	private void verificarPreenchimentoLoginESenha(String loginFornecido, String senhaFornecida) {
		if (loginFornecido == null || loginFornecido.trim().equals("")
				|| senhaFornecida == null
				|| senhaFornecida.trim().equals(""))
			throw new BadCredentialsException(
					"Login obligatoire");
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}

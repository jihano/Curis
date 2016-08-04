package com.curisLoc.service;

import com.curisLoc.model.User;

public interface UserService {
	public User obterUsuarioPeloLogin(String login);
	public User obterUsuarioMedLogin(String login);
}

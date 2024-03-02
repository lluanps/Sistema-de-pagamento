package com.luan.pix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.luan.pix.domain.exception.EntidadeEmUsoException;
import com.luan.pix.domain.model.Usuario;
import com.luan.pix.repository.UsuarioRepository;
import com.luan.pix.utils.RandomStringUtils;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Usuario salvarUsuario(Usuario usuario) {
		if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
			throw new EntidadeEmUsoException(String.format("O Email %d já tem um cadastro em uso", usuario.getEmail()));
		} 
		
		String senhaCodificada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCodificada);
		
		String randomCode = RandomStringUtils.generateString(64);
		usuario.setCodigoDeVerificacao(randomCode);
		usuario.setAtivo(false);
		
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		return usuarioSalvo;
	}
	
	public Usuario buscaOuFalha(Long usuarioId) {
		return usuarioRepository.findById(usuarioId).orElseThrow();
	}

}

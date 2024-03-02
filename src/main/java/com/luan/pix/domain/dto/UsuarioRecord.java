package com.luan.pix.domain.dto;

import com.luan.pix.domain.model.Usuario;

public record UsuarioRecord(String nome, String email, String senha) {
	
	public Usuario toModel() {
		return new Usuario(nome, email, senha);
	}

}

package com.luan.pix.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luan.pix.domain.dto.UsuarioRecord;
import com.luan.pix.domain.model.Usuario;
import com.luan.pix.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvarUsuario(@RequestBody UsuarioRecord usuariorRecord) {
		Usuario usuario = usuariorRecord.toModel();
		Usuario usuarioSalvo = service.salvarUsuario(usuario);
		return usuarioSalvo;
	}
	
}

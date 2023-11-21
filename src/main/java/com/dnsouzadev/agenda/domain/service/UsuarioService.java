package com.dnsouzadev.agenda.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dnsouzadev.agenda.domain.entity.Usuario;
import com.dnsouzadev.agenda.domain.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public boolean usuarioExiste(String usuario) {
        return repository.findByUsuario(usuario).isPresent();
    }

    public UserDetails loadUserByUsername(String usuario) {
        Optional<Usuario> optUsuario = repository.findByUsuario(usuario);

        if(optUsuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário ão encontrado");
        }

        Usuario user = optUsuario.get();

        return new User(user.getUsuario(), user.getSenha(), new ArrayList<>());
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario save(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }

}

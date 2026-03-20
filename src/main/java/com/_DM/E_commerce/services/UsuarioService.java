package com._DM.E_commerce.services;

import com._DM.E_commerce.dtos.UsuarioDTO;
import com._DM.E_commerce.entities.Usuario;
import com._DM.E_commerce.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDTO criarUsuario(UsuarioDTO dto) {
        Usuario usuario = new Usuario(dto.getNome(), dto.getEmail(), dto.getTelefone(), dto.getSenha(), dto.getRole());
        usuarioRepository.save(usuario);
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getNome(), usuario.getEmail(), usuario.getTelefone(), usuario.getSenha(), usuario.getRole());
        return usuarioDTO;
    }

    public List<Usuario> mostrarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Usuario> listaUsuarios = new ArrayList<>();

        listaUsuarios = usuarios;

        return listaUsuarios;
    }

    public UsuarioDTO mostrarUsuarioPorId(UUID id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + id));

        return new UsuarioDTO(usuario.getNome(), usuario.getEmail(), usuario.getTelefone(), usuario.getSenha(), usuario.getRole());
    }

    public String atualizarUsuario(UUID id, UsuarioDTO dto) {
        Optional<Usuario> listaUsuarios = usuarioRepository.findById(id);

        if (listaUsuarios.isPresent()) {
            Usuario usuario = listaUsuarios.get();
            usuario.setNome(dto.getNome());
            usuario.setEmail(dto.getEmail());

            return usuario.toString();
        } else {
            return "Usuario não encontrado";
        }
    }

    public String deletarUsuario(UUID id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return "Usuário excluído com sucesso";
        } else {
            return "Usuário não encontrado";
        }
    }
}

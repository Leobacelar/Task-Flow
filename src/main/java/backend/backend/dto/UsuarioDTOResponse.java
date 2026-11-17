package backend.backend.dto;

import backend.backend.model.Usuario;

public record UsuarioDTOResponse(Long id, String nome, String email) {

    public static UsuarioDTOResponse converter(Usuario usuario) {
        return new UsuarioDTOResponse(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}

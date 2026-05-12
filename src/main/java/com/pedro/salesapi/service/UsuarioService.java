package com.pedro.salesapi.service;

import com.pedro.salesapi.dto.LoginRequestDTO;
import com.pedro.salesapi.dto.LoginResponseDTO;
import com.pedro.salesapi.dto.UsuarioRequestDTO;
import com.pedro.salesapi.dto.UsuarioResponseDTO;
import com.pedro.salesapi.entity.Role;
import com.pedro.salesapi.entity.Usuario;
import com.pedro.salesapi.exception.RegraNegocioException;
import com.pedro.salesapi.repository.UsuarioRepository;
import com.pedro.salesapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UsuarioResponseDTO salvar(UsuarioRequestDTO usuarioDTO) {

        if (repository.existsByEmail(usuarioDTO.getEmail().toLowerCase())) {
            throw new RegraNegocioException(
                    "Email já cadastrado"
            );
        }

        Usuario usuario = new Usuario();

        usuario.setNome(usuarioDTO.getNome());

        usuario.setEmail(usuarioDTO.getEmail().toLowerCase());

        usuario.setSenha(
                passwordEncoder.encode(usuarioDTO.getSenha())
        );

        usuario.setRole(Role.ROLE_CLIENTE);

        usuario.setAtivo(true);

        Usuario salvo = repository.save(usuario);

        return new UsuarioResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getEmail(),
                salvo.getRole()
        );
    }

    public LoginResponseDTO login(LoginRequestDTO loginDTO) {

        Usuario user = repository
                .findByEmail(loginDTO.getEmail().toLowerCase())
                .orElseThrow(() ->
                        new RegraNegocioException(
                                "Usuário não encontrado"
                        )
                );

        if (!user.isAtivo()) {
            throw new RegraNegocioException(
                    "Usuário desativado"
            );
        }

        if (!passwordEncoder.matches(
                loginDTO.getSenha(),
                user.getSenha()
        )) {

            throw new RegraNegocioException(
                    "Senha inválida"
            );
        }

        String token = jwtService.gerarToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new LoginResponseDTO(token);
    }
}
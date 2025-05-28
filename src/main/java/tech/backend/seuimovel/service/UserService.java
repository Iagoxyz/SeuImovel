package tech.backend.seuimovel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.backend.seuimovel.dto.LoginDTO;
import tech.backend.seuimovel.dto.UserDTO;
import tech.backend.seuimovel.entities.User;
import tech.backend.seuimovel.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User cadastrar(UserDTO dto) {
        User usuario = new User();
        usuario.setName(dto.name);
        usuario.setEmail(dto.email);
        usuario.setPassword(dto.password); // Sem criptografia aqui
        return userRepository.save(usuario);
    }

    public User login(LoginDTO dto) {
        return userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));
    }
}



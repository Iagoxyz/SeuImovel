package tech.backend.seuimovel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.backend.seuimovel.dto.ListingDTO;
import tech.backend.seuimovel.dto.LoginDTO;
import tech.backend.seuimovel.dto.LoginResponseDTO;
import tech.backend.seuimovel.dto.UserDTO;
import tech.backend.seuimovel.entities.Listing;
import tech.backend.seuimovel.entities.User;

import tech.backend.seuimovel.repository.ListingRepository;
import tech.backend.seuimovel.repository.UserRepository;
import tech.backend.seuimovel.service.UserService;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ListingRepository listingRepository;


    @PostMapping("/usuarios")
    public ResponseEntity<User> cadastrar(@RequestBody UserDTO dto) {
        User user = userService.cadastrar(dto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            User user = userService.login(loginDTO);
            LoginResponseDTO response = new LoginResponseDTO(user.getName(), "Usuário autenticado com sucesso");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }

    @PostMapping("/anuncios")
    public ResponseEntity<Listing> criarAnuncio(@RequestBody ListingDTO dto) {
        User usuario = userRepository.findById(dto.usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Listing anuncio = new Listing();
        anuncio.setCategoria(dto.categoria);
        anuncio.setEndereco(dto.endereco);
        anuncio.setMetragem(dto.metragem);
        anuncio.setComodos(dto.comodos);
        anuncio.setTipo(dto.tipo);
        anuncio.setPreco(dto.preco);
        anuncio.setStatus(dto.status);
        anuncio.setImgURL(dto.imgURL);
        anuncio.setUsuario(usuario);

        return ResponseEntity.ok(listingRepository.save(anuncio));
    }
}

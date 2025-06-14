package tech.backend.seuimovel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.backend.seuimovel.dto.*;
import tech.backend.seuimovel.entities.Listing;
import tech.backend.seuimovel.entities.User;

import tech.backend.seuimovel.repository.ListingRepository;
import tech.backend.seuimovel.repository.UserRepository;
import tech.backend.seuimovel.service.UserService;
import tech.backend.seuimovel.session.Session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        User user = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword()).orElseThrow();
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }

        Session.currentUser = user; // <- define o usuário como "logado"

        return ResponseEntity.ok("Usuário autenticado com sucesso");
    }

    @PostMapping("/criar/anuncios")
    public ResponseEntity<?> criarAnuncio(@RequestBody ListingDTO dto) {
        Long usuarioId = dto.getUsuarioId();

        Optional<User> userOptional = userRepository.findById(usuarioId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com ID " + usuarioId + " não encontrado");
        }

        User usuario = userOptional.get();

        Listing anuncio = new Listing();
        anuncio.setCategoria(dto.getCategoria());
        anuncio.setEndereco(dto.getEndereco());
        anuncio.setMetragem(dto.getMetragem());
        anuncio.setComodos(dto.getComodos());
        anuncio.setTipo(dto.getTipo());
        anuncio.setPreco(dto.getPreco());
        anuncio.setStatus(dto.getStatus());
        anuncio.setImgURL(dto.getImgURL());
        anuncio.setUsuario(usuario);

        Listing salvo = listingRepository.save(anuncio);

        Map<String, Object> response = new HashMap<>();
        response.put("imgUrl", salvo.getImgURL());
        response.put("status", salvo.getStatus());
        response.put("preco", salvo.getPreco());
        response.put("tipo", salvo.getTipo());
        response.put("comodos", salvo.getComodos());
        response.put("metragem", salvo.getMetragem());
        response.put("endereco", salvo.getEndereco());
        response.put("categoria", salvo.getCategoria());
        response.put("id", salvo.getId());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/anuncios/{id}")
    public ResponseEntity<?> buscarAnunciosPorUsuario(@PathVariable("id") Long usuarioId) {
        Optional<User> usuarioOptional = userRepository.findById(usuarioId);

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com ID " + usuarioId + " não encontrado");
        }

        List<Listing> anuncios = listingRepository.findByUsuarioId(usuarioId);

        if (anuncios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum anúncio encontrado para o usuário com ID: " + usuarioId);
        }

        return ResponseEntity.ok(anuncios);
    }

    @DeleteMapping("/anuncios/{id}")
    public ResponseEntity<String> deletarAnuncio(@PathVariable Long id) {
        if (!listingRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Anúncio não encontrado");
        }

        listingRepository.deleteById(id);
        return ResponseEntity.ok("Anúncio apagado com sucesso!");
    }

    @GetMapping("/anuncios")
    public ResponseEntity<List<Listing>> listarAnuncios() {
        List<Listing> anuncios = listingRepository.findAll();
        return ResponseEntity.ok(anuncios);
    }

    @PutMapping("/usuarios/recuperar-senha")
    public ResponseEntity<?> recuperarSenha(@RequestBody RecuperarSenhaDTO dto) {
        Optional<User> usuarioOptional = userRepository.findByEmail(dto.getEmail());

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário com e-mail " + dto.getEmail() + " não encontrado");
        }

        User usuario = usuarioOptional.get();
        usuario.setPassword(dto.getNovaSenha());
        userRepository.save(usuario);

        return ResponseEntity.ok("Senha atualizada com sucesso!");
    }

}

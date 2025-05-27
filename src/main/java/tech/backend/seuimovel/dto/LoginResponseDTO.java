package tech.backend.seuimovel.dto;

public class LoginResponseDTO {
    private String name;
    private String message;

    public LoginResponseDTO(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}

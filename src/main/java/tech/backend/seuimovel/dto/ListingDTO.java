package tech.backend.seuimovel.dto;

public class ListingDTO {
    public String categoria;
    public String endereco;
    public String metragem;
    public String comodos;
    public String tipo;
    public Double preco;
    public String status;
    public String imgURL;
    public Long usuarioId;

    public ListingDTO() {
    }

    public String getCategoria() {
        return categoria;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getMetragem() {
        return metragem;
    }

    public String getComodos() {
        return comodos;
    }

    public String getTipo() {
        return tipo;
    }

    public Double getPreco() {
        return preco;
    }

    public String getStatus() {
        return status;
    }

    public String getImgURL() {
        return imgURL;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
}

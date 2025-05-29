<h2>
  Projeto SeuImóvel - Sistema de Anúncios de Imóveis
</h2>

<p>
    <img src="https://img.shields.io/github/languages/count/Iagoxyz/SeuImovel"/>
    <img src="https://img.shields.io/github/repo-size/Iagoxyz/SeuImovel"/>
    <img src="https://img.shields.io/github/last-commit/Iagoxyz/SeuImovel"/>
    <img src="https://img.shields.io/github/issues/Iagoxyz/SeuImovel"/>
</p>


## Tecnologias 

Este projeto foi desenvolvido com as seguintes tecnologias:

- Java (17)
- Spring Boot
- Spring Web
- Spring Data JPA / Hibernate
- Banco de Dados H2
- Maven

## Projeto

Projeto desenvolvido como atividade acadêmica. O sistema tem como objetivo gerenciar anúncios de imóveis para venda ou aluguel, permitindo o cadastro de usuários, criação e listagem de anúncios. A aplicação expõe uma API RESTful que pode ser integrada a um front-end para exibição dos dados.

---

## :bookmark_tabs: Documentação da API

### URL base

```http 
http://localhost:8080/

```
``` Criar Usuário
POST /usuarios

{
  "nome": "João da Silva",
  "email": "joao@email.com"
}
```

``` Criar Anuncio:
POST /anuncios

{
  "categoria": "Casa",
  "endereco": "Rua das Flores, 123",
  "metragem": 120,
  "comodos": 5,
  "tipo": "Venda",
  "preco": 300000,
  "status": "Disponível",
  "imgURL": "https://exemplo.com/imagem.jpg",
  "usuarioId": 1
}



package br.com.springalura.forum.modelo.dto;

import br.com.springalura.forum.modelo.Topico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TopicoDto {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime datacriacao;

    public Long getId() {
        return id;
    }

    public TopicoDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public TopicoDto setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String getMensagem() {
        return mensagem;
    }

    public TopicoDto setMensagem(String mensagem) {
        this.mensagem = mensagem;
        return this;
    }

    public LocalDateTime getDatacriacao() {
        return datacriacao;
    }

    public TopicoDto setDatacriacao(LocalDateTime datacriacao) {
        this.datacriacao = datacriacao;
        return this;
    }
}

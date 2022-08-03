package br.com.springalura.forum.modelo.parserdto;

import br.com.springalura.forum.modelo.Topico;
import br.com.springalura.forum.modelo.dto.TopicoDto;

public class ParserTopico {
    public static ParserTopico get(){
        return new ParserTopico();
    }

    public TopicoDto parserTopicoDto(Topico topico){
        TopicoDto dto = new TopicoDto();
        dto.setId(topico.getId());
        dto.setMensagem(topico.getMensagem());
        dto.setTitulo(topico.getTitulo());
        dto.setDatacriacao(topico.getDataCriacao());
        return dto;
    }
}

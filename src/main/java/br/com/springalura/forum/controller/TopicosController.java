package br.com.springalura.forum.controller;

import br.com.springalura.forum.modelo.Topico;
import br.com.springalura.forum.modelo.dto.DetalhesDoTopicoDto;
import br.com.springalura.forum.modelo.dto.TopicoDto;
import br.com.springalura.forum.modelo.form.AtualizacaoTopicoForm;
import br.com.springalura.forum.modelo.form.TopicoForm;
import br.com.springalura.forum.modelo.parserdto.ParserTopico;
import br.com.springalura.forum.repository.CursoRepository;
import br.com.springalura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> lista(String nomeCurso){

        if(nomeCurso == null){
            return topicoRepository
                    .findAll()
                    .stream()
                    .map(ParserTopico.get()::parserTopicoDto)
                    .collect(Collectors.toList());
        }else {
            return topicoRepository
                    .findByCursoNome(nomeCurso)
                    .stream()
                    .map(ParserTopico.get()::parserTopicoDto)
                    .collect(Collectors.toList());
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uribuilder){
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uribuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new ParserTopico().parserTopicoDto(topico));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable("id") Long codigo){
        Optional<Topico> topico = topicoRepository.findById(codigo);
        if(topico.isPresent()){
        return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@PathVariable("id") Long codigo, @RequestBody @Valid AtualizacaoTopicoForm form){
        Optional<Topico> optional = topicoRepository.findById(codigo);
        if(optional.isPresent()){
            Topico topico = form.atualizar(codigo, topicoRepository);
            return ResponseEntity.ok(ParserTopico.get().parserTopicoDto(topico));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable("id") Long codigo){
        Optional<Topico> topico = topicoRepository.findById(codigo);
        if(topico.isPresent()){
            topicoRepository.deleteById(codigo);

            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }

    }


}

package br.com.fiap.cidadesesg.controller;

import br.com.fiap.cidadesesg.model.Cidade;
import br.com.fiap.cidadesesg.service.CidadeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {

    private final CidadeService service;

    public CidadeController(CidadeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cidade> listar(@RequestParam(required = false) String estado) {
        return service.listarTodas(estado);
    }

    @GetMapping("/{id}")
    public Cidade buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade criar(@Valid @RequestBody Cidade cidade) {
        return service.criar(cidade);
    }

    @PutMapping("/{id}")
    public Cidade atualizar(@PathVariable Long id, @Valid @RequestBody Cidade cidade) {
        return service.atualizar(id, cidade);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @GetMapping("/status")
    public Map<String, String> status() {
        return Map.of(
                "aplicacao", "Cidades ESG Inteligentes",
                "status", "online"
        );
    }
}

package br.com.fiap.cidadesesg.service;

import br.com.fiap.cidadesesg.model.Cidade;
import br.com.fiap.cidadesesg.repository.CidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    private final CidadeRepository repository;

    public CidadeService(CidadeRepository repository) {
        this.repository = repository;
    }

    public List<Cidade> listarTodas(String estado) {
        if (estado != null && !estado.isBlank()) {
            return repository.findByEstadoIgnoreCase(estado);
        }
        return repository.findAll();
    }

    public Cidade buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cidade não encontrada com id: " + id));
    }

    public Cidade criar(Cidade cidade) {
        return repository.save(cidade);
    }

    public Cidade atualizar(Long id, Cidade dados) {
        Cidade cidade = buscarPorId(id);
        cidade.setNome(dados.getNome());
        cidade.setEstado(dados.getEstado());
        cidade.setNotaAmbiental(dados.getNotaAmbiental());
        cidade.setNotaSocial(dados.getNotaSocial());
        cidade.setNotaGovernanca(dados.getNotaGovernanca());
        return repository.save(cidade);
    }

    public void excluir(Long id) {
        Cidade cidade = buscarPorId(id);
        repository.delete(cidade);
    }
}

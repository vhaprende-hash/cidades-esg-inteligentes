package br.com.fiap.cidadesesg.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cidades")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da cidade é obrigatório.")
    @Column(nullable = false, length = 120)
    private String nome;

    @NotBlank(message = "O estado é obrigatório.")
    @Size(min = 2, max = 2, message = "Use a sigla do estado com 2 caracteres.")
    @Column(nullable = false, length = 2)
    private String estado;

    @Min(value = 0, message = "A nota ambiental deve ser no mínimo 0.")
    @Max(value = 100, message = "A nota ambiental deve ser no máximo 100.")
    @Column(nullable = false)
    private Integer notaAmbiental;

    @Min(value = 0, message = "A nota social deve ser no mínimo 0.")
    @Max(value = 100, message = "A nota social deve ser no máximo 100.")
    @Column(nullable = false)
    private Integer notaSocial;

    @Min(value = 0, message = "A nota de governança deve ser no mínimo 0.")
    @Max(value = 100, message = "A nota de governança deve ser no máximo 100.")
    @Column(nullable = false)
    private Integer notaGovernanca;

    @Column(nullable = false)
    private Double indiceEsg;

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    public Cidade() {
    }

    public Cidade(String nome, String estado, Integer notaAmbiental, Integer notaSocial, Integer notaGovernanca) {
        this.nome = nome;
        this.estado = estado;
        this.notaAmbiental = notaAmbiental;
        this.notaSocial = notaSocial;
        this.notaGovernanca = notaGovernanca;
    }

    @PrePersist
    @PreUpdate
    public void calcularIndice() {
        if (notaAmbiental != null && notaSocial != null && notaGovernanca != null) {
            this.indiceEsg = (notaAmbiental + notaSocial + notaGovernanca) / 3.0;
        }
        if (criadoEm == null) {
            this.criadoEm = LocalDateTime.now();
        }
        if (estado != null) {
            this.estado = estado.toUpperCase();
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }

    public Integer getNotaAmbiental() {
        return notaAmbiental;
    }

    public Integer getNotaSocial() {
        return notaSocial;
    }

    public Integer getNotaGovernanca() {
        return notaGovernanca;
    }

    public Double getIndiceEsg() {
        return indiceEsg;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNotaAmbiental(Integer notaAmbiental) {
        this.notaAmbiental = notaAmbiental;
    }

    public void setNotaSocial(Integer notaSocial) {
        this.notaSocial = notaSocial;
    }

    public void setNotaGovernanca(Integer notaGovernanca) {
        this.notaGovernanca = notaGovernanca;
    }

    public void setIndiceEsg(Double indiceEsg) {
        this.indiceEsg = indiceEsg;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}

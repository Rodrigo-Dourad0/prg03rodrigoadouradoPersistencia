/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author almei
 */
@Entity
public class Curso {
        
    @Id
    @GeneratedValue(strategy  = GenerationType.AUTO)
    private Long id;
    
    @Column (name= "nome", nullable = false)
    private String nome;
    
    @Column (name= "codigo_curso", nullable = false)
    private String codigo;
    
    @Column (name= "carga_horaria", nullable = false)
    private int cargaHoraria;
    
    @Column (name= "ativo")
    private boolean ativo;

    public Curso() {
    }
    
    
    public Curso(Long id, String nome, String codigo, int cargaHoraria, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.ativo = ativo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    
  
 
}

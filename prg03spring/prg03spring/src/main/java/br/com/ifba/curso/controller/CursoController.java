/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoIService;
import br.com.ifba.curso.service.CursoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author almei
 */
@Controller
public class CursoController implements CursoIController {
    
    @Autowired
    private final CursoIService cursoIService = new CursoService();
    
    @Override 
    public List<Curso> findAll() throws RuntimeException{
        return cursoIService.findAll();
    }
    
    @Override
    public Curso save(Curso curso) throws RuntimeException{
        return cursoIService.save(curso);
    }
    
    @Override 
    public Curso update(Curso curso) throws RuntimeException{
        return cursoIService.update(curso);
    }
    
    @Override
    public void delete(Curso curso) throws RuntimeException{
         cursoIService.delete(curso);    
    }
    
    @Override
    public Curso findById(Long id) throws RuntimeException{
        return cursoIService.findById(id);
    }
    
    @Override 
    public List<Curso> findByNome(String nome)throws RuntimeException{
        return cursoIService.findByNome(nome);
    }
    
}

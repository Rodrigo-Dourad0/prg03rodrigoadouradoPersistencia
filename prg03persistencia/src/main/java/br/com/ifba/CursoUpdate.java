/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba;

import br.com.ifba.curso.entity.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author almei
 */
public class CursoUpdate {
    private final static EntityManagerFactory entityManagerFactory = 
                 Persistence.createEntityManagerFactory("prg03persistencia");

         private final static EntityManager entityManager
                 = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {


            Curso curso = new Curso();
            curso.setNome("Administração");
            curso.setCodigo("ADM");
            curso.setCargaHoraria(10);
            curso.setAtivo(true);

            entityManager.getTransaction().begin();
            entityManager.persist(curso);
            entityManager.getTransaction().commit();
    }
}

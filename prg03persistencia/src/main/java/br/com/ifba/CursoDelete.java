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
public class CursoDelete {
             private final static EntityManagerFactory entityManagerFactory = 
                 Persistence.createEntityManagerFactory("prg03persistencia");

         private final static EntityManager entityManager
                 = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {

        //REMOVE (DELETANDO) UM CURSO
        
        Curso cursoEncontrado = entityManager.find(Curso.class, 1L);
        
        entityManager.getTransaction().begin();
        
        entityManager.remove(cursoEncontrado);
        
        entityManager.getTransaction().commit();
        
        
        //Fechando os recursos
        
        entityManager.close();
        
        entityManagerFactory.close();

    }    
}

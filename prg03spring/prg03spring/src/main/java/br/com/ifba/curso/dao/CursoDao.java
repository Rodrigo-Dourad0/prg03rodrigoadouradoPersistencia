/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.dao;


import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.dao.GenericDao;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author almei
 */

@Repository
public class CursoDao extends GenericDao<Curso> implements CursoIDao{
    
       @Override
    public List<Curso> findByNome(String nome) {
        // Consulta JPQL que busca cursos cujo nome contenha o texto informado (case-insensitive)
        String jpql = "SELECT c FROM Curso c WHERE LOWER(c.nome) LIKE :nome";

        // Executa a consulta com o parâmetro formatado em minúsculas e com curingas %
        return entityManager.createQuery(jpql, Curso.class)
                .setParameter("nome", "%" + nome.toLowerCase() + "%")
                .getResultList(); // Retorna a lista de cursos encontrados
    }
}

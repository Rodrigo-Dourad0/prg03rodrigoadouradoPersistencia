package br.com.ifba.infrastructure.dao;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Transactional
public abstract class GenericDao<Entity extends PersistenceEntity> implements GenericIDao<Entity> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public Entity save(Entity entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Entity update(Entity entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(Entity entity) {
        entity = findById(entity.getId());
        entityManager.remove(entity);
    }

    @Override
    public List<Entity> findAll() {
        return entityManager.createQuery("FROM " + getTypeClass().getName(), getTypeClass()).getResultList();
    }

    @Override
    public Entity findById(Long id) {
        return entityManager.find(getTypeClass(), id);
    }

    @SuppressWarnings("unchecked")
    protected Class<Entity> getTypeClass() {
        return (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}

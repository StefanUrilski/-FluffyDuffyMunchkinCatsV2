package fdmc.repository;

import fdmc.domain.entities.Cat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class CatRepositoryImpl implements CatRepository {

    private final EntityManager entityManager;


    @Inject
    public CatRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Cat cat) {
        entityManager.getTransaction().begin();
        entityManager.persist(cat);
        entityManager.getTransaction().commit();
    }

    @Override
    public Cat findById(String id) {
        return entityManager
                .createQuery("" +
                        "select c " +
                        "from Cat as c " +
                        "where c.id = :id", Cat.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Cat> findAll() {
        return entityManager
                .createQuery("" +
                        "select c " +
                        "from Cat as c ", Cat.class)
                .getResultList();
    }
}

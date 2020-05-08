package dao;

import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class UserDao {

    private final EntityManager em;
    String PERSISTENCE_UNIT_NAME = "Maven_Persistence";

    public UserDao() {
        em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                .createEntityManager();
    }

    public UserDao(EntityManager em) {
        this.em = em;
    }

    public List<User> findAll() {
        /* 
        https://docs.oracle.com/javaee/6/tutorial/doc/bnbrg.html       
         */
        TypedQuery<User> typedQuery = (TypedQuery<User>) em.createNativeQuery("SELECT u FROM Utente u", User.class);
        List<User> personaList = typedQuery.setMaxResults(10).getResultList();
        return personaList;
    }

    public List<User> findAllQNative() {
        /*
        https://www.oracle.com/technetwork/articles/vasiliev-jpql-087123.html
         */
        List<User> personaList = (List<User>) em.createNativeQuery("SELECT * FROM Utente", User.class)
                .setMaxResults(10)
                .getResultList();
        return personaList;
    }

    public List<User> findByName(String username) {
        /* 
        https://docs.oracle.com/javaee/6/tutorial/doc/bnbrg.html       
         */
        TypedQuery<User> typedQuery = (TypedQuery<User>) em.createQuery("SELECT p FROM Utente p WHERE p.username LIKE :username", User.class)
                .setParameter("username", username);
        List<User> personaList = typedQuery.setMaxResults(10).getResultList();
        return personaList;
    }

    public boolean insertUtente(User u) {
        em.getTransaction().begin();
        try {
            em.persist(u);
            // -- workaround cache entity manager
            em.flush();
            em.clear();
            // --
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }
    
}

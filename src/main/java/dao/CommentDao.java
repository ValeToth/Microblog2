package dao;

import entity.Comment;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class CommentDao {

    private final EntityManager em;
    String PERSISTENCE_UNIT_NAME = "Maven_Persistence";

    public CommentDao() {
        em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                .createEntityManager();
    }

    public CommentDao(EntityManager em) {
        this.em = em;
    }

    public List<Comment> findAll() {
        /* 
        https://docs.oracle.com/javaee/6/tutorial/doc/bnbrg.html       
         */
        TypedQuery<Comment> typedQuery = (TypedQuery<Comment>) em.createNativeQuery("SELECT * FROM Commento", Comment.class);
        List<Comment> commentList = typedQuery.setMaxResults(10).getResultList();
        return commentList;
    }

    public List<Comment> findAllQNative() {
        /*
        https://www.oracle.com/technetwork/articles/vasiliev-jpql-087123.html
         */
        List<Comment> commentList = (List<Comment>) em.createNativeQuery("SELECT * FROM Utente", Comment.class)
                .setMaxResults(10)
                .getResultList();
        return commentList;
    }

    public boolean insertComment(Comment c) {
        em.getTransaction().begin();
        try {
            em.persist(c);
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

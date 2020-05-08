package dao;

import javax.persistence.*;

public class DAO {

    String PERSISTENCE_UNIT_NAME = "Maven_Persistence";
    static EntityManager em;
    private final UserDao userDao;
    private final PostDao postDao;
    private final CommentDao commentDao;

    public DAO() {
        em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
        userDao = new UserDao(em);
        postDao = new PostDao(em);
        commentDao = new CommentDao(em);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public PostDao getPostDao() {
        return postDao;
    }
    public CommentDao getCommentDao() {
        return commentDao;
    }
}

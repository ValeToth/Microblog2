package jersey.service;

import dao.*;
import entity.Post;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/posts")
public class Service {
    
    private final EntityManager em;
    String PERSISTENCE_UNIT_NAME = "Maven_Persistence";

    public Service() {
        em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                .createEntityManager();
    }

    public Service(EntityManager em) {
        this.em = em;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Post> getPosts() {
        PostDao postdao = new PostDao();
        List<Post> posts = new ArrayList<Post>();
        posts.addAll(postdao.findAll());
        return posts;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public List<Post> getPostById(@PathParam("id") Long id) {
        PostDao postdao = new PostDao();
        List<Post> posts = new ArrayList<>();
        posts.addAll(postdao.findPostById(id));
        return posts;
    }
    
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_JSON)
    public void newPost(Post p) {
        PostDao postdao = new PostDao();
        postdao.insertPost(p);
    }
}

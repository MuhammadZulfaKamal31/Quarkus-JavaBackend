package tech.readone;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.panache.common.Sort;
import tech.readone.models.Article;
import tech.readone.models.User;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @GET
    public List<User> getUsers() {
        return User.listAll(Sort.ascending("id"));
    }

    @GET
    @Path("{id}")
    public User getUserById(@PathParam("id") Long id) {
        return User.findById(id);
    }

    @POST
    @Transactional
    public List<User> addUser(User user) {
        user.persist();
        return User.listAll(Sort.ascending("id"));
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public List<User> deleteUserByid(@PathParam("id") Long id) {
        User.deleteById(id);
        return Article.listAll();

    }
    // @POST
    // @Transactional
    // public List<User> addArticle(User user) {
    // user.persist();
    // for (User user : user.adress) { // kalau mau bisa nulis di article langsung
    // bisa tambahin forlopp ini
    // user.persist();
    // }
    // return User.listAll(Sort.ascending("id"));

    // }
    // }
}
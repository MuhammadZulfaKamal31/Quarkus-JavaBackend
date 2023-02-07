package tech.readone;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.panache.common.Sort;
import tech.readone.models.Adress;
import tech.readone.models.User;

@Path("adress")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdressResource {

    @GET
    public List<Adress> getAdress() {
        return Adress.listAll(Sort.ascending("id"));
    }

    @POST
    @Transactional
    @Path("{id}")
    public List<Adress> addAdressToUser(@PathParam("id") Long id, Adress adress) {
        User user = User.findById(id);
        adress.user = user;
        adress.persist();
        return Adress.listAll(Sort.ascending("id"));
    }
}

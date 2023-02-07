package org.acme;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("fruits")
public class FruitResource {

    List<Fruit> fruits = new ArrayList<>();

    @Inject
    Validator validator;

    @GET
    public List<Fruit> getFruits() {
        return fruits;
    }

    @GET
    @Path("{angka}")
    public Fruit getFruitByIndex(@PathParam("angka") Integer index) {
        return fruits.get(index);
    }

    // @POST
    // @Produces(MediaType.APPLICATION_JSON)
    // @Consumes(MediaType.APPLICATION_JSON)
    // public Result addFruit(Fruit fruit) {
    // Set<ConstraintViolation<Fruit>> violations = validator.validate(fruit);
    // if (violations.isEmpty()) {
    // fruits.add(fruit);
    // return new Result("Buah " + fruit.name + " Berhasil di tambahkan");
    // } else {
    // return new Result(violations);
    // }
    // }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Result addFruit(@Valid Fruit fruit) {
        fruits.add(fruit);
        return new Result("Buah baru berhasil di tambahkan");
    }

    @POST
    @Path("batch")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruit> addFruits(List<Fruit> lotOfFruit) {
        for (Fruit fruit : lotOfFruit) {
            fruits.add(fruit);
        }
        return lotOfFruit;
    }

    @PUT
    @Path("{index}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Fruit editFruit(@PathParam("index") Integer index, Fruit newFruit) {
        fruits.set(index, newFruit);
        return newFruit;
    }

    @DELETE
    @Path("{index}")
    public String deleteFruit(@PathParam("index") Integer index) {
        fruits.remove(index.intValue());
        return "Berhasil di Hapus";
    }
}

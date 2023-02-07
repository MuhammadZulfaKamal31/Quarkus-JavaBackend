package org.acme;

import java.util.HashMap;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("words")
public class Helloresource {

    HashMap<Integer, String> words = new HashMap<>();
    // List<String> words = new ArrayList<>();
    public static Integer id = 1;

    @GET
    public HashMap<Integer, String> getWord() {
        return words;
    }

    @GET
    @Path("{index}")
    public String getWordByIndex(@PathParam("{index}") Integer id) {
        return words.get(id);
    }

    @POST
    public HashMap<Integer, String> addWord(String word) {
        words.put(id++, word);
        return words;
    }

    @PUT
    @Path("{index}")
    public HashMap<Integer, String> chargeWord(Integer index, String word) {
        words.replace(index, word);
        return words;
    }

    @DELETE
    @Path("{index}")
    public HashMap<Integer, String> deleteWords(Integer index) {
        words.remove(index.intValue());
        return words;
    }
}

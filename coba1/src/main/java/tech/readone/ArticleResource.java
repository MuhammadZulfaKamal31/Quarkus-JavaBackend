package tech.readone;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.panache.common.Sort;
import tech.readone.models.Article;
import tech.readone.models.Comment;

@Path("article")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArticleResource {

    @GET
    public List<Article> getArticle() {
        return Article.listAll(Sort.ascending("id"));
    }

    @GET
    @Path("{id}")
    @Transactional
    public Article getArticle(@PathParam("id") Long id) {
        return Article.findById("id");
    }

    @POST
    @Transactional
    public List<Article> addArticle(Article article) {
        article.persist();
        for (Comment comment : article.comments) { // kalau mau bisa nulis di article langsung bisa tambahin forlopp ini
            comment.persist();
        }return Article.listAll(Sort.ascending("id"));

    }

    @PUT
    @Transactional
    @Path("{id}")
    public List<Article> updateArticle(@PathParam("id") Long id, Article newArticle) {
        Article OldArticle = Article.findById(id);
        OldArticle.tittle = newArticle.tittle;
        OldArticle.body = newArticle.body;
        return Article.listAll(Sort.ascending("id"));
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public List<Article> deleteById(@PathParam("id") Long id) {
        Article.deleteById(id);
        return Article.listAll(Sort.ascending("id"));
    }
}
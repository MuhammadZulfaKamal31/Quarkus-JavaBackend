package tech.readone;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import tech.readone.models.Article;
import tech.readone.models.Comment;

@Path("tittle/{idArticle}/command")
public class CommandResource {

    @GET
    public List<Comment> getCommantsByArticleId(@PathParam("idArticle") Long id) {
        return Comment.find("article_id = ?1", id).list();
    }

    @GET
    @Path("{idComment}")
    public Comment getCommentById(@PathParam("idArticle") Long idArticle, @PathParam("idComment") Long id) {
        return Comment.findById(id);
    }

    @POST
    @Transactional
    public List<Comment> addComments(@PathParam("idArticle") Long idArticle, Comment comment) {
        Article article = Article.findById(idArticle);
        article.comments.add(comment);// article.commaent dari public List commend/ penyimpanannya
        comment.persist();
        return Comment.find("article_id = ?1", idArticle).list();// parameternya ambil dari end point
    }

}

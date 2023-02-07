package tech.readone;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.quarkus.panache.common.Sort;
import tech.readone.models.Task;

@Path("tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    @GET
    public List<Task> getTask() {

        return Task.listAll(Sort.ascending("Id"));
    }

    @GET
    @Path("{id}")
    public Object getTaskByid(@PathParam("id") Long id) {
        Task task = Task.findById(id);
        HashMap<String, Object> result = new HashMap<>();
        if (task == null) {
            result.put("message", " Task dengan id tersebut tidak di temukan");
            return result;
        }
        return task;
    }

    @POST
    @Transactional
    public List<Task> addTask(@Valid Task task) {
        task.persist();
        return Task.listAll(Sort.ascending("Id"));
    }

    @GET
    @Path("sbject")
    public List<Task> getTaskBySubject(@QueryParam("sbject") String subject) {
        return Task.list("subject", subject);
    }

    @PUT
    @Transactional
    @Path("{id}")
    public List<Task> editTaskById(@PathParam("id") Long id, Task task) {
        Task oldTask = Task.findById(id);
        oldTask.tittle = task.tittle;
        oldTask.description = task.description;
        oldTask.subject = task.subject;
        oldTask.score = task.score;
        return Task.listAll(Sort.ascending("Id"));
    }

    @PUT
    @Transactional
    @Path("{id}/tittle")
    @Consumes(MediaType.TEXT_PLAIN)
    public List<Task> editTaskById(@PathParam("id") Long id, String title) {
        Task task = Task.findById(id);
        task.tittle = title;
        return Task.listAll(Sort.ascending("tittle"));// sesuai dengan nama tabel bukan parameter
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public HashMap<String, Object> delete(@PathParam("id") Long id) {

        HashMap<String, Object> result = new HashMap<>();
        if (Task.deleteById(id)) {
            result.put("message", " Task dengan id " + id + " tersebut berhasil di hapus");
        } else {
            result.put("message", "Task dengan id " + id + " tersebut gagal di hapus");
        }
        return result;
    }
}
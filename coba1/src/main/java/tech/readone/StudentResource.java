package tech.readone;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.panache.common.Sort;
import tech.readone.models.Student;

@Path("student")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

    @GET
    public List<Student> getStudent() {
        return Student.listAll(Sort.ascending("id"));
    }

    @POST
    @Transactional
    public List<Student> addStudent(Student student) {
        student.persist();
        return Student.listAll(Sort.ascending("id"));
    }
}

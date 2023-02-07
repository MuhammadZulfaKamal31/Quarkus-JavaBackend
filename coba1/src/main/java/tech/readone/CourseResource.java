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
import tech.readone.models.Course;
import tech.readone.models.Student;

@Path("courses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourseResource {

    @GET
    public List<Course> getCourse() {
        return Course.listAll(Sort.ascending("id"));
    }

    @POST
    @Transactional
    public List<Course> addCourse(Course course) {
        course.persist();
        return Course.listAll(Sort.ascending("id"));
    }

    @POST // cara nambahin di tabel student_couse
    @Transactional
    @Path("{idCourse}/student/{idStudent}")
    public List<Course> enrollStudent(@PathParam("idCourse") Long idCourse, @PathParam("idStudent") Long idStudent) {
        Course course = Course.findById(idCourse);
        Student student = Student.findById(idStudent);
        course.students.add(student); // ini cara hubunginnya
        course.persist();
        return Course.listAll(Sort.ascending("id"));
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public List<Course> deleteCourseById(@PathParam("id") Long id) {
        Course.deleteById(id);
        return Course.listAll(Sort.ascending("id"));
    }
}

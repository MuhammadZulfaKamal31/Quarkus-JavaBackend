package org.acme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("tasks")
public class TaskResource {

    @Inject
    EntityManager em;

    public Connection connection = null;
    public Long id = 1L;

    public TaskResource() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/kawahedukasi1", "user",
                    "Agustus31");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @GET
    @Path("typeQuery")
    public List<Task> getTaskTypeQuery() {
        // List<Task> tasks = em.createQuery("SELECT t FROM Task t",
        // Task.class).getResultList();
        List<Task> tasks = em.createNamedQuery("Task.getAllTasks ", Task.class).getResultList();
        return tasks;
    }

    @GET
    @Path("getCuaca")
    public List<Cuaca> getCuaca() {
        List<Cuaca> weathers = em.createQuery("SELECT g FROM Cuaca g", Cuaca.class).getResultList();
        return weathers;
    }

    @GET
    @Path("typedQuery/{id}")
    public Task getTaskById(@PathParam("id") Long kataKunci) {
        // Task task = em.createQuery("SELECT t FROM Task t WHERE t.id = :id",
        // Task.class).setParameter("id", kataKunci)
        // .getSingleResult();
        Task task = em.createNamedQuery("Task.getTaskById", Task.class).setParameter("uy", kataKunci).getSingleResult();
        return task;
    }

    @GET
    @Path("nativeQuery/{id}")
    public Object getTaskNativeQuery(@PathParam("id") Long AngkaTask) {
        Object tasks = em.createNativeQuery("SELECT * FROM tasks WHERE id = :angka", Task.class)
                .setParameter("angka", AngkaTask).getSingleResult();
        return tasks;
    }

    @GET
    @Path("nativeQuery")
    public List<Task> getTaskNativeQuery() {
        List<Task> tasks = em.createNativeQuery("SELECT * from tasks", Task.class).getResultList();
        return tasks;
    }

    @GET
    @Path("nativeObject")
    public List<HashMap<String, Object>> getresult() {
        List<Object[]> tasks = em.createNativeQuery("SELECT * FROM tasks").getResultList();
        List<HashMap<String, Object>> result = new ArrayList<>();
        for (Object[] task : tasks) {
            HashMap<String, Object> isi = new HashMap<>();
            isi.put("id", task[0]);
            isi.put("description", task[1]);
            isi.put("mata_pelajaran", task[2]);
            isi.put("nilai", task[3]);
            isi.put("title", task[4]);
            result.add(isi);
        }
        return result;
    }

    // @POST
    // public List<Task> addTask(Task task) {
    // List<Task> tasks = new ArrayList<>();
    // try {
    // PreparedStatement penambahanTask = connection.prepareStatement(
    // "INSERT INTO tasks(id, tittle, description, mata_pelajaran, nilai)
    // VALUES(?,?,?,?,?)");
    // penambahanTask.setLong(1, id++);
    // penambahanTask.setString(2, task.tittle);
    // penambahanTask.setString(3, task.description);
    // penambahanTask.setString(4, task.mataPelajaran);
    // penambahanTask.setLong(5, task.nilai);
    // penambahanTask.executeUpdate();
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // tasks = em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
    // return tasks;
    // }

    @POST
    @Transactional
    public List<Task> addTask(Task task) {
        List<Task> tasks = new ArrayList<>();
        em.persist(task);
        tasks = em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
        return tasks;
    }

    // @PUT
    // @Path("{id}")
    // public List<Task> updateTask(@PathParam("id") Long id, Task task) {
    // List<Task> tasks = new ArrayList<>();
    // try {
    // PreparedStatement updateTask = connection
    // .prepareStatement(
    // "UPDATE tasks SET tittle = ?, description = ?, mata_pelajaran = ?, nilai = ?
    // WHERE id = ?");

    @GET
    public List<Task> getTASK() {
        List<Task> tasks = em.createQuery("SELECT t FROM Task t ORDER BY t.id", Task.class).getResultList();
        return tasks;
    }

    // updateTask.setLong(5, id);
    // updateTask.setString(1, task.tittle);
    // updateTask.setString(2, task.description);
    // updateTask.setString(3, task.mataPelajaran);
    // updateTask.setLong(4, task.nilai);
    // updateTask.executeUpdate();
    // } catch (SQLException e) {
    // throw new RuntimeException(e);
    // }
    // tasks = em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
    // return tasks;
    // }
    @PUT
    @Path("{id}")
    @Transactional
    public List<Task> updateTask(@PathParam("id") Long id, Task task) {
        List<Task> tasks = new ArrayList<>();

        Task oldTask = em.createQuery("SELECT t FROM Task t WHERE t.id = :id", Task.class).setParameter("id", id)
                .getSingleResult();

        oldTask.tittle = task.tittle;
        oldTask.description = task.description;
        oldTask.mataPelajaran = task.mataPelajaran;
        oldTask.nilai = task.nilai;

        tasks = em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
        return tasks;
    }

    // @DELETE
    // @Path("{id}")
    // public List<Task> updateTask(@PathParam("id") Long id) {
    // List<Task> tasks = new ArrayList<>();
    // try {
    // PreparedStatement updateTask = connection
    // .prepareStatement("DELETE FROM tasks WHERE id = ?");
    // updateTask.setLong(1, id);

    // updateTask.executeUpdate();
    // } catch (SQLException e) {
    // throw new RuntimeException(e);
    // }
    // tasks = em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
    // return tasks;
    // }
    @DELETE
    @Transactional
    @Path("{id}")
    public List<Task> updateTask(@PathParam("id") Long id) {
        List<Task> tasks = new ArrayList<>();
        Task task = em.createQuery("SELECT t FROM Task t WHERE t.id = :angka", Task.class).setParameter("angka", id)
                .getSingleResult();
        em.remove(task);
        tasks = em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
        return tasks;
    }
}

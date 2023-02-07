package org.acme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("weatherr")
public class CuacaResource {

    public Connection connection = null;

    @GET
    public String cuacaResource() {

        try {
            connection = DriverManager.getConnection("jdbc:postgresql:localhost:5432/kawahedukasi1", "user",
                    "Agustus31");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "berhasil";
    }

    @GET
    @Path("kumpulan")
    public List<Cuaca> getWeather() {
        List<Cuaca> weather = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/kawahedukasi1", "user",
                    "Agustus31");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cuaca");
            while (resultSet.next()) {
                Cuaca cuaca = new Cuaca();
                cuaca.id = resultSet.getLong("id");
                cuaca.kota = resultSet.getString("kota");
                cuaca.suhuLow = resultSet.getInt("suhu_low");
                cuaca.suhuHigh = resultSet.getInt("suhu_high");
                cuaca.tanggal = resultSet.getDate("tanggal");
                weather.add(cuaca);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return weather;
    }

    // @POST
    // public Cuaca addWeather(Cuaca cuaca) {
    // try {
    // connection =
    // DriverManager.getConnection("jdbc:postgresql://localhost:5432/kawahedukasi1",
    // "user",
    // "Agustus31");
    // PreparedStatement prepareStatement = connection.prepareStatement(
    // "INSERT INTO cuaca(id, kota, suhu_low, suhu_high, tanggal)
    // VALUES(nextval('hibernate_sequence'),?,?,?,?)");
    // prepareStatement.setString(1, cuaca.kota);
    // prepareStatement.setInt(2, cuaca.suhuLow);
    // prepareStatement.setInt(3, cuaca.suhuHigh);
    // prepareStatement.setDate(4, cuaca.tanggal);
    // prepareStatement.executeUpdate();
    // } catch (Exception e) {
    // throw new RuntimeException(e);
    // }
    // return cuaca;
    // }

    @POST
    public Cuaca addWeather(Cuaca cuaca) {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql:localhost:5432/kawahedukasi1", "user",
                    "Agustus31");
            PreparedStatement prepareStatement = connection.prepareStatement(
                    "INSERT INTO cuaca(id, kota, suhu_low, suhu_high, tanggal) VALUES(nextval('hibernate_sequence'),?,?,?,?)");
            prepareStatement.setString(1, cuaca.kota);
            prepareStatement.setInt(2, cuaca.suhuLow);
            prepareStatement.setInt(3, cuaca.suhuHigh);
            prepareStatement.setDate(4, cuaca.tanggal);
            prepareStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cuaca;
    }
//maaf salah seharus ini bab task yang dihapus
}

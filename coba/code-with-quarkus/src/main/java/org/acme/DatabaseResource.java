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

@Path("database")
public class DatabaseResource {

    public Connection connection = null;

    @GET
    @Path("percobaan")
    public String percobaanDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/kawahedukasi1", "user",
                    "Agustus31");
            int result = 0;

            Statement pernyataan = connection.createStatement();
            result = pernyataan.executeUpdate(" INSERT INTO cuaca VALUES (1,'Bandung', 19, 34, '2022-11-17')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "berhasil";
    }

    @GET
    @Path("kota")
    public List<String> getKota() {
        List<String> kumpulankota = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/kawahedukasi1", "user",
                    "Agustus31");
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT kota FROM cuaca");
            while (resultset.next()) {
                kumpulankota.add(resultset.getString("kota"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kumpulankota;
    }

    @GET
    @Path("suhulow")
    public ArrayList<Integer> getSuhuLow() {
        ArrayList<Integer> nilaisuhulow = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/kawahedukasi1", "user",
                    "Agustus31");
            PreparedStatement preparestatement = connection
                    .prepareStatement("SELECT suhu_low FROM cuaca WHERE kota = ?");
            preparestatement.setString(1, "Semarang");
            ResultSet hasil = preparestatement.executeQuery();
            while (hasil.next()) {
                nilaisuhulow.add(hasil.getInt("suhu_low"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nilaisuhulow;
    }

    @POST
    public Weather addWeather(Weather weather) {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/kawahedukasi1", "user",
                    "Agustus31");
            PreparedStatement tambahCuaca = connection.prepareStatement("INSERT INTO cuaca VALUES(?,?,?,?)");
            tambahCuaca.setString(1, weather.kota);
            tambahCuaca.setInt(2, weather.suhuLow);
            tambahCuaca.setInt(3, weather.suhuHigh);
            tambahCuaca.setDate(4, weather.tanggal);
            tambahCuaca.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return weather;
    }
    // public Connection connection =
    // DriverManager.getConnection("jdbc:postgresql://localhost:5432/kawaheduksi1",
    // "postgres", "Agustus31");

    @GET
    public String testing() {
        return "dadad";
    }
}

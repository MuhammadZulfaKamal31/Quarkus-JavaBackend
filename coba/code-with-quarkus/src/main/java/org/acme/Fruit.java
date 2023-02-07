package org.acme;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Fruit {

    @NotBlank(message = "nama tidak boleh kosong")
    public String name;

    @NotBlank(message = "warnanya tidak boleh kosong")
    public String color;

    @Max(value = 100, message = "jumlah buah terlalu banyak, maksimal 100")
    @NotNull(message = "total tidak boleh kosong")
    public Integer total;

    public void fruit() {

    }

    public Fruit(String name, String color, Integer total) {
        this.name = name;
        this.color = color;
        this.total = total;
    }
}

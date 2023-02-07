package org.acme;

import javax.ws.rs.Path;
import javax.ws.rs.GET;

@Path("optimus")
public class MauAPA {

    @GET
    public Integer testing1() {
        Integer varA = 3;
        Integer varB = 40;

        return varA + varB;

    }
}

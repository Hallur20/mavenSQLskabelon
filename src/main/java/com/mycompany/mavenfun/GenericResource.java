/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenfun;

import com.google.gson.Gson;
import deploy.DeploymentConfiguration;
import facade.factory;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
/**
 * REST Web Service
 *
 * @author hvn15
 */
@Path("hey")
public class GenericResource {
    @Context
    private UriInfo context;
    private factory f;
    private Gson gson;
    


    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
        f = new factory();
        f.addEntityManagerFactory(Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME));
        gson = new Gson();
    }

    @GET
    @Path("hallur")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHallurString() {
        //TODO return proper representation object
        return gson.toJson("hallur");
    }

    @GET
    @Path("getSqlInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSQL() {

        return gson.toJson(f.getSql());
    }
}

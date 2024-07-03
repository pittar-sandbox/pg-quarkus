package ca.pitt.demo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@Path("/persons")
public class PersonResource {
    
    @Inject
    PersonRepository personRepository;

    @GET
    @Path("/{id}")
    public Person get(Long id) {
        return Person.findById(id);
    }

    @POST
    @Transactional
    public Response create(Person person) {
        System.err.println("Create a person");
        person.persist();
        return Response.created(URI.create("/" + person.getId())).build();
    }

    @GET
    @Path("/count")
    public Long count() {
        return Person.count();
    }
}

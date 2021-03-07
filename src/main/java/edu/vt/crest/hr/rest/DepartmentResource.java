package edu.vt.crest.hr.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import edu.vt.crest.hr.entity.DepartmentEntity;
import edu.vt.crest.hr.services.DepartmentService;

/**
 * Serves as a RESTful endpoint for manipulating DepartmentEntity(s)
 */
@Stateless
@Path("/departments")
public class DepartmentResource {

	//Used to interact with DepartmentEntity(s)
	@Inject
	DepartmentService departmentService;

	/**
	 * Endpoint to create department
	 * @param department the DepartmentEntity to create
	 * @return a Response containing the new DepartmentEntity
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(DepartmentEntity department) {
		try {
			DepartmentEntity createdDepartment = departmentService.createDepartment(department);
			if(createdDepartment != null)
			{ 
				return Response.ok(createdDepartment).build();
			}
			else throw new Exception();
		}
		catch(Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	/**
	 * Endpoint to find department by id
	 * @param id of the DepartmentEntity to return
	 * @return a Response containing the matching DepartmentEntity
	 */
	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@PathParam("id") Long id) {
		try {
			return Response.ok(departmentService.findById(id)).build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

	/**
	 * Endpoint to get departments list within given range
	 * @param startPosition the index of the first DepartmentEntity to return
	 * @param maxResult the maximum number of DepartmentEntity(s) to return
	 *                  beyond the startPosition
	 * @return a list of DepartmentEntity(s)
	 * @throws Exception 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<DepartmentEntity> listAll(@QueryParam("start") Integer startPosition,
			@QueryParam("max") Integer maxResult) throws Exception {
		return departmentService.listAll(startPosition, maxResult);
	}

	/**
	 * Endpoint to update a department
	 * @param id the id of the DepartmentEntity to update
	 * @param department the entity used to update
	 * @return a Response containing the updated DepartmentEntity
	 */
	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, DepartmentEntity department) {
		try {
			return Response.ok(departmentService.update(id, department)).build();
			}
		catch(Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
			}
	}

}
package com.sample.hellomgmt.service.rest.resource;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sample.workflow.interfaces.WorkflowProxyService;



@Path("/workflow")
@RequestScoped
public class WorkflowResource {
	
	@EJB
	private WorkflowProxyService jbpmProxy;

     
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{workFlowProcessId}/{name}")
    public Response startProcessById(@PathParam("workFlowProcessId") String workFlowProcessId, @PathParam("name") String name) {
        if(workFlowProcessId == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
        	jbpmProxy.startProcess(workFlowProcessId, name);
        	
//        	TaskService taskService = jbpmProxy.getTaskService();
//        	
//    		// let john execute Task 1
//    		List<TaskSummary> list = taskService.getTasksAssignedAsPotentialOwner("john", "en-UK");
//    		TaskSummary task = list.get(0);
//    		System.out.println("John is executing task " + task.getName());
//    		taskService.start(task.getId(), "john");
//    		taskService.complete(task.getId(), "john", null);
//
//    		// let mary execute Task 2
//    		list = taskService.getTasksAssignedAsPotentialOwner("mary", "en-UK");
//    		task = list.get(0);
//    		System.out.println("Mary is executing task " + task.getName());
//    		taskService.start(task.getId(), "mary");
//    		taskService.complete(task.getId(), "mary", null);
			
			return Response.ok(Response.Status.CREATED).build();
        }
    }
}

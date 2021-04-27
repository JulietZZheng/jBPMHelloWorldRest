package com.sample.workflow;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.api.task.TaskService;

import com.sample.workflow.interfaces.WorkflowProxyService;


@Startup
@Singleton
@Local(WorkflowProxyService.class)
public class StartupBean implements WorkflowProxyService {
//    public static final String DEPLOYMENT_ID = "com.sample.workflows:hello-mgmt-workflow:1.0.0-SNAPSHOT";
//    
//    @EJB
//    private DeploymentServiceEJBRemote deploymentService;
    
//    @EJB
//    private ProcessServiceEJBRemote processService;
	
	KieSession ksession;
	TaskService taskService;

    @PostConstruct
    public void init() {
//    	String[] gav = DEPLOYMENT_ID.split(":"); // Splits into group, artifact, and version 
//
//    	deploymentService.deploy(gav[0], gav[1], gav[2]);
    	
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieBase kbase = kContainer.getKieBase("kbase");

		RuntimeManager manager = createRuntimeManager(kbase);
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		ksession = engine.getKieSession();
		taskService = engine.getTaskService();
    	
    }
    
	private RuntimeManager createRuntimeManager(KieBase kbase) {
		//PersistenceUtil.setupPoolingDataSource(PersistenceUtil.getDatasourceProperties(), "java:/jdbc/__jbpm");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.jbpm.domain");
		RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get()
			.newDefaultBuilder().entityManagerFactory(emf)
			.knowledgeBase(kbase);
		return RuntimeManagerFactory.Factory.get()
			.newSingletonRuntimeManager(builder.get(), "com.sample:example:1.0");
	}
    
    @Override
	public Long startProcess(String processId, String name) {
    	long processInstanceId = -1;
//    	Map<String, Object> params = new HashMap<String, Object>();
//    	processInstanceId = processService.startProcess(StartupBean.DEPLOYMENT_ID, processId, params);
    	
    	//ksession.startProcess(processId);
    	
		// KieSession ksession = createRuntimeManager("quickstarts/ScriptTask.bpmn").getRuntimeEngine(null).getKieSession();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("person", new Person("krisv"));
		ksession.startProcess(processId, params);
    	
    	System.out.println("Process instance " + processInstanceId + " has been successfully started.");
    	
    	return processInstanceId;
    }

}
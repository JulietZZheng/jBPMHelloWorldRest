package com.sample.hellomgmt.service.rest;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.sample.hellomgmt.service.rest.resource.WorkflowResource;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class HelloMgmtServiceApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(WorkflowResource.class);
        return s;
    }

}

package com.kyeeego.stepikwebserver.jmx.resources;

import com.kyeeego.stepikwebserver.resources.ResourceServer;

public class ResourceServerController implements ResourceServerControllerMBean {

    @Override
    public String getName() {
        return ResourceServer.instance().getTestResource().getName();
    }

    @Override
    public int getAge() {
        return ResourceServer.instance().getTestResource().getAge();
    }
}

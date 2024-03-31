package com.kyeeego.stepikwebserver.resources;

import com.kyeeego.stepikwebserver.models.TestResource;
import com.kyeeego.stepikwebserver.utils.xml.XMLSAXReader;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceServer {

    private static ResourceServer self;

    public static ResourceServer instance() {
        if (self == null) {
            self = new ResourceServer();
        }

        return self;
    }

    private ResourceServer() {
    }

    public void readFromFile(String path) {
        testResource = XMLSAXReader.readXML(path);
    }

    @Getter
    private TestResource testResource = new TestResource();
}

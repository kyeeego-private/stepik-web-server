package com.kyeeego.stepikwebserver.utils.xml;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

@Slf4j
public class SaxHandler extends DefaultHandler {

    private static final String CLASSNAME = "class";
    private String element = null;

    @Getter
    private Object object = null;

    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (!qName.equals(CLASSNAME)) {
            element = qName;
        } else {
            var className = attributes.getValue(0);
            try {
                object = Class.forName(className).getConstructor().newInstance();
            } catch (Exception e) {
                log.error("Unable to instantiate class " + className + ": " + e);
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        element = null;
    }

    public void characters(char ch[], int start, int length) {
        if (element != null) {
            var value = new String(ch, start, length);
            try {
                var f = object.getClass().getDeclaredField(element);
                f.setAccessible(true);

                if (f.getType().isPrimitive()) {
                    f.set(object, Integer.parseInt(value));
                } else {
                    f.set(object, value);
                }

            } catch (NoSuchFieldException e) {
                log.error("No such field: " + element);
            } catch (IllegalAccessException e) {
                log.error("Not allowed to access field: " + element);
            }
        }
    }
}
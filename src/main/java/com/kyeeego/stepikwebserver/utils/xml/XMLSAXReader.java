package com.kyeeego.stepikwebserver.utils.xml;

import lombok.extern.slf4j.Slf4j;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

@Slf4j
public class XMLSAXReader {

    public static <T> T readXML(String xmlFile) {
        try {
            var factory = SAXParserFactory.newInstance();
            var saxParser = factory.newSAXParser();

            var handler = new SaxHandler();
            saxParser.parse(xmlFile, handler);

            return (T) handler.getObject();

        } catch (Exception e) {
            log.error("Error while parsing XML: " + e);
        }

        return null;
    }
}

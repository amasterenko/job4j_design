package ru.job4.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class ToFromXML {
    public static void main(String[] args) throws JAXBException, IOException {
        Folder folder = new Folder("test", 1220, false, new User("username", 4039), "read", "ready", "hidden");
        System.out.println(folder);
        JAXBContext context = JAXBContext.newInstance(Folder.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(folder, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Folder resFolder = (Folder) unmarshaller.unmarshal(reader);
            System.out.println(resFolder);
        }
    }
}

package com.example.xml_processing.constants;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public enum Utils {
    ;

    public static <T>void writeXmlIntoFile(T object, Path filePath) throws IOException, JAXBException {
        File file = filePath.toFile();

        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(object, file);
    }
}

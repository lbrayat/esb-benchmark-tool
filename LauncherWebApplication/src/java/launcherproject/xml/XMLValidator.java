package launcherproject.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class XMLValidator {

    public static boolean validateXMLFile(String xsdPath, String xmlPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            try {
                validator.validate(new StreamSource(new File(xmlPath)));
            } catch (IOException ex) {
                System.out.println("Exception: "+ex.getMessage());
                return false;
            }
        } catch (SAXException ex) {
            System.out.println("Exception: "+ex.getMessage());
            return false;
        }
        return true;
    }
}

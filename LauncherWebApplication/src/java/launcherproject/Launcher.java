/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcherproject;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.*;
import javax.jws.WebParam;

/**
 *
 * @author admin
 */
public class Launcher {

    private static String inputFilePath;
    private static final Logger logger = Logger.getLogger("EBTLauncher");
    private static String SCHEMA_PATH = "schemas/scenario.xsd";
    private Scenario scenario;

    public Launcher (String filePath) {
        inputFilePath = filePath;
    }

    /**
     * @return the inputFilePath
     */
    public static String getInputFilePath() {
        return inputFilePath;
    }

    /**
     * @param aInputFilePath the inputFilePath to set
     */
    public static void setInputFilePath(String aInputFilePath) {
        inputFilePath = aInputFilePath;
    }

    /**
     * @return the scenario
     */
    public Scenario getScenario() {
        return scenario;
    }

    /**
     * @param scenario the scenario to set
     */
    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public Boolean checkResourcesAvailability() {

        for (ProviderClient p : scenario.getProviderList()) {
            if (isWSDLAvailable(p.getURL().toString()) == false) {
                return false;
            }
        }
        for (ConsumerClient c : scenario.getConsumerList()) {
            if (isWSDLAvailable(c.getURL().toString()) == false) {
                return false;
            }
        }
        return true;
    }

    public Boolean isWSDLAvailable(String wsdlAddr) {
        
        Integer timeout = 100;
        wsdlAddr = wsdlAddr.replaceFirst("https", "http"); // Otherwise an exception may be thrown on invalid SSL certificates.
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(wsdlAddr).openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            /* System.out.println("Response Code : "+responseCode); */
            return (responseCode==HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connexion Out : "+e);
            return false;
        }
    }

    private static void launchScenario (Scenario scenario, Logger logger) {

        System.out.println("Launching scenario...");
        logger.log(Level.INFO, "Launcher : start");

        // Configure all Providers
        System.out.println("Configuring all Providers...");
        for (ProviderClient iProvider : scenario.getProviderList()) {
            iProvider.configure();
        }

        // Configure all Consumer
        System.out.println("Configuring all Consumers...");
        for (ConsumerClient iConsumer : scenario.getConsumerList()) {
            iConsumer.configure();
        }

        // Start all Providers
        System.out.println("Starting all Providers...");
        for (ProviderClient iProvider : scenario.getProviderList()) {
            iProvider.callStart();
        }

        // Start all Consumers
        System.out.println("Starting all Consumers...");
        for (ConsumerClient iProvider : scenario.getConsumerList()) {
            iProvider.callStart();
        }

        logger.log(Level.INFO, "Launcher : complete");
        System.out.println("End of the simulation");
    }

    private static boolean validateScenario (File xmlFile) {
        File xsdFile;
        boolean valid = false;
        xsdFile = new File(SCHEMA_PATH); 
        valid  = launcherproject.xml.XMLValidator.validateXMLFile(SCHEMA_PATH, xmlFile.getAbsolutePath());
        System.out.println("Inuput file valid against scenario.xsd ? " + valid);
        return valid;
    }

    public void runLauncher () {

        File xmlFile = new File(getInputFilePath());

        System.out.println("XML File exists? " + xmlFile.exists());
        if (xmlFile.exists()) {
            if (validateScenario(xmlFile)) {
                // Initialize the scenario from XML config file
                scenario = new Scenario(xmlFile.getAbsolutePath());
                System.out.println("Resources available ?" +checkResourcesAvailability());
                if (checkResourcesAvailability()) {
                    launchScenario (scenario, logger);
                }
                else {
                    System.out.println("Resources are not available, the scenario can not be simulated.");
                    return;
                }
            }
            else {
                System.out.println("The XML file is not valid, the scenario can not be parsed.");
                return;
            }
        }
        else {
                System.out.println("Sorry, the XML file does not exist.");
        }

    }

    public void writeOutputFile (String results, String OutputfilePath) {

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(OutputfilePath));
            out.write(results);
            out.close();
        } catch (IOException iox) {
            // do stuff with exception
            iox.printStackTrace();
        }
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcherproject;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lbrayat
 */
public class Main {

    private static final Logger logger = Logger.getLogger("Launcher");
    private static final String FILE_PATH = "/home/marc/NetBeansProjects/esb-benchmark-tool/scenario.xml";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        logger.log(Level.INFO, "Launcher : start");

        File f = new File(FILE_PATH);
        System.err.println(f.exists());

        // Initialize the scenario from XML config file
        Scenario scenario = new Scenario(f.getAbsolutePath());

        // Check that all Consumer responds

        // Configure all Providers
        for (ProviderClient iProvider : scenario.getProviderList()) {
            iProvider.configure();
        }

        // Configure all Consumer
        for (ConsumerClient iConsumer : scenario.getConsumerList()) {
            iConsumer.configure();
        }

       // Start all Providers
        for (ProviderClient iProvider : scenario.getProviderList()) {
            iProvider.callStart();
        }

        // Start all Consumers
        for (ConsumerClient iProvider : scenario.getConsumerList()) {
            iProvider.callStart();
        }
        logger.log(Level.INFO, "Launcher : complete");
    }

}

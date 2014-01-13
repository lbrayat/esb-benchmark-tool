/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcherproject;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import launcherproject.results.ResultsSingletonFactory;

/**
 *
 * @author lbrayat
 */
public class Main {

    private static final Logger logger = Logger.getLogger("Launcher");
    private static final String FILE_PATH = "/home/marc/NetBeansProjects/esb-benchmark-tool/scenarioMarc.xml";

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
        for (ConsumerClient iConsumer : scenario.getConsumerList()) {
            iConsumer.callStart();
        }
        logger.log(Level.INFO, "Launcher : complete");

        logger.log(Level.INFO, "Waiting for the end of the simulation");
        try {
            Thread.sleep(2 * 60 * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        logger.log(Level.INFO, "Simulation ended");

        logger.log(Level.INFO, "fetching results of each provider and consumer");

               // Start all Providers
        for (ProviderClient iProvider : scenario.getProviderList()) {
            String results = iProvider.callGetResults(true);

            while (!results.isEmpty()){
                results = iProvider.callGetResults(false);
            }
        }

        // Start all Consumers
        for (ConsumerClient iConsumer : scenario.getConsumerList()) {
            String results = iConsumer.callGetResults(true);

            while (!results.isEmpty()){
                results = iConsumer.callGetResults(false);
            }
        }

        logger.log(Level.INFO, "Done fetching results of each provider and consumer");

        logger.log(Level.INFO, "Writing to file results of each provider and consumer");
        ResultsSingletonFactory.getInstance().writeResultsToFile();


    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcherproject.results;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import launcherproject.ConsumerClient;
import launcherproject.MainCLI;
import launcherproject.ProviderClient;
import launcherproject.webservice.FinishLineWS;

/**
 *
 * @author marc
 */
public class ResultsFetcher{

        private Logger logger = Logger.getLogger(ResultsFetcher.class.getName());

        List<ProviderClient> providerList;
        List<ConsumerClient> consumerList;

        public ResultsFetcher(List<ProviderClient> providerList, List<ConsumerClient> consumerList) {
            this.providerList = providerList;
            this.consumerList = consumerList;
            logger.info("Consumer List is set, size : "+consumerList.size());
        }

        public ResultsFetcher() {
            this.providerList=new ArrayList<ProviderClient>();
            this.consumerList = new ArrayList<ConsumerClient>();
        }






        public void fetch(int numberOfConsumerDone){

           logger.log(Level.INFO, "Fetch is called (consumerDone/totalConsumers) "+numberOfConsumerDone+"/"+consumerList.size());


            if (numberOfConsumerDone == consumerList.size()){
                
                // now fetch the results (Ernesto est mon modèle)
                logger.log(Level.INFO, "fetching results of each provider and consumer");

                       // Start all Providers
                for (ProviderClient iProvider : providerList) {
                    String results = iProvider.callGetResults(true);

                    while (!results.isEmpty()){
                        results = iProvider.callGetResults(false);
                    }
                }

                // Start all Consumers
                for (ConsumerClient iConsumer : consumerList) {
                    String results = iConsumer.callGetResults(true);

                    while (!results.isEmpty()){
                        results = iConsumer.callGetResults(false);
                    }
                }

                logger.log(Level.INFO, "Done fetching results of each provider and consumer");

                logger.log(Level.INFO, "Writing to file results of each provider and consumer ");
                ResultsSingletonFactory.getInstance().writeResultsToFile();
            }
        }

    }
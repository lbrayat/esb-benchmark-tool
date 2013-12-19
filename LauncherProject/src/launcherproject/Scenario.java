/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcherproject;

import java.util.ArrayList;
import java.util.List;
import launcherproject.xml.Parser;
import launcherproject.xml.ScenarioXML;
import launcherproject.xml.ScenarioXML.Actor;
import launcherproject.xml.ScenarioXML.Consumer;
import launcherproject.xml.ScenarioXML.PhaseConsumer;
import launcherproject.xml.ScenarioXML.PhaseProvider;
import launcherproject.xml.ScenarioXML.Provider;

/**
 * Interface between XML objects and WS Client objects
 * @author lbrayat
 */
public class Scenario {

    private List<ConsumerClient> mConsumersClientList;
    private List<ProviderClient> mProvidersClientList;

    /**
     * Initialize WS Client classes from XML scenario
     * @param scenarioXML
     */
    public Scenario(String aXmlPath) {

        super();

        mConsumersClientList = new ArrayList<ConsumerClient>();
        mProvidersClientList = new ArrayList<ProviderClient>();

        loadConfigFile(aXmlPath);
    }

    private String getActorWSDL(ScenarioXML scenario, String identifier) {

        for (Actor actor : scenario.getConfigurationXML().getListActors()) {
            if (actor.getIdentifier().equals(identifier)) {
                return actor.getAddress();
            }
        }

        return "";
    }

    public void loadConfigFile(String configFile) {

        ScenarioXML scenario = new ScenarioXML ();
        String fileName = configFile;
        System.err.println(fileName);
        Parser parser = new Parser(fileName);
        parser.doParse();
        scenario.setConfigurationXML(Parser.getConfigurationXML());
        scenario.setConsumersXML(Parser.getConsumersXML());
        scenario.setProvidersXML(Parser.getProvidersXML());

        // Initialize the ConsumerClient list

        for (Consumer consumerXML : scenario.getConsumersXML()) {
            
            ConsumerClient consumer = new ConsumerClient(
                                            Integer.parseInt(consumerXML.getIdentifier()),
                                            getActorWSDL(scenario, consumerXML.getIdentifier())
                                            );

            int nbPhase = 1;
            for (PhaseConsumer phase : consumerXML.getListPhases()) {
                consumer.addPhase(
                        getActorWSDL(scenario, phase.getProviderId()),
                        nbPhase, 
                        phase.getNumberOfRequests(),
                        phase.getSendPeriod(),
                        phase.getPacketSize(),
                        phase.getStartDate(),
                        phase.getNumberOfBursts()
                        );
                
                nbPhase++;
            }
            
            mConsumersClientList.add(consumer);
        }

        // Initialize the ProviderClient list

        for (Provider providerXML : scenario.getProvidersXML()) {

            ProviderClient provider = new ProviderClient(
                                            Integer.parseInt(providerXML.getIdentifier()),
                                            getActorWSDL(scenario, providerXML.getIdentifier())
                                            );

            int nbPhase = 1;
            for (PhaseProvider phase : providerXML.getListPhases()) {
                provider.addPhase(
                            nbPhase,
                            phase.getProcessingTime(),
                            phase.getPacketSize(),
                            phase.getPercentageLoss(),
                            phase.getEndDate()
                        );

                nbPhase++;
            }

            mProvidersClientList.add(provider);
        }
    }

    public List<ConsumerClient> getConsumerList() {
        return mConsumersClientList;
    }

    public List<ProviderClient> getProviderList() {
        return mProvidersClientList;
    }

}

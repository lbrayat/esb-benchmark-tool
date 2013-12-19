/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcherproject.xml;

import java.io.*;
import org.jdom2.*;
import org.jdom2.input.*;
import java.util.List;
import java.util.Iterator;

/**
 *
 * @author admin
 */
public class JDOM2 {

    private static Document document;
    private static Element racine;
    private static Parser parser;

    public JDOM2 (Parser parser)
    {
        // Création d'une instance de SAXBuilder
        SAXBuilder sxb = new SAXBuilder();
        try
        {
            document = sxb.build(new File(parser.getFilePath()));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        // On initialise un nouvel élément racine avec l'élément racine du document.
        racine = document.getRootElement();
        JDOM2.setParser(parser);
    }
    static void handleConfiguration ()
    {
	Element eConfiguration = racine.getChild("configuration");
	List actorList = eConfiguration.getChildren("actor");
	Iterator i = actorList.iterator();
	while (i.hasNext()) {
            Element currentActor = (Element)i.next();
            ScenarioXML.Actor emptyActor = new ScenarioXML().new Actor ();
            emptyActor.setIdentifier(currentActor.getAttributeValue("identifier"));
            emptyActor.setTypeOfActor(ScenarioXML.TypeOfActor.ParseTypeOfActor(currentActor.getChild("type").getText()));
            emptyActor.setAddress(currentActor.getChild("address").getText());
            Parser.getConfigurationXML().getListActors().add(emptyActor);
        }
    }
    static void handleConsumers ()
    {
	Element eConsumers = racine.getChild("consumers");
        List consumerList = eConsumers.getChildren("consumer");
        Iterator i = consumerList.iterator();
	while (i.hasNext()) {
            Element currentConsumer = (Element)i.next();
            ScenarioXML.Consumer emptyConsumer = new ScenarioXML().new Consumer();
            emptyConsumer.setIdentifier(currentConsumer.getAttributeValue("identifier"));
            List phaseList = currentConsumer.getChildren("phaseConsumer");
            Iterator j = phaseList.iterator();
            while (j.hasNext()) {
                Element currentPhase = (Element)j.next();
                ScenarioXML.PhaseConsumer emptyPhaseConsumer = new ScenarioXML().new PhaseConsumer ();
                emptyPhaseConsumer.setProviderId(currentPhase.getChild("providerId").getText());
                emptyPhaseConsumer.setTrafficClass(ScenarioXML.TrafficClass.ParseTrafficClass(currentPhase.getChild("trafficClass").getText()));
                emptyPhaseConsumer.setNumberOfRequests(Integer.parseInt(currentPhase.getChild("numberOfRequests").getText()));
                emptyPhaseConsumer.setSendPeriod(Integer.parseInt(currentPhase.getChild("sendPeriod").getText()));
                emptyPhaseConsumer.setPacketSize(Integer.parseInt(currentPhase.getChild("packetSize").getText()));
                emptyPhaseConsumer.setStartDate(Long.parseLong(currentPhase.getChild("startDate").getText()));
                emptyPhaseConsumer.setNumberOfBursts(Integer.parseInt(currentPhase.getChild("numberOfBursts").getText()));
                emptyConsumer.addPhase(emptyPhaseConsumer);
            }
            Parser.getConsumersXML().add(emptyConsumer);
        }
    }
    static void handleProviders ()
    {
	Element eProviders = racine.getChild("providers");
        List providerList = eProviders.getChildren("provider");
        Iterator i = providerList.iterator();
	while (i.hasNext()) {
            Element currentProvider = (Element)i.next();
            ScenarioXML.Provider emptyProvider = new ScenarioXML ().new Provider();
            emptyProvider.setIdentifier(currentProvider.getAttributeValue("identifier"));
            List phaseList = currentProvider.getChildren("phaseProvider");
            Iterator j = phaseList.iterator();
            while (j.hasNext()) {
                Element currentPhase = (Element)j.next();
                ScenarioXML.PhaseProvider emptyPhaseProvider= new ScenarioXML().new PhaseProvider ();
                emptyPhaseProvider.setProcessingTime(Integer.parseInt(currentPhase.getChild("processingTime").getText()));
                emptyPhaseProvider.setNumberOfResponses(Integer.parseInt(currentPhase.getChild("numberOfResponses").getText()));
                emptyPhaseProvider.setPacketSize(Integer.parseInt(currentPhase.getChild("packetSize").getText()));
                emptyPhaseProvider.setPercentageLoss(Integer.parseInt(currentPhase.getChild("percentageLoss").getText()));
                emptyPhaseProvider.setStartDate(Long.parseLong(currentPhase.getChild("startDate").getText()));
                emptyPhaseProvider.setEndDate(Long.parseLong(currentPhase.getChild("endDate").getText()));
                emptyProvider.addPhase(emptyPhaseProvider);
            }
            Parser.getProvidersXML().add(emptyProvider);
        }
    }
    public static Parser getParser() {
        return parser;
    }

    public static void setParser(Parser parser) {
	JDOM2.parser = parser;
    }

}
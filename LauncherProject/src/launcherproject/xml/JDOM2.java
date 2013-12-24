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

    private Document document;
    private Element racine;
    private Parser parser;

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
        this.setParser(parser);
    }

    public void handleConfiguration ()
    {
	Element eConfiguration = racine.getChild("configuration");
	List actorList = eConfiguration.getChildren("actor");
	Iterator i = actorList.iterator();
	while (i.hasNext()) {
            Element currentActor = (Element)i.next();
            Actor emptyActor = new Actor ();
            emptyActor.setIdentifier(currentActor.getAttributeValue("identifier"));
            emptyActor.setTypeOfActor(TypeOfActor.ParseTypeOfActor(currentActor.getChild("type").getText()));
            emptyActor.setAddress(currentActor.getChild("address").getText());
            parser.getConfigurationXML().getListActors().add(emptyActor);
        }
    }
    public void handleConsumers ()
    {
	Element eConsumers = racine.getChild("consumers");
        List consumerList = eConsumers.getChildren("consumer");
        Iterator i = consumerList.iterator();
	while (i.hasNext()) {
            Element currentConsumer = (Element)i.next();
            Consumer emptyConsumer = new Consumer();
            emptyConsumer.setIdentifier(currentConsumer.getAttributeValue("identifier"));
            List phaseList = currentConsumer.getChildren("phaseConsumer");
            Iterator j = phaseList.iterator();
            while (j.hasNext()) {
                Element currentPhase = (Element)j.next();
                PhaseConsumer emptyPhaseConsumer = new PhaseConsumer ();
                emptyPhaseConsumer.setProviderId(currentPhase.getChild("providerId").getText());
                emptyPhaseConsumer.setTargetedProviderConf(Integer.parseInt(currentPhase.getChild("targetedProviderConf").getText()));
                emptyPhaseConsumer.setTrafficClass(TrafficClass.ParseTrafficClass(currentPhase.getChild("trafficClass").getText()));
                emptyPhaseConsumer.setNumberOfRequests(Integer.parseInt(currentPhase.getChild("numberOfRequests").getText()));
                emptyPhaseConsumer.setSendPeriod(Integer.parseInt(currentPhase.getChild("sendPeriod").getText()));
                emptyPhaseConsumer.setPacketSize(Integer.parseInt(currentPhase.getChild("packetSize").getText()));
                emptyPhaseConsumer.setStartDate(Long.parseLong(currentPhase.getChild("startDate").getText()));
                emptyPhaseConsumer.setNumberOfBursts(Integer.parseInt(currentPhase.getChild("numberOfBursts").getText()));
                emptyConsumer.addPhase(emptyPhaseConsumer);
            }
            parser.getConsumersXML().add(emptyConsumer);
        }
    }
    public void handleProviders ()
    {
	Element eProviders = racine.getChild("providers");
        List providerList = eProviders.getChildren("provider");
        Iterator i = providerList.iterator();
	while (i.hasNext()) {
            Element currentProvider = (Element)i.next();
            Provider emptyProvider = new Provider();
            emptyProvider.setIdentifier(currentProvider.getAttributeValue("identifier"));
            List phaseList = currentProvider.getChildren("phaseProvider");
            Iterator j = phaseList.iterator();
            while (j.hasNext()) {
                Element currentPhase = (Element)j.next();
                PhaseProvider emptyPhaseProvider= new PhaseProvider();
                emptyPhaseProvider.setConfId(Integer.parseInt(currentPhase.getAttributeValue("id")));
                emptyPhaseProvider.setProcessingTime(Integer.parseInt(currentPhase.getChild("processingTime").getText()));
                emptyPhaseProvider.setPacketSize(Integer.parseInt(currentPhase.getChild("packetSize").getText()));
                emptyPhaseProvider.setPercentageLoss(Integer.parseInt(currentPhase.getChild("percentageLoss").getText()));
                emptyProvider.addPhase(emptyPhaseProvider);
            }
            parser.getProvidersXML().add(emptyProvider);
        }
    }
    public Parser getParser() {
        return this.parser;
    }

    public void setParser(Parser parser) {
	this.parser = parser;
    }

}
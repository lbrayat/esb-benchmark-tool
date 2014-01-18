/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcherproject.convertor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author 19012831
 */
public class RawDataConvertor {

    private Map<String, MessageData> mMessageDatasList = new HashMap<String, MessageData>();
    private int nbIncompleteMsg;

    private Map<String, List<Integer>> mConsumerList = new HashMap<String, List<Integer>>();
    private Map<String, List<Integer>> mProviderList = new HashMap<String, List<Integer>>();

    public boolean load(String aRowFile) throws FileNotFoundException, IOException {

        mMessageDatasList.clear();
        nbIncompleteMsg = 0;

        BufferedReader br = new BufferedReader(new FileReader(aRowFile));
        try {
            String line = br.readLine();

            while (line != null) {
                addOrUpdate(line);
                line = br.readLine();
            }
        } finally {
            br.close();
        }

        return true;
    }

    private boolean addOrUpdate(String line) {
        MessageData msg = new MessageData();
        if (!msg.load(line)) {
           System.err.println("Incorrect line : " + line);
           nbIncompleteMsg++;
           return false;
        }

        if (mMessageDatasList.containsKey(msg.fullMessageId)) {
            mMessageDatasList.get(msg.fullMessageId).updateWith(msg);
        }
        else {
            mMessageDatasList.put(msg.fullMessageId, msg);
        }

        addOrUpdateConsumer(msg);
        addOrUpdateProvider(msg);

        return true;
    }

    private void addOrUpdateConsumer(MessageData msg) {

        if (!mConsumerList.containsKey(msg.consumerId)) {
            mConsumerList.put(msg.consumerId, new ArrayList<Integer>());
        }

        List<Integer> list = mConsumerList.get(msg.consumerId);
        if ((msg.consumerPhaseId != 0) && !list.contains(msg.consumerPhaseId)) {
            list.add(msg.consumerPhaseId);
        }
    }


    private void addOrUpdateProvider(MessageData msg) {

        if (!mProviderList.containsKey(msg.providerId)) {
            mProviderList.put(msg.providerId, new ArrayList<Integer>());
        }

        List<Integer> list = mProviderList.get(msg.providerId);
        if ((msg.providerConfId != 0) && !list.contains(msg.providerConfId)) {
            list.add(msg.providerConfId);
        }
    }

    private boolean writeRawAsCSV(File aCSVFile) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(aCSVFile));

        writer.write("Message ID;TS1;TS2;TS3;TS4;Consumer ID;Consumer Phase ID;Provider ID;Provider Conf ID;Payload Question;Payload Question Length;Payload Response;Payload Response Length\n");

        for(String msgId : mMessageDatasList.keySet()) {
            writer.write(mMessageDatasList.get(msgId).toCSV() + "\n");
        }

        writer.close();

        return true;
    }

    private boolean writeRawAsXML(File aXMLFile) {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("rawdatas");
            doc.appendChild(rootElement);

            // Pour chaque message
            for(String msgId : mMessageDatasList.keySet()) {
                
                // Creation du noeud
                Element messageNode = doc.createElement("message");
                rootElement.appendChild(messageNode);

                // Ajout de l'ID
                messageNode.setAttribute("id", mMessageDatasList.get(msgId).fullMessageId);

                // ts1
                Element ts1 = doc.createElement("ts1");
                ts1.appendChild(doc.createTextNode(String.valueOf(mMessageDatasList.get(msgId).ts1)));
                messageNode.appendChild(ts1);

                // ts2
                Element ts2 = doc.createElement("ts2");
                ts2.appendChild(doc.createTextNode(String.valueOf(mMessageDatasList.get(msgId).ts2)));
                messageNode.appendChild(ts2);

                // ts3
                Element ts3 = doc.createElement("ts3");
                ts3.appendChild(doc.createTextNode(String.valueOf(mMessageDatasList.get(msgId).ts3)));
                messageNode.appendChild(ts3);

                // ts4
                Element ts4 = doc.createElement("ts4");
                ts4.appendChild(doc.createTextNode(String.valueOf(mMessageDatasList.get(msgId).ts4)));
                messageNode.appendChild(ts4);

                // providerConfId
                Element providerConfId = doc.createElement("providerConfId");
                providerConfId.appendChild(doc.createTextNode(String.valueOf(mMessageDatasList.get(msgId).providerConfId)));
                messageNode.appendChild(providerConfId);

                // payloadGo
                Element payloadGo = doc.createElement("payloadGo");
                payloadGo.appendChild(doc.createTextNode(String.valueOf(mMessageDatasList.get(msgId).payloadGo)));
                messageNode.appendChild(payloadGo);

                // payloadBack
                Element payloadBack = doc.createElement("payloadBack");
                payloadBack.appendChild(doc.createTextNode(String.valueOf(mMessageDatasList.get(msgId).payloadBack)));
                messageNode.appendChild(payloadBack);

                // providerId
                Element providerId = doc.createElement("providerId");
                providerId.appendChild(doc.createTextNode(String.valueOf(mMessageDatasList.get(msgId).providerId)));
                messageNode.appendChild(providerId);

                // consumerId
                Element consumerId = doc.createElement("consumerId");
                consumerId.appendChild(doc.createTextNode(String.valueOf(mMessageDatasList.get(msgId).consumerId)));
                messageNode.appendChild(consumerId);

                // consumerPhaseId
                Element consumerPhaseId = doc.createElement("consumerPhaseId");
                consumerPhaseId.appendChild(doc.createTextNode(String.valueOf(mMessageDatasList.get(msgId).consumerPhaseId)));
                messageNode.appendChild(consumerPhaseId);

            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(aXMLFile);

            transformer.transform(source, result);

      } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
            return false;
      } catch (TransformerException tfe) {
            tfe.printStackTrace();
            return false;
      }

      return true;
    }

    private long getFirstPointTS() {
        long result = Long.MAX_VALUE;

        for(String msgId : mMessageDatasList.keySet()) {
            if (mMessageDatasList.get(msgId).getRefTimeStamp() < result) {
                result = mMessageDatasList.get(msgId).getRefTimeStamp();
            }
        }
        return result;
    }

    private long getLastPointTS() {
        long result = 0;

        for(String msgId : mMessageDatasList.keySet()) {
            if (mMessageDatasList.get(msgId).getRefTimeStamp() > result) {
                result = mMessageDatasList.get(msgId).getRefTimeStamp();
            }
        }
        return result;
    }

    /**
     * This function returns a Hashmap containing a list of messages. We divide the
     * total time of the simulation in {@code nbPoints} periods. For each period
     * we give the last timestamp in key associated with the list of messages in this
     * period.
     * @param nbPoints
     * @return
     */
    /*
    private HashMap<Long, List<MessageData>> getMessagesByPeriod(int nbPoints) {

        long lastTS = getLastPointTS();
        long firstTS = getFirstPointTS();
        long period = (lastTS - firstTS) / nbPoints;

        // Create the hashmap for periods
        HashMap<Long, List<MessageData>> messagesByPeriod = new HashMap<Long, List<MessageData>>();


       

        return messagesByPeriod;
    }

    public HashMap<Long, Long> getProcessAverageByPeriod(int nbPoints) {

        HashMap<Long, List<MessageData>> messagesByPeriod = getMessagesByPeriod(nbPoints);
        HashMap<Long, Long> result = new HashMap<Long, Long>();

        for(Entry<Long, List<MessageData>> entry : messagesByPeriod.entrySet()) {

            long average = 0;
            for(MessageData msg : entry.getValue()) {
                average += msg.getTotalTime();
            }
            average = average / entry.getValue().size();

            result.put(entry.getKey(), Long.valueOf(average));
        }

        return result;
    }
     * */

    public void writeKPI(File aXMLFile) throws IOException {

        writeRawAsCSV(new File(aXMLFile.getAbsolutePath() + ".raw.cvs"));
        writeRawAsXML(new File(aXMLFile.getAbsolutePath() + ".raw.xml"));
        writeOnlyKPI(aXMLFile);
    }

    private boolean writeOnlyKPI(File aXMLFile) {

        List<MessageData> messagesList = new ArrayList<MessageData>(mMessageDatasList.values());
        KpiCalculator calculator = new KpiCalculator(messagesList);

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("kpi");
            doc.appendChild(rootElement);

            // Global datas

            Element globalNode = doc.createElement("global");
            rootElement.appendChild(globalNode);

            addGlobalKpi(doc, globalNode, calculator);

            // KPI by consumer phase

            Element consumerPhaseNode = doc.createElement("consumers");
            rootElement.appendChild(consumerPhaseNode);

            addConsumerPhaseKpi(doc, consumerPhaseNode, calculator);

            // KPI by provider conf

            Element providerConfNode = doc.createElement("providers");
            rootElement.appendChild(providerConfNode);

            addProviderConfKpi(doc, providerConfNode, calculator);

            // KPI by couple

            Element coupleNode = doc.createElement("couples");
            rootElement.appendChild(coupleNode);

            addCoupleKpi(doc, coupleNode, calculator);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(aXMLFile);

            transformer.transform(source, result);

      } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
            return false;
      } catch (TransformerException tfe) {
            tfe.printStackTrace();
            return false;
      }

        return true;
    }

    private void addCoupleKpi(Document doc, Element parentNode, KpiCalculator calculator) {

        for(String providerId : mProviderList.keySet()) {

            for(String consumerId : mConsumerList.keySet()) {

                if (calculator.hasStatsForCouple(consumerId, providerId)) {

                    Element coupleNode = doc.createElement("couple");
                    coupleNode.setAttribute("consumerId", consumerId);
                    coupleNode.setAttribute("providerId", providerId);
                    parentNode.appendChild(coupleNode);

                    Element propagationTimeAveragePerCouple = doc.createElement("propagationTimeAverage");
                    propagationTimeAveragePerCouple.appendChild(doc.createTextNode(String.valueOf(calculator.propagationTimeAveragePerCouple(consumerId, providerId))));
                    coupleNode.appendChild(propagationTimeAveragePerCouple);

                    Element propagationTimeStandardDeviationPerCouple = doc.createElement("propagationTimeStandardDeviation");
                    propagationTimeStandardDeviationPerCouple.appendChild(doc.createTextNode(String.valueOf(calculator.propagationTimeStandardDeviationPerCouple(consumerId, providerId))));
                    coupleNode.appendChild(propagationTimeStandardDeviationPerCouple);

                    Element maxPropagationTimePerCouple = doc.createElement("maxPropagationTime");
                    maxPropagationTimePerCouple.appendChild(doc.createTextNode(String.valueOf(calculator.maxPropagationTimePerCouple(consumerId, providerId))));
                    coupleNode.appendChild(maxPropagationTimePerCouple);

                    Element minPropagationTimePerCouple = doc.createElement("minPropagationTime");
                    minPropagationTimePerCouple.appendChild(doc.createTextNode(String.valueOf(calculator.minPropagationTimePerCouple(consumerId, providerId))));
                    coupleNode.appendChild(minPropagationTimePerCouple);

                    Element totalPacketSizePerCouple = doc.createElement("totalPacketSize");
                    totalPacketSizePerCouple.appendChild(doc.createTextNode(String.valueOf(calculator.totalPacketSizePerCouple(consumerId, providerId))));
                    coupleNode.appendChild(totalPacketSizePerCouple);

                    Element averageThroughputTotal = doc.createElement("averageThroughputTotal");
                    averageThroughputTotal.appendChild(doc.createTextNode(String.valueOf(calculator.averageThroughputTotalPerCouple(consumerId, providerId))));
                    coupleNode.appendChild(averageThroughputTotal);
                }
            }
        }
    }

    private void addProviderConfKpi(Document doc, Element parentNode, KpiCalculator calculator) {
        for(String providerId : mProviderList.keySet()) {
            Element providerNode = doc.createElement("provider");
            providerNode.setAttribute("id", providerId);
            parentNode.appendChild(providerNode);

            for(Integer confId : mProviderList.get(providerId)) {
                Element providerConfNode = doc.createElement("conf");
                providerConfNode.setAttribute("id", confId.toString());
                providerNode.appendChild(providerConfNode);

                Element propagationTimeAverageConf = doc.createElement("propagationTimeAverage");
                propagationTimeAverageConf.appendChild(doc.createTextNode(String.valueOf(calculator.propagationTimeAverageConf(providerId, confId))));
                providerConfNode.appendChild(propagationTimeAverageConf);

                Element propagationTimeStandardDeviationConf = doc.createElement("propagationTimeStandardDeviation");
                propagationTimeStandardDeviationConf.appendChild(doc.createTextNode(String.valueOf(calculator.propagationTimeStandardDeviationConf(providerId, confId))));
                providerConfNode.appendChild(propagationTimeStandardDeviationConf);

                Element maxPropagationTimePerConf = doc.createElement("maxPropagationTime");
                maxPropagationTimePerConf.appendChild(doc.createTextNode(String.valueOf(calculator.maxPropagationTimePerConf(providerId, confId))));
                providerConfNode.appendChild(maxPropagationTimePerConf);

                Element minPropagationTimePerConf = doc.createElement("minPropagationTime");
                propagationTimeAverageConf.appendChild(doc.createTextNode(String.valueOf(calculator.minPropagationTimePerConf(providerId, confId))));
                providerConfNode.appendChild(minPropagationTimePerConf);

                Element totalPacketSizeConf = doc.createElement("totalPacketSize");
                propagationTimeAverageConf.appendChild(doc.createTextNode(String.valueOf(calculator.totalPacketSizeConf(providerId, confId))));
                providerConfNode.appendChild(totalPacketSizeConf);

                Element averageThroughputTotalPerConf = doc.createElement("averageThroughputTotal");
                averageThroughputTotalPerConf.appendChild(doc.createTextNode(String.valueOf(calculator.averageThroughputTotalPerConf(providerId, confId))));
                providerConfNode.appendChild(averageThroughputTotalPerConf);

            }
        }
    }

    private void addConsumerPhaseKpi(Document doc, Element parentNode, KpiCalculator calculator) {
        for(String consumerId : mConsumerList.keySet()) {
            Element consumerNode = doc.createElement("consumer");
            consumerNode.setAttribute("id", consumerId);
            parentNode.appendChild(consumerNode);

            for(Integer phaseId : mConsumerList.get(consumerId)) {
                Element consumerPhaseNode = doc.createElement("phase");
                consumerPhaseNode.setAttribute("id", phaseId.toString());
                consumerNode.appendChild(consumerPhaseNode);

                Element propagationTimeAveragePhase = doc.createElement("propagationTimeAverage");
                propagationTimeAveragePhase.appendChild(doc.createTextNode(String.valueOf(calculator.propagationTimeAveragePhase(consumerId, phaseId))));
                consumerPhaseNode.appendChild(propagationTimeAveragePhase);
                
                Element propagationTimeStandardDeviationPhase = doc.createElement("propagationTimeStandardDeviation");
                propagationTimeStandardDeviationPhase.appendChild(doc.createTextNode(String.valueOf(calculator.propagationTimeStandardDeviationPhase(consumerId, phaseId))));
                consumerPhaseNode.appendChild(propagationTimeStandardDeviationPhase);

                Element maxPropagationTimePerPhase = doc.createElement("maxPropagationTime");
                maxPropagationTimePerPhase.appendChild(doc.createTextNode(String.valueOf(calculator.maxPropagationTimePerPhase(consumerId, phaseId))));
                consumerPhaseNode.appendChild(maxPropagationTimePerPhase);

                Element minPropagationTimePerPhase = doc.createElement("minPropagationTime");
                minPropagationTimePerPhase.appendChild(doc.createTextNode(String.valueOf(calculator.minPropagationTimePerPhase(consumerId, phaseId))));
                consumerPhaseNode.appendChild(minPropagationTimePerPhase);

                Element totalPacketSize = doc.createElement("totalPacketSize");
                totalPacketSize.appendChild(doc.createTextNode(String.valueOf(calculator.totalPacketSize(consumerId, phaseId))));
                consumerPhaseNode.appendChild(totalPacketSize);

                Element averageThroughputTotalPerPhase = doc.createElement("averageThroughputTotal");
                averageThroughputTotalPerPhase.appendChild(doc.createTextNode(String.valueOf(calculator.averageThroughputTotalPerPhase(consumerId, phaseId))));
                consumerPhaseNode.appendChild(averageThroughputTotalPerPhase);

            }
        }
    }

    private void addGlobalKpi(Document doc, Element globalNode, KpiCalculator calculator) {

        Element propagationTimeAverageTotal = doc.createElement("propagationTimeAverage");
        propagationTimeAverageTotal.appendChild(doc.createTextNode(String.valueOf(calculator.propagationTimeAverageTotal())));
        globalNode.appendChild(propagationTimeAverageTotal);

        Element propagationTimeStandardDeviationTotal = doc.createElement("propagationTimeStandardDeviation");
        propagationTimeStandardDeviationTotal.appendChild(doc.createTextNode(String.valueOf(calculator.propagationTimeStandardDeviationTotal())));
        globalNode.appendChild(propagationTimeStandardDeviationTotal);

        Element packetSizeAverageTotalCP = doc.createElement("packetSizeAverageConsumerProvider");
        packetSizeAverageTotalCP.appendChild(doc.createTextNode(String.valueOf(calculator.packetSizeAverageTotal(2))));
        globalNode.appendChild(packetSizeAverageTotalCP);

        Element packetSizeAverageTotalPC = doc.createElement("packetSizeAverageProviderConsumer");
        packetSizeAverageTotalPC.appendChild(doc.createTextNode(String.valueOf(calculator.packetSizeAverageTotal(1))));
        globalNode.appendChild(packetSizeAverageTotalPC);

        Element packetSizeAverageTotalBoth = doc.createElement("packetSizeAverageBoth");
        packetSizeAverageTotalBoth.appendChild(doc.createTextNode(String.valueOf(calculator.packetSizeAverageTotal(3))));
        globalNode.appendChild(packetSizeAverageTotalBoth);
      
        Element packetSizeTotal = doc.createElement("packetSize");
        packetSizeTotal.appendChild(doc.createTextNode(String.valueOf(calculator.packetSizeTotal())));
        globalNode.appendChild(packetSizeTotal);

        Element maxPropagationTime = doc.createElement("maxPropagationTime");
        maxPropagationTime.appendChild(doc.createTextNode(String.valueOf(calculator.maxPropagationTime())));
        globalNode.appendChild(maxPropagationTime);

        Element minPropagationTime = doc.createElement("minPropagationTime");
        minPropagationTime.appendChild(doc.createTextNode(String.valueOf(calculator.minPropagationTime())));
        globalNode.appendChild(minPropagationTime);

        Element timeOfSimulationInMillis = doc.createElement("timeOfSimulationInMillis");
        timeOfSimulationInMillis.appendChild(doc.createTextNode(String.valueOf(calculator.timeOfSimulationInMillis())));
        globalNode.appendChild(timeOfSimulationInMillis);

        Element averageThroughputTotal = doc.createElement("averageThroughput");
        averageThroughputTotal.appendChild(doc.createTextNode(String.valueOf(calculator.averageThroughputTotal())));
        globalNode.appendChild(averageThroughputTotal);
        
    }
}

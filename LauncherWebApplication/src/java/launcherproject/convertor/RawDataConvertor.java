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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Map.Entry;
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

        return true;
    }

    public boolean writeRawAsCSV(File aCSVFile) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(aCSVFile));

        writer.write("Message ID;TS1;TS2;TS3;TS4;Consumer ID;Consumer Phase ID;Provider ID;Provider Conf ID;Payload Question;Payload Question Length;Payload Response;Payload Response Length\n");

        for(String msgId : mMessageDatasList.keySet()) {
            writer.write(mMessageDatasList.get(msgId).toCSV() + "\n");
        }

        writer.close();

        return true;
    }

    public boolean writeRawAsXML(File aXMLFile) {

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
    }
}

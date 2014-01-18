/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcherproject.convertor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author 19012831
 */
public class MessageData {

    String fullMessageId;
    long ts1;
    long ts2;
    long ts3;
    long ts4;
    int providerConfId;
    String payloadGo;
    String payloadBack;
    int consumerPhaseId;

    String shortMessageId;
    String providerId;
    String consumerId;

    int state;

    public boolean load(String line) {

        String[] tokens = line.split(";");

       if (tokens.length < 5) {
           return false;
       }

       fullMessageId = tokens[0];

       Pattern p = Pattern.compile("ProviderID:(\\d+)ConsumerID:(\\d+)-MessageID:(\\d+)");
       Matcher m = p.matcher(fullMessageId);
       boolean b = m.matches();
       if (!b) {
           return false;
       }
       providerId = m.group(1);
       consumerId =  m.group(2);
       shortMessageId =  m.group(3);

       providerConfId = Integer.decode(tokens[1]);

       state = Integer.decode(tokens[4]);
       switch (state) {
           case 1:  ts1 = Long.decode(tokens[3]);
                    payloadGo = tokens[2];
                    consumerPhaseId = Integer.decode(tokens[5]);
                    break;
           case 2:  ts2 = Long.decode(tokens[3]);
                    payloadGo = tokens[2];
                    break;
           case 3:  ts3 = Long.decode(tokens[3]);
                    payloadBack = tokens[2];
                    break;
           case 4:  ts4 = Long.decode(tokens[3]);
                    payloadBack = tokens[2];
                    consumerPhaseId = Integer.decode(tokens[5]);
                    break;
       }

       return true;
    }

    public long getRefTimeStamp() {
        return ts4;
    }

    public void updateWith(MessageData newMsg) {

       state = newMsg.state;
       switch (state) {
           case 1:  ts1 = newMsg.ts1;
                    payloadGo = newMsg.payloadGo;
                    consumerPhaseId = newMsg.consumerPhaseId;
                    break;
           case 2:  ts2 = newMsg.ts2;
                    payloadGo = newMsg.payloadGo;
                    break;
           case 3:  ts3 = newMsg.ts3;
                    payloadBack = newMsg.payloadBack;
                    break;
           case 4:  ts4 = newMsg.ts4;
                    payloadBack = newMsg.payloadBack;
                    break;
       }
    }

    public long getTotalTime() {
        return ts4 - ts1;
    }

    // Message ID;TS1;TS2;TS3;TS4;Consumer ID;Consumer Phase ID;Provider ID;Provider Conf ID;Payload Question;Payload Question Length;Payload Response;Payload Response Length
    public String toCSV() {
        return fullMessageId + ";"
                + ts1 + ";"
                + ts2 + ";"
                + ts3 + ";"
                + ts4 + ";"
                + consumerId + ";"
                + consumerPhaseId + ";"
                + providerId + ";"
                + providerConfId + ";"
                + payloadGo + ";"
                + payloadGo.length() + ";"
                + payloadBack + ";"
                + payloadBack.length();
    }
}

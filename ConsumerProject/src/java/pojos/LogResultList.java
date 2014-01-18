/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 19012831
 */
public class LogResultList {

    public static final int OUT_CONSUMER = 1;
    public static final int IN_PROVIDER = 2;
    public static final int OUT_PROVIDER = 3;
    public static final int IN_CONSUMER = 4;

    private List<LogResult> list;
    private static int NB_OF_RESULT = 100;

    private int offsetForGet = 0;



    private class LogResult {

        String idMessage;
        int idConf;
        String payload;
        long timestamp;
        int inoutType;
        int consumerConfId;

        public LogResult(String idMessage, int idConf, String payload, long timestamp, int input,  int consumerConfId) {
            this.idMessage = idMessage;
            this.idConf = idConf;
            this.payload = payload;
            this.timestamp = timestamp;
            this.inoutType = input;
            this.consumerConfId = consumerConfId;
        }

        @Override
        public String toString() {
            return idMessage
                    + ";" + String.valueOf(idConf)
                    + ";" + payload
                    + ";" + timestamp
                    + ";" + inoutType
                    + ";" + consumerConfId;
        }

    }

    public LogResultList() {
        list = new ArrayList<LogResult>();
    }

    public synchronized void addLog(String idMessage, int idConf, String payload, long timestamp, int isInput, int consumerConfId) {
        
        list.add(new LogResult(idMessage, idConf, payload, timestamp, isInput, consumerConfId));
        System.out.println("[Log Result List] a log is added, list size : "+list.size());
    }

    public String getFirstLog() {

        offsetForGet = 0;
        return getNextLog();
    }

    public String getNextLog() {
        String result = "";
        for (int i = 0; i < NB_OF_RESULT; i++) {
            int indexToGet = offsetForGet + i;
            if (list.size() > indexToGet) {
                result = result + list.get(indexToGet).toString() + "\n";
            }
            else {
                break;
            }
        }

        offsetForGet+=NB_OF_RESULT;

        return result;
    }
}

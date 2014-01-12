/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package providerPckg;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 19012831
 */
public class LogResultList {

    private List<LogResult> list;
    private static int NB_OF_RESULT = 100;

    private static int offsetForGet = 0;

    private class LogResult {

        String idMessage;
        int idConf;
        String payload;
        long timestamp;
        boolean isInput;

        public LogResult(String idMessage, int idConf, String payload, long timestamp, boolean input) {
            this.idMessage = idMessage;
            this.idConf = idConf;
            this.payload = payload;
            this.timestamp = timestamp;
            this.isInput = input;
        }

        @Override
        public String toString() {
            return idMessage
                    + ";" + String.valueOf(idConf)
                    + ";" + payload
                    + ";" + timestamp
                    + ";" + (isInput ? "I" : "O");
        }

    }

    public LogResultList() {
        list = new ArrayList<LogResult>();
    }

    public void addLog(String idMessage, int idConf, String payload, long timestamp, boolean isInput) {
        list.add(new LogResult(idMessage, idConf, payload, timestamp, isInput));
    }

    public String getFirstLog() {

        offsetForGet = 0;
        return getNextLog();
    }

    public String getNextLog() {
        String result = "";

        for (int i = 0; i < NB_OF_RESULT; i++) {
            int indexToGet = offsetForGet + i;
            if (list.size() <= indexToGet) {
                result = result + list.get(indexToGet) + "\n";
            }
            else {
                break;
            }
        }

        return result;
    }
}

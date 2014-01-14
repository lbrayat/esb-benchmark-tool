/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcherproject.results;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Results {

        private String results;
        private String filePath;

        public Results(String filePath) {
            results = "";
            this.filePath = filePath;
        }


        public void addResults(String resultsToAdd){
            Logger.getLogger(Results.class.getCanonicalName()).info("Adding results : " + resultsToAdd);
            results += resultsToAdd;
            Logger.getLogger(Results.class.getCanonicalName()).info("new results : " + results);

        }

        public void writeResultsToFile(){
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            BufferedWriter out=null;
            try {
                out = new BufferedWriter(new FileWriter("./test.txt"));
                out.write("========================================================");
                out.newLine();
                out.write("Writing results to file at "+ dateFormat.format(new Date()));
                out.newLine();
                out.write("========================================================");
                out.newLine();
                out.write(results);
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
            }
  
        }
    }
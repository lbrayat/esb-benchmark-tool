/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcherproject.webservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import launcherproject.ConsumerClient;
import launcherproject.Main;
import launcherproject.MainCLI;
import launcherproject.ProviderClient;
import launcherproject.Scenario;
import launcherproject.results.ResultsFetcher;

/**
 *
 * @author marc
 */
@WebService()
public class FinishLineWS {

    private AtomicInteger consumerCount;
    private static final String tempFile = "/home/marc/SimuState.tmp";


    public FinishLineWS() {
        consumerCount = new AtomicInteger(0);
        PrintWriter writer;
        try {
            writer = new PrintWriter(tempFile, "UTF-8");
            writer.println("");
            writer.close();
        } catch (FileNotFoundException ex) {
                Logger.getLogger(FinishLineWS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
              Logger.getLogger(FinishLineWS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    


    /**
     * Web service operation
     */
    @WebMethod(operationName = "imDone")
    public int imDone(@WebParam(name = "consumerName")
    String consumerName) {

        //TODO write your implementation code here:
        consumerCount.addAndGet(1);
        System.out.println("[FinishLineWS] A consumer is done "+consumerCount.get());

        File f = new File(Main.FILE_PATH);
        System.err.println(f.exists());
        Scenario scenario = new Scenario(f.getAbsolutePath());

        if (consumerCount.get()==scenario.getConsumerList().size()){
            new ResultsFetcher(scenario.getProviderList(), scenario.getConsumerList()).fetch(consumerCount.get());
            PrintWriter writer;
            try {
                writer = new PrintWriter(tempFile, "UTF-8");
                writer.println("done");
                writer.close();
                System.out.println("[FinishLineWS] Simulation is done ");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinishLineWS.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(FinishLineWS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return 0;
    }

    /**
     * simu -i /home/marc/NetBeansProjects/esb-benchmark-tool/scenarioMarc.xml -o /home/marc/zejknfzlenf.xml
     * Web service operation
     */
    @WebMethod(operationName = "reset")
    public String reset() {
        
        consumerCount = new AtomicInteger(0);
        return "reset ok !";

    }


}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcherproject.webservice;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import launcherproject.ConsumerClient;
import launcherproject.Main;
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


    public FinishLineWS() {
        consumerCount = new AtomicInteger(0);
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

        new ResultsFetcher(scenario.getProviderList(), scenario.getConsumerList()).fetch(consumerCount.get());
        return 0;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "reset")
    public String reset() {
        
        consumerCount = new AtomicInteger(0);
        return "reset ok !";

    }


}

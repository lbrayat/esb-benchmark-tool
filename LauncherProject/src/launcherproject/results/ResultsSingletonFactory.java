/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcherproject.results;

/**
 *
 * @author marc
 */

public class ResultsSingletonFactory {

    private static Results theInstance;
    private static final String FILE_PATH = "./results.txt";

    public static Results getInstance(){
        
        if (theInstance==null){
            theInstance = new Results(FILE_PATH);
        }
        return theInstance;
    }

    

}


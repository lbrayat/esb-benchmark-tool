/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pojos;

/**
 *
 * @author marc
 */
public class LogResultListSingletonFactory {

    private static LogResultList theResultsList;

    public static LogResultList getInstance(){
        if (theResultsList==null){
            theResultsList = new LogResultList();
        }

        return theResultsList;
    }

    public static void resetInstance(){
        theResultsList = new LogResultList();
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcherproject.results;

/**
 *
 * @author marc
 */
public class ResultTester {

    public ResultTester() {

    }


    public void test1(){
        Results r = ResultsSingletonFactory.getInstance();
        r.addResults("blablablablbalab");
        r.addResults("okezokozoekozkeokzek");
        r.writeResultsToFile();
    }

    public static void main(String[] args) {
        new ResultTester().test1();
    }

}

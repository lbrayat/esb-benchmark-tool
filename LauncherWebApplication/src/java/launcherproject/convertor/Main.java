/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcherproject.convertor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author 19012831
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        RawDataConvertor convertor = new RawDataConvertor();
        convertor.load("C:/tmp/test.txt");
        convertor.writeKPI(new File("C:/tmp/kpi.xml"));
    }
}

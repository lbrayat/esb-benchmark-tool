/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcherproject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author lbrayat
 */
public abstract class ESBWSClient {

    protected String mWSDL;



    protected abstract URL getURL();

    protected abstract int callStartConf();

    protected abstract int callEndConf();

    protected abstract int callStart();

    public abstract boolean configure();

}

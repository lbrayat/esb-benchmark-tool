/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcherproject.xml;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class ScenarioXML {

    private ConfigurationXML configurationXML;
    private ArrayList<Consumer> consumersXML = new ArrayList<Consumer>();
    private ArrayList<Provider> providersXML = new ArrayList<Provider>();


    /**
     * @return the configurationXML
     */
    public ConfigurationXML getConfigurationXML() {
        return configurationXML;
    }

    /**
     * @param configurationXML the configurationXML to set
     */
    public void setConfigurationXML(ConfigurationXML configurationXML) {
        this.configurationXML = configurationXML;
    }

    /**
     * @return the consumersXML
     */
    public ArrayList<Consumer> getConsumersXML() {
        return consumersXML;
    }

    /**
     * @param consumersXML the consumersXML to set
     */
    public void setConsumersXML(ArrayList<Consumer> consumersXML) {
        this.consumersXML = consumersXML;
    }

    /**
     * @return the providersXML
     */
    public ArrayList<Provider> getProvidersXML() {
        return providersXML;
    }

    /**
     * @param providersXML the providersXML to set
     */
    public void setProvidersXML(ArrayList<Provider> providersXML) {
        this.providersXML = providersXML;
    }



    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");
        result.append(this.getClass().getName() + " Object {" + NEW_LINE);
        result.append(" configurationXML : " + getConfigurationXML() + NEW_LINE);
        result.append(" consumers : " + getConsumersXML() + NEW_LINE);
        result.append(" providers : " + getProvidersXML() + NEW_LINE );
        result.append("}");
        return result.toString();
    }
}

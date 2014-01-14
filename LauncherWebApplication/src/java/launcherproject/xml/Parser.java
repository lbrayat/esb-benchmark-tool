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
public class Parser {

    private JDOM2 jdom1;
    private ConfigurationXML configurationXML;
    private ArrayList<Consumer> consumersXML = new ArrayList<Consumer>();
    private ArrayList<Provider> providersXML = new ArrayList<Provider>();
    private String filePath;

    public Parser (String filePath) {
        setConfigurationXML(new ConfigurationXML());
        setConsumersXML(new ArrayList<Consumer>());
        setProvidersXML(new ArrayList<Provider>());
        setFilePath(filePath);
        setJdom2(new JDOM2(this));
    }
    public void doParse () {
        jdom1.handleConfiguration();
        System.out.println("Configuration handled");
        
        jdom1.handleConsumers();
        System.out.println("Consumers handled");

        jdom1.handleProviders();
        System.out.println("Providers handled");

    }
    /**
     * @return the jdom1
     */
    public JDOM2 getJdom2() {
        return jdom1;
    }

    /**
     * @param aJdom1 the jdom1 to set
     */
    public void setJdom2(JDOM2 aJdom2) {
        jdom1 = aJdom2;
    }

    /**
     * @return the configurationXML
     */
    public ConfigurationXML getConfigurationXML() {
        return configurationXML;
    }

    /**
     * @param aConfigurationXML the configurationXML to set
     */
    public void setConfigurationXML(ConfigurationXML aConfigurationXML) {
        configurationXML = aConfigurationXML;
    }

    /**
     * @return the consumersXML
     */
    public ArrayList<Consumer> getConsumersXML() {
        return consumersXML;
    }

    /**
     * @param aConsumersXML the consumersXML to set
     */
    public void setConsumersXML(ArrayList<Consumer> aConsumersXML) {
        consumersXML = aConsumersXML;
    }

    /**
     * @return the providersXML
     */
    public ArrayList<Provider> getProvidersXML() {
        return providersXML;
    }

    /**
     * @param aProvidersXML the providersXML to set
     */
    public void setProvidersXML(ArrayList<Provider> aProvidersXML) {
        providersXML = aProvidersXML;
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }    
}

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

    private static JDOM2 jdom1;
    private static ScenarioXML.ConfigurationXML configurationXML;
    private static ArrayList<ScenarioXML.Consumer> consumersXML = new ArrayList<ScenarioXML.Consumer>();
    private static ArrayList<ScenarioXML.Provider> providersXML = new ArrayList<ScenarioXML.Provider>();
    private String filePath;

    public Parser (String filePath) {
        setConfigurationXML(new ScenarioXML().new ConfigurationXML());
        setConsumersXML(new ArrayList<ScenarioXML.Consumer>());
        setProvidersXML(new ArrayList<ScenarioXML.Provider>());
        setFilePath(filePath);
        setJdom2(new JDOM2(this));
    }
    public void doParse () {
        getJdom2();
        JDOM2.handleConfiguration();
        getJdom2();
        JDOM2.handleConsumers();
        getJdom2();
        JDOM2.handleProviders();
    }
    /**
     * @return the jdom1
     */
    public static JDOM2 getJdom2() {
        return jdom1;
    }

    /**
     * @param aJdom1 the jdom1 to set
     */
    public static void setJdom2(JDOM2 aJdom2) {
        jdom1 = aJdom2;
    }

    /**
     * @return the configurationXML
     */
    public static ScenarioXML.ConfigurationXML getConfigurationXML() {
        return configurationXML;
    }

    /**
     * @param aConfigurationXML the configurationXML to set
     */
    public static void setConfigurationXML(ScenarioXML.ConfigurationXML aConfigurationXML) {
        configurationXML = aConfigurationXML;
    }

    /**
     * @return the consumersXML
     */
    public static ArrayList<ScenarioXML.Consumer> getConsumersXML() {
        return consumersXML;
    }

    /**
     * @param aConsumersXML the consumersXML to set
     */
    public static void setConsumersXML(ArrayList<ScenarioXML.Consumer> aConsumersXML) {
        consumersXML = aConsumersXML;
    }

    /**
     * @return the providersXML
     */
    public static ArrayList<ScenarioXML.Provider> getProvidersXML() {
        return providersXML;
    }

    /**
     * @param aProvidersXML the providersXML to set
     */
    public static void setProvidersXML(ArrayList<ScenarioXML.Provider> aProvidersXML) {
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

package launcherproject.xml;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class TestParser {

    private static Parser parser;

	public static void main (String[] args)
        {
            System.out.println("Programme de test du parseur XML");
            ScenarioXML scenario = new ScenarioXML ();
            String fileName = "C:/Documents and Settings/lbrayat/Mes documents/Downloads/ParsingXML/ParsingXML/scenario.xml";
            setParser(new Parser(fileName));
            getParser().doParse();
            scenario.setConfigurationXML(Parser.getConfigurationXML());
            scenario.setConsumersXML(Parser.getConsumersXML());
            scenario.setProvidersXML(Parser.getProvidersXML());
            System.out.println(scenario);
	}

	public static Parser getParser()
        {
            return parser;
	}

	public static void setParser(Parser parser)
        {
            TestParser.parser = parser;
	}
}

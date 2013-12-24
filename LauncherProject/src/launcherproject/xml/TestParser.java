package launcherproject.xml;

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
            String fileName = "/home/marc/Downloads/scenario.xml";
            setParser(new Parser(fileName));
            getParser().doParse();
            scenario.setConfigurationXML(parser.getConfigurationXML());
            scenario.setConsumersXML(parser.getConsumersXML());
            scenario.setProvidersXML(parser.getProvidersXML());
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

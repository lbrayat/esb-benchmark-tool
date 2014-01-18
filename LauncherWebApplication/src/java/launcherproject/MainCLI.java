/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcherproject;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import launcherproject.convertor.RawDataConvertor;
import launcherproject.results.Results;
import launcherproject.webservice.FinishLineWS;


/**
 *
 * @author lbrayat
 */
public class MainCLI {

    private static Launcher launcher;
    private static final String tempFile = "/home/marc/SimuState.tmp";
   

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args) {

        System.out.print("**** ESB Benchmark Tool ****"+"\n");
        while (true) {
            
            String arguments [] = null;

            // Etape 1: Définition des options
            Options options = new Options();
            options.addOption("simu", "simulate", false, "simulates a scenario");
            options.addOption("h", "help", false, "prints the help content");
            options.addOption(OptionBuilder
                    .withArgName("file")
                    .hasArg()
                    .isRequired()
                    .withDescription("input file")
                    .withLongOpt("input")
                    .create("i"));
            options.addOption(OptionBuilder
                    .withArgName("file")
                    .hasArg()
                    .isRequired()
                    .withDescription("output file")
                    .withLongOpt("output")
                    .create("o"));

            try {

                //  Prompt the user to enter a command
                System.out.print("EBT> ");

                //  Open up standard input
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                String command = null;

                //  Read the command from the command-line; need to use try/catch with the
                //  readLine() method
                try {
                   command = br.readLine();
                } catch (IOException ioe) {
                    System.out.println("IO error trying to read your command!" + ioe.getMessage());
                    System.exit(1);
                }

                System.out.println("Your command : " + command);
                if (command.equals("q") || command.equals("quit")) {
                    System.out.println("Ending program");
                    System.exit(0);
                }
                arguments = command.split(" ");

                // Analyze the command
                CommandLineParser parser = new GnuParser();
                CommandLine cmd;
                cmd = parser.parse(options, arguments);
                String inputFilePath = cmd.getOptionValue("i");


                launcher = new Launcher (inputFilePath);
                launcher.runLauncher();

                String xmlOutFile = cmd.getOptionValue("o");
 
                // wait for the end of the simulation
                System.out.println("Simulation running");
                int i=0;
                boolean done = false;
                while (!done){
                    if (i==10){
                        System.out.println();
                        i=0;
                    }
                        Thread.sleep(1000);
                        System.out.print(done+" ");
                        i++;

                        BufferedReader bReader = new BufferedReader(new FileReader(tempFile));
                        String line = bReader.readLine();
                        if(line!=null && line.equalsIgnoreCase("done")){
                            done = true;
                            PrintWriter writer = new PrintWriter(tempFile);
                            writer.print("");
                            writer.close();
                        }


                }

                // wait for the end of the simulation
                System.out.println("Simulation is finished, writing results to file");
                // writing csv and xml results
                RawDataConvertor rdc = new RawDataConvertor();
                try {
                    rdc.load(Results.rawDataFilePath);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    Logger.getLogger(Results.class.getName()).log(Level.SEVERE, "xml file = "+xmlOutFile, "xml file = "+xmlOutFile);
                    rdc.writeKPI(new File(xmlOutFile));
                } catch (IOException ex) {
                    Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
                }

                } catch(MissingOptionException e){
                    // vérifie si l'option -h est présente
                    boolean help = false;
                    try {
                        Options helpOptions = new Options();
                        helpOptions.addOption("h", "help", false, "prints the help content");
                        CommandLineParser parser = new PosixParser();
                        CommandLine line = parser.parse(helpOptions, arguments);
                        if(line.hasOption("h")) help = true;
                    } catch(Exception ex){ }
                        if(!help) System.err.println(e.getMessage());
                        HelpFormatter formatter = new HelpFormatter();
                        formatter.printHelp( "App" , options );
                        //System.exit(1);
                    } catch(MissingArgumentException e){
                        System.err.println(e.getMessage());
                        HelpFormatter formatter = new HelpFormatter();
                        formatter.printHelp( "App" , options );
                        //System.exit(1);
                    } catch(ParseException e){
                        System.err.println("Error while parsing the command line: "+e.getMessage());
                        //System.exit(1);
                    } catch(Exception e){
                            e.printStackTrace();
                    }
            }
    }

}

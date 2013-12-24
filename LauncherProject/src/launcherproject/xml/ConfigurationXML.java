package launcherproject.xml;

import java.util.ArrayList;


    public class ConfigurationXML {
        private ArrayList<Actor> listActors = new ArrayList<Actor>();

        public ConfigurationXML ()
        { }

        /**
         * @return the listActors
         */
        public ArrayList<Actor> getListActors() {
            return listActors;
        }

        /**
         * @param listActors the listActors to set
         */
        public void setListActors(ArrayList<Actor> listActors) {
            this.listActors = listActors;
        }

        @Override
        public String toString () {
            StringBuilder result = new StringBuilder();
            String NEW_LINE = System.getProperty("line.separator");
            result.append(this.getClass().getName() + " Object {" + NEW_LINE);
            result.append(" listOfActors : " + getListActors() + NEW_LINE);
            result.append("}");
            return result.toString();
        }
    }

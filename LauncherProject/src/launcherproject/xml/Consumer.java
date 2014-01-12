package launcherproject.xml;

import java.util.ArrayList;



    public class Consumer {
        private String identifier;
        private ArrayList<PhaseConsumer> listPhases = new ArrayList<PhaseConsumer>();

        public Consumer ()
        { }

        /**
         * @return the identifier
         */
        public String getIdentifier() {
            return identifier;
        }

        /**
         * @param identifier the identifier to set
         */
        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        /**
         * @return the listPhases
         */
        public ArrayList<PhaseConsumer> getListPhases() {
            return listPhases;
        }

        /**
         * @param listPhases the listPhases to set
         */
        public void setListPhases(ArrayList<PhaseConsumer> listPhases) {
            this.listPhases = listPhases;
        }

        public void addPhase (PhaseConsumer phaseConsumer)
        {
            this.listPhases.add(phaseConsumer);
        }

        @Override
        public String toString () {
            StringBuilder result = new StringBuilder();
            String NEW_LINE = System.getProperty("line.separator");
            result.append(this.getClass().getName() + " Object {" + NEW_LINE);
            result.append(" identifier : " + getIdentifier() + NEW_LINE);
            result.append(" listPhases : " + NEW_LINE);
            for(PhaseConsumer phase : getListPhases()) {
                result.append(" phase : " + phase + NEW_LINE);
            }
            result.append("}");
            return result.toString();
        }
    }
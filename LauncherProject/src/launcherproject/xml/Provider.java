package launcherproject.xml;

import java.util.ArrayList;

 public class Provider {
        private String identifier;
        private ArrayList<PhaseProvider> listPhases = new ArrayList<PhaseProvider>();

        public Provider ()
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
        public ArrayList<PhaseProvider> getListPhases() {
            return listPhases;
        }

        /**
         * @param listPhases the listPhases to set
         */
        public void setListPhases(ArrayList<PhaseProvider> listPhases) {
            this.listPhases = listPhases;
        }

        public void addPhase (PhaseProvider phaseProvider)
        {
            this.listPhases.add(phaseProvider);
        }

        @Override
        public String toString () {
            StringBuilder result = new StringBuilder();
            String NEW_LINE = System.getProperty("line.separator");
            result.append(this.getClass().getName() + " Object {" + NEW_LINE);
            result.append(" identifier : " + getIdentifier() + NEW_LINE);
            result.append(" listPhases : " + NEW_LINE);
            for(PhaseProvider phase : getListPhases()) {
                result.append(" phase : " + phase + NEW_LINE);
            }
            result.append("}");
            return result.toString();
        }
    }
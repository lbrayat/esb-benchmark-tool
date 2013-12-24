package launcherproject.xml;

    public class Actor {
        private TypeOfActor typeOfActor;
        private String identifier;
        private String address;

        public Actor ()
        { }

        /**
         * @return the typeOfActor
         */
        public TypeOfActor getTypeOfActor() {
            return typeOfActor;
        }

        /**
         * @param typeOfActor the typeOfActor to set
         */
        public void setTypeOfActor(TypeOfActor typeOfActor) {
            this.typeOfActor = typeOfActor;
        }

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
         * @return the address
         */
        public String getAddress() {
            return address;
        }

        /**
         * @param address the address to set
         */
        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString ()
        {
            StringBuilder result = new StringBuilder();
            String NEW_LINE = System.getProperty("line.separator");
            result.append(this.getClass().getName() + " Object {" + NEW_LINE);
            result.append(" typeOfActor : " + getTypeOfActor() + NEW_LINE);
            result.append(" identifier : " + getIdentifier() + NEW_LINE);
            result.append(" address : " + getAddress() + NEW_LINE);
            result.append("}");
            return result.toString();
        }

    }
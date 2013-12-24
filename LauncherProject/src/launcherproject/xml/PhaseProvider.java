package launcherproject.xml;

public class PhaseProvider {

        private int idConf;
        private int processingTime;
        private int packetSize;
        private int percentageLoss;

        public PhaseProvider (){
            idConf=0;
            processingTime=0;
            packetSize=0;
            percentageLoss=0; 
        }

         public PhaseProvider(int mIdPhase, int mTreatmentTime, int mResponseSize, int mLostPercent) {
            this.idConf = mIdPhase;
            this.processingTime = mTreatmentTime;
            this.packetSize = mResponseSize;
            this.percentageLoss = mLostPercent;
        }

        /**
         * @return the processingTime
         */
        public int getProcessingTime() {
            return processingTime;
        }

        /**
         * @param processingTime the processingTime to set
         */
        public void setProcessingTime(int processingTime) {
            this.processingTime = processingTime;
        }


        /**
         * @return the packetSize
         */
        public int getPacketSize() {
            return packetSize;
        }

        /**
         * @param packetSize the packetSize to set
         */
        public void setPacketSize(int packetSize) {
            this.packetSize = packetSize;
        }

        /**
         * @return the percentageLoss
         */
        public int getPercentageLoss() {
            return percentageLoss;
        }

        /**
         * @param percentageLoss the percentageLoss to set
         */
        public void setPercentageLoss(int percentageLoss) {
            this.percentageLoss = percentageLoss;
        }

        public int getConfId() {
            return idConf;
        }

        public void setConfId(int idConf){
            this.idConf = idConf;
        }
      

        @Override
        public String toString () {
            StringBuilder result = new StringBuilder();
            String NEW_LINE = System.getProperty("line.separator");
            result.append(this.getClass().getName() + " Object {" + NEW_LINE);
            result.append(" processingTime : " + getProcessingTime() + NEW_LINE);
            result.append(" packetSize : " + getPacketSize() + NEW_LINE);
            result.append(" percentageLoss : " + getPercentageLoss() + NEW_LINE);
            result.append(" conf id : " + getConfId() + NEW_LINE);
            result.append("}");
            return result.toString();
        }


    }
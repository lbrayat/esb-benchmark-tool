package launcherproject.xml;

  public enum TrafficClass {
        BE, RT, NRT, CBR, BURST;
        public static TrafficClass ParseTrafficClass (String s) {
            if (s.equalsIgnoreCase("RT"))
                return TrafficClass.RT;
            else if (s.equalsIgnoreCase("NRT"))
                return TrafficClass.NRT;
            else if (s.equalsIgnoreCase("CBR"))
                return TrafficClass.CBR;
            else if (s.equalsIgnoreCase("BURST"))
                return TrafficClass.BURST;
            else
                return TrafficClass.BE;
        }

        public static TrafficClass intToTrafficClass(int a){
            if (a==1)
                return TrafficClass.RT;
            else if (a==2)
                return TrafficClass.NRT;
            else if (a==3)
                return TrafficClass.CBR;
            else if (a==4)
                return TrafficClass.BURST;
            else
                return TrafficClass.BE;
        }

        public static int trafficClassToInt(TrafficClass a){
            if (a.equals(TrafficClass.RT))
                return 1;
            else if (a.equals(TrafficClass.NRT))
                return 2;
            else if (a.equals(TrafficClass.CBR))
                return 3;
            else if (a.equals(TrafficClass.BURST))
                return 4;
            else
                return 5;
        }
    }
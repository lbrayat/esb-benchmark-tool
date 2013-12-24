/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package launcherproject.xml;

public enum TypeOfActor {
        provider, consumer;
        public static TypeOfActor ParseTypeOfActor (String s) {
            if (s.equals("provider"))
                return TypeOfActor.provider;
            else
                return TypeOfActor.consumer;
        }
    }

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messaging;

/**
 *
 * @author marc
 */
public class TestMessageSender {

    public TestMessageSender() {
    }

    public void test1(){
        // send a finish message via JMS
        MessageSender ms = new MessageSender();
        ms.send("finished");
    }

    public static void main(String[] args) {
        new TestMessageSender().test1();
    }

}

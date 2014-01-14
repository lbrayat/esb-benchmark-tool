/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messaging;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author marc
 */
public class MessageSender {

    private static final String CONNECTION_FACTORY_NAME="jms/ConnectionFactory";
    private static final String DESTINATION_IN="guest";
    private static final String DESTINATION_OUT="guest";


    private InitialContext context;
    private ConnectionFactory factory;
    private Connection connection;
    private Session session;
    private Destination toDestination;
    private MessageProducer sender;
    private Destination fromDestination;
    private MessageConsumer receiver;

    public MessageSender() {
        try {
            // initialize fields
            //To get to JNDI context
            context = new InitialContext();
            //To get a ConnectionFactory from JNDI
            factory = (ConnectionFactory) context.lookup(CONNECTION_FACTORY_NAME);
            //Creating a connection from the factory
            connection = factory.createConnection();
            //Creating a session from the connection
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //Creating a producer to send objects
            toDestination = (Destination) context.lookup(DESTINATION_OUT);
            sender = session.createProducer(toDestination);
            //Creating a consumer to receive results objects
            fromDestination = (Destination) context.lookup(DESTINATION_IN);
            receiver = session.createConsumer(fromDestination);
            //Starting the connection
            connection.start();
        } catch (JMSException ex) {
            Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

  //send an object to the topic/queue
    public boolean send(String textToSend) {
        try {
            final TextMessage message = session.createTextMessage();
            message.setText(textToSend);
            sender.send(message);
        } catch (Exception e) {
            finalize();
        }
        return true;
    }

    //receive an object from the topic/queue
    public String receive() {
        String result = null;
        try {
            Message message = receiver.receive();
            if (message instanceof TextMessage) {
                TextMessage object = (TextMessage) message;
                result = object.getText();
            }
        } catch (Exception e) {
            finalize();
        }
        return result;
    }

    @Override
    protected void finalize() {
        if (context != null) {
            try {
                context.close();
            } catch (NamingException e) {

            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }


}

package org.anurag.jms_active_mq;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageReceiver {

    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    private static String queueName = "MESSAGE_QUEUE";

    public static void main(String[] args) throws JMSException {
        System.out.println(url);

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(queueName);

        MessageConsumer consumer = session.createConsumer(destination);

        Message message = consumer.receive();

        if(message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("Msg received : " + textMessage.getText());
        }

        connection.close();


    }
}

package com.example.ProjetDomotiqueAPI.MQTT;

import org.eclipse.paho.client.mqttv3.*;

public class SimpleMqttClient implements MqttCallback {

    MqttClient myClient;
    MqttConnectOptions connOpt;

    static final String BROKER_URL = "tcp://localhost:1883";
    static final String MQTT_CLIENT_ID = "PD_API";
    static final String MQTT_USERNAME = "olivier";
    static final String MQTT_PASSWORD = "olivier";

    //This callback is invoked upon losing the MQTT connection.
    @Override
    public void connectionLost(Throwable t) {
        System.out.println("Connection lost!");
        // code to reconnect to the broker would go here if desired
    }

    //This callback is invoked when a message published by this client is successfully received by the broker.
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        //System.out.println("Pub complete" + new String(token.getMessage().getPayload()));
    }

    //This callback is invoked when a message is received on a subscribed topic.
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) {
        //Not dealing with arriving message
    }

    public void publishMqttMessage(String topic, String message, int pubQoS){

        MqttTopic mqttTopic = myClient.getTopic(topic);
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttMessage.setQos(pubQoS);
        mqttMessage.setRetained(false);

        // Publish the message;
        MqttDeliveryToken token;
        try {
            // publish message to broker
            token = mqttTopic.publish(mqttMessage);
            token.waitForCompletion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connectBroker(){

        // setup MQTT Client
        connOpt = new MqttConnectOptions();
        connOpt.setCleanSession(true);
        connOpt.setKeepAliveInterval(30);
        connOpt.setUserName(MQTT_USERNAME);
        connOpt.setPassword(MQTT_PASSWORD.toCharArray());

        // Connect to Broker
        try {
            myClient = new MqttClient(BROKER_URL, MQTT_CLIENT_ID);
            myClient.setCallback(this);
            myClient.connect(connOpt);
        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void shutdownClient(){
        // disconnect
        try {
            myClient.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
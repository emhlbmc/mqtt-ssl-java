package cn.demo.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


public class MqttHandler {

	public static String MQTT_BROKER = "ssl://120.76.xx.xx:xxxx";
	public static String MQTT_USERNAME = "admin";
	public static String MQTT_PASSWORD = "password";

	public static String caFilePath = "G:\\openssl_ca\\ca.crt";
	public static String clientCrtFilePath = "G:\\openssl_ca\\client.crt";
	public static String clientKeyFilePath = "G:\\openssl_ca\\client.key";


	public void publishMessageByMqtt(String topic) throws Exception {
		String content = "Message from my device! I'm test!";
		int qos = 2;
		String clientId = "cspublish_mqtt";
		MemoryPersistence persistence = new MemoryPersistence();
		try {
			MqttClient sampleClient = new MqttClient(MQTT_BROKER, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			connOpts.setUserName(MQTT_USERNAME);
			connOpts.setPassword(MQTT_PASSWORD.toCharArray());
			connOpts.setConnectionTimeout(1000);
			connOpts.setKeepAliveInterval(2000);
			connOpts.setSocketFactory(
					SslUtil.getSocketFactory(caFilePath, clientCrtFilePath, clientKeyFilePath, "cs123456"));
			try {
				sampleClient.connect(connOpts);
				MqttMessage message = new MqttMessage(content.getBytes());
				message.setQos(qos);
				sampleClient.publish(topic, message);
				System.out.println("Message published");
			} catch (Throwable e) {
				System.out.println("Error " + e.getMessage());
			}
		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}

	}

}

package cn.demo.mqtt;

public class MqttMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("run...");
		MqttHandler handler = new MqttHandler();
		handler.publishMessageByMqtt("test...");
	}
}

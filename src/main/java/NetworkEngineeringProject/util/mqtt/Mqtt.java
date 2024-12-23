package NetworkEngineeringProject.util.mqtt;

import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mqtt {

    private MqttClient mqttClient;

    @Autowired
    private OnMessageCallback onMessageCallback;

    @PostConstruct
    public void init() {
        int qos = 1;
        String broker = "tcp://mqtt.kindsof.xyz:1882";
        String clientId = "JavaService";
        MemoryPersistence persistence = new MemoryPersistence();
        String subTopic = "sensor_mq2";
        String pubTopic = "sensor_mq2";
        try {
            mqttClient = new MqttClient(broker, clientId, persistence);

            // MQTT 连接选项
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName("JavaService");
            connOpts.setPassword("1564245937".toCharArray());
            // 保留会话
            connOpts.setCleanSession(true);

            // 设置回调
            mqttClient.setCallback(onMessageCallback);

            // 建立连接
            System.out.println("Connecting to broker: " + broker);
            mqttClient.connect(connOpts);

            System.out.println("Connected");

            // 订阅
            mqttClient.subscribe(subTopic);

            // 发布消息 (如果需要)
            // MqttMessage message = new MqttMessage(content.getBytes());
            // message.setQos(qos);
            // mqttClient.publish(pubTopic, message);
            // System.out.println("Message published");


        } catch (MqttException me) {
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}

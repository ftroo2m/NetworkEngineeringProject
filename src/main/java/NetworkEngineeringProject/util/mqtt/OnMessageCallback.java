package NetworkEngineeringProject.util.mqtt;

import NetworkEngineeringProject.entity.AirqualityvalueEntity;
import NetworkEngineeringProject.service.IAirqualityvalueService;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static NetworkEngineeringProject.util.util.gson;

@Component
public class OnMessageCallback implements MqttCallback {

    @Autowired
    IAirqualityvalueService airqualityvalueService;

    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        System.out.println("连接断开，可以做重连");
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        System.out.println("接收消息主题:" + topic);
        System.out.println("接收消息Qos:" + message.getQos());
        System.out.println("接收消息内容:" + new String(message.getPayload()));
        String jsonPayload = new String(message.getPayload());

        // 使用 Gson 来解析 JSON 数据
        AirqualityvalueEntity airqualityvalueEntity = gson.fromJson(jsonPayload, AirqualityvalueEntity.class);

        // 打印解析后的对象
        System.out.println(airqualityvalueEntity);

        // 保存实体（假设 airqualityvalueService 是一个已经注入的服务）
        airqualityvalueService.save(airqualityvalueEntity);

        // 打印保存成功信息
        System.out.println("Post Success");
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }
}

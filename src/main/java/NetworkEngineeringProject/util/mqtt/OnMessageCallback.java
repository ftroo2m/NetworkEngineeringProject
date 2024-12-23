package NetworkEngineeringProject.util.mqtt;

import NetworkEngineeringProject.entity.AirqualityvalueEntity;
import NetworkEngineeringProject.service.IAirqualityvalueService;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class OnMessageCallback implements MqttCallback {

    private static final Logger logger = LoggerFactory.getLogger(OnMessageCallback.class);

    @Autowired
    IAirqualityvalueService airqualityvalueService;

    private Gson gson = new Gson(); // Ensure Gson is initialized

    @Override
    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        logger.error("MQTT连接丢失，原因: {}", cause.getMessage());
        // 添加重连逻辑，例如调用Mqtt客户端的reconnect方法
        // 你可以在 Mqtt 类中定义一个 reconnect() 方法，然后在这里调用它来实现重连。
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // 订阅后收到的消息会执行到这里
        logger.info("接收消息主题: {}", topic);
        logger.info("接收消息Qos: {}", message.getQos());
        String payload = new String(message.getPayload());
        logger.info("接收消息内容: {}", payload);

        try {
            // 使用 Gson 来解析 JSON 数据
            AirqualityvalueEntity airqualityvalueEntity = gson.fromJson(payload, AirqualityvalueEntity.class);

            // 打印解析后的对象
            logger.info("解析后的实体对象: {}", airqualityvalueEntity);

            // 保存实体
            airqualityvalueService.save(airqualityvalueEntity);

            // 打印保存成功信息
            logger.info("保存数据成功！");
        } catch (Exception e) {
            logger.error("解析或保存消息时发生错误: {}", e.getMessage(), e);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        logger.info("消息发布完成，状态: {}", token.isComplete());
    }
}

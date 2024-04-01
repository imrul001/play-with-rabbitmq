package com.ihasan.playwithrabbitmq.consumer;

import com.ihasan.playwithrabbitmq.configs.MessageConfig;
import com.ihasan.playwithrabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
public class OrderConsumer {

    @RabbitListener(queues = MessageConfig.QUEUE)
    public void consume(OrderStatus orderStatus){
        System.out.println("Message received from query "+orderStatus);
    }
}

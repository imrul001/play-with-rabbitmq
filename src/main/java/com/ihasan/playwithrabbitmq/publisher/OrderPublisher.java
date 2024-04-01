package com.ihasan.playwithrabbitmq.publisher;

import com.ihasan.playwithrabbitmq.configs.MessageConfig;
import com.ihasan.playwithrabbitmq.dto.Order;
import com.ihasan.playwithrabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/my/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/{restaurant}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurant){
        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed successfully in "+restaurant);
        rabbitTemplate.convertSendAndReceive(MessageConfig.EXCHANGE, MessageConfig.ROUTING_KEY, orderStatus);
        return "Success !!";
    }
}

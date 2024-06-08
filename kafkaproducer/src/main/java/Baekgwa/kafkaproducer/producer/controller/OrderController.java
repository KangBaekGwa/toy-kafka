package Baekgwa.kafkaproducer.producer.controller;

import Baekgwa.kafkaproducer.producer.dto.OrderDto;
import Baekgwa.kafkaproducer.producer.entity.Order;
import Baekgwa.kafkaproducer.producer.service.KafkaProducer;
import Baekgwa.kafkaproducer.producer.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final KafkaProducer kafkaProducer;

    @PostMapping
    public String itemOrder(
            @RequestBody Order order
    ) {
        orderService.itemOrder(order);
        OrderDto build = OrderDto.builder()
                .id(order.getId())
                .name("성욱")
                .price(order.getOrderPrice())
                .build();

        kafkaProducer.send("example-money-topic", build);
        return "ok";
    }
}

package Baekgwa.kafkaproducer.producer.service;

import Baekgwa.kafkaproducer.producer.entity.Order;
import Baekgwa.kafkaproducer.producer.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderJpaRepository orderJpaRepository;

    public void itemOrder(Order order){
        orderJpaRepository.save(order);
    }
}

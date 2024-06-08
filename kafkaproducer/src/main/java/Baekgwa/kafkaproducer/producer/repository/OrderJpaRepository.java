package Baekgwa.kafkaproducer.producer.repository;

import Baekgwa.kafkaproducer.producer.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {

}

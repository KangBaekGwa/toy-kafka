package Baekgwa.kafkacustomer.customer.repository;

import Baekgwa.kafkacustomer.customer.entity.Money;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyJpaRepository extends JpaRepository<Money, Long> {
    Optional<Money> findAllByName(String name);
}

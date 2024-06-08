package Baekgwa.kafkacustomer.customer.service;

import Baekgwa.kafkacustomer.customer.dto.OrderDto;
import Baekgwa.kafkacustomer.customer.entity.Money;
import Baekgwa.kafkacustomer.customer.repository.MoneyJpaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final MoneyJpaRepository moneyJpaRepository;

    @KafkaListener(topics="example-money-topic")
    public void processMessage(String kafkaMessage){
        log.info("kafka Message : {}", kafkaMessage);

        OrderDto orderDto = new OrderDto();
        ObjectMapper mapper = new ObjectMapper();
        try{
            orderDto = mapper.readValue(kafkaMessage, new TypeReference<>() {});
            log.info("findDto Id = {}", orderDto.getId());
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }

        Money findData = moneyJpaRepository.findAllByName(orderDto.getName()).orElseThrow(
                () -> new RuntimeException("찾기 실패")
        );

        Money updateMoney = Money.builder()
                .id(findData.getId())
                .name(findData.getName())
                .price(findData.getPrice() - orderDto.getPrice())
                .build();

        moneyJpaRepository.save(updateMoney);
    }
}

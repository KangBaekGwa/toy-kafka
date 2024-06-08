package Baekgwa.kafkacustomer.customer.service;

import Baekgwa.kafkacustomer.customer.entity.Money;
import Baekgwa.kafkacustomer.customer.repository.MoneyJpaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MoneyService {

    private final MoneyJpaRepository moneyJpaRepository;

//    @KafkaListener(topics="example-money-topic", clientIdPrefix = )
    @KafkaListener(topics="example-money-topic")
    public void processMessage(String kafkaMessage){
        log.info("kafka Message : {}", kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try{
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }

        Money findData = moneyJpaRepository.findAllByName((String) map.get("name")).orElseThrow(
                () -> new RuntimeException("찾기 실패")
        );

        Money updateMoney = Money.builder()
                .id(findData.getId())
                .name(findData.getName())
                .price(findData.getPrice() - (Long) map.get("price"))
                .build();

        moneyJpaRepository.save(updateMoney);
    }
}

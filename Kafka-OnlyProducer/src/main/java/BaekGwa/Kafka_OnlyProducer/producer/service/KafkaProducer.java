package BaekGwa.Kafka_OnlyProducer.producer.service;

import BaekGwa.Kafka_OnlyProducer.producer.dto.RealChartTradeStockInfoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    //todo : Order 에서 Object로 변경.
    public String send(String KafkaTopic, RealChartTradeStockInfoDto dto){
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(dto);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        kafkaTemplate.send(KafkaTopic, jsonInString);
        log.info("send Message = {}", dto.toString());

        return "send complete";
    }
}

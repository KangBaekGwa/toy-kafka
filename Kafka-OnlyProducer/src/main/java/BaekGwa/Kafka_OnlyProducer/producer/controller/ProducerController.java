package BaekGwa.Kafka_OnlyProducer.producer.controller;

import BaekGwa.Kafka_OnlyProducer.producer.dto.RealChartTradeStockInfoDto;
import BaekGwa.Kafka_OnlyProducer.producer.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final KafkaProducer kafkaProducer;

    @PostMapping("/message1")
    public String sendMessage_RealChartTradeStockInfo(
            @RequestBody RealChartTradeStockInfoDto dto
    ){
        kafkaProducer.send("realchart-trade-stockinfo",
                RealChartTradeStockInfoDto
                        .builder()
                        .stockCode(dto.getStockCode())
                        .price(dto.getPrice())
                        .date(dto.getDate())
                        .build());

        return "ok";
    }
}

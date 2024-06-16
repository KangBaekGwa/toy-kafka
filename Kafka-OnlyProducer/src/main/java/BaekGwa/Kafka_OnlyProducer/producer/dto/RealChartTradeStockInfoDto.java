package BaekGwa.Kafka_OnlyProducer.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RealChartTradeStockInfoDto {
    public String stockCode;
    public String price;
    public String date;
}

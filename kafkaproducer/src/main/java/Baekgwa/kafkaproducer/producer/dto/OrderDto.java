package Baekgwa.kafkaproducer.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDto {
    private Long id;
    private String name;
    private Long price;
}

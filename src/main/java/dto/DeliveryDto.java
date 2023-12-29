package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DeliveryDto {

        private String deliveryId;
        private String courierId;
        private Double value;
        private Instant createdTimestamp;
}

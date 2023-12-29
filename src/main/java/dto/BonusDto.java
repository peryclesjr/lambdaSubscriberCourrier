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
public class BonusDto {
    private String bonusId;
    private String deliveryId;
    private Instant modifiedTimestamp;
    private Double value;
}

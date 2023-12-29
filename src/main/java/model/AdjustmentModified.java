package model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@DynamoDBTable(tableName = "AdjustmentTransaction")
public class AdjustmentModified {
    private String adjustmentId;
    private String deliveryId;
    private Instant modifiedTimestamp;
    private Double value;
}

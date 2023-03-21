package tbd.health.exercise.dto;

import java.util.List;

public class OrderDto {

    public long customerId;
    private List<Long> productIds;

    public long getCustomerId() {
        return customerId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }
}

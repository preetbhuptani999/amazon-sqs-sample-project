package tbd.health.exercise.service;

import tbd.health.exercise.dto.OrderDto;

public interface UserService {
    void createOrder(OrderDto orderDto);

    void updateOrder(String status);
}

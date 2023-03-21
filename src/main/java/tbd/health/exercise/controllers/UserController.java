package tbd.health.exercise.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tbd.health.exercise.dto.OrderDto;
import tbd.health.exercise.dto.ResponseDto;
import tbd.health.exercise.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value= "/order")
    public ResponseDto createOrder(@RequestBody OrderDto orderDto){

        userService.createOrder(orderDto);
        return new ResponseDto("Success", "Order created successfully");
    }

    @PostMapping(value= "/labpartner/webhook")
    public void updateOrderDetails(@RequestBody String orderDetails){

        userService.updateOrder(orderDetails);
    }
}

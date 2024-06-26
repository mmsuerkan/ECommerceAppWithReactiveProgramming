package com.example.hackingspringboot;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ServerController {

   private final KitchenService kitchen;

    public ServerController(KitchenService kitchenService) {
        this.kitchen = kitchenService;
    }

    @GetMapping(value = "server", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Dish> serveDishes() {
        return this.kitchen.getDishes();
    }
    @GetMapping(value = "served-dishes", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Dish> servedDishes() {
        return this.kitchen.getDishes().map(dish -> Dish.deliver(dish));
    }


}

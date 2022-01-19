package edu.cloud.apps.rest;

import edu.cloud.apps.model.ShoppingCartDTO;
import edu.cloud.apps.service.ShoppingCartService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/shoppingCarts", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(final ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public ResponseEntity<List<ShoppingCartDTO>> getAllShoppingCarts() {
        return ResponseEntity.ok(shoppingCartService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartDTO> getShoppingCart(@PathVariable final Long id) {
        return ResponseEntity.ok(shoppingCartService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createShoppingCart(
            @RequestBody @Valid final ShoppingCartDTO shoppingCartDTO) {
        return new ResponseEntity<>(shoppingCartService.create(shoppingCartDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateShoppingCart(@PathVariable final Long id,
            @RequestBody @Valid final ShoppingCartDTO shoppingCartDTO) {
        shoppingCartService.update(id, shoppingCartDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShoppingCart(@PathVariable final Long id) {
        shoppingCartService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

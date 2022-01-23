package edu.cloud.apps.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.cloud.apps.model.ShoppingCartDTO;
import edu.cloud.apps.service.ShoppingCartService;


@RestController
@RequestMapping(value = "/api/shoppingcarts", produces = MediaType.APPLICATION_JSON_VALUE)
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
    public ResponseEntity<ShoppingCartDTO> createShoppingCart(
            @RequestBody @Valid final ShoppingCartDTO shoppingCartDTO) {
        return new ResponseEntity<>(shoppingCartService.create(shoppingCartDTO), HttpStatus.CREATED);
    }
    
    @PostMapping("/{id}/product/{productId}/quantity/{quantity}")
    public ResponseEntity<ShoppingCartDTO> addProduct(@PathVariable final Long id, @PathVariable final Long productId, @PathVariable final Integer quantity) {
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.addProduct(id, productId, quantity);
		return ResponseEntity.ok(shoppingCartDTO);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<ShoppingCartDTO> finalizeShoppingCart(@PathVariable final Long id) {
    	ShoppingCartDTO shoppingCartDTO = shoppingCartService.finalizeShoppingCart(id);
    	return ResponseEntity.ok(shoppingCartDTO);
    }
    
    @DeleteMapping("/{id}/product/{productId}")
    public ResponseEntity<ShoppingCartDTO> deleteShoppingCart(@PathVariable final Long id, @PathVariable final Long productId) {
    	ShoppingCartDTO shoppingCartDTO = shoppingCartService.delete(id, productId);
        return ResponseEntity.ok(shoppingCartDTO);
    }

}

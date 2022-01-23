package edu.cloud.apps.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import edu.cloud.apps.adapters.web.CartAdapter;
import edu.cloud.apps.adapters.web.dto.CartResponseDTO;
import edu.cloud.apps.dto.ShoppingCartDTO;


@RestController
@RequestMapping(value = "/api/shoppingcarts", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShoppingCartController {

	@Autowired
	CartAdapter shoppingCartService;
	
    @GetMapping
    public ResponseEntity<List<CartResponseDTO>> getAllShoppingCarts() {
        return ResponseEntity.ok(shoppingCartService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponseDTO> getShoppingCart(@PathVariable final Long id) {
        return ResponseEntity.ok(shoppingCartService.get(id));
    }

    @PostMapping
    public ResponseEntity<CartResponseDTO> createShoppingCart(
            @RequestBody @Valid final ShoppingCartDTO shoppingCartDTO) {
        return new ResponseEntity<>(shoppingCartService.create(shoppingCartDTO), HttpStatus.CREATED);
    }
    
    @PostMapping("/{id}/product/{productId}/quantity/{quantity}")
    public ResponseEntity<CartResponseDTO> addProduct(@PathVariable final Long id, @PathVariable final Long productId, @PathVariable final Integer quantity) {
       return ResponseEntity.ok(shoppingCartService.addProduct(id, productId, quantity));
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<CartResponseDTO> finalizeShoppingCart(@PathVariable final Long id) {
    	return ResponseEntity.ok(shoppingCartService.finalizeShoppingCart(id));
    }
    
    @DeleteMapping("/{id}/product/{productId}")
    public ResponseEntity<CartResponseDTO> deleteShoppingCart(@PathVariable final Long id, @PathVariable final Long productId) {
    	return ResponseEntity.ok(shoppingCartService.delete(id, productId));
    }

}

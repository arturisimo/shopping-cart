package edu.cloud.apps.rest;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.fasterxml.jackson.annotation.JsonView;

import edu.cloud.apps.adapters.web.CartAdapter;
import edu.cloud.apps.adapters.web.dto.CartRequestDTO;
import edu.cloud.apps.adapters.web.dto.CartResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping(value = "/api/shoppingcarts", produces = MediaType.APPLICATION_JSON_VALUE)
public class CartController {

	@Autowired
	CartAdapter cartService;
	
	@Operation(summary = "Get all shoppings carts")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "List all carts", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = CartResponseDTO.class)) }) })
    @GetMapping
    @JsonView(CartResponseDTO.Basic.class)
    public ResponseEntity<List<CartResponseDTO>> getAllShoppingCarts() {
        return ResponseEntity.ok(cartService.findAll());
    }
    
    @Operation(summary = "Get a review by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the review", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CartResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Review not found", content = @Content) })
	@GetMapping("/{id}")
    @JsonView(CartResponseDTO.Basic.class)
    public ResponseEntity<CartResponseDTO> getShoppingCart(@Parameter(description = "id of cart")  @PathVariable final Long id) {
    	return ResponseEntity.ok(cartService.get(id));
    }
    
    @Operation(summary = "create a new shopping cart")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "New cart", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = CartResponseDTO.class)) }), })
    @PostMapping
    @JsonView(CartResponseDTO.Basic.class)
    public ResponseEntity<CartResponseDTO> createShoppingCart(
            @RequestBody @Valid final CartRequestDTO cartRequestDTO) {
    	
    	CartResponseDTO createdCart = cartService.create(cartRequestDTO);
		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(createdCart.getId()).toUri();
		return ResponseEntity.created(location).body(createdCart);
    }
    
    @Operation(summary = "add a new product to and existing shopping cart")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Updated shopping cart", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CartResponseDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "cart not found", content = @Content) })
    @PostMapping("/{id}/product/{productId}/quantity/{quantity}")
    @JsonView(CartResponseDTO.Basic.class)
    public ResponseEntity<CartResponseDTO> addProduct(@Parameter(description = "id of cart") @PathVariable final Long id, @Parameter(description = "product's id")  @PathVariable final Long productId, @Parameter(description = "quantity of the product") @PathVariable final Integer quantity) {
       return ResponseEntity.ok(cartService.addProduct(id, productId, quantity));
    }
    
    @Operation(summary = "Finalize a shopping cart validating if there's enougth products of every product")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Finalized shopping cart", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CartResponseDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Cart not found", content = @Content) })
    @PatchMapping("/{id}")
    @JsonView(CartResponseDTO.Basic.class)
    public ResponseEntity<CartResponseDTO> finalizeShoppingCart(@Parameter(description = "id of cart") @PathVariable final Long id) {
    	return ResponseEntity.ok(cartService.finalizeShoppingCart(id));
    }
    
    @Operation(summary = "Delete a product from a shopping cart")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Deleted product", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CartResponseDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Cart not found", content = @Content) })
    @DeleteMapping("/{id}/product/{productId}")
    @JsonView(CartResponseDTO.Basic.class)
    public ResponseEntity<CartResponseDTO> deleteProduct(@Parameter(description = "id of cart") @PathVariable final Long id, @Parameter(description = "product's id") @PathVariable final Long productId) {
    	return ResponseEntity.ok(cartService.deleteProduct(id, productId));
    }
    
    @Operation(summary = "Delete a shopping cart by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Deleted cart", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CartResponseDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Cart not found", content = @Content) })
    @DeleteMapping("/{id}")
    @JsonView(CartResponseDTO.Basic.class)
    public ResponseEntity<CartResponseDTO> delete(@Parameter(description = "id of cart") @PathVariable final Long id) {
    	return ResponseEntity.ok(cartService.delete(id));
    }

}

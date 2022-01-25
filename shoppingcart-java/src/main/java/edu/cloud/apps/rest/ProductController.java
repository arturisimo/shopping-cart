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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import edu.cloud.apps.adapters.web.ProductAdapter;
import edu.cloud.apps.adapters.web.dto.ProductRequestDTO;
import edu.cloud.apps.adapters.web.dto.ProductResponseDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@OpenAPIDefinition (info =
@Info(
          title = "Shopping Cart REST API",
          description = "Aplicación de comercio electrónico"
  )
)
@RestController
@RequestMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
	
	@Autowired
	ProductAdapter productService;
	
	@Operation(summary = "Get all products")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "List all products", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class)) }) })
    @GetMapping
    @JsonView(ProductResponseDTO.Basic.class)
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }
	
	@Operation(summary = "Get a product by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the product", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Product not found", content = @Content) })
    @GetMapping("/{id}")
	@JsonView(ProductResponseDTO.Basic.class)
	public ResponseEntity<ProductResponseDTO> getProduct(@Parameter(description = "id of product") @PathVariable final Long id) {
        return ResponseEntity.ok(productService.get(id));
    }
	
	@Operation(summary = "create a new product")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "New product", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class)) }), })
    @PostMapping
    @JsonView(ProductResponseDTO.Basic.class)
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid final ProductRequestDTO productDTO) {
		
		ProductResponseDTO createdProduct = productService.create(productDTO);
		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(createdProduct.getId()).toUri();
		return ResponseEntity.created(location).body(createdProduct);
    }
	
	@Operation(summary = "update the stock of a product with a quantity")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Updated product", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Product not found", content = @Content) })
    @PutMapping("/{id}/stock/{quantity}")
	@JsonView(ProductResponseDTO.Basic.class)
	public ResponseEntity<ProductResponseDTO> updateProduct(@Parameter(description = "id of product") @PathVariable final Long id, @Parameter(description = "quantity of product") @PathVariable final Integer quantity) {
    	return ResponseEntity.ok(productService.updateStock(id, quantity));
    }
	
	@Operation(summary = "Delete a product by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Deleted product", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Product not found", content = @Content) })
    @DeleteMapping("/{id}")
	@JsonView(ProductResponseDTO.Basic.class)
	public ResponseEntity<ProductResponseDTO> deleteProduct(@Parameter(description = "id of product") @PathVariable final Long id) {
    	return ResponseEntity.ok(productService.delete(id));
    }

}

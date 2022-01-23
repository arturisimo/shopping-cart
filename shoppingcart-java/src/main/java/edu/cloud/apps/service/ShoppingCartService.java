package edu.cloud.apps.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.cloud.apps.domain.Product;
import edu.cloud.apps.domain.ProductCart;
import edu.cloud.apps.domain.ShoppingCart;
import edu.cloud.apps.exception.FinalizedCartException;
import edu.cloud.apps.exception.StockException;
import edu.cloud.apps.model.ProductCartDTO;
import edu.cloud.apps.model.ShoppingCartDTO;
import edu.cloud.apps.repos.ProductRepository;
import edu.cloud.apps.repos.ShoppingCartRepository;


@Service
public class ShoppingCartService {

	private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    public ShoppingCartService(final ShoppingCartRepository shoppingCartRepository, final ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
    }

    public List<ShoppingCartDTO> findAll() {
        return shoppingCartRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ShoppingCartDTO get(final Long id) {
        return shoppingCartRepository.findById(id)
        		.map(this::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public ShoppingCartDTO create(final ShoppingCartDTO shoppingCartDTO) {
        final ShoppingCart shoppingCart = toEntity(shoppingCartDTO);
        return toDto(shoppingCartRepository.save(shoppingCart));
    }

    public ShoppingCartDTO finalizeShoppingCart(Long id) {
       ShoppingCart shoppingCart = findActiveCart(id);
       shoppingCart = validate(shoppingCart);
       shoppingCart.setFinalized(true);
       shoppingCart.getProducts().stream().map(ProductCart::getProduct)
       									  .forEach(product -> productRepository.save(product));
       return toDto(shoppingCartRepository.save(shoppingCart));
	}

	public ShoppingCartDTO delete(final Long id, Long productId) {
    	ShoppingCart shoppingCart = findActiveCart(id);
		
    	Set<ProductCart> products = shoppingCart.getProducts().stream().filter(product -> productId != product.getProduct().getId()).collect(Collectors.toSet()); 
    	
    	if (products.size() == shoppingCart.getProducts().size())
    		 throw new ResponseStatusException(HttpStatus.NOT_FOUND);	
    	
    	shoppingCart.setProducts(products);
    	return toDto(shoppingCartRepository.save(shoppingCart));
    }

	public ShoppingCartDTO addProduct(Long id, Long productId, Integer quantity) {
		
		ShoppingCart shoppingCart = findActiveCart(id);
		
		Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		
		ProductCart productCart = new ProductCart(product, quantity);
		shoppingCart = addProduct(shoppingCart, productCart);
		
		return toDto(shoppingCartRepository.save(shoppingCart));
	}
	
	private ShoppingCart addProduct(ShoppingCart shoppingCart, ProductCart productCart) {
		validateStock(productCart);
		Optional<ProductCart> opt = shoppingCart.getProducts().stream().filter(p -> p.getProduct().getId() == productCart.getProduct().getId()).findAny();
		opt.ifPresentOrElse(
				product -> {
					Integer newQuantity = product.getQuantity() + productCart.getQuantity();
					product.setQuantity(newQuantity);
					validateStock(product);
				},
				() -> shoppingCart.getProducts().add(productCart));
		return shoppingCart;
	}
	
	private void validateStock(ProductCart productCart) {
		Product product = productCart.getProduct();
		Integer newStock = product.getStock()-productCart.getQuantity();
		if (newStock < 0 )
			throw new StockException(product.getName());
	}
	
	private ShoppingCart validate(ShoppingCart shoppingCart) {
		shoppingCart.getProducts().forEach(productCart -> {
			Product product = productCart.getProduct();
			Integer newStock = product.getStock() - productCart.getQuantity();
			if (newStock < 0) {
				throw new StockException(product.getName());
			} else {
				product.setStock(newStock);
			}
		});
		return shoppingCart;
	}
	
	private ShoppingCart findActiveCart(Long id) {
		ShoppingCart shoppingCart = shoppingCartRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		if (shoppingCart.getFinalized())
			throw new FinalizedCartException(shoppingCart.getId());
		return shoppingCart;
	}
	
	private ShoppingCartDTO toDto(ShoppingCart shoppingCart) {
		ShoppingCartDTO shoppingCartDTO = modelMapper.map(shoppingCart, ShoppingCartDTO.class);
		shoppingCartDTO.setProducts(shoppingCart.getProducts().stream().map(this::productToDto).collect(Collectors.toSet()));
		return shoppingCartDTO;
	}
	
	private ProductCartDTO productToDto(ProductCart product) {
		return modelMapper.map(product, ProductCartDTO.class);
	}
	
	
	private ShoppingCart toEntity(ShoppingCartDTO shoppingCartDTO) {
		return  modelMapper.map(shoppingCartDTO, ShoppingCart.class);
	}


}

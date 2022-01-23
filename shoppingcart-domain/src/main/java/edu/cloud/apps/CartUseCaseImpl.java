package edu.cloud.apps;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import edu.cloud.apps.dto.ProductCartDTO;
import edu.cloud.apps.dto.ProductDTO;
import edu.cloud.apps.dto.ShoppingCartDTO;
import edu.cloud.apps.exception.FinalizedCartException;
import edu.cloud.apps.exception.NotFoundInCartException;
import edu.cloud.apps.model.Product;
import edu.cloud.apps.model.ProductCart;
import edu.cloud.apps.model.ShoppingCart;
import edu.cloud.apps.ports.db.CartRepositoryUseCase;
import edu.cloud.apps.ports.db.ProductRepositoryUseCase;
import edu.cloud.apps.ports.web.CartUseCase;

public class CartUseCaseImpl implements CartUseCase {
	
	private CartRepositoryUseCase cartRepository;
	private ProductRepositoryUseCase productRepository;
	private ModelMapper modelMapper;

	public CartUseCaseImpl(CartRepositoryUseCase cartRepository, ProductRepositoryUseCase productRepository, ModelMapper modelMapper) {
		this.cartRepository = cartRepository;
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public List<ShoppingCartDTO> findAll() {
		return cartRepository.findAll();
				
	}

	@Override
	public ShoppingCartDTO get(Long id) {
		return cartRepository.findById(id).orElseThrow();
	}

	@Override
	public ShoppingCartDTO create(ShoppingCartDTO shoppingCartDTO) {
		return cartRepository.save(shoppingCartDTO);
	}

	@Override
	public ShoppingCartDTO finalizeShoppingCart(Long id) {
		ShoppingCart shoppingCart = toModel(findActiveCart(id));
		shoppingCart = shoppingCart.validate();
		shoppingCart.setFinalized(true);
		return cartRepository.save(toDto(shoppingCart));
	}

	@Override
	public ShoppingCartDTO delete(Long id, Long productId) {
		ShoppingCartDTO shoppingCart = findActiveCart(id);
		
		Set<ProductCartDTO> products = shoppingCart.getProducts().stream()
					.filter(product -> productId != product.getProduct().getId()).collect(Collectors.toSet()); 
    	
    	if (products.size() == shoppingCart.getProducts().size())
    		 throw new NotFoundInCartException();	
    	
    	shoppingCart.setProducts(products);
    	shoppingCart.getProducts().stream().map(ProductCartDTO::getProduct)
    			.forEach(product -> productRepository.save(product));
		return cartRepository.save(shoppingCart);
	}

	@Override
	public ShoppingCartDTO addProduct(Long id, Long productId, Integer quantity) {
		ShoppingCart shoppingCart = toModel(findActiveCart(id));
		Product product = toProductModel(productRepository.findById(productId).orElseThrow());
		
		Optional<ProductCart> opt = shoppingCart.getProducts().stream().filter(p -> p.getProduct().getId() == product.getId()).findAny();
		opt.ifPresentOrElse(
				productCart -> {
					Integer newQuantity = productCart.getQuantity() + quantity;
					productCart.setQuantity(newQuantity);
					productCart.validateStock();
				},
				() -> shoppingCart.getProducts().add(new ProductCart(product, quantity)));
		
		return cartRepository.save(toDto(shoppingCart));
	}
	
	
	private ShoppingCartDTO findActiveCart(Long id) {
		ShoppingCartDTO shoppingCart = get(id);
		
		if (shoppingCart.getFinalized())
			throw new FinalizedCartException(shoppingCart.getId());
		return shoppingCart;
	}

	private Product toProductModel(ProductDTO productDto) {
		return modelMapper.map(productDto, Product.class);
	}
	
	private ShoppingCartDTO toDto(ShoppingCart ShoppingCart) {
		return modelMapper.map(ShoppingCart, ShoppingCartDTO.class);
	}
	private ShoppingCart toModel(ShoppingCartDTO shoppingCartDTO) {
		return modelMapper.map(shoppingCartDTO, ShoppingCart.class);
	}
	
}
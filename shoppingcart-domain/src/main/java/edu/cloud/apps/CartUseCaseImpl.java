package edu.cloud.apps;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import edu.cloud.apps.dto.ProductCartDTO;
import edu.cloud.apps.dto.ProductDTO;
import edu.cloud.apps.dto.CartDTO;
import edu.cloud.apps.exception.FinalizedCartException;
import edu.cloud.apps.exception.NotFoundInCartException;
import edu.cloud.apps.model.Product;
import edu.cloud.apps.model.ProductCart;
import edu.cloud.apps.model.Cart;
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
	public List<CartDTO> findAll() {
		return cartRepository.findAll();
	}

	@Override
	public CartDTO get(Long id) {
		return cartRepository.findById(id).orElseThrow();
	}

	@Override
	public CartDTO create(CartDTO shoppingCartDTO) {
		return cartRepository.save(shoppingCartDTO);
	}

	@Override
	public CartDTO finalizeShoppingCart(Long id) {
		Cart shoppingCart = toModel(findActiveCart(id));
		shoppingCart = shoppingCart.validate();
		shoppingCart.setFinalized(true);
		CartDTO shoppingCartDto = toDto(shoppingCart);
		shoppingCartDto.getProducts().stream().map(ProductCartDTO::getProduct)
			.forEach(product -> productRepository.save(product));
		return cartRepository.save(shoppingCartDto);
	}
	
	@Override
	public CartDTO remove(Long id) {
		return cartRepository.deleteById(id).orElseThrow();
	}
	
	@Override
	public CartDTO removeProduct(Long id, Long productId) {
		CartDTO shoppingCart = findActiveCart(id);
		
		Set<ProductCartDTO> products = shoppingCart.getProducts().stream()
					.filter(product -> productId != product.getProduct().getId()).collect(Collectors.toSet()); 
    	
    	if (products.size() == shoppingCart.getProducts().size())
    		 throw new NotFoundInCartException(productId + "is not in the shopping cart");	
    	
    	shoppingCart.setProducts(products);
    	shoppingCart.getProducts().stream().map(ProductCartDTO::getProduct)
    			.forEach(product -> productRepository.save(product));
		return cartRepository.save(shoppingCart);
	}

	@Override
	public CartDTO addProduct(Long id, Long productId, Integer quantity) {
		Cart cart = toModel(findActiveCart(id));
		Product product = toProductModel(productRepository.findById(productId).orElseThrow());
		
		Optional<ProductCart> opt = cart.getProducts().stream().filter(p -> p.getProduct().getId() == product.getId()).findAny();
		opt.ifPresentOrElse(
				productCart -> {
					Integer newQuantity = productCart.getQuantity() + quantity;
					productCart.setQuantity(newQuantity);
					productCart.validateStock();
				},
				() -> cart.getProducts().add(new ProductCart(product, quantity)));
		
		return cartRepository.save(toDto(cart));
	}
	
	
	private CartDTO findActiveCart(Long id) {
		CartDTO shoppingCart = get(id);
		
		if (shoppingCart.getFinalized())
			throw new FinalizedCartException(shoppingCart.getId());
		return shoppingCart;
	}

	private Product toProductModel(ProductDTO productDto) {
		return modelMapper.map(productDto, Product.class);
	}
	
	private CartDTO toDto(Cart cart) {
		return modelMapper.map(cart, CartDTO.class);
	}
	private Cart toModel(CartDTO cartDTO) {
		return modelMapper.map(cartDTO, Cart.class);
	}
	
}
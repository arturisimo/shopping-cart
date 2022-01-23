package edu.cloud.apps.adapters.db;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import edu.cloud.apps.adapters.db.entity.CartEntity;
import edu.cloud.apps.dto.ShoppingCartDTO;
import edu.cloud.apps.ports.db.CartRepositoryUseCase;
import edu.cloud.apps.repos.CartRepository;

@Service
public class CartRepositoryAdapter implements CartRepositoryUseCase {
	
	CartRepository cartRepository;
	
	ModelMapper modelMapper;
	
	public CartRepositoryAdapter(CartRepository cartRepository, ModelMapper modelMapper) {
		super();
		this.cartRepository = cartRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<ShoppingCartDTO> findAll() {
		return cartRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public Optional<ShoppingCartDTO> findById(Long id) {
		return cartRepository.findById(id).map(this::toDto);
	}

	@Override
	public ShoppingCartDTO save(ShoppingCartDTO cartDTO) {
		return toDto(cartRepository.save(toEntity(cartDTO)));
	}


	@Override
	public Optional<ShoppingCartDTO> delete(Long id, Long productId) {
		Optional<ShoppingCartDTO> cart = cartRepository.findById(id).map(this::toDto);
		cart.ifPresent(p -> cartRepository.deleteById(id));
		return cart;
	}
	
	private ShoppingCartDTO toDto(CartEntity cart) {
		return modelMapper.map(cart, ShoppingCartDTO.class);
	}

	private CartEntity toEntity(ShoppingCartDTO cartDTO) {
		return modelMapper.map(cartDTO, CartEntity.class);
	}
	
}

package edu.cloud.apps.service;

import edu.cloud.apps.domain.ShoppingCart;
import edu.cloud.apps.model.ShoppingCartDTO;
import edu.cloud.apps.repos.ShoppingCartRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(final ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public List<ShoppingCartDTO> findAll() {
        return shoppingCartRepository.findAll()
                .stream()
                .map(shoppingCart -> mapToDTO(shoppingCart, new ShoppingCartDTO()))
                .collect(Collectors.toList());
    }

    public ShoppingCartDTO get(final Long id) {
        return shoppingCartRepository.findById(id)
                .map(shoppingCart -> mapToDTO(shoppingCart, new ShoppingCartDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ShoppingCartDTO shoppingCartDTO) {
        final ShoppingCart shoppingCart = new ShoppingCart();
        mapToEntity(shoppingCartDTO, shoppingCart);
        return shoppingCartRepository.save(shoppingCart).getId();
    }

    public void update(final Long id, final ShoppingCartDTO shoppingCartDTO) {
        final ShoppingCart shoppingCart = shoppingCartRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(shoppingCartDTO, shoppingCart);
        shoppingCartRepository.save(shoppingCart);
    }

    public void delete(final Long id) {
        shoppingCartRepository.deleteById(id);
    }

    private ShoppingCartDTO mapToDTO(final ShoppingCart shoppingCart,
            final ShoppingCartDTO shoppingCartDTO) {
        shoppingCartDTO.setId(shoppingCart.getId());
        shoppingCartDTO.setFinalized(shoppingCart.getFinalized());
        return shoppingCartDTO;
    }

    private ShoppingCart mapToEntity(final ShoppingCartDTO shoppingCartDTO,
            final ShoppingCart shoppingCart) {
        shoppingCart.setFinalized(shoppingCartDTO.getFinalized());
        return shoppingCart;
    }

}

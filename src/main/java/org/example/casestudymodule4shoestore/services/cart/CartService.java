package org.example.casestudymodule4shoestore.services.cart;

import org.example.casestudymodule4shoestore.models.Cart;
import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.repositories.cart.ICartRepository;
import org.example.casestudymodule4shoestore.services.IGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CartService implements IGenerateService<Cart> {
    @Autowired
    private ICartRepository cartRepository;

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Page<Cart> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return Optional.empty();
    }


    public Cart findCartByCustomerId(Integer customerId){
        return cartRepository.findByCustomerId(customerId);
    }
}

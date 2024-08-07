package org.example.casestudymodule4shoestore.services.cart;

import jakarta.transaction.Transactional;
import org.example.casestudymodule4shoestore.models.Cart;
import org.example.casestudymodule4shoestore.models.Product;
import org.example.casestudymodule4shoestore.repositories.cart.ICartDetailRepository;
import org.example.casestudymodule4shoestore.repositories.cart.ICartRepository;
import org.example.casestudymodule4shoestore.services.IGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CartService implements IGenerateService<Cart> {
    @Autowired
    private ICartRepository cartRepository;
    @Autowired
    private ICartDetailRepository cartDetailRepository;

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return Optional.empty();
    }


    public Cart findCartByCustomerId(Long customerId){
        return cartRepository.findByCustomerId(customerId);
    }
    @Transactional
    public Cart save(Cart cart){
        return cartRepository.save(cart);
    }
    public void remove(Cart cart){
        cartRepository.delete(cart);
    }

    public void deleteCartItem(Long idCart, Long idProduct, Long idSize) {
        cartDetailRepository.deleteCartItem(idCart,idProduct,idSize);
    }
}

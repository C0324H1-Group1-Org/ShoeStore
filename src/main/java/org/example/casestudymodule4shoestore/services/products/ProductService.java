package org.example.casestudymodule4shoestore.services.products;

import org.example.casestudymodule4shoestore.dtos.product.CartDTO;
import org.example.casestudymodule4shoestore.models.*;
import org.example.casestudymodule4shoestore.repositories.cart.ICartDetailRepository;
import org.example.casestudymodule4shoestore.repositories.products.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ISizeRepository sizeRepository;
    @Autowired
    private IBrandRepository brandRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IProductSizeRepository productSizeRepository;
    @Autowired
    private ICartDetailRepository cartDetailRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

   @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }
    public void remove(Long id){
        productRepository.deleteById(id);
    }

    public List<Size> findAllSize(){
        return sizeRepository.findAll();
    }
    public List<Category> findAllCategory(){
        return categoryRepository.findAll();
    }
    public List<Brand> findAllBrand(){
        return brandRepository.findAll();
    }
    public void saveAllProductSize(List<ProductSize> productSizes){
        productSizeRepository.saveAll(productSizes);
    }

    public CartDetail existByProductWhereCartId(Product product, Cart cart, Size size){
        return cartDetailRepository.findCartDetailByIdCartAndIdProductAndIdSize(cart,product,size);
    }

    public void saveCartDetail(CartDetail cartDetail){
        cartDetailRepository.save(cartDetail);
    }

    public Optional<Size> findSizeById(int size){
        return sizeRepository.findById(size);
    }





    public boolean addProduct(Integer productId, int quantity, int size,int idCart) {
        return productRepository.addProduct(productId,quantity,size,idCart);
    }

    public int findIdCart(int idCustomer) {
        return productRepository.findIdCart(idCustomer);
    }

    public List<Product> sortProductsByPrice(){
        return productRepository.sortProductsByPrice();
    }

    @Override
    public Iterable<Product> findProductByCategory(Integer id) {
        return productRepository.findAllByCat_Id(id);
    }

    @Override
    public Page<Product> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1,pageSize);
        return productRepository.findAll(pageable);

    }

    @Override
    public Page<Product> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1, 9);
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> findProductByName(String name) {
        return productRepository.findAllByNameContaining("%" + name + "%");

    }
}

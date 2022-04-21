package ma.enset.ecom.web;

import ma.enset.ecom.entities.Product;
import ma.enset.ecom.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductRestController {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping(path = "/products")
    public List<Product>  productList(){
        return productRepository.findAll();
    }
    @GetMapping(path = "/products/{id}")
    public Product  getProduct(@PathVariable(name = "id") String id){
        return productRepository.findById(id).get();
    }
    @PostMapping(path = "/products")
    public Product saveProduct(@RequestBody  Product product){
        product.setId(UUID.randomUUID().toString());
        return productRepository.save(product);
    }
    @PutMapping(path = "/products/{id}")
    public Product updateProduct(@RequestBody  Product product,@PathVariable String id){
        product.setId(id);
        return productRepository.save(product);
    }
    @DeleteMapping(path = "/products/{id}")
    public void deleteProduct(@PathVariable String id){

         productRepository.deleteById(id);
    }
}

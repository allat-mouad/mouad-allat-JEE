package ma.enset.ecom.service;

import ma.enset.ecom.dtos.ProductDTO;
import ma.enset.ecom.entities.Category;
import ma.enset.ecom.entities.Product;
import ma.enset.ecom.mappers.CatalogMappers;
import ma.enset.ecom.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CatalogMappers catalogMappers;
    @Override
    public ProductDTO save(ProductDTO productDTO){
        Product product=catalogMappers.fromProductDTO(productDTO);
        Product savedProduct=productRepository.save(product);
       return catalogMappers.fromProduct(savedProduct);

    }
    @Override
    public List<ProductDTO> (ProductDTO productDTO){
        Product product=catalogMappers.fromProductDTO(productDTO);
        Product savedProduct=productRepository.save(product);
        return catalogMappers.fromProduct(savedProduct);

    }
}

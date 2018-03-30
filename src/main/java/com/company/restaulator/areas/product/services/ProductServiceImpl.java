package com.company.restaulator.areas.product.services;

import com.company.restaulator.areas.DTOConverter;
import com.company.restaulator.areas.product.dtos.ProductDTO;
import com.company.restaulator.areas.product.entities.Product;
import com.company.restaulator.areas.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<ProductDTO> findAllBySupplier(long supplierId) {
        List<Product> products = this.productRepo.findAllBySupplier(supplierId);
        List<ProductDTO> result = products.stream()
                .map(p -> DTOConverter.convert(p, ProductDTO.class))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public void save(ProductDTO product) {
        Product p = DTOConverter.convert(product, Product.class);
        this.productRepo.save(p);
    }

    @Override
    public ProductDTO findById(long id) {
        Product product = this.productRepo.findById(id);
        return DTOConverter.convert(product, ProductDTO.class);
    }

    @Override
    public void delete(ProductDTO product) {
        Product p = this.productRepo.findById(product.getId());
        if(p != null) {
            this.productRepo.delete(p);
        }
    }
}

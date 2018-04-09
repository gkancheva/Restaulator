package com.company.restaulator.areas.product.services;

import com.company.restaulator.areas.DTOConverter;
import com.company.restaulator.areas.product.dtos.IngredientDTO;
import com.company.restaulator.areas.product.dtos.ProductDTO;
import com.company.restaulator.areas.product.entities.Ingredient;
import com.company.restaulator.areas.product.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepo;
    private final ProductService productService;

    public IngredientServiceImpl(IngredientRepository ingredientRepo, ProductService productService) {
        this.ingredientRepo = ingredientRepo;
        this.productService = productService;
    }

    @Override
    public List<IngredientDTO> findAll() {
        List<Ingredient> ingredients = this.ingredientRepo.findAll();
        return ingredients.stream()
                .map(i -> DTOConverter.convert(i, IngredientDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<IngredientDTO> findAllOrdered() {
        List<Ingredient> ingredients = this.ingredientRepo.findAllDelivered();
        return ingredients.stream()
                .map(i -> DTOConverter.convert(i, IngredientDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<IngredientDTO> findAllIngredientsForOrderBySupplierId(long supplierId) {
        List<ProductDTO> products = this.productService.findAllBySupplier(supplierId);
        List<IngredientDTO> ingredients = new ArrayList<>();
        products.forEach(p -> ingredients.add(new IngredientDTO(p)));
        return ingredients;
    }

    @Override
    public void save(IngredientDTO ingredientDTO) {
        Ingredient ingredient = DTOConverter.convert(ingredientDTO, Ingredient.class);
        this.ingredientRepo.save(ingredient);
    }

    @Override
    public IngredientDTO findById(long id) {
        return DTOConverter.convert(this.ingredientRepo.findById(id), IngredientDTO.class);
    }
}
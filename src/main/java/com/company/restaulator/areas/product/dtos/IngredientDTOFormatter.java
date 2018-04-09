package com.company.restaulator.areas.product.dtos;

import com.company.restaulator.areas.product.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
public class IngredientDTOFormatter implements Formatter<IngredientDTO> {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientDTOFormatter(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @Override
    public IngredientDTO parse(String s, Locale locale) throws ParseException {
        long id = Long.parseLong(s);
        return this.ingredientService.findById(id);
    }

    @Override
    public String print(IngredientDTO ingredient, Locale locale) {
        return (ingredient != null ? String.valueOf(ingredient.getId()) : "");
    }
}

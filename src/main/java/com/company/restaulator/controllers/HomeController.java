package com.company.restaulator.controllers;

import com.company.restaulator.models.entities.Meal;
import com.company.restaulator.models.entities.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

    private static final String INDEX = "index";

    @GetMapping("/")
    public ModelAndView home(ModelAndView mav) {
        List<Meal> meals = new ArrayList<>();
        Recipe musaka = new Recipe("Мусака", new ArrayList<>(), 0.400, new BigDecimal(2.00));
        meals.add(new Meal(musaka, 40, new BigDecimal(7.00)));
        Recipe orizSasPile = new Recipe("Ориз с пиле", new ArrayList<>(), 0.300, new BigDecimal(3.00));
        meals.add(new Meal(orizSasPile, 60, new BigDecimal(7.5)));
        Recipe kufteNaSkara = new Recipe("Кюфтета на скара с гарнитура", new ArrayList<>(), 0.350, new BigDecimal(4.00));
        meals.add(new Meal(kufteNaSkara, 60, new BigDecimal(8.5)));
        Recipe cremeCraamel = new Recipe("Крем карамел", new ArrayList<>(), 0.150, new BigDecimal(1.00));
        meals.add(new Meal(cremeCraamel, 60, new BigDecimal(3.5)));
        mav.addObject("meals", meals);
        return this.view(INDEX, "meals", meals);
    }
}

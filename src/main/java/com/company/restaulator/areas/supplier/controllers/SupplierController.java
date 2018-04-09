package com.company.restaulator.areas.supplier.controllers;

import com.company.restaulator.areas.product.dtos.IngredientDTO;
import com.company.restaulator.areas.product.services.IngredientService;
import com.company.restaulator.areas.product.services.ProductService;
import com.company.restaulator.areas.supplier.dtos.OrderCreateDTO;
import com.company.restaulator.areas.supplier.services.OrderService;
import com.company.restaulator.controllers.BaseController;
import com.company.restaulator.areas.supplier.dtos.SupplierDTO;
import com.company.restaulator.areas.supplier.services.SupplierService;
import com.company.restaulator.utils.MessagesConst;
import com.company.restaulator.utils.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/suppliers")
public class SupplierController extends BaseController {

    private static final String SUPPLIER_KEY = "supplier";
    private static final String SUPPLIERS_KEY = "suppliers";
    private static final String PRODUCTS_KEY = "products";
    private static final String ADD_SUPPLIER_VIEW = "suppliers/add-supplier";
    private static final String EDIT_SUPPLIER_VIEW = "suppliers/edit-supplier";
    private static final String SUPPLIERS_ALL_VIEW = "suppliers/suppliers";
    private static final String SUPPLIERS_ALL_REDIRECT = "suppliers";
    private static final String SUPPLIER_ORDER = "suppliers/make-order";

    private static final String ORDER_KEY = "order";

    private final SupplierService supplierService;
    private final ProductService productService;
    private final IngredientService ingredientService;
    private final OrderService orderService;

    @Autowired
    public SupplierController(SupplierService supplierService, ProductService productService, IngredientService ingredientService, OrderService orderService) {
        this.supplierService = supplierService;
        this.productService = productService;
        this.ingredientService = ingredientService;
        this.orderService = orderService;
    }

    @GetMapping
    public ModelAndView all() {
        List<SupplierDTO> suppliers = this.supplierService.findAll();
        return this.view(SUPPLIERS_ALL_VIEW, SUPPLIERS_KEY, suppliers);
    }

    @GetMapping("/add")
    public ModelAndView add(@ModelAttribute(SUPPLIER_KEY)SupplierDTO supplier) {
        return this.view(ADD_SUPPLIER_VIEW, SUPPLIER_KEY, supplier);
    }

    @PostMapping("/add")
    public ModelAndView add(@Valid @ModelAttribute(SUPPLIER_KEY) SupplierDTO supplier, BindingResult br) {
        if(br.hasErrors()) {
            return this.view(ADD_SUPPLIER_VIEW);
        }
        SupplierDTO s = this.supplierService.findByName(supplier.getName());
        if(s != null) {
            return this.viewWithMessage(ADD_SUPPLIER_VIEW, MessagesConst.SUPPLIER_ALREADY_EXISTS, Notification.Type.DANGER);
        }
        this.supplierService.save(supplier);
        return this.redirect(SUPPLIERS_ALL_REDIRECT);
    }

    @GetMapping("/edit")
    public ModelAndView edit(@ModelAttribute(SUPPLIER_KEY) SupplierDTO supplier) {
        supplier = this.supplierService.findByName(supplier.getName());
        return this.view(EDIT_SUPPLIER_VIEW, SUPPLIER_KEY, supplier);
    }

    @PostMapping("/edit")
    public ModelAndView edit(@Valid @ModelAttribute(SUPPLIER_KEY) SupplierDTO supplier, BindingResult br, RedirectAttributes ra) {
        if(br.hasErrors()) {
            return this.view(EDIT_SUPPLIER_VIEW);
        }
        this.supplierService.save(supplier);
        return this.redirectWithMessage(SUPPLIERS_ALL_REDIRECT, MessagesConst.SUPPLIER_SUCCESSFULLY_EDITED, Notification.Type.SUCCESS, ra);
    }

    @GetMapping("/order")
    public ModelAndView order(@ModelAttribute(SUPPLIER_KEY) SupplierDTO supplier, @ModelAttribute(ORDER_KEY) OrderCreateDTO order) {
        List<IngredientDTO> ingredients = this.ingredientService.findAllIngredientsForOrderBySupplierId(supplier.getId());
        order.setSupplier(this.supplierService.findById(supplier.getId()));
        order.setIngredients(ingredients);
        return this.view(SUPPLIER_ORDER, PRODUCTS_KEY, this.productService.findAllBySupplier(supplier.getId()));
    }

    @PostMapping("/order")
    public ModelAndView order(@ModelAttribute(ORDER_KEY) OrderCreateDTO order, BindingResult br) {
        String debug = "";
        if(br.hasErrors()) {
            return this.view(SUPPLIER_ORDER, PRODUCTS_KEY, this.productService.findAllBySupplier(order.getSupplier().getId()));
        }
        this.orderService.save(order);
        return new ModelAndView("");
    }
}
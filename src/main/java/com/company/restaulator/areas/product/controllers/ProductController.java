package com.company.restaulator.areas.product.controllers;

import com.company.restaulator.areas.product.dtos.ProductDTO;
import com.company.restaulator.areas.product.services.ProductService;
import com.company.restaulator.areas.supplier.dtos.SupplierDTO;
import com.company.restaulator.areas.supplier.services.SupplierService;
import com.company.restaulator.controllers.BaseController;
import com.company.restaulator.utils.Messages;
import com.company.restaulator.utils.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductController extends BaseController {

    private static final String PRODUCT_KEY = "product";
    private static final String PRODUCTS_KEY = "products";
    private static final String SUPPLIER_KEY = "supplier";
    private static final String SUPPLIERS_KEY = "suppliers";
    private static final String SUPPLIERS_VIEW_ALL = "suppliers";
    private static final String PRODUCTS_VIEW = "products/products";
    private static final String PRODUCT_ADD_VIEW = "products/add-product";
    private static final String PRODUCT_EDIT_VIEW = "products/edit-product";
    private static final String PRODUCT_DELETE_VIEW = "products/delete-product";

    private final ProductService productService;
    private final SupplierService supplierService;

    @Autowired
    public ProductController(ProductService productService, SupplierService supplierService) {
        this.productService = productService;
        this.supplierService = supplierService;
    }

    @GetMapping
    public ModelAndView productsBySupplier(@ModelAttribute(SUPPLIER_KEY)SupplierDTO supplier) {
        supplier = this.supplierService.findByName(supplier.getName());
        List<ProductDTO> products = this.productService.findAllBySupplier(supplier.getId());
        Map<String, Object> map = new HashMap<>();
        map.put(PRODUCTS_KEY, products);
        map.put(SUPPLIER_KEY, supplier);
        return this.view(PRODUCTS_VIEW, map);
    }

    @GetMapping("/add")
    public ModelAndView add(@ModelAttribute(PRODUCT_KEY) ProductDTO product) {
        return this.view(PRODUCT_ADD_VIEW, this.getSuppliersAndProduct(product));
    }

    @PostMapping("/add")
    public ModelAndView add(@Valid @ModelAttribute(PRODUCT_KEY) ProductDTO product, BindingResult br) {
        if(br.hasErrors()) {
            return this.view(PRODUCT_ADD_VIEW, this.getSuppliersAndProduct(product));
        }
        this.productService.save(product);
        return this.redirect(SUPPLIERS_VIEW_ALL);
    }

    @GetMapping("/edit")
    public ModelAndView edit(@ModelAttribute(PRODUCT_KEY) ProductDTO product) {
        product = this.productService.findById(product.getId());
        return this.view(PRODUCT_EDIT_VIEW, this.getSuppliersAndProduct(product));
    }

    @PostMapping("/edit")
    public ModelAndView edit(@Valid @ModelAttribute(PRODUCT_KEY) ProductDTO product, BindingResult br, RedirectAttributes ra) {
        if(br.hasErrors()) {
            return this.view(PRODUCT_EDIT_VIEW, this.getSuppliersAndProduct(product));
        }
        this.productService.save(product);
        return this.redirectWithMessage(SUPPLIERS_VIEW_ALL, Messages.PRODUCT_SUCCESSFULLY_EDITED, Notification.Type.SUCCESS, ra);
    }

    @GetMapping("/delete")
    public ModelAndView delete(@ModelAttribute(PRODUCT_KEY) ProductDTO product) {
        product = this.productService.findById(product.getId());
        return this.view(PRODUCT_DELETE_VIEW, this.getSuppliersAndProduct(product));
    }

    @PostMapping("/delete")
    public ModelAndView deleteSubmit(@ModelAttribute(PRODUCT_KEY) ProductDTO product, RedirectAttributes ra) {
        this.productService.delete(product);
        return this.redirectWithMessage(SUPPLIERS_VIEW_ALL, Messages.PRODUCT_SUCCESSFULLY_DELETED, Notification.Type.SUCCESS, ra);
    }

    private Map<String, Object> getSuppliersAndProduct(ProductDTO product) {
        Map<String, Object> map = new HashMap<>();
        List<SupplierDTO> suppliers = this.supplierService.findAll();
        map.put(SUPPLIERS_KEY, suppliers);
        map.put(PRODUCT_KEY, product);
        return map;
    }

}
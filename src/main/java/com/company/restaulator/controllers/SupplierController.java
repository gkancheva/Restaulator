package com.company.restaulator.controllers;

import com.company.restaulator.models.dtos.SupplierDTO;
import com.company.restaulator.services.SupplierService;
import com.company.restaulator.utils.Messages;
import com.company.restaulator.utils.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/suppliers")
public class SupplierController extends BaseController {

    private static final String SUPPLIER_KEY = "supplier";
    private static final String SUPPLIERS_KEY = "suppliers";
    private static final String ADD_SUPPLIER_VIEW = "suppliers/add-supplier";
    private static final String EDIT_SUPPLIER_VIEW = "suppliers/edit-supplier";
    private static final String SUPPLIERS_ALL_VIEW = "suppliers/suppliers";
    private static final String SUPPLIERS_ALL_REDIRECT = "suppliers";

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
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
            return this.viewWithMessage(ADD_SUPPLIER_VIEW, Messages.SUPPLIER_ALREADY_EXISTS, Notification.Type.DANGER);
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
    public ModelAndView edit(@Valid @ModelAttribute(SUPPLIER_KEY) SupplierDTO supplier, BindingResult br) {
        if(br.hasErrors()) {
            return this.view(EDIT_SUPPLIER_VIEW);
        }
        this.supplierService.save(supplier);
        return this.redirect(SUPPLIERS_ALL_REDIRECT);
    }
}
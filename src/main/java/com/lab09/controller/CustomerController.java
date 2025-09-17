package com.lab09.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lab09.model.Customer;
import com.lab09.dto.CustomerResponse;
import com.lab09.service.WebCustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class CustomerController {
	@Autowired
	WebCustomerService customerService;

	@Autowired
	public CustomerController(WebCustomerService s) {
		this.customerService = s;
	}
	
	//GET list of customers
	@GetMapping("/web/customers")
	public String getCustomers(Model model) {
		model.addAttribute("customers",
				customerService.getAllCustomers()
				.collectList().block());
		return "customerListCRUD";
	}
	
	//GET a customer
	@GetMapping("/web/customers/{id}") // view
	public String getViewAuthorById(@PathVariable Long id, Model model) {
		CustomerResponse customer = customerService.getCustomerById(id).block(); //Mon<Author>.block()->Author
		model.addAttribute("customer", customer);
		return "detailCustomer";
	}
	
	
	
}

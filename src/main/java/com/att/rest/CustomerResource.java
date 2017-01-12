package com.att.rest;

import java.util.List;

import javax.inject.Inject;
import javax.websocket.server.PathParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.att.dao.CustomerDaoImpl;
import com.att.domain.Customer;

/**
 * 
 * @author Jishnu Mahesh
 *
 */
@RestController
@RequestMapping("/customer")
public class CustomerResource {

	private final Logger log = Logger.getLogger(CustomerResource.class);

	@Inject
	private CustomerDaoImpl customerDaoImpl;

	/**
	 * REST-API to get the Customer based on id
	 * 
	 * @param id
	 * @return Customer
	 */
	@RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
	public Customer getCustomer(@RequestParam("id") long id) {
		log.debug("Getting customer id:" + id);
		return customerDaoImpl.getCustomer(id);
	}

	/**
	 * REST-API to get All the Customers Available in DB
	 * 
	 * @return List of Customers
	 */
	@RequestMapping(value = "/getAllCustomer", method = RequestMethod.GET)
	public List<Customer> getAllCustomers() {
		log.debug("Retreving All Customers");
		return customerDaoImpl.getAllCustomers();
	}

	/**
	 * REST-API to insert the Customer into DB
	 * 
	 * @param customer
	 * @return status
	 */
	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	public @ResponseBody String createCustomer(@RequestBody Customer customer) {
		String status = null;
		if (customerDaoImpl.insertCustomer(customer) == 1) {
			log.debug("Customer added Sucessfully" + customer.getId());
			status = "Sucessfully inserted Customer";
		} else {
			status = "Failed to insert Customer";
		}
		return status;
	}

	/**
	 * REST-API to update the Customer details into DB
	 * 
	 * @param customer
	 * @return status
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.PUT)
	public @ResponseBody String updateCustomer(@RequestBody Customer customer) throws Exception {
		String status = null;
		if (customerDaoImpl.updateCustomer(customer) == 1) {
			log.debug("Customer updated Sucessfully" + customer.getId());
			status = "Sucessfully updated Customer";
		} else {
			status = "Failed to update Customer";
		}
		return status;
	}

	/**
	 * REST-API to Delete the Customer from the DB based on id
	 * 
	 * @param id
	 * @return status
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.DELETE)
	public @ResponseBody String deleteCustomer(@PathParam(value = "id") long id) throws Exception {
		String status = null;
		if (customerDaoImpl.deleteCustomer(id) == 1) {
			log.debug("Customer deleted Sucessfully" + id);
			status = "Sucessfully deleted Customer";
		} else {
			status = "Failed to delete Customer";
		}
		return status;
	}
}

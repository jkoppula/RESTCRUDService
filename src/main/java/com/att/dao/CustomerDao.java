package com.att.dao;

import java.util.List;

import com.att.domain.Customer;

/**
 * 
 * @author Jishnu Mahesh
 *
 *         Customer DAO interface
 *
 */
public interface CustomerDao {

	public Customer getCustomer(long id);

	public List<Customer> getAllCustomers();

	public int insertCustomer(Customer customer);

	public int deleteCustomer(long id);

	public int updateCustomer(Customer customer);
}

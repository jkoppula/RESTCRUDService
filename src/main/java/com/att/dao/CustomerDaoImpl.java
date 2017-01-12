package com.att.dao;

import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.att.domain.Customer;

/**
 * 
 * @author Jishnu Mahesh
 *
 *         Customer DAO class
 */
public class CustomerDaoImpl extends JdbcDaoSupport implements CustomerDao {

	String sql = "SELECT * FROM CUSTOMER";

	/**
	 * Gets the Customer record based on ID
	 */
	@Override
	public Customer getCustomer(long id) {
		String sqlQuery = sql + " where id = ?";
		Customer customer = getJdbcTemplate().queryForObject(sqlQuery, new Object[] { id },
				new BeanPropertyRowMapper<>(Customer.class));
		return customer;
	}

	/**
	 * Gets All Customers
	 */
	@Override
	public List<Customer> getAllCustomers() {

		List<Customer> customer = getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(Customer.class));

		return customer;
	}

	/**
	 * Insert Customer record into DB
	 */
	@Override
	public int insertCustomer(Customer customer) {
		String sqlQuery = " insert into customer (id, firstname, lastname, addressline1, addressline2, city, state, zip, country) values(?,?,?,?,?,?,?,?,?)";
		return getJdbcTemplate().update(sqlQuery,
				new Object[] { customer.getId(), customer.getFirstName(), customer.getLastName(),
						customer.getAddressLine1(), customer.getAddressLine2(), customer.getCity(), customer.getState(),
						customer.getZip(), customer.getCountry() });
	}

	/**
	 * Delete Customer record from DB based on id
	 */
	@Override
	public int deleteCustomer(long id) {
		String sqlQuery = "delete customer where id=?";
		return getJdbcTemplate().update(sqlQuery, id);
	}

	/**
	 * Updates the Customer record
	 */
	@Override
	public int updateCustomer(Customer customer) {
		String sqlQuery = "update customer set firstname =?,lastname =?, addressline1=?, addressline2=?, city=?, state=?, zip=?, country=? where id=?";
		return getJdbcTemplate().update(sqlQuery,
				new Object[] { customer.getFirstName(), customer.getLastName(), customer.getAddressLine1(),
						customer.getAddressLine2(), customer.getCity(), customer.getState(), customer.getZip(),
						customer.getCountry(), customer.getId() });
	}

}

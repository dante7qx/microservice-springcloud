package com.hnasys.boot.dao.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hnasys.boot.dao.demo.domain.Person;

public interface PersonDao extends JpaRepository<Person, Integer>, JpaSpecificationExecutor<Person> {
	
	@Query("select p from Person p where p.name like '%:name%' and p.address like '%:address%' order by p.age desc")
	public List<Person> findByNameAndAddress(@Param("name") String name, @Param("address") String address) throws Exception;
	
	public List<Person> findByAgeGreaterThanEqual(int age) throws Exception;
	
	public Person findByName(String name) throws Exception;
	
	@Modifying
	@Query("update Person set updateBy=?1")
	public int updateUpdateBy(String updateBy) throws Exception;
}

package com.hnasys.boot.dao.demo;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hnasys.boot.dao.BootDaoApplicationTests;
import com.hnasys.boot.dao.demo.dao.PersonDao;
import com.hnasys.boot.dao.demo.domain.Person;

public class PersonDaoTest extends BootDaoApplicationTests {
	private Logger logger = LoggerFactory.getLogger(PersonDaoTest.class);
	
	@Autowired
	private PersonDao personDao;
	
	@Test
	public void findByAgeGreaterThanEqual() {
		try {
			List<Person> persons = personDao.findByAgeGreaterThanEqual(20);
			Assert.assertNotNull(persons);
			for (Person person : persons) {
				logger.info(person.getName() + " --> " + person.getUpdateDate());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void save() {
		try {
			Person p = new Person();
			p.setName("小龙");
			p.setAddress("昆仑山 神木旁");
			p.setAge(41);
			p.setUpdateBy("dante");
			p = personDao.save(p);
			Assert.assertSame("昆仑山 神木旁", p.getAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void update() {
		try {
			Person p = personDao.findOne(14);
			p.setAddress("伦敦泰晤士大街 11 No.5");
			personDao.save(p);
			Assert.assertSame("伦敦泰晤士大街 11 No.5", p.getAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试时需要在接口上添加，jpa执行update、delete需要添加事务
	 * @Transactional
	 */
	@Test
	public void updateUpdateBy() {
		try {
			int row = personDao.updateUpdateBy("dante");
			Assert.assertTrue(row > 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

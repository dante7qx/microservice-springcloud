package com.hnasys.boot.service.demo;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.hnasys.boot.dao.demo.domain.Person;
import com.hnasys.boot.service.BootServiceApplicationTests;
import com.hnasys.boot.service.demo.service.PersonService;

public class PersonServiceTest extends BootServiceApplicationTests {
	
	@Autowired
	private PersonService personService;
	
	@Test
	public void findPage() {
		try {
			Page<Person> pages = personService.findPage();
			Assert.assertNotNull(pages);
			List<Person> persons = pages.getContent();
			for (Person person : persons) {
				System.out.println(person.getName() + "-->" + person.getAge());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

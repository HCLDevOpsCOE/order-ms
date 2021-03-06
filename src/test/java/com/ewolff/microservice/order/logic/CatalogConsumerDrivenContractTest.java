package com.ewolff.microservice.order.logic;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ewolff.microservice.order.OrderApp;
import com.ewolff.microservice.order.clients.CatalogClient;
import com.ewolff.microservice.order.clients.Item;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OrderApp.class)
@IntegrationTest
@WebAppConfiguration
@ActiveProfiles("test")
public class CatalogConsumerDrivenContractTest {

	@Autowired
	CatalogClient catalogClient;

	@Test
	public void testFindAll() {
		Collection<Item> result = catalogClient.findAll();
		assertEquals(
				1,
				result.stream()
						.filter(i -> (i.getName().equals("Xiomi")
								&& i.getPrice() == 45.0 && i.getItemId() == 1))
						.count());
	}

	@Test
	public void testGetOne() {
		Collection<Item> allItems = catalogClient.findAll();
		Long id = allItems.iterator().next().getItemId();
		Item result = catalogClient.getOne(id);
		assertEquals(id.longValue(), result.getItemId());
	}

}

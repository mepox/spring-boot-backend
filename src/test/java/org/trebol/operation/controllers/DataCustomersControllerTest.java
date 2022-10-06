package org.trebol.operation.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.trebol.jpa.entities.Customer;
import org.trebol.jpa.services.GenericCrudJpaService;
import org.trebol.jpa.services.IPredicateJpaService;
import org.trebol.jpa.services.ISortSpecJpaService;
import org.trebol.operation.PaginationService;
import org.trebol.pojo.CustomerPojo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class DataCustomersControllerTest {
  @Mock PaginationService paginationService;
  @Mock ISortSpecJpaService<Customer> sortService;
  @Mock GenericCrudJpaService<CustomerPojo, Customer> crudService;
  @Mock IPredicateJpaService<Customer> predicateService;
  private DataCustomersController instance;

  @BeforeEach
  void setUp() {
    instance = new DataCustomersController(
            paginationService,
            sortService,
            crudService,
            predicateService
    );
  }

  @Test
  void sanity_check() {
    assertNotNull(instance);
  }
}
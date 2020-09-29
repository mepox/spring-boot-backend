package cl.blm.newmarketing.store.services.impl;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import cl.blm.newmarketing.store.api.pojo.ProductFamilyPojo;
import cl.blm.newmarketing.store.api.pojo.ProductPojo;
import cl.blm.newmarketing.store.api.pojo.ProductTypePojo;
import cl.blm.newmarketing.store.jpa.entities.Product;
import cl.blm.newmarketing.store.jpa.entities.ProductFamily;
import cl.blm.newmarketing.store.jpa.entities.ProductType;
import cl.blm.newmarketing.store.services.CatalogService;
import cl.blm.newmarketing.store.services.crud.GenericEntityCrudService;

/**
 *
 * @author Benjamin La Madrid <bg.lamadrid@gmail.com>
 */
@Service
public class CatalogServiceImpl
    implements CatalogService {

  private final GenericEntityCrudService<ProductFamilyPojo, ProductFamily, Integer> productFamiliesService;
  private final GenericEntityCrudService<ProductTypePojo, ProductType, Integer> productTypesService;
  private final GenericEntityCrudService<ProductPojo, Product, Integer> productsService;

  @Autowired
  public CatalogServiceImpl(GenericEntityCrudService<ProductFamilyPojo, ProductFamily, Integer> productFamiliesService,
      GenericEntityCrudService<ProductTypePojo, ProductType, Integer> productTypesService,
      GenericEntityCrudService<ProductPojo, Product, Integer> productsService) {
    this.productFamiliesService = productFamiliesService;
    this.productTypesService = productTypesService;
    this.productsService = productsService;
  }

  @Override
  public Collection<ProductPojo> readProducts(Integer requestPageSize, Integer requestPageIndex, Map<String, String> queryParamsMap) {
    Predicate filters = productsService.queryParamsMapToPredicate(queryParamsMap);
    return productsService.read(requestPageSize, requestPageIndex, filters);
  }

  @Override
  public ProductPojo readProduct(Integer id) {
    return productsService.find(id);
  }

  @Override
  public Collection<ProductTypePojo> readProductTypes() {
    return productTypesService.read(10, 0, null);
  }

  @Override
  public Collection<ProductFamilyPojo> readProductFamilies() {
    return productFamiliesService.read(10, 0, null);
  }

}

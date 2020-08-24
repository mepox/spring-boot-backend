package cl.blm.newmarketing.backend.rest.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.blm.newmarketing.backend.BackendAppGlobals;
import cl.blm.newmarketing.backend.pojos.ProductPojo;
import cl.blm.newmarketing.backend.rest.dtos.ProductDto;
import cl.blm.newmarketing.backend.rest.services.CrudService;

/**
 * API point of entry for Client entities
 *
 * @author Benjamin La Madrid <bg.lamadrid at gmail.com>
 */
@RestController
@RequestMapping("/api")
public class ProductsController
    extends EntityCrudController<ProductDto, Integer> {
  private final static Logger LOG = LoggerFactory.getLogger(ProductsController.class);

  @Autowired
  private ConversionService conversion;

  @Autowired
  public ProductsController(BackendAppGlobals globals, CrudService<ProductDto, Integer> crudService) {
    super(globals, crudService);
  }

  @GetMapping("/products")
  public Collection<ProductPojo> read(@RequestParam Map<String, String> allRequestParams) {
    return this.read(null, null, allRequestParams);
  }

  @GetMapping("/products/{requestPageSize}")
  public Collection<ProductPojo> read(@PathVariable Integer requestPageSize,
      @RequestParam Map<String, String> allRequestParams) {
    return this.read(requestPageSize, null, allRequestParams);
  }

  @GetMapping("/products/{requestPageSize}/{requestPageIndex}")
  public Collection<ProductPojo> read(@PathVariable Integer requestPageSize, @PathVariable Integer requestPageIndex,
      @RequestParam Map<String, String> allRequestParams) {
    LOG.info("read");
    Collection<ProductDto> products = this.readFromService(requestPageSize, requestPageIndex, allRequestParams);

    @SuppressWarnings("unchecked")
    Collection<ProductPojo> productPojos = conversion.convert(products, List.class);
    return productPojos;
  }
}
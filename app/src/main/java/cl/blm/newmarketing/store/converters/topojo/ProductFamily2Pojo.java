package cl.blm.newmarketing.store.converters.topojo;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cl.blm.newmarketing.store.api.pojo.ProductFamilyPojo;
import cl.blm.newmarketing.store.jpa.entities.ProductFamily;

/**
 *
 * @author Benjamin La Madrid <bg.lamadrid at gmail.com>
 */
@Component
public class ProductFamily2Pojo
    implements Converter<ProductFamily, ProductFamilyPojo> {

  @Override
  public ProductFamilyPojo convert(ProductFamily source) {
    ProductFamilyPojo target = new ProductFamilyPojo();
    target.setId(source.getId());
    target.setName(source.getName());
    return target;
  }
}
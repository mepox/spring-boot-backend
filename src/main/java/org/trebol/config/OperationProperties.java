package org.trebol.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Read-only wrapper class for settings read from 'custom.properties' file
 *
 * @author Benjamin La Madrid <bg.lamadrid at gmail.com>
 */
@Configuration
@ConfigurationProperties(prefix = "trebol.operation")
public class OperationProperties {
  private Integer itemsPerPage;
  private Integer maxAllowedPageSize;

  public Integer getMaxAllowedPageSize() {
    return maxAllowedPageSize;
  }

  public void setMaxAllowedPageSize(Integer maxAllowedPageSize) {
    this.maxAllowedPageSize = maxAllowedPageSize;
  }

  public Integer getItemsPerPage() {
    return itemsPerPage;
  }

  public void setItemsPerPage(Integer itemsPerPage) {
    this.itemsPerPage = itemsPerPage;
  }
}

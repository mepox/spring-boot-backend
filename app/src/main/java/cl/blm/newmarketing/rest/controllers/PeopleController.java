package cl.blm.newmarketing.rest.controllers;

import cl.blm.newmarketing.rest.AppGlobals;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import cl.blm.newmarketing.rest.dtos.PersonDto;
import cl.blm.newmarketing.rest.services.CrudService;

/**
 * API point of entry for anything person-related.
 *
 * @author Benjamin La Madrid <bg.lamadrid at gmail.com>
 */
@RestController
@RequestMapping("/api")
public class PeopleController {
  private final static Logger LOG = LoggerFactory.getLogger(PeopleController.class);

  @Autowired private CrudService<PersonDto, Long> personSvc;
  @Autowired private AppGlobals globals;

  @GetMapping("/people")
  public Collection<PersonDto> read(
          @RequestParam Map<String, String> allRequestParams
  ) {
    return this.read(null, null, allRequestParams);
  }

  @GetMapping("/people/{requestPageSize}")
  public Collection<PersonDto> read(
          @PathVariable Integer requestPageSize,
          @RequestParam Map<String, String> allRequestParams
  ) {
    return this.read(requestPageSize, null, allRequestParams);
  }

  /**
   * Retrieve a page of people.
   * Si el URL tenía un query string (RequestParam, lo transforma a un Map, genera un Predicate a partir
   * de él y filtra la búsqueda con este Predicate.
   *
   * @param requestPageSize
   * @param requestPageIndex
   * @param allRequestParams Un Map conteniendo una colección pares nombre/valor.
   *
   * @see RequestParam
   * @see Predicate
   * @return Una colección de objetos PersonaDTO
   */
  @GetMapping("/people/{requestPageSize}/{requestPageIndex}")
  public Collection<PersonDto> read(
          @PathVariable Integer requestPageSize,
          @PathVariable Integer requestPageIndex,
          @RequestParam Map<String, String> allRequestParams
  ) {
    LOG.info("read");
    Integer pageSize = globals.ITEMS_PER_PAGE;
    Integer pageIndex = 0;
    Predicate filters = null;

    if (requestPageSize != null && requestPageSize > 0) {
      pageSize = requestPageSize;
    }
    if (requestPageIndex != null && requestPageIndex > 0) {
      pageIndex = requestPageIndex - 1;
    }
    if (allRequestParams != null && !allRequestParams.isEmpty()) {
      filters = personSvc.queryParamsMapToPredicate(allRequestParams);
    }

    Collection<PersonDto> personas = personSvc.read(pageSize, pageIndex, filters);
    return personas;
  }
}
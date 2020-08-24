package cl.blm.newmarketing.backend.rest.dtos;

/**
 *
 * @author Benjamin La Madrid <bg.lamadrid at gmail.com>
 */
public class ClientDto {
  private Integer clientId;
  private PersonDto person;

  public ClientDto() {
    super();
  }

  public Integer getClientId() {
    return clientId;
  }

  public void setClientId(Integer clientId) {
    this.clientId = clientId;
  }

  public PersonDto getPerson() {
    return person;
  }

  public void setPerson(PersonDto person) {
    this.person = person;
  }
}
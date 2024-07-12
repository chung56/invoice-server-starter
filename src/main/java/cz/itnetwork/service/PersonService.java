package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticsDTO;
import java.util.List;

public interface PersonService {

  /**
   * Creates a new person
   *
   * @param personDTO Person to create
   * @return newly created person
   */
  PersonDTO addPerson(PersonDTO personDTO);

  /**
   * <p>Sets hidden flag to true for the person with the matching [id]</p>
   * <p>In case a person with the passed [id] isn't found, the method <b>silently fails</b></p>
   *
   * @param id Person to delete
   */
  void removePerson(long id);

  /**
   * Fetches all non-hidden persons
   *
   * @return List of all non-hidden persons
   */
  List<PersonDTO> getAll();

  /**
   * Fetched non-hidden person
   *
   * @param id Person to fetch
   * @return non-hidden person
   */
  PersonDTO getPersonById(long id);

  /**
   * Updated non-hidden person
   *
   * @param personId Person to fetch
   * @param personDTO Person to be updated
   * @return new updated person
   */
  PersonDTO updatePerson(Long personId, PersonDTO personDTO);

  /**
   * Fetched all persons who are sellers
   *
   * @param identificationNumber Seller to fetch
   * @return List of sellers
   */
  List<InvoiceDTO> getSellerByIdentification(String identificationNumber);

  /**
   * Fetched all persons who are buyers
   *
   * @param identificationNumber Buyer to fetch
   * @return List of buyer
   */
  List<InvoiceDTO> getBuyerByIdentification(String identificationNumber);

  /**
   * Fetched all person statistics
   *
   * @return List of person statistics
   */
  List<PersonStatisticsDTO> personStatistics();
}

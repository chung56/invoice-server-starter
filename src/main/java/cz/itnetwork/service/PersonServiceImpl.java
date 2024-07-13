package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticsDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.dto.mapper.PersonMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

  @Autowired
  private PersonMapper personMapper;

  @Autowired
  private InvoiceMapper invoiceMapper;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private InvoiceRepository invoiceRepository;

  public PersonDTO addPerson(PersonDTO personDTO) {
    PersonEntity entity = personMapper.toEntity(personDTO);
    entity = personRepository.save(entity);

    return personMapper.toDTO(entity);
  }

  @Override
  public void removePerson(long personId) {
    try {
      PersonEntity person = fetchPersonById(personId);
      person.setHidden(true);
      personRepository.save(person);
    } catch (NotFoundException ignored) {
      // The contract in the interface states, that no exception is thrown, if the entity is not found.
    }
  }

  @Override
  public List<PersonDTO> getAll() {
    return personRepository.findByHidden(false)
        .stream()
        .map(i -> personMapper.toDTO(i))
        .collect(Collectors.toList());
  }

  @Override
  public PersonDTO getPersonById(long id) {
    PersonEntity personEntity = fetchPersonById(id);
    return personMapper.toDTO(personEntity);

  }

  @Override
  public PersonDTO updatePerson(Long personId, PersonDTO personDTO) {
    if (!personRepository.existsById(personId)) {
      throw new EntityNotFoundException("Person with id " + personId + " wasn't found in the database.");
    }
    PersonEntity oldPerson = fetchPersonById(personId);
    oldPerson.setHidden(true);
    personRepository.save(oldPerson);

    PersonEntity newPerson = personMapper.toEntity(personDTO);
    newPerson.setId(null);
    PersonEntity saved = personRepository.save(newPerson);
    return personMapper.toDTO(saved);
  }

  @Override
  public List<InvoiceDTO> getSellerByIdentification(String identificationNumber) {
    List<InvoiceDTO> result = new ArrayList<>();
    List<InvoiceEntity> invoices = invoiceRepository.findAll();
    for (InvoiceEntity invoiceEntity : invoices) {
      if (invoiceEntity.getSeller() != null &&
          invoiceEntity.getSeller().getIdentificationNumber().equals(identificationNumber)) {
        result.add(invoiceMapper.toDTO(invoiceEntity));
      }
    }
    return result;
  }

  @Override
  public List<InvoiceDTO> getBuyerByIdentification(String identificationNumber) {
    List<InvoiceDTO> result = new ArrayList<>();
    List<InvoiceEntity> invoices = invoiceRepository.findAll();
    for (InvoiceEntity invoiceEntity : invoices) {
      if (invoiceEntity.getBuyer() != null &&
          invoiceEntity.getBuyer().getIdentificationNumber().equals(identificationNumber)) {
        result.add(invoiceMapper.toDTO(invoiceEntity));
      }
    }
    return result;
  }

  @Override
  public List<PersonStatisticsDTO> personStatistics() {
    return personRepository.findPersonStatistics();
  }

  // region: Private methods

  /**
   * <p>Attempts to fetch a person.</p>
   * <p>In case a person with the passed [id] doesn't exist a [{@link org.webjars.NotFoundException}] is thrown.</p>
   *
   * @param id Person to fetch
   * @return Fetched entity
   * @throws org.webjars.NotFoundException In case a person with the passed [id] isn't found
   */
  private PersonEntity fetchPersonById(long id) {
    return personRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Person with id " + id + " wasn't found in the database."));
  }
  // endregion
}

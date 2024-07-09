package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticsDTO;
import cz.itnetwork.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

  @Autowired
  private PersonService personService;

  @Secured("ROLE_ADMIN")
  @PostMapping({"/persons", "/persons/"})
  public PersonDTO addPerson(@RequestBody PersonDTO personDTO) {
    return personService.addPerson(personDTO);
  }

  @GetMapping({"/persons", "/persons/"})
  public List<PersonDTO> getPersons() {
    return personService.getAll();
  }

  @Secured("ROLE_ADMIN")
  @DeleteMapping({"/persons/{personId}", "/persons/{personId}/"})
  public void deletePerson(@PathVariable Long personId) {
    personService.removePerson(personId);
  }

  @GetMapping({"/persons/{personId}", "/persons/{personId}/"})
  public PersonDTO getPersonbyId(@PathVariable Long personId) {
    return personService.getPersonById(personId);
  }

  @Secured("ROLE_ADMIN")
  @PutMapping({"/persons/{personId}", "/persons/{personId}/"})
  public PersonDTO editPerson(@PathVariable Long personId, @RequestBody PersonDTO personDTO) {
    return personService.updatePerson(personId, personDTO);
  }

  @GetMapping({"/identification/{identificationNumber}/sales", "/identification/{identificationNumber}/sales/"})
  public List<InvoiceDTO> getSalesByIdentificationNumber(@PathVariable String identificationNumber) {
    return personService.getSellerByIdentification(identificationNumber);
  }

  @GetMapping({"/identification/{identificationNumber}/purchases", "/identification/{identificationNumber}/purchases/"})
  public List<InvoiceDTO> getBuyersByIdentificationNumber(@PathVariable String identificationNumber) {
    return personService.getBuyerByIdentification(identificationNumber);
  }

  @GetMapping({"/persons/statistics", "/persons/statistics/"})
  public List<PersonStatisticsDTO> getPersonStatistics() {
    return personService.personStatistics();
  }
}


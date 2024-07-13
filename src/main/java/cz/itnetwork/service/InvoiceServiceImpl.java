package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

  @Autowired
  InvoiceRepository invoiceRepository;

  @Autowired
  InvoiceMapper invoiceMapper;

  @Autowired
  private PersonRepository personRepository;

  @Override
  public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
    InvoiceEntity invoice = invoiceMapper.toEntity(invoiceDTO);
    mapPersonToInvoice(invoice, invoiceDTO);
    InvoiceEntity saved = invoiceRepository.save(invoice);
    return invoiceMapper.toDTO(saved);
  }

  @Override
  public InvoiceDTO getInvoiceById(long invoiceId) {
    InvoiceEntity invoiceEntity = invoiceRepository.getReferenceById(invoiceId);
    return invoiceMapper.toDTO(invoiceEntity);
  }

  @Override
  public List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter) {
    List<InvoiceDTO> result = new ArrayList<>();
    for (InvoiceEntity invoice : invoiceRepository.getFilteredInvoices(invoiceFilter, PageRequest.of(0, invoiceFilter.getLimit()))) {
      result.add(invoiceMapper.toDTO(invoice));
    }
    return result;
  }

  @Override
  public InvoiceDTO deleteInvoice(long invoiceId) {
    InvoiceEntity invoice = invoiceRepository.getReferenceById(invoiceId);
    InvoiceDTO model = invoiceMapper.toDTO(invoice);
    invoiceRepository.delete(invoice);
    return model;
  }

  @Override
  public InvoiceDTO updateInvoice(InvoiceDTO invoiceDTO, Long invoiceId) {
    if (!invoiceRepository.existsById(invoiceId)) {
      throw new EntityNotFoundException("Invoice with id " + invoiceId + " wasn't found in the database.");
    }

    InvoiceEntity invoiceEntity = invoiceMapper.toEntity(invoiceDTO);
    invoiceEntity.setId(invoiceId);
    mapPersonToInvoice(invoiceEntity, invoiceDTO);
    InvoiceEntity save = invoiceRepository.save(invoiceEntity);
    return invoiceMapper.toDTO(save);
  }

  @Override
  public InvoiceStatisticsDTO getInvoiceStatistics() {
    LocalDate now = LocalDate.now();
    LocalDate startDate = now.withDayOfYear(1);
    LocalDate endDate = now.withDayOfYear(now.lengthOfYear());

    Long currentYearSum = invoiceRepository.getCurrentYearSum(startDate, endDate);
    Long allTimeSum = invoiceRepository.getAllTimeSum();
    Long invoicesCount = invoiceRepository.getInvoicesCount();

    return new InvoiceStatisticsDTO(currentYearSum, allTimeSum, invoicesCount);
  }

  private void mapPersonToInvoice(InvoiceEntity invoice, InvoiceDTO invoiceDTO) {
    // Map seller to invoice
    PersonEntity seller = personRepository.getReferenceById(invoiceDTO.getSeller().getId());
    invoice.setSeller(seller);

    // Map buyer to invoice
    PersonEntity buyer = personRepository.getReferenceById(invoiceDTO.getBuyer().getId());
    invoice.setBuyer(buyer);
  }
}

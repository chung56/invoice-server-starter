package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;
import java.util.List;


public interface InvoiceService {

  InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

  InvoiceDTO getInvoiceById(long invoiceId);

  List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter);

  InvoiceDTO deleteInvoice(long invoiceId);

  InvoiceDTO updateInvoice(InvoiceDTO invoiceDTO, Long invoiceId);

  InvoiceStatisticsDTO getInvoiceStatistics();
}

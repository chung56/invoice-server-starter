package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;
import java.util.List;


public interface InvoiceService {

  /**
   * Creates a new invoice
   *
   * @param invoiceDTO Invoice to create
   * @return newly create invoice
   */
  InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

  /**
   * Fetched invoice
   *
   * @param invoiceId Invoice to fetch
   * @return invoice
   */
  InvoiceDTO getInvoiceById(long invoiceId);

  /**
   * Fetches all filtered invoices
   *
   * @param invoiceFilter invoice to be fetched
   * @return List of filtered invoices
   */
  List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter);

  /**
   * Delete invoice
   *
   * @param invoiceId Invoice to delete
   * @return deleted invoice
   */
  InvoiceDTO deleteInvoice(long invoiceId);

  /**
   * Updated invoice
   *
   * @param invoiceDTO Invoice to be updated
   * @param invoiceId Invoice to fetch
   */
  InvoiceDTO updateInvoice(InvoiceDTO invoiceDTO, Long invoiceId);

  /**
   * Fetched invoice statistics
   *
   * @return invoice statistics
   */
  InvoiceStatisticsDTO getInvoiceStatistics();
}

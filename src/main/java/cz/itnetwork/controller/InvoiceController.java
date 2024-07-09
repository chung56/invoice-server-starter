package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.service.InvoiceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InvoiceController {

  @Autowired
  private InvoiceService invoiceService;

  @Secured("ROLE_ADMIN")
  @PostMapping({"/invoices/", "/invoices"})
  public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
    return invoiceService.addInvoice(invoiceDTO);
  }

  @GetMapping({"invoices/{invoiceId}/", "invoices/{invoiceId}"})
  public InvoiceDTO getInvoiceById(@PathVariable long invoiceId) {
    return invoiceService.getInvoiceById(invoiceId);
  }

  @GetMapping({"invoices/", "invoices"})
  public List<InvoiceDTO> getInvoices(InvoiceFilter invoiceFilter) {
    return invoiceService.getAllInvoices(invoiceFilter);
  }

  @Secured("ROLE_ADMIN")
  @DeleteMapping({"invoices/{invoiceId}/", "invoices/{invoiceId}"})
  public InvoiceDTO removeInvoice(@PathVariable long invoiceId) {
    return invoiceService.deleteInvoice(invoiceId);
  }

  @Secured("ROLE_ADMIN")
  @PutMapping({"invoices/{invoiceId}/", "invoices/{invoiceId}"})
  public InvoiceDTO editInvoice(@RequestBody InvoiceDTO invoiceDTO, @PathVariable Long invoiceId) {
    return invoiceService.updateInvoice(invoiceDTO, invoiceId);
  }

  @GetMapping({"invoices/statistic/", "invoices/statistic"})
  public InvoiceStatisticsDTO getInvoiceStatistics() {
    return invoiceService.getInvoiceStatistics();
  }
}

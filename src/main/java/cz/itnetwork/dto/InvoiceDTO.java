package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

  @JsonProperty("_id")
  private Long id;

  private int invoiceNumber;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate issued;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate dueDate;

  private String product;

  private Long price;

  private int vat;

  private String note;

  private PersonDTO buyer;

  private PersonDTO seller;

}

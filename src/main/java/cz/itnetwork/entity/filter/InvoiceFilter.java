package cz.itnetwork.entity.filter;

import lombok.Data;

@Data
public class InvoiceFilter {
  private Long buyerID = -1L;
  private Long sellerID	 = -1L;
  private String product = "";
  private Long minPrice = -1L;;
  private Long maxPrice = -1L;;
  private Integer limit = 100;
}

package cz.itnetwork.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "invoice")
@Getter
@Setter
public class InvoiceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private int invoiceNumber;

  @Column(nullable = false)
  private LocalDate issued;

  @Column(nullable = false)
  private LocalDate dueDate;

  @Column(nullable = false)
  private String product;

  @Column(nullable = false)
  private Long price;

  @Column(nullable = false)
  private int vat;

  private String note;

  @ManyToOne
  private PersonEntity buyer;

  @ManyToOne
  private PersonEntity seller;

}

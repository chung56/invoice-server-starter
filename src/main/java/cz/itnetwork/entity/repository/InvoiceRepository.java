package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.filter.InvoiceFilter;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

  @Query("SELECT SUM(i.price) FROM invoice i WHERE i.dueDate BETWEEN :startDate AND :endDate")
  Long getCurrentYearSum(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

  @Query("SELECT SUM(i.price) FROM invoice i")
  Long getAllTimeSum();

  @Query("SELECT COUNT(i) FROM invoice i")
  Long getInvoicesCount();

  @Query("SELECT i FROM invoice i " +
      "WHERE (:#{#filter.getBuyerID()} = -1 OR i.buyer.id = :#{#filter.getBuyerID()}) " +
      "AND (:#{#filter.getSellerID()} = -1 OR i.seller.id = :#{#filter.getSellerID()})  " +
      "AND (:#{#filter.getProduct()} = '' OR i.product = :#{#filter.getProduct()}) " +
      "AND (:#{#filter.getMinPrice()} = -1 OR i.price >= :#{#filter.getMinPrice()}) " +
      "AND (:#{#filter.getMaxPrice()} = -1 OR i.price <= :#{#filter.getMaxPrice()}) "
  )
  List<InvoiceEntity> getFilteredInvoices(InvoiceFilter filter, PageRequest pageable);
}

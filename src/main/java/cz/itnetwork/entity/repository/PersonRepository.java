package cz.itnetwork.entity.repository;

import cz.itnetwork.dto.PersonStatisticsDTO;
import cz.itnetwork.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

  List<PersonEntity> findByHidden(boolean hidden);

  @Query("SELECT NEW cz.itnetwork.dto.PersonStatisticsDTO(p.id, p.name, COALESCE(SUM(i.price), 0.0)) "
      + "FROM person p "
      + "LEFT JOIN invoice i ON p.id = i.seller.id "
      + "WHERE p.hidden = false "
      + "GROUP BY p.id")
  List<PersonStatisticsDTO> findPersonStatistics();
}

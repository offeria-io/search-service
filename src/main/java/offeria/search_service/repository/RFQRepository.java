package offeria.search_service.repository;

import offeria.search_service.domain.RFQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Standard JPA Repository for RFQ.
 */
@Repository
public interface RFQRepository extends JpaRepository<RFQ, Long> {
}

package offeria.search_service.repository;

import offeria.search_service.domain.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Standard JPA Repository for Material.
 */
@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
}

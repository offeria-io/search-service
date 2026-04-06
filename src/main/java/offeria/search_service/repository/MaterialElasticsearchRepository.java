package offeria.search_service.repository;

import offeria.search_service.domain.Material;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Elasticsearch Repository for Material full-text search.
 */
public interface MaterialElasticsearchRepository extends ElasticsearchRepository<Material, Long> {
}

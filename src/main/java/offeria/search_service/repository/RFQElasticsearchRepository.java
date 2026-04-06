package offeria.search_service.repository;

import offeria.search_service.domain.RFQ;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Elasticsearch Repository for RFQ full-text search.
 */
public interface RFQElasticsearchRepository extends ElasticsearchRepository<RFQ, Long> {
}

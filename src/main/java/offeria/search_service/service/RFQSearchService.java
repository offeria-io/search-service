package offeria.search_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import offeria.search_service.domain.RFQ;
import offeria.search_service.dto.RFQDTO;
import offeria.search_service.mapper.RFQMapper;
import offeria.search_service.repository.RFQElasticsearchRepository;
import offeria.search_service.repository.RFQRepository;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for RFQ related search and management.
 * Handles both JPA for persistence and Elasticsearch for full-text search.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RFQSearchService {

    private final RFQRepository rfqRepository;
    private final RFQElasticsearchRepository rfqElasticsearchRepository;
    private final RFQMapper rfqMapper;
    private final ElasticsearchOperations elasticsearchOperations;

    /**
     * Saves an RFQ to both DB and Elasticsearch.
     */
    @Transactional
    public RFQDTO saveRFQ(RFQDTO rfqDTO) {
        log.info("Saving RFQ: {}", rfqDTO.getTitle());
        RFQ rfq = rfqMapper.toEntity(rfqDTO);
        rfq = rfqRepository.save(rfq);
        
        // Sync with Elasticsearch
        rfqElasticsearchRepository.save(rfq);
        
        return rfqMapper.toDTO(rfq);
    }

    /**
     * Advanced full-text search using Elasticsearch for RFQs.
     */
    public List<RFQDTO> searchRFQs(String query) {
        log.info("Searching for RFQs with query: {}", query);
        
        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(q -> q
                    .multiMatch(m -> m
                        .fields("title", "requirements", "status")
                        .query(query)
                    )
                )
                .build();

        SearchHits<RFQ> searchHits = elasticsearchOperations.search(nativeQuery, RFQ.class);
        
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .map(rfqMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get all RFQs from DB (standard JPA list).
     */
    public List<RFQDTO> getAllRFQs() {
        log.info("Fetching all RFQs");
        return rfqMapper.toDTOList(rfqRepository.findAll());
    }
}

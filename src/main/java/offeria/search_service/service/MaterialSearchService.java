package offeria.search_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import offeria.search_service.domain.Material;
import offeria.search_service.dto.MaterialDTO;
import offeria.search_service.mapper.MaterialMapper;
import offeria.search_service.repository.MaterialElasticsearchRepository;
import offeria.search_service.repository.MaterialRepository;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for Material related search and management.
 * Handles both JPA for persistence and Elasticsearch for full-text search.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MaterialSearchService {

    private final MaterialRepository materialRepository;
    private final MaterialElasticsearchRepository materialElasticsearchRepository;
    private final MaterialMapper materialMapper;
    private final ElasticsearchOperations elasticsearchOperations;

    /**
     * Saves a material to both DB and Elasticsearch.
     */
    @Transactional
    public MaterialDTO saveMaterial(MaterialDTO materialDTO) {
        log.info("Saving material: {}", materialDTO.getName());
        Material material = materialMapper.toEntity(materialDTO);
        material = materialRepository.save(material);
        
        // Sync with Elasticsearch
        materialElasticsearchRepository.save(material);
        
        return materialMapper.toDTO(material);
    }

    /**
     * Advanced full-text search using Elasticsearch.
     */
    public List<MaterialDTO> searchMaterials(String query) {
        log.info("Searching for materials with query: {}", query);
        
        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(q -> q
                    .multiMatch(m -> m
                        .fields("name", "description", "category")
                        .query(query)
                    )
                )
                .build();

        SearchHits<Material> searchHits = elasticsearchOperations.search(nativeQuery, Material.class);
        
        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .map(materialMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Simple search by category using JPA.
     */
    public List<MaterialDTO> findByCategory(String category) {
        log.info("Finding materials by category: {}", category);
        // This is just to demonstrate JPA usage in the service
        // In a real scenario, you'd define this method in the repository
        return materialMapper.toDTOList(materialRepository.findAll().stream()
                .filter(m -> m.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList()));
    }
}

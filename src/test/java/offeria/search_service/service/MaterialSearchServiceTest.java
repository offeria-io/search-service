package offeria.search_service.service;

import offeria.search_service.domain.Material;
import offeria.search_service.dto.MaterialDTO;
import offeria.search_service.mapper.MaterialMapper;
import offeria.search_service.repository.MaterialElasticsearchRepository;
import offeria.search_service.repository.MaterialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MaterialSearchServiceTest {

    @Mock
    private MaterialRepository materialRepository;

    @Mock
    private MaterialElasticsearchRepository materialElasticsearchRepository;

    @Mock
    private MaterialMapper materialMapper;

    @Mock
    private ElasticsearchOperations elasticsearchOperations;

    @InjectMocks
    private MaterialSearchService materialSearchService;

    private Material material;
    private MaterialDTO materialDTO;

    @BeforeEach
    void setUp() {
        material = Material.builder()
                .id(1L)
                .name("Steel Beam")
                .category("Construction")
                .price(new BigDecimal("150.00"))
                .build();

        materialDTO = MaterialDTO.builder()
                .id(1L)
                .name("Steel Beam")
                .category("Construction")
                .price(new BigDecimal("150.00"))
                .build();
    }

    @Test
    void saveMaterial_ShouldSaveToBothRepositories() {
        // Arrange
        when(materialMapper.toEntity(any(MaterialDTO.class))).thenReturn(material);
        when(materialRepository.save(any(Material.class))).thenReturn(material);
        when(materialMapper.toDTO(any(Material.class))).thenReturn(materialDTO);

        // Act
        MaterialDTO result = materialSearchService.saveMaterial(materialDTO);

        // Assert
        assertNotNull(result);
        assertEquals(materialDTO.getName(), result.getName());
        verify(materialRepository, times(1)).save(any(Material.class));
        verify(materialElasticsearchRepository, times(1)).save(any(Material.class));
    }
}

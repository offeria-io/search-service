package offeria.search_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import offeria.search_service.dto.MaterialDTO;
import offeria.search_service.service.MaterialSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Material Search REST APIs.
 */
@RestController
@RequestMapping("/api/v1/search/materials")
@RequiredArgsConstructor
public class MaterialSearchController {

    private final MaterialSearchService materialSearchService;

    /**
     * Endpoint to create/index a new material.
     */
    @PostMapping
    public ResponseEntity<MaterialDTO> createMaterial(@Valid @RequestBody MaterialDTO materialDTO) {
        return ResponseEntity.ok(materialSearchService.saveMaterial(materialDTO));
    }

    /**
     * Endpoint for advanced full-text search.
     */
    @GetMapping
    public ResponseEntity<List<MaterialDTO>> searchMaterials(@RequestParam String q) {
        return ResponseEntity.ok(materialSearchService.searchMaterials(q));
    }

    /**
     * Endpoint for category-based filtering.
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<MaterialDTO>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(materialSearchService.findByCategory(category));
    }
}

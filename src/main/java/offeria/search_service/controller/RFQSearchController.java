package offeria.search_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import offeria.search_service.dto.RFQDTO;
import offeria.search_service.service.RFQSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for RFQ Search REST APIs.
 */
@RestController
@RequestMapping("/api/v1/search/rfqs")
@RequiredArgsConstructor
public class RFQSearchController {

    private final RFQSearchService rfqSearchService;

    /**
     * Endpoint to create/index a new RFQ.
     */
    @PostMapping
    public ResponseEntity<RFQDTO> createRFQ(@Valid @RequestBody RFQDTO rfqDTO) {
        return ResponseEntity.ok(rfqSearchService.saveRFQ(rfqDTO));
    }

    /**
     * Endpoint for advanced full-text search.
     */
    @GetMapping
    public ResponseEntity<List<RFQDTO>> searchRFQs(@RequestParam String q) {
        return ResponseEntity.ok(rfqSearchService.searchRFQs(q));
    }

    /**
     * Endpoint to list all RFQs.
     */
    @GetMapping("/all")
    public ResponseEntity<List<RFQDTO>> getAllRFQs() {
        return ResponseEntity.ok(rfqSearchService.getAllRFQs());
    }
}

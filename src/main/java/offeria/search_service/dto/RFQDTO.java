package offeria.search_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Request for Quotation (RFQ).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RFQDTO {

    private Long id;

    @NotBlank(message = "RFQ title is required")
    private String title;

    @NotBlank(message = "RFQ requirements are required")
    private String requirements;

    @NotBlank(message = "RFQ status is required")
    private String status;

    private LocalDateTime createdAt;
}

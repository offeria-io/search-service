package offeria.search_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for Material.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDTO {

    private Long id;

    @NotBlank(message = "Material name is required")
    private String name;

    private String description;

    @NotBlank(message = "Material category is required")
    private String category;

    @NotNull(message = "Material price is required")
    private BigDecimal price;

    private LocalDateTime createdAt;
}

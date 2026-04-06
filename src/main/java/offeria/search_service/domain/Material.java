package offeria.search_service.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing a Material.
 * Stored in PostgreSQL for persistence and indexed in Elasticsearch for full-text search.
 */
@Entity
@Table(name = "materials")
@Document(indexName = "materials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.annotation.Id
    private Long id;

    @Column(nullable = false)
    @Field(type = FieldType.Text, analyzer = "standard")
    private String name;

    @Column(columnDefinition = "TEXT")
    @Field(type = FieldType.Text, analyzer = "standard")
    private String description;

    @Column(nullable = false)
    @Field(type = FieldType.Keyword)
    private String category;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}

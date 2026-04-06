package offeria.search_service.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

/**
 * Entity representing a Request for Quotation (RFQ).
 * Stored in PostgreSQL for persistence and indexed in Elasticsearch for full-text search.
 */
@Entity
@Table(name = "rfqs")
@Document(indexName = "rfqs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RFQ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.annotation.Id
    private Long id;

    @Column(nullable = false)
    @Field(type = FieldType.Text, analyzer = "standard")
    private String title;

    @Column(columnDefinition = "TEXT")
    @Field(type = FieldType.Text, analyzer = "standard")
    private String requirements;

    @Column(nullable = false)
    @Field(type = FieldType.Keyword)
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}

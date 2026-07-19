package am.wago.mongocollector.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "component_events", indexes = {
        @Index(name = "idx_ce_category",    columnList = "category"),
        @Index(name = "idx_ce_synced_at",   columnList = "synced_at"),
        @Index(name = "idx_ce_manufacturer", columnList = "manufacturer_name")
})
public class ComponentEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "mongo_id", nullable = false, unique = true, length = 64)
    private String mongoId;

    @Column(name = "category", length = 32)
    private String category;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "currency", length = 8)
    private String currency;

    @Column(name = "manufacturer_name", length = 64)
    private String manufacturerName;

    @Column(name = "manufacturer_country", length = 64)
    private String manufacturerCountry;

    @Column(name = "warranty_months")
    private Integer warrantyMonths;

    @Column(name = "support_email", length = 128)
    private String supportEmail;

    @Column(name = "address_city", length = 64)
    private String addressCity;

    @Column(name = "address_country", length = 64)
    private String addressCountry;

    @Column(name = "model", length = 128)
    private String model;

    @Column(name = "socket", length = 32)
    private String socket;

    @Column(name = "tdp_watts")
    private Integer tdpWatts;

    @Column(name = "clock_speed_ghz")
    private Double clockSpeedGhz;

    @Column(name = "core_count")
    private Integer coreCount;

    @Column(name = "memory_type", length = 16)
    private String memoryType;

    @Column(name = "single_core_score")
    private Integer singleCoreScore;

    @Column(name = "multi_core_score")
    private Integer multiCoreScore;

    @Column(name = "test_tool", length = 64)
    private String testTool;

    @Column(name = "mongo_created_at")
    private Instant mongoCreatedAt;

    @Column(name = "synced_at", nullable = false)
    private Instant syncedAt;
}

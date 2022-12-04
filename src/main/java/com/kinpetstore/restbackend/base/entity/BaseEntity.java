package com.kinpetstore.restbackend.base.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@NoRepositoryBean
@MappedSuperclass
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    @PrePersist
    void prePersist() {
        this.setCreateTime(new Date());
        this.setLastUpdateTime(new Date());
    }

    @PreUpdate
    void preUpdate() {
        this.setLastUpdateTime(new Date());
    }
}

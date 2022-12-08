package com.kinpetstore.restbackend.entity;

import com.kinpetstore.restbackend.base.entity.BaseEntity;
import com.kinpetstore.restbackend.domain.response.StoreResponse;
import com.kinpetstore.restbackend.entity.enums.StoreStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static com.kinpetstore.restbackend.constant.DefaultSetting.DEFAULT_LOCALE;

@Table(name = "store")
@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Store extends BaseEntity {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "store_id")
    @BatchSize(size = 50)
    private List<LocalizedStore> localizedStore;

    @Column(name = "phone_number", length = 20)
    @NotNull
    @NotEmpty
    private String phoneNumber;

    @Column(name = "facebook_url", length = 1000)
    @NotNull
    @NotEmpty
    private String facebookUrl;

    @Column(name = "instagram_url", length = 1000)
    @NotNull
    @NotEmpty
    private String instagramUrl;

    @Column(name = "web_url", length = 1000)
    @NotNull
    @NotEmpty
    private String webUrl;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    private District district;

    private StoreStatus status;

    public void addLocalizedStore(LocalizedStore localizedStoreInput) {
        if (!Objects.isNull(localizedStoreInput)) {
            if (Objects.isNull(localizedStore)) {
                localizedStore = new ArrayList<LocalizedStore>();
            }
            localizedStore.add(localizedStoreInput);
        }
    }

    public StoreResponse toResponse(Locale locale) {
        var matchedStore = localizedStore.stream()
                .filter(it -> it.getLocale().equals(locale))
                .findFirst()
                .get();

        if (Objects.isNull(matchedStore)) {
            matchedStore = localizedStore.stream()
                    .filter(it -> it.getLocale().equals(DEFAULT_LOCALE))
                    .findFirst()
                    .get();
        }

        return StoreResponse.builder()
                .id(getId())
                .phoneNumber(phoneNumber)
                .facebookUrl(facebookUrl)
                .instagramUrl(instagramUrl)
                .webUrl(webUrl)
                .latitude(latitude)
                .longitude(longitude)
                .district(district.toResponse(locale))
                .status(status)
                .name(matchedStore.getName())
                .description(matchedStore.getDescription())
                .address(matchedStore.getAddress())
                .build();
    }
}

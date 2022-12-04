package com.kinpetstore.restbackend.entity;

import com.kinpetstore.restbackend.base.entity.BaseEntity;
import com.kinpetstore.restbackend.domain.response.DistrictResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Table(name = "district")
@Getter
@Setter
@Entity
public class District extends BaseEntity {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "district_id")
    @BatchSize(size = 50)                   //Query in batch to avoid N+1 problem
    private List<LocalizedDistrict> localizedDistricts;

    @NotNull
    @NotEmpty
    @Column(length = 20, unique = true)
    private String districtCode;

    //Custom setter instead of hibernate setter function
    public void setLocalizedDistrict(List<LocalizedDistrict> incomingLocalizedDistrictList) {
        localizedDistricts.forEach(
                it -> {
                    var matchedDistrict = incomingLocalizedDistrictList.stream()
                            .filter(newDistrict -> newDistrict.getLocale().equals(it.getLocale())).findFirst()
                            .orElse(null);

                    if (!Objects.isNull(matchedDistrict)) {
                        it.setName(matchedDistrict.getName());
                    }
                }
        );
    }

    public void addLocalizedDistrict(LocalizedDistrict localizedDistrictInput) {
        if (!Objects.isNull(localizedDistrictInput)) {
            if (Objects.isNull(localizedDistricts)) {
                localizedDistricts = new ArrayList<LocalizedDistrict>();
            }
            localizedDistricts.add(localizedDistrictInput);
        }
    }

    public DistrictResponse toResponse(Locale locale) {
        return DistrictResponse.builder()
                .id(getId())
                .districtCode(districtCode)
                .name(localizedDistricts.stream()
                        .filter(it -> it.getLocale().equals(locale))
                        .findFirst()
                        .get()
                        .getName()
                )
                .build();
    }
}

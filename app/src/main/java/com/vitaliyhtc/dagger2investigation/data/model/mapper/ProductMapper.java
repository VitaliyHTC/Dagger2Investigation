package com.vitaliyhtc.dagger2investigation.data.model.mapper;

import com.vitaliyhtc.dagger2investigation.domain.model.Product;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class ProductMapper implements Function<com.vitaliyhtc.dagger2investigation.data.model.response.Product, Product> {

    @Override
    public Product apply(@NonNull com.vitaliyhtc.dagger2investigation.data.model.response.Product p) throws Exception {
        return new Product(
                p.getId(),
                p.isDead(),
                p.getName(),
                p.getTags(),
                p.isDiscontinued(),
                p.getPriceInCents(),
                p.getRegularPriceInCents(),
                p.getLimitedTimeOfferSavingsInCents(),
                p.getLimitedTimeOfferEndsOn(),
                p.getBonusRewardMiles(),
                p.getBonusRewardMilesEndsOn(),
                p.getStockType(),
                p.getPrimaryCategory(),
                p.getSecondaryCategory(),
                p.getOrigin(),
                p.getPackageOfProduct(),
                p.getPackageUnitType(),
                p.getPackageUnitVolumeInMilliliters(),
                p.getTotalPackageUnits(),
                p.getVolumeInMilliliters(),
                p.getAlcoholContent(),
                p.getPricePerLiterOfAlcoholInCents(),
                p.getPricePerLiterInCents(),
                p.getInventoryCount(),
                p.getInventoryVolumeInMilliliters(),
                p.getInventoryPriceInCents(),
                p.getSugarContent(),
                p.getProducerName(),
                p.getReleasedOn(),
                p.isHasValueAddedPromotion(),
                p.isHasLimitedTimeOffer(),
                p.isHasBonusRewardMiles(),
                p.isSeasonal(),
                p.isVqa(),
                p.isOcb(),
                p.isKosher(),
                p.getValueAddedPromotionDescription(),
                p.getDescription(),
                p.getServingSuggestion(),
                p.getTastingNote(),
                p.getUpdatedAt(),
                p.getImageThumbUrl(),
                p.getImageUrl(),
                p.getVarietal(),
                p.getStyle(),
                p.getTertiaryCategory(),
                p.getSugarInGramsPerLiter(),
                p.getClearanceSaleSavingsInCents(),
                p.isHasClearanceSale(),
                p.getProductNo()
        );
    }
}

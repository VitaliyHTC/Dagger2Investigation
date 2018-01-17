package com.vitaliyhtc.dagger2investigation.data.model.response;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Product {

    @SerializedName("id")
    private int id;

    @SerializedName("is_dead")
    private boolean isDead;
    @SerializedName("name")
    private String name;
    @SerializedName("tags")
    private String tags;
    @SerializedName("is_discontinued")
    private boolean isDiscontinued;
    @SerializedName("price_in_cents")
    private int priceInCents;
    @SerializedName("regular_price_in_cents")
    private int regularPriceInCents;
    @SerializedName("limited_time_offer_savings_in_cents")
    private int limitedTimeOfferSavingsInCents;
    @SerializedName("limited_time_offer_ends_on")
    private String limitedTimeOfferEndsOn;
    @SerializedName("bonus_reward_miles")
    private int bonusRewardMiles;
    @SerializedName("bonus_reward_miles_ends_on")
    private String bonusRewardMilesEndsOn;
    @SerializedName("stock_type")
    private String stockType;
    @SerializedName("primary_category")
    private String primaryCategory;
    @SerializedName("secondary_category")
    private String secondaryCategory;
    @SerializedName("origin")
    private String origin;
    @SerializedName("package")
    private String packageOfProduct;
    @SerializedName("package_unit_type")
    private String packageUnitType;
    @SerializedName("package_unit_volume_in_milliliters")
    private int packageUnitVolumeInMilliliters;
    @SerializedName("total_package_units")
    private int totalPackageUnits;
    @SerializedName("volume_in_milliliters")
    private int volumeInMilliliters;
    @SerializedName("alcohol_content")
    private int alcoholContent;
    @SerializedName("price_per_liter_of_alcohol_in_cents")
    private int pricePerLiterOfAlcoholInCents;
    @SerializedName("price_per_liter_in_cents")
    private int pricePerLiterInCents;
    @SerializedName("inventory_count")
    private int inventoryCount;
    @SerializedName("inventory_volume_in_milliliters")
    private long inventoryVolumeInMilliliters;
    @SerializedName("inventory_price_in_cents")
    private long inventoryPriceInCents;
    @SerializedName("sugar_content")
    private String sugarContent;
    @SerializedName("producer_name")
    private String producerName;
    @SerializedName("released_on")
    private String releasedOn;
    @SerializedName("has_value_added_promotion")
    private boolean hasValueAddedPromotion;
    @SerializedName("has_limited_time_offer")
    private boolean hasLimitedTimeOffer;
    @SerializedName("has_bonus_reward_miles")
    private boolean hasBonusRewardMiles;
    @SerializedName("is_seasonal")
    private boolean isSeasonal;
    @SerializedName("is_vqa")
    private boolean isVqa;
    @SerializedName("is_ocb")
    private boolean isOcb;
    @SerializedName("is_kosher")
    private boolean isKosher;
    @SerializedName("value_added_promotion_description")
    private String valueAddedPromotionDescription;
    @SerializedName("description")
    private String description;
    @SerializedName("serving_suggestion")
    private String servingSuggestion;
    @SerializedName("tasting_note")
    private String tastingNote;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("image_thumb_url")
    private String imageThumbUrl;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("varietal")
    private String varietal;
    @SerializedName("style")
    private String style;
    @SerializedName("tertiary_category")
    private String tertiaryCategory;
    @SerializedName("sugar_in_grams_per_liter")
    private String sugarInGramsPerLiter;
    @SerializedName("clearance_sale_savings_in_cents")
    private int clearanceSaleSavingsInCents;
    @SerializedName("has_clearance_sale")
    private boolean hasClearanceSale;
    @SerializedName("product_no")
    private int productNo;


    public Product() {
    }

    public Product(int id, boolean isDead, String name, String tags, boolean isDiscontinued,
                   int priceInCents, int regularPriceInCents, int limitedTimeOfferSavingsInCents,
                   String limitedTimeOfferEndsOn, int bonusRewardMiles, String bonusRewardMilesEndsOn,
                   String stockType, String primaryCategory, String secondaryCategory, String origin,
                   String packageOfProduct, String packageUnitType, int packageUnitVolumeInMilliliters,
                   int totalPackageUnits, int volumeInMilliliters, int alcoholContent, int pricePerLiterOfAlcoholInCents,
                   int pricePerLiterInCents, int inventoryCount, long inventoryVolumeInMilliliters,
                   long inventoryPriceInCents, String sugarContent, String producerName, String releasedOn,
                   boolean hasValueAddedPromotion, boolean hasLimitedTimeOffer, boolean hasBonusRewardMiles,
                   boolean isSeasonal, boolean isVqa, boolean isOcb, boolean isKosher, String valueAddedPromotionDescription,
                   String description, String servingSuggestion, String tastingNote, String updatedAt,
                   String imageThumbUrl, String imageUrl, String varietal, String style, String tertiaryCategory,
                   String sugarInGramsPerLiter, int clearanceSaleSavingsInCents, boolean hasClearanceSale, int productNo) {
        this.id = id;
        this.isDead = isDead;
        this.name = name;
        this.tags = tags;
        this.isDiscontinued = isDiscontinued;
        this.priceInCents = priceInCents;
        this.regularPriceInCents = regularPriceInCents;
        this.limitedTimeOfferSavingsInCents = limitedTimeOfferSavingsInCents;
        this.limitedTimeOfferEndsOn = limitedTimeOfferEndsOn;
        this.bonusRewardMiles = bonusRewardMiles;
        this.bonusRewardMilesEndsOn = bonusRewardMilesEndsOn;
        this.stockType = stockType;
        this.primaryCategory = primaryCategory;
        this.secondaryCategory = secondaryCategory;
        this.origin = origin;
        this.packageOfProduct = packageOfProduct;
        this.packageUnitType = packageUnitType;
        this.packageUnitVolumeInMilliliters = packageUnitVolumeInMilliliters;
        this.totalPackageUnits = totalPackageUnits;
        this.volumeInMilliliters = volumeInMilliliters;
        this.alcoholContent = alcoholContent;
        this.pricePerLiterOfAlcoholInCents = pricePerLiterOfAlcoholInCents;
        this.pricePerLiterInCents = pricePerLiterInCents;
        this.inventoryCount = inventoryCount;
        this.inventoryVolumeInMilliliters = inventoryVolumeInMilliliters;
        this.inventoryPriceInCents = inventoryPriceInCents;
        this.sugarContent = sugarContent;
        this.producerName = producerName;
        this.releasedOn = releasedOn;
        this.hasValueAddedPromotion = hasValueAddedPromotion;
        this.hasLimitedTimeOffer = hasLimitedTimeOffer;
        this.hasBonusRewardMiles = hasBonusRewardMiles;
        this.isSeasonal = isSeasonal;
        this.isVqa = isVqa;
        this.isOcb = isOcb;
        this.isKosher = isKosher;
        this.valueAddedPromotionDescription = valueAddedPromotionDescription;
        this.description = description;
        this.servingSuggestion = servingSuggestion;
        this.tastingNote = tastingNote;
        this.updatedAt = updatedAt;
        this.imageThumbUrl = imageThumbUrl;
        this.imageUrl = imageUrl;
        this.varietal = varietal;
        this.style = style;
        this.tertiaryCategory = tertiaryCategory;
        this.sugarInGramsPerLiter = sugarInGramsPerLiter;
        this.clearanceSaleSavingsInCents = clearanceSaleSavingsInCents;
        this.hasClearanceSale = hasClearanceSale;
        this.productNo = productNo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public boolean isDiscontinued() {
        return isDiscontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        isDiscontinued = discontinued;
    }

    public int getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(int priceInCents) {
        this.priceInCents = priceInCents;
    }

    public int getRegularPriceInCents() {
        return regularPriceInCents;
    }

    public void setRegularPriceInCents(int regularPriceInCents) {
        this.regularPriceInCents = regularPriceInCents;
    }

    public int getLimitedTimeOfferSavingsInCents() {
        return limitedTimeOfferSavingsInCents;
    }

    public void setLimitedTimeOfferSavingsInCents(int limitedTimeOfferSavingsInCents) {
        this.limitedTimeOfferSavingsInCents = limitedTimeOfferSavingsInCents;
    }

    public String getLimitedTimeOfferEndsOn() {
        return limitedTimeOfferEndsOn;
    }

    public void setLimitedTimeOfferEndsOn(String limitedTimeOfferEndsOn) {
        this.limitedTimeOfferEndsOn = limitedTimeOfferEndsOn;
    }

    public int getBonusRewardMiles() {
        return bonusRewardMiles;
    }

    public void setBonusRewardMiles(int bonusRewardMiles) {
        this.bonusRewardMiles = bonusRewardMiles;
    }

    public String getBonusRewardMilesEndsOn() {
        return bonusRewardMilesEndsOn;
    }

    public void setBonusRewardMilesEndsOn(String bonusRewardMilesEndsOn) {
        this.bonusRewardMilesEndsOn = bonusRewardMilesEndsOn;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getPrimaryCategory() {
        return primaryCategory;
    }

    public void setPrimaryCategory(String primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    public String getSecondaryCategory() {
        return secondaryCategory;
    }

    public void setSecondaryCategory(String secondaryCategory) {
        this.secondaryCategory = secondaryCategory;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPackageOfProduct() {
        return packageOfProduct;
    }

    public void setPackageOfProduct(String packageOfProduct) {
        this.packageOfProduct = packageOfProduct;
    }

    public String getPackageUnitType() {
        return packageUnitType;
    }

    public void setPackageUnitType(String packageUnitType) {
        this.packageUnitType = packageUnitType;
    }

    public int getPackageUnitVolumeInMilliliters() {
        return packageUnitVolumeInMilliliters;
    }

    public void setPackageUnitVolumeInMilliliters(int packageUnitVolumeInMilliliters) {
        this.packageUnitVolumeInMilliliters = packageUnitVolumeInMilliliters;
    }

    public int getTotalPackageUnits() {
        return totalPackageUnits;
    }

    public void setTotalPackageUnits(int totalPackageUnits) {
        this.totalPackageUnits = totalPackageUnits;
    }

    public int getVolumeInMilliliters() {
        return volumeInMilliliters;
    }

    public void setVolumeInMilliliters(int volumeInMilliliters) {
        this.volumeInMilliliters = volumeInMilliliters;
    }

    public int getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(int alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public int getPricePerLiterOfAlcoholInCents() {
        return pricePerLiterOfAlcoholInCents;
    }

    public void setPricePerLiterOfAlcoholInCents(int pricePerLiterOfAlcoholInCents) {
        this.pricePerLiterOfAlcoholInCents = pricePerLiterOfAlcoholInCents;
    }

    public int getPricePerLiterInCents() {
        return pricePerLiterInCents;
    }

    public void setPricePerLiterInCents(int pricePerLiterInCents) {
        this.pricePerLiterInCents = pricePerLiterInCents;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public long getInventoryVolumeInMilliliters() {
        return inventoryVolumeInMilliliters;
    }

    public void setInventoryVolumeInMilliliters(long inventoryVolumeInMilliliters) {
        this.inventoryVolumeInMilliliters = inventoryVolumeInMilliliters;
    }

    public long getInventoryPriceInCents() {
        return inventoryPriceInCents;
    }

    public void setInventoryPriceInCents(long inventoryPriceInCents) {
        this.inventoryPriceInCents = inventoryPriceInCents;
    }

    public String getSugarContent() {
        return sugarContent;
    }

    public void setSugarContent(String sugarContent) {
        this.sugarContent = sugarContent;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(String releasedOn) {
        this.releasedOn = releasedOn;
    }

    public boolean isHasValueAddedPromotion() {
        return hasValueAddedPromotion;
    }

    public void setHasValueAddedPromotion(boolean hasValueAddedPromotion) {
        this.hasValueAddedPromotion = hasValueAddedPromotion;
    }

    public boolean isHasLimitedTimeOffer() {
        return hasLimitedTimeOffer;
    }

    public void setHasLimitedTimeOffer(boolean hasLimitedTimeOffer) {
        this.hasLimitedTimeOffer = hasLimitedTimeOffer;
    }

    public boolean isHasBonusRewardMiles() {
        return hasBonusRewardMiles;
    }

    public void setHasBonusRewardMiles(boolean hasBonusRewardMiles) {
        this.hasBonusRewardMiles = hasBonusRewardMiles;
    }

    public boolean isSeasonal() {
        return isSeasonal;
    }

    public void setSeasonal(boolean seasonal) {
        isSeasonal = seasonal;
    }

    public boolean isVqa() {
        return isVqa;
    }

    public void setVqa(boolean vqa) {
        isVqa = vqa;
    }

    public boolean isOcb() {
        return isOcb;
    }

    public void setOcb(boolean ocb) {
        isOcb = ocb;
    }

    public boolean isKosher() {
        return isKosher;
    }

    public void setKosher(boolean kosher) {
        isKosher = kosher;
    }

    public String getValueAddedPromotionDescription() {
        return valueAddedPromotionDescription;
    }

    public void setValueAddedPromotionDescription(String valueAddedPromotionDescription) {
        this.valueAddedPromotionDescription = valueAddedPromotionDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServingSuggestion() {
        return servingSuggestion;
    }

    public void setServingSuggestion(String servingSuggestion) {
        this.servingSuggestion = servingSuggestion;
    }

    public String getTastingNote() {
        return tastingNote;
    }

    public void setTastingNote(String tastingNote) {
        this.tastingNote = tastingNote;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getImageThumbUrl() {
        return imageThumbUrl;
    }

    public void setImageThumbUrl(String imageThumbUrl) {
        this.imageThumbUrl = imageThumbUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVarietal() {
        return varietal;
    }

    public void setVarietal(String varietal) {
        this.varietal = varietal;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTertiaryCategory() {
        return tertiaryCategory;
    }

    public void setTertiaryCategory(String tertiaryCategory) {
        this.tertiaryCategory = tertiaryCategory;
    }

    public String getSugarInGramsPerLiter() {
        return sugarInGramsPerLiter;
    }

    public void setSugarInGramsPerLiter(String sugarInGramsPerLiter) {
        this.sugarInGramsPerLiter = sugarInGramsPerLiter;
    }

    public int getClearanceSaleSavingsInCents() {
        return clearanceSaleSavingsInCents;
    }

    public void setClearanceSaleSavingsInCents(int clearanceSaleSavingsInCents) {
        this.clearanceSaleSavingsInCents = clearanceSaleSavingsInCents;
    }

    public boolean isHasClearanceSale() {
        return hasClearanceSale;
    }

    public void setHasClearanceSale(boolean hasClearanceSale) {
        this.hasClearanceSale = hasClearanceSale;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

}

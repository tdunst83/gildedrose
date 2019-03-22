package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals("Aged Brie")
                    && !isBackstageItem(item)) {
                decreasingQualityNotLessThanZero(item);
            } else {
                if (item.quality < 50) {
                    incrementingQuality(item,  1);

                    if (isBackstageItem(item)) {
                        increaseBasedOnSellIn(item);
                    }
                }
            }

            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!item.name.equals("Aged Brie")) {
                    if (!isBackstageItem(item)) {
                        decreasingQualityNotLessThanZero(item);
                    } else {
                        incrementingQuality(item, - item.quality);
                    }
                } else {
                    increaseIfNotMaxQualityReached(item, 1);
                }
            }
        }
    }

    private boolean isBackstageItem(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private void decreasingQualityNotLessThanZero(Item item) {
        if (item.quality > 0) {
            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                incrementingQuality(item, -1);
            }
        }
    }

    private void increaseBasedOnSellIn(Item item) {
        if (item.sellIn < 11) {
            int i = item.sellIn < 6 ? 2 : 1;
            increaseIfNotMaxQualityReached(item, i);
        }

    }

    private void increaseIfNotMaxQualityReached(Item item, int i) {
        if (item.quality < 50) {
            incrementingQuality(item, i);
        }
    }


    private void incrementingQuality(Item item, int i) {
        item.quality += i;
    }
}
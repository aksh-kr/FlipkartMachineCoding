package org.example.flipkartmachinecoding.utils;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.util.List;

public interface SlotStrategy {
    List<String> getSlots(String sellerId, List<AbstractReadWriteAccess.Item> items, String startDate);
}

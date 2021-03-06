package com.shysoft.amj;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by lijunfe on 10/3/2018.
 */
public class ScraperTest {
    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @Test
    public void testScrapByAsinWithPriceAvailable() throws Exception {

        Map<String, String> expectedProdInfo = new HashMap<String, String>();
        expectedProdInfo.put("prodName",  "G-Technology G-DRIVE PRO with Thunderbolt High Speed Portable RAID Solution 2TB (0G02828)");
        expectedProdInfo.put("price", "$699.95");
        expectedProdInfo.put("imgUrl", "https://images-na.ssl-images-amazon.com/images/I/710FeD12hIL._SL1426_.jpg");

        Scraper scraper = new Scraper();
        String asinWithPrice = "B00GJYCIVK";
        assertEquals(expectedProdInfo, scraper.scrapeByAsin(asinWithPrice));
    }

    @Test
    public void testScrapByAsinWhenPriceNotAvailable() throws Exception {

        Map<String, String> expectedProdInfo = new HashMap<String, String>();
        expectedProdInfo.put("prodName",  "G-Technology G-SPEED Q High-Performance 4-Bay RAID Storage Solution 16TB (USB3.0/eSATA/FireWire 800) (0G02840)");
        expectedProdInfo.put("price", "N/A");
        expectedProdInfo.put("imgUrl", "");

        Scraper scraper = new Scraper();
        String asinWithPrice = "B00EPGKA4G";
        assertEquals(expectedProdInfo, scraper.scrapeByAsin(asinWithPrice));
    }

    @Test
    public void testAmazonJapanScrapByAsin() throws  Exception {
        Map<String, String> expectedProdInfo = new HashMap<String, String>();
        expectedProdInfo.put("prodName",  "G-Technology G-SPEED Q High-Performance 4-Bay RAID Storage Solution 16TB (USB3.0/eSATA/FireWire 800) (0G02840)");
        expectedProdInfo.put("price", "N/A");
        expectedProdInfo.put("imgUrl", "");

        Scraper scraper = new Scraper();
        String asinWithPrice = "B00HEHL9YS";
        assertEquals(expectedProdInfo, scraper.scrapeByAsin(asinWithPrice));

    }

    @Test
    public void testScrapWhenNotExistingAsin() throws Exception {
        Scraper scraper = new Scraper();
        String asinNotFound = "NOTFOUND";

        Map<String, String> expectedProdInfo = new HashMap<String, String>();
        expectedProdInfo.put("prodName",  "N/A");
        expectedProdInfo.put("price", "N/A");
        expectedProdInfo.put("imgUrl", "");

        assertEquals(expectedProdInfo, scraper.scrapeByAsin(asinNotFound));
    }

}
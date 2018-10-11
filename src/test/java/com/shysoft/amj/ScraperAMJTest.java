package com.shysoft.amj;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by lijunfe on 10/3/2018.
 */
public class ScraperAMJTest {
    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @Test
    public void testScrapByAsinWithPriceAvailable() throws Exception {

        Map<String, String> expectedProdInfo = new HashMap<String, String>();
        expectedProdInfo.put("prodName",  "テラモト/テラモト EFコーナーブルーム30cm(3617769) CL-736-030-0 [その他]");
        expectedProdInfo.put("price", "￥ 1,534");
        expectedProdInfo.put("imgUrl", "https://images-fe.ssl-images-amazon.com/images/I/21FwjBP--vL._SY300_QL70_.jpg");

        ScraperAMJ scraper = new ScraperAMJ();
        String asinWithPrice = "B00HEHL9YS";
        assertEquals(expectedProdInfo, scraper.scrapeByAsin(asinWithPrice));
    }

    @Test
    public void testScrapByAsinWithPriceAvailableB003WSYUNK() throws Exception {

        Map<String, String> expectedProdInfo = new HashMap<String, String>();
        expectedProdInfo.put("prodName",  "HORIC アンテナ分波器 BS/CS/地デジ対応 白ケーブル2本付き(S-4C-FB) 40cm BCUV-971");
        expectedProdInfo.put("price", "￥ 880");
        expectedProdInfo.put("imgUrl", "https://images-fe.ssl-images-amazon.com/images/I/41k9d4z85%2BL._SY300_QL70_.jpg");

        ScraperAMJ scraper = new ScraperAMJ();
        String asinWithPrice = "B003WSYUNK";
        assertEquals(expectedProdInfo, scraper.scrapeByAsin(asinWithPrice));
    }

    @Test
    public void testScrapByAsinWithMultiplePriceAvailable() throws Exception {

        Map<String, String> expectedProdInfo = new HashMap<String, String>();
        expectedProdInfo.put("prodName",  "コンビ Combi テテオ teteo 授乳のお手本 哺乳びん耐熱ガラス製 240mｌ Mサイズ乳首付 (2・3ヵ月~18ヵ月まで) 4段階流量調節機能付き");
        expectedProdInfo.put("price", "￥ 1,264");
        expectedProdInfo.put("imgUrl", "https://images-fe.ssl-images-amazon.com/images/I/31DL3QvaetL._SY300_QL70_.jpg");

        ScraperAMJ scraper = new ScraperAMJ();
        String asinWithPrice = "B00I59NHZI";
        assertEquals(expectedProdInfo, scraper.scrapeByAsin(asinWithPrice));
    }


    @Test
    public void testScrapByAsinWhenPriceNotAvailable() throws Exception {

        ScraperAMJ scraper = new ScraperAMJ();
        String asinWithPrice = "B001U51210";
        assertEquals("N/A", scraper.scrapeByAsin(asinWithPrice).get("price"));
    }


    @Test
    public void testScrapWhenNotExistingAsin() throws Exception {
        ScraperAMJ scraper = new ScraperAMJ();
        String asinNotFound = "NOTFOUND";

        Map<String, String> expectedProdInfo = new HashMap<String, String>();
        expectedProdInfo.put("prodName",  "N/A");
        expectedProdInfo.put("price", "N/A");
        expectedProdInfo.put("imgUrl", "");

        assertEquals(expectedProdInfo, scraper.scrapeByAsin(asinNotFound));
    }

}
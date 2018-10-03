package com.shysoft.amj;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.JXDocument;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lijunfe on 9/23/2018.
 */
public class Scraper {

    private final static String urlAmazon = "http://www.amazon.com/dp/";

    public static void main(String[] args) {
        Scraper app =  new Scraper();
        try {
            String html = app.getHtml();
//            System.out.println(html);
//            app.testGetText(html);
            app.doScrap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> scrapeByAsin(String asin) {

        Document doc = null;
        try {
            doc = Jsoup.connect(urlAmazon+asin).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String htmlProdName = "h1#title";

        String prodName = "N/A";
        Elements elementsProd = doc.select(htmlProdName);
        if (!elementsProd.isEmpty()) {
            prodName = elementsProd.first().text();
        }


        String htmlPrice= "span#priceblock_ourprice";

        String price = "N/A";

        Elements elementsPrice = doc.select(htmlPrice);
        if (!elementsPrice.isEmpty()) {
            price = elementsPrice.first().text();
        }

        Map<String, String>  prodInfo = new HashMap<String, String>();
        prodInfo.put("prodName",  prodName);
        prodInfo.put("price", price);



        return prodInfo;
    }

    private void doScrap() throws Exception {
        long tS = System.currentTimeMillis();
        Document doc = Jsoup.connect("https://www.amazon.com/dp/B00GJYCIVK").get();
        long tE = System.currentTimeMillis();

        System.out.println("time spent on url " + (tE - tS));

        tS = System.currentTimeMillis();
        String elementProdName = "h1#title";

        Elements elementsProd = doc.select(elementProdName);
        String prodName = elementsProd.first().text();
        tE = System.currentTimeMillis();
        System.out.println("time spent on parsing " + (tE - tS));
        System.out.println(prodName);

        tS = System.currentTimeMillis();
        Elements elementsPrice = doc.select("span#priceblock_ourprice");
        String price = elementsPrice.first().text();
        tE = System.currentTimeMillis();
        System.out.println("time spent on parsing price " + (tE - tS));
        System.out.println("price: " + price);

    }

    private String getHtml() throws IOException {
        Document doc = Jsoup.connect("http://www.amazon.com/dp/B0046UR4F4").get();
        String html  = doc.html();
        return html;
    }

    private void testGetText(String html) {

        String xpath = "//h1[@id=\"title\"]//text()";
        JXDocument underTest =  JXDocument.create(html);
        List<Object> res = underTest.sel(xpath);

        System.out.println(res);


    }

    private void testHtmlCleaner(String html) {
//        CleanerProperties props = new CleanerProperties();
//        TagNode tagNode = new HtmlCleaner(props).clean(
//                new URL(url)
//        );
//        org.w3c.dom.Document doc = new DomSerializer(new CleanerProperties()).createDOM(tagNode);
    }
}

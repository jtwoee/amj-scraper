package com.shysoft.amj;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.JXDocument;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lijunfe on 9/23/2018.
 */
public class ScraperAMJ {

    private final static String urlAmazon = "https://www.amazon.co.jp/dp/";

    public static void main(String[] args) {
        ScraperAMJ app =  new ScraperAMJ();
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
        Map<String, String>  prodInfo = new HashMap<String, String>();
        String prodName = "N/A";
        String price = "N/A";
        String imgUrl = "";
        //assume no product info available
        prodInfo.put("prodName",  prodName);
        prodInfo.put("price", price);
        prodInfo.put("imgUrl", imgUrl);

        Document doc = null;
        try {
//            doc = Jsoup.connect(urlAmazon+asin).get();
             doc = Jsoup //
                    .connect(urlAmazon+asin) //
                    .proxy("web-proxy.rose.hp.com", 8080) // sets a HTTP proxy
                    .userAgent("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2") //
                    .header("Content-Language", "en-US") //
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
            //if connection failed or 404
            //doc = null;
        }

        if (doc == null) {
            return prodInfo;
        }

        String htmlProdName = "span#productTitle";


        Elements elementsProd = doc.select(htmlProdName);
        if (!elementsProd.isEmpty()) {
            prodName = elementsProd.first().text();
        }


        String htmlPrice= "span#priceblock_ourprice";



        Elements elementsPrice = doc.select(htmlPrice);
        if (!elementsPrice.isEmpty()) {
            price = elementsPrice.first().text();
        }

        String htmlImage = "div#imgTagWrapperId";



        Elements elementsImage = doc.select(htmlImage);

        if (!elementsImage.isEmpty()) {
            Element image = elementsImage.first().selectFirst("img");

            if (image != null) {
                imgUrl = image.attr("src");
            }
        }

        prodInfo.put("prodName",  prodName);
        prodInfo.put("price", price);
        prodInfo.put("imgUrl", imgUrl);

        return prodInfo;
    }

    private void doScrap() throws Exception {
        long tS = System.currentTimeMillis();
//        Document doc = Jsoup.connect("https://www.amazon.com/dp/B00GJYCIVK").get();
        Document doc = Jsoup.connect("https://www.amazon.com/dp/B00YW5DLB4").get();
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
        String price = "NA";
        if (!elementsPrice.isEmpty()) {
            price = elementsPrice.first().text();
        }

        tE = System.currentTimeMillis();
        System.out.println("time spent on parsing price " + (tE - tS));
        System.out.println("price: " + price);

        Elements elementsImage = doc.select("div#imgTagWrapperId");
//        System.out.println(elementsImage.first());
//        System.out.println(elementsImage.first().select("img"));
        Element image = elementsImage.first().selectFirst("img");
        System.out.println(image);
        System.out.println(image.attr("data-old-hires"));
        System.out.println(image.attr("src"));
//        String imageUrl = image.absUrl("src");
//        System.out.println(imageUrl);


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

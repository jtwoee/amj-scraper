package com.shysoft.amj;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by lijunfe on 9/29/2018.
 */
public class XpathScraper {

    public static void main(String[] args) {


            try {
                Document doc = Jsoup
                        .connect("http://registroapps.uniandes.edu.co/scripts/adm_con_horario1_joomla.php?depto=IIND")
                        .timeout(20000)
                        .get();

                Elements tds = doc.select("font:containsOwn(10110)");
                if (tds != null && tds.size() > 0) {
                    for (Element td : tds.parents().first().siblingElements()) {
                        System.out.println(td.text());
                    }
                }
                System.out.println("Done");
            } catch (IOException e) {
                e.printStackTrace();
            }



    }
}

package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.quartz.AlertRabbit;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HabrCareerParse {
    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer", SOURCE_LINK);

    private static final int PAGES = 5;

    private static List<String> data = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= PAGES; i++) {
            String link = String.format("%s?page=%s", PAGE_LINK, i);
            Connection connection = Jsoup.connect(link);
            readPage(connection.get());
        }
        data.forEach(System.out::println);
    }

    private static void readPage(Document document) {
        Elements rows = document.select(".vacancy-card__inner");
        rows.forEach(row -> {
            Element titleElement = row.select(".vacancy-card__title").first();
            Element linkElement = titleElement.child(0);
            String vacancyName = getTextElement(titleElement);
            String link = String.format("%s%s", SOURCE_LINK, getAttrElement(linkElement, "href"));

            Element dateEl = row.select(".vacancy-card__date").first();
            Element time = dateEl.child(0);
            String dateStr = getAttrElement(time, "dateTime");
            DateTimeParser dtParser = new HarbCareerDateTimeParser();
            LocalDateTime lDT = dtParser.parse(dateStr);
            data.add(String.format("%s %s %s",
                    vacancyName,
                    lDT.format(DateTimeFormatter.ISO_DATE),
                    link)
            );
        });
    }

    private static String getTextElement(Element element) {
        return element.text();
    }

    private static String getAttrElement(Element element, String attr) {
        return element.attr(attr);
    }

}

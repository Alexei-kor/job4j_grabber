package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {
    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer", SOURCE_LINK);

    private DateTimeParser dateTimeParser;

    private static final int PAGES = 5;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public List<Post> list(String link) {
        List<Post> data = new ArrayList<>();
        try {
            for (int i = 1; i <= PAGES; i++) {
                Connection connection = getConnnect(String.format("%s?page=%s", link, i));
                data.addAll(readPage(connection.get()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        HabrCareerParse hcp = new HabrCareerParse(new HarbCareerDateTimeParser());
        hcp.list(PAGE_LINK).forEach(System.out::println);
    }

    private List<Post> readPage(Document document) {
        Elements rows = document.select(".vacancy-card__inner");
        List<Post> data = new ArrayList<>();
        rows.forEach(row -> data.add(getPostFromSite(row)));
        return data;
    }

    private Post getPostFromSite(Element element) {
        Element titleElement = element.select(".vacancy-card__title").first();
        Element linkElement = titleElement.child(0);
        String vacancyName = getTextElement(titleElement);
        String link = String.format("%s%s", SOURCE_LINK, getAttrElement(linkElement, "href"));

        Element dateEl = element.select(".vacancy-card__date").first();
        Element time = dateEl.child(0);
        String dateStr = getAttrElement(time, "dateTime");
        return new Post(
                vacancyName,
                link,
                retrieveDescription(link),
                dateTimeParser.parse(dateStr));
    }

    private String retrieveDescription(String link) {
        String rsl = "";
        Connection connection = getConnnect(link);
        try {
            Document document = connection.get();
            rsl = document.select(".style-ugc").text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private Connection getConnnect(String link) {
        return Jsoup.connect(link);
    }

    private String getTextElement(Element element) {
        return element.text();
    }

    private String getAttrElement(Element element, String attr) {
        return element.attr(attr);
    }

}

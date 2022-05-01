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

    private static final DateTimeParser DATE_TIME_PARSER = new HarbCareerDateTimeParser();

    @Override
    public List<Post> list(String link) {
        List<Post> data = new ArrayList<>();
        try {
            Connection connection = getConnnect(link);
            data.addAll(readPage(connection.get()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        HabrCareerParse hcp = new HabrCareerParse();
        hcp.list(PAGE_LINK).forEach(System.out::println);
    }

    private static List<Post> readPage(Document document) {
        Elements rows = document.select(".vacancy-card__inner");
        List<Post> data = new ArrayList<>();
        rows.forEach(row -> data.add(getPostFromSite(row)));
        return data;
    }

    private static Post getPostFromSite(Element element) {
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
                DATE_TIME_PARSER.parse(dateStr));
    }

    private static String retrieveDescription(String link) {
        StringBuilder stringBuilder = new StringBuilder();
        Connection connection = getConnnect(link);
        try {
            Document document = connection.get();
            Element el = document.select(".job_show_description__vacancy_description").first();
            el.child(0).children().forEach(row -> {
                stringBuilder.append(row.text());
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private static Connection getConnnect(String link) {
        return Jsoup.connect(link);
    }

    private static String getTextElement(Element element) {
        return element.text();
    }

    private static String getAttrElement(Element element, String attr) {
        return element.attr(attr);
    }

}

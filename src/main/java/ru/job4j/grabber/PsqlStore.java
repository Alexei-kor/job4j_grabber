package ru.job4j.grabber;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {

    private Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            cnn = DriverManager.getConnection(
                    cfg.getProperty("url"),
                    cfg.getProperty("username"),
                    cfg.getProperty("password")
            );
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Post post) {
        String request = "insert into grab.posts(name, text, link, created) values(?, ?, ?, ?)";
        try (PreparedStatement prSt = cnn.prepareStatement(request)) {
            prSt.setString(1, post.getTitle());
            prSt.setString(2, post.getDescription());
            prSt.setString(3, post.getLink());
            prSt.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> rsl = new ArrayList<>();
        String request = "select * from grab.posts";
        try (Statement st = cnn.createStatement()) {
            ResultSet set = st.executeQuery(request);
            while (set.next()) {
                rsl.add(getPost(set));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public Post findById(int id) {
        Post rsl = null;
        String request = "select * from grab.posts where id = ?";
        try (PreparedStatement prSt = cnn.prepareStatement(request)) {
            prSt.setInt(1, id);
            ResultSet set = prSt.executeQuery();
            if (set.next()) {
                rsl = getPost(set);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    private Post getPost(ResultSet set) throws SQLException {
        return new Post(
                set.getInt(1),
                set.getString(2),
                set.getString(3),
                set.getString(4),
                set.getTimestamp(5).toLocalDateTime()
        );
    }

    public static void main(String[] args) {
        Properties prop = loadProperties();
        HabrCareerParse hcp = new HabrCareerParse(new HarbCareerDateTimeParser());
        PsqlStore store = new PsqlStore(prop);
        hcp.list("https://career.habr.com/vacancies/java_developer").forEach(store::save);
        System.out.println(store.findById(15));
        store.getAll().forEach(System.out::println);
    }

    public static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = PsqlStore.class
                .getClassLoader()
                .getResourceAsStream("careerParser.properties")) {
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }
}

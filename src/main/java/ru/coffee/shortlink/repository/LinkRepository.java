package ru.coffee.shortlink.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.coffee.shortlink.model.Link;

import java.util.List;

@Repository
public class LinkRepository {

    private final JdbcTemplate jdbcTemplate;

    public LinkRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void storeLink(String link, String original) {
        String storeShortLink = "INSERT INTO link (link, original, rank ,`count`) VALUES (?, ?, 0, 0)";
        jdbcTemplate.update(storeShortLink, link, original);
    }

    public String findFullLink(String link) {
        String sql = "SELECT original FROM link WHERE link = ?";

        return jdbcTemplate.queryForObject(sql, String.class, link);
    }

    public Link findStats(String link) {
        String sql = "SELECT link, original, rank, `count` FROM link " +
                "WHERE link = ? ORDER BY count DESC";
        RowMapper<Link> rowMapper = (r, i) -> {
            Link link1 = new Link();
            link1.setLink(r.getString("link"));
            link1.setOriginal((r.getString("original")));
            link1.setRank(r.getInt("rank"));
            link1.setCount(r.getInt("count"));
            return link1;
        };
        return jdbcTemplate.queryForObject(sql, rowMapper, link);
    }

    public void changeCount(String link) {
        String sql = "UPDATE link SET `count` = `count` + 1 WHERE link = ?";
        jdbcTemplate.update(sql, link);
    }

    public List<Link> findPageAndCount(int page, int count) {
        String sql = "";
        RowMapper<Link> rowMapper = (r, i) -> {
            Link link1 = new Link();
            link1.setLink(r.getString("link"));
            link1.setOriginal((r.getString("original")));
            link1.setRank(r.getInt("rank"));
            link1.setCount(r.getInt("count"));
            return link1;
        };


        return jdbcTemplate.query(sql, rowMapper);
    }
}

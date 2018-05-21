package com.sg.dao;

import com.sg.model.DVD;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class DvdDaoJdbcTemplateImpl implements DvdDao {

    private JdbcTemplate jt;

    // setter injections
    public void setJdbcTemplate(JdbcTemplate jt) {
        this.jt = jt;
    }

    private static final String SQL_INSERT_DVD = "insert into Dvd (Title, ReleaseYear, Director,  Rating, Notes)" +
            "Values(?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_DVD = "delete from Dvd where DvdID = ?";

    private static final String SQL_UPDATE_DVD = "update Dvd set Title = ?, ReleaseYear = ?, Director = ?, " +
            "Rating = ?, Notes = ? where DvdID = ?";

    private static final String SQL_SELECT_DVD_BY_ID = "select * from Dvd where DvdID = ?";

    private static final String SQL_SELECT_ALL_DVD = "select * from Dvd";

    // SQL statements for search
    private static final String SQL_SEARCH_TITLE = "select * from Dvd where Title = ?";

    private static final String SQL_SEARCH_DIRECTOR = "select * from Dvd where Director = ?";

    private static final String SQL_SEARCH_RELEASE_YEAR = "select * from Dvd where ReleaseYear = ?";

    private static final String SQL_SEARCH_RATING = "select * from Dvd where Rating = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public DVD addDvd(DVD dvd) {
        jt.update(SQL_INSERT_DVD, dvd.getTitle(), Date.valueOf(dvd.getReleaseYear()), dvd.getDirector(),  dvd.getRating(),
        dvd.getNotes());

        int id = jt.queryForObject("select LAST_INSERT_ID()", Integer.class);
        dvd.setDvdId(id);

        return dvd;
    }

    @Override
    public void removeDvd(int dvdId) {
        jt.update(SQL_DELETE_DVD, dvdId);

    }

    @Override
    public void updateDvd(DVD dvd) {
        jt.update(SQL_UPDATE_DVD, dvd.getTitle(), Date.valueOf(dvd.getReleaseYear()), dvd.getDirector(),
                dvd.getRating(), dvd.getNotes(), dvd.getDvdId());

    }

    @Override
    public List<DVD> retrieveAllDvds() {

        return jt.query(SQL_SELECT_ALL_DVD, new DvdMapper());
    }

    @Override
    public DVD retrieveDvdById(int dvdId) {
        try {
            return jt.queryForObject(SQL_SELECT_DVD_BY_ID, new DvdMapper(), dvdId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<DVD> searchContacts(String searchTerm, String category) {
        // get all the dvds

        List<DVD> dvdSearchList = new ArrayList<>();

        switch (category) {
            case "title":
               dvdSearchList = searchByTitle(searchTerm);
                break;
            case "release-year":
                dvdSearchList = searchByReleaseYear(searchTerm);
                break;
            case "director-name":
                dvdSearchList = searchByDirector(searchTerm);
                break;
            case "rating":
                dvdSearchList = searchByRating(searchTerm);
                break;
        }
        return dvdSearchList;
    }

    private List<DVD> searchByTitle(String userSearch) {

        return jt.query(SQL_SEARCH_TITLE, new DvdMapper(),userSearch);
    }

    private List<DVD> searchByReleaseYear(String userSearch) {
        return jt.query(SQL_SEARCH_RELEASE_YEAR, new DvdMapper(), Date.valueOf(userSearch));
    }

    private List<DVD> searchByDirector(String userSearch) {
        return jt.query(SQL_SEARCH_DIRECTOR, new DvdMapper(),userSearch);
    }

    private List<DVD> searchByRating(String userSearch) {
        return jt.query(SQL_SEARCH_RATING, new DvdMapper(),userSearch);
    }

    private static final class DvdMapper implements RowMapper<DVD> {

        @Override
        public DVD mapRow(ResultSet rs, int i) throws SQLException {
            // create new instance of DVD
            DVD dvd = new DVD();
            // set values from database on object
            dvd.setTitle(rs.getString("Title"));
            dvd.setReleaseYear(rs.getDate("ReleaseYear").toLocalDate());
            dvd.setDirector(rs.getString("Director"));
            dvd.setRating(rs.getString("Rating"));
            dvd.setNotes(rs.getString("Notes"));
            dvd.setDvdId(rs.getInt("DvdID"));

            return dvd;

        }
    }
}

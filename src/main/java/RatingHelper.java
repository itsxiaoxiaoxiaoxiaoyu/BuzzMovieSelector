import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * RatingHelper Class which helps communicate with controller information.
 * with database
 * @author huangdun
 */
public final class RatingHelper {
    /**
     * Constructor.
     */
    private RatingHelper() {
    }
    /**
     * findRatingsByMovieId method which returns all rating records.
     * of the given movieId
     * @param newMovieId movieId of the expected Rating list
     * @return a list of Rating whose movieId matches given movieId
     */
    public static List<Rating> findRatingsByMovieId(final String newMovieId) {
        Connection con = null;
        PreparedStatement pst = null;

        List<Rating> ratings = new ArrayList<>();

        try {
            con = Connector.getConnection();
            String stm = "select * from tb_rating where movieid = ?";
            try {
                pst = con.prepareStatement(stm);
                pst.setString(1, newMovieId);
                pst.execute();
                ResultSet rs = pst.getResultSet();

                if (rs.next()) {
                    do {
                        int ratingId = rs.getInt("rating");
                        int user = rs.getInt("user");
                        int score = rs.getInt("score");
                        String comment = rs.getString("comment");
                        ratings.add(new Rating(ratingId, user, newMovieId,
                                comment, score));
                    } while (rs.next());
                }

            } catch (SQLException e1) {
                Logger.getLogger(UserManager.class.getName())
                    .log(Level.SEVERE, null, e1);
            } finally {
                if (pst != null) {
                        pst.close();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserManager.class.getName())
                .log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                        con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserManager.class.getName())
                    .log(Level.SEVERE, null, ex);
            }
        }
        return ratings;
    }

    /**
     * Insert the rating in the database if no previous rating
     * has the same user id and movie id exists. Update if it
     * exists
     * @param rating Rating record which we want to update or insert
     */
    public static void insertOrUpdateRating(final Rating rating) {
        Connection con = null;
        PreparedStatement pst = null;
        final int three = 3;
        final int four = 4;
        try {
            con = Connector.getConnection();
            try {
                String stm = "select * from tb_rating where rating = ?";
                pst = con.prepareStatement(stm);
                pst.setInt(1, rating.getRatingId());
                pst.execute();
                ResultSet rs = pst.getResultSet();
                try {
                    if (!rs.next()) {
                        stm = "insert into tb_rating(\"user\", movieid,"
                                + " comment, score) values(?, ?, ?, ? )";
                        pst = con.prepareStatement(stm);
                        pst.setInt(1, rating.getUser());
                        pst.setString(2, rating.getMovieId());
                        pst.setString(three, rating.getComment());
                        pst.setInt(four, rating.getScore());
                    } else {
                        stm = "update tb_rating set "
                                + "comment = ?, "
                                + "score = ? "
                                + "where rating = ?";
                        pst = con.prepareStatement(stm);
                        pst.setString(1, rating.getComment());
                        pst.setInt(2, rating.getScore());
                        pst.setInt(three, rating.getRatingId());
                    }

                    pst.execute();
                } catch (SQLException e1) {
                    Logger.getLogger(UserManager.class.getName())
                        .log(Level.SEVERE, null, e1);
                } finally {
                        pst.close();
                }
            } catch (SQLException e1) {
            Logger.getLogger(UserManager.class.getName())
                .log(Level.SEVERE, null, e1);
            } finally {
                    pst.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserManager.class.getName())
                .log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                        con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserManager.class.getName())
                    .log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Parse the filter to get a list of Ratings with top 24.
     * highest average scores
     * @param filters a list of filters
     * @return a list of movieIds of filtered ratings with
     * 24 highest average scores
     */
    static List<String> findFilteredTopMovies(final
            Map<String, Integer> filters) {
        Connection con;

        List<String> movieIds = new ArrayList<>();

        try {
            con = Connector.getConnection();
            String stm = "select movieid, avg(score) from tb_rating r"
                    + " join tb_user u on r.user = u.user where true";



            if (filters.containsKey("major")) {
                stm += " and u.major = :major";
            }

            if (filters.containsKey("gender")) {
                stm += " and u.gender = :gender";
            }


            stm += " group by movieid order by avg desc limit 24";

            NamedParameterStatement nps = new NamedParameterStatement(con, stm);
            if (filters.containsKey("major")) {
                nps.setInt("major", filters.get("major"));
            }

            if (filters.containsKey("gender")) {
                nps.setInt("gender", filters.get("gender"));
            }

            System.out.println(stm);

            ResultSet rs = nps.executeQuery();
            con.close();

            if (rs.next()) {
                do {
                    movieIds.add(rs.getString("movieid"));
                } while (rs.next());
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return movieIds;
    }
}
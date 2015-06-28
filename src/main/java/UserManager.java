import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UserManager Class which help do operations on database table associating with
 * users.
 * @author huangdun
 */
public final class UserManager {
    /**
     * Utility class does not have a constructor.
     */
    private UserManager() {
    }
    /**
     * Static method find which returns information of the user with given
     * username.
     * @param username username of user expected to be found
     * @return User object which contains all information of the user
     */
    public static User find(final String username) {
        Connection con = null;
        PreparedStatement pst = null;
        User user = new User(username);

        try {
            con = Connector.getConnection();
            String stm = "select * from tb_user where username = ?";

            try {
                pst = con.prepareStatement(stm);
                pst.setString(1, username);
                pst.execute();
                ResultSet rs = pst.getResultSet();

                if (rs.next()) {
                    user.setPassword(rs.getString("password"));
                    user.setUser(rs.getInt("user"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    user.setFirstName(rs.getString("firstname"));
                    user.setLastName(rs.getString("lastname"));
                    user.setMajor(rs.getInt("major"));
                    user.setGender(rs.getInt("gender"));
                    user.setRole(rs.getInt("role"));
                    user.setStatus(rs.getInt("status"));
                    user.setInterest(rs.getString("interest"));
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
            Logger.getLogger(
                UserManager.class.getName()).log(Level.SEVERE, null, ex
            );
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

        return user;
    }

    /**
     * Lock the account of given user id.
     * @param user user id of the user to be locked
     */
    public static void lockAccount(final int user) {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = Connector.getConnection();
            String stm = "update tb_user set status = 3 where \"user\" = ?";

            try {
                pst = con.prepareStatement(stm);
                pst.setInt(1, user);
                pst.execute();
            } catch (SQLException e1) {
                Logger.getLogger(UserManager.class.getName())
                    .log(Level.SEVERE, null, e1);
            } finally {
                if (pst != null) {
                    pst.close();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(
                UserManager.class.getName()).log(Level.SEVERE, null, ex
            );
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
     * Returns all users in the system.
     * @return array list of all users in the system
     */
    public static ArrayList<User> getAllUsers() {
        Connection con = null;
        PreparedStatement pst = null;
        ArrayList<User> userList;
        userList = new ArrayList();

        try {
            con = Connector.getConnection();
            String stm = "select * from tb_user";

            try {
                pst = con.prepareStatement(stm);
                pst.execute();
                ResultSet rs = pst.getResultSet();

                if (rs.next()) {
                    do {
                        User user = new User();
                        user.setUsername(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        user.setUser(rs.getInt("user"));
                        user.setEmail(rs.getString("email"));
                        user.setFirstName(rs.getString("firstname"));
                        user.setLastName(rs.getString("lastname"));
                        user.setMajor(rs.getInt("major"));
                        user.setRole(rs.getInt("role"));
                        user.setStatus(rs.getInt("status"));

                        userList.add(user);
                    } while (rs.next());
                } else {
                    return null;
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
            Logger.getLogger(
                UserManager.class.getName()).log(Level.SEVERE, null, ex
            );
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


        return userList;
    }

    /**
     * Return a list of all User Statuses (Locked, Banned, Active).
     * @return a list of all user status information from database
     */
    public static List<Status> getStatuses() {
        Connection con = null;
        PreparedStatement pst = null;

        List<Status> statuses = new ArrayList<>();

        try {
            con = Connector.getConnection();
            String stm = "select * from tb_status";

            try {
                pst = con.prepareStatement(stm);
                pst.execute();
                ResultSet rs = pst.getResultSet();

                if (rs.next()) {
                    do {
                        statuses.add(
                            new Status(rs.getInt("status"),
                            rs.getString("label"))
                        );
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
            Logger.getLogger(
                UserManager.class.getName()).log(Level.SEVERE, null, ex
            );
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

        return statuses;
    }

    /**
     * Return a list of all Major.
     * @return a list of all Major information from database
     */
    public static List<Major> getAllMajors() {
        Connection con = null;
        PreparedStatement pst = null;

        List<Major> majors = new ArrayList<>();

        try {
            con = Connector.getConnection();
            String stm = "select * from tb_major";

            pst = con.prepareStatement(stm);
            pst.execute();
            ResultSet rs = pst.getResultSet();

            try {
                if (rs.next()) {
                    do {
                        majors.add(
                            new Major(rs.getInt("major"), rs.getString("label"))
                        );
                    } while (rs.next());
                }
            } catch (SQLException e1) {
                Logger.getLogger(UserManager.class.getName())
                    .log(Level.SEVERE, null, e1);
            } finally {
                pst.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(
                UserManager.class.getName()).log(Level.SEVERE, null, ex
            );
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

        return majors;
    }

    /**
     * Return a list of all User Genders(Male, Female).
     * @return a list of all user genders information from database
     */
    public static List<Gender> getAllGenders() {
        Connection con = null;
        PreparedStatement pst = null;

        List<Gender> genders = new ArrayList<>();

        try {
            con = Connector.getConnection();
            String stm = "select * from tb_gender";

            try {
                pst = con.prepareStatement(stm);
                pst.execute();
                ResultSet rs = pst.getResultSet();

                if (rs.next()) {
                    do {
                        genders.add(
                            new Gender(rs.getInt("gender"),
                            rs.getString("label"))
                        );
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
            Logger.getLogger(
                UserManager.class.getName()).log(Level.SEVERE, null, ex
            );
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

        return genders;
    }

    /**
     * Insert new user information into database.
     * @param newUser a User Object which contains all
     * information to be inserted
     */
    public static void registerUser(final User newUser) {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = Connector.getConnection();
            try {
                int field = 1;
                String stm = "insert into "
                        + "tb_user("
                        + "username, "
                        + "password, "
                        + "firstname, "
                        + "lastname, "
                        + "major, "
                        + "status, "
                        + "role) "
                        + "values (?, ?, ?, ?, ?, ?, ?)";
                pst = con.prepareStatement(stm);
                pst.setString(field++, newUser.getUsername());
                pst.setString(field++, Encryptor
                        .encrypt(newUser.getPassword()));
                pst.setString(field++, newUser.getFirstName());
                pst.setString(field++, newUser.getLastName());
                pst.setInt(field++, newUser.getMajor());
                pst.setInt(field++, newUser.getStatus());
                pst.setInt(field++, newUser.getRole());

                pst.execute();
            } catch (SQLException e1) {
                Logger.getLogger(UserManager.class.getName())
                    .log(Level.SEVERE, null, e1);
            } finally {
                pst.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(
                UserManager.class.getName()).log(Level.SEVERE, null, ex
            );
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
     * Update the given user in database.
     * @param updatedUser the User object which contains all user information,
     * which is supposed to be updated
     */
    public static void updateUser(final User updatedUser) {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = Connector.getConnection();
            try {
                int field = 1;
                String stm = "update tb_user " + "set "
                        + "password = ?, "
                        + "firstname = ?, "
                        + "lastname = ?, "
                        + "email = ?, "
                        + "phone = ?, "
                        + "interest = ?, "
                        + "major = ?, "
                        + "gender = ?, "
                        + "status = ? "
                        + "where \"user\" = ?";

                pst = con.prepareStatement(stm);
                pst.setString(field++, updatedUser.getPassword());
                pst.setString(field++, updatedUser.getFirstName());
                pst.setString(field++, updatedUser.getLastName());
                pst.setString(field++, updatedUser.getEmail());
                pst.setString(field++, updatedUser.getPhone());
                pst.setString(field++, updatedUser.getInterest());
                pst.setInt(field++, updatedUser.getMajor());
                if (updatedUser.getGender() > 0) {
                    pst.setInt(field++, updatedUser.getGender());
                } else {
                    pst.setNull(field++, Types.INTEGER);
                }
                pst.setInt(field++, updatedUser.getStatus());
                pst.setInt(field++, updatedUser.getUser());
                pst.execute();
            } catch (SQLException e1) {
                Logger.getLogger(UserManager.class.getName())
                    .log(Level.SEVERE, null, e1);
            } finally {
                pst.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(
                UserManager.class.getName()).log(Level.SEVERE, null, ex
            );
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
}
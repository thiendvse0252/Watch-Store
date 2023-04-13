/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.util.DBUtils;

public class WatchDAO {

    private static final String CATE = "SELECT categoryID, name FROM tblCategories";
    private static final String PROD = "SELECT productID, name, price, quantity, images, categoryID FROM tblProduct";
    private static final String GETBYCID = "SELECT productID, name, price, quantity, images, categoryID FROM tblProduct WHERE productID like ?";
    private static final String CHECK = "SELECT productID from tblProduct WHERE productID = ?";
    private static final String INSERT = "INSERT INTO tblProduct (productID, name, price, quantity, images, categoryID) VALUES(?,?,?,?,?,?)";
    private static final String REMOVE = "DELETE tblProduct WHERE productID = ?";

    public List<Category> getAllCategory() throws SQLException {
        List<Category> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CATE);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("categoryID");
                    String name = rs.getString("name");
                    list.add(new Category(id, name));
                }
            }

        } catch (SQLException | NamingException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean insert(String id, String name, String price, String quantity, String images, String category) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, id);
                ptm.setString(2, name);
                ptm.setString(3, price);
                ptm.setString(4, quantity);
                ptm.setString(5, images);
                ptm.setString(6, category);
                check = ptm.executeUpdate() > 0;
            }
        } catch (SQLException | NamingException e) {
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public static List<Watch> getProductByID(String search) throws SQLException {
        List<Watch> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            if (search.equalsIgnoreCase("all")) {
                search = "";
            }
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(GETBYCID);
            ptm.setString(1, "%" + search + "%");
            rs = ptm.executeQuery();
            while (rs.next()) {
                list.add(new Watch(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException | NamingException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean checkDuplicate(String id) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK);
                ptm.setString(1, id);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (SQLException | NamingException e) {

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public List<Watch> getAllProduct() throws SQLException {
        List<Watch> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PROD);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("productID");
                    String name = rs.getString("name");
                    double price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    String image = rs.getString("images");
                    String categoryID = rs.getString("categoryID");
                    list.add(new Watch(id, name, price, quantity, image, categoryID));
                }
            }

        } catch (SQLException | NamingException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public static Watch getWatch(String ID) throws
            SQLException, NamingException, ClassNotFoundException {
        String SQLQuery = "SELECT productID, name, price, quantity, images, categoryID "
                + "FROM tblProduct "
                + "WHERE productID = ?";

        Connection conn = null;
        PreparedStatement PreS = null;
        ResultSet ReS = null;

        try {
            conn = DBUtils.getConnection();
            PreS = conn.prepareCall(SQLQuery);
            PreS.setString(1, ID);
            ReS = PreS.executeQuery();
            while (ReS.next()) {
                Watch acc = new Watch(ReS.getString(1), ReS.getString(2), ReS.getDouble(3), ReS.getInt(4), ReS.getString(5), ReS.getString(6));
                return acc;

            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (PreS != null) {
                PreS.close();
            }
            if (ReS != null) {
                ReS.close();
            }

        }
        return null;

    }

    public static ArrayList<Watch> getWatchs(String ID) throws
            SQLException, NamingException, ClassNotFoundException {
        String SQLQuery = "SELECT productID, name, price, quantity, images, categoryID "
                + "FROM tblProduct "
                + "WHERE productID like ?";
        ArrayList<Watch> tea = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (ID.equalsIgnoreCase("all")) {
                ID = "";
            }
            ps = conn.prepareCall(SQLQuery);
            ps.setString(1, "%" + ID + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Watch t = new Watch(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(5), rs.getString(6));
                tea.add(t);

            }
        } catch (SQLException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return tea;

    }

    public boolean removeWatch(String id) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(REMOVE);
                ptm.setString(1, id);
                int value = ptm.executeUpdate();
                check = value > 0;
            }
        } catch (SQLException | NamingException e) {
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateProduct(Watch watch) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("UPDATE tblProduct SET name = ?, price = ?, quantity = ?, images = ?, categoryID = ? WHERE productID = ?");
                ptm.setString(1, watch.getName());
                ptm.setDouble(2, watch.getPrice());
                ptm.setInt(3, watch.getQuantity());
                ptm.setString(4, watch.getImage());
                ptm.setString(5, watch.getCategoryID());
                ptm.setString(6, watch.getId());
                check = ptm.executeUpdate() > 0;
            }
        } catch (SQLException | NamingException e) {
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

}

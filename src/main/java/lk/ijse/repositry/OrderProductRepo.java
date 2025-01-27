package lk.ijse.repositry;

import lk.ijse.db.DbConnection;
import lk.ijse.model.OrderProduct;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderProductRepo {

    public static boolean save(List<OrderProduct> odList) throws SQLException {
            for (OrderProduct od : odList) {
                boolean isSaved = save(od);
                if(!isSaved) {
                    return false;
                }
            }
            return true;
    }
    private static boolean save(OrderProduct od) throws SQLException {
        String sql = "INSERT INTO OrderProduct VALUES(?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setString(1, od.getProductId());
        pstm.setString(2, od.getOrderId());
        pstm.setInt(3, od.getQty());
        pstm.setDouble(4, od.getSellingPrice());

        return pstm.executeUpdate() > 0;    //false ->  |
    }
}

package iuh.fit.ontap_de3.dao.Impl;

import iuh.fit.ontap_de3.dao.KhuVucDAO;
import iuh.fit.ontap_de3.model.KhuVuc;
import iuh.fit.ontap_de3.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KhuVucDAOImpl implements KhuVucDAO {
    @Override
    public List<KhuVuc> getAllKhuVuc() {
        List<KhuVuc> list = new ArrayList<>();
        String sql = "select * from khuvuc";
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                KhuVuc khuVuc = new KhuVuc();
                khuVuc.setMaKV(rs.getInt("makv"));
                khuVuc.setTenKV(rs.getString("tenkv"));
                khuVuc.setDiaChi(rs.getString("diachi"));
                list.add(khuVuc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

package iuh.fit.ontap_de3.dao.Impl;

import iuh.fit.ontap_de3.dao.PhongTroDAO;
import iuh.fit.ontap_de3.model.KhuVuc;
import iuh.fit.ontap_de3.model.PhongTro;
import iuh.fit.ontap_de3.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhongTroDAOImpl implements PhongTroDAO {
    private PhongTro mapPT(ResultSet rs) throws Exception {
        PhongTro phongTro = new PhongTro();
        phongTro.setMaPT(rs.getInt("mapt"));
        phongTro.setTenPT(rs.getString("tenpt"));
        phongTro.setGiaThue(rs.getDouble("giathue"));
        phongTro.setHinhAnh(rs.getString("hinhanh"));
        phongTro.setDienTich(rs.getFloat("dientich"));
        KhuVuc khuVuc = new KhuVuc();
        khuVuc.setMaKV(rs.getInt("makv"));
        phongTro.setMaKV(khuVuc);
        return phongTro;
    }

    @Override
    public List<PhongTro> getAllPhongTro() {
        List<PhongTro> list = new ArrayList<>();
        String sql = "select * from phongtro";
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapPT(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public PhongTro getByID(int id) {
        String sql = "select * from phongtro where mapt=?";
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapPT(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<PhongTro> getByKhuVuc(int maKV) {
        List<PhongTro> list = new ArrayList<>();
        String sql = "select * from phongtro where makv=?";
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, maKV);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapPT(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<PhongTro> getByName(String name) {
        List<PhongTro> list = new ArrayList<>();
        String sql = "select * from phongtro where tenpt like ?";
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapPT(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updatePhongTro(PhongTro phongTro) {
        String sql = "update phongtro set tenpt=?, giathue=?, dientich=?, hinhanh=?, makv=? where mapt=?";
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, phongTro.getTenPT());
            ps.setDouble(2, phongTro.getGiaThue());
            ps.setFloat(3, phongTro.getDienTich());
            ps.setString(4, phongTro.getHinhAnh());
            ps.setInt(5, phongTro.getMaKV().getMaKV());
            ps.setInt(6, phongTro.getMaPT());
            return ps.executeUpdate() > 0;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addPhongTro(PhongTro phongTro) {
        String sql = "insert into phongtro(tenpt, giathue, dientich, hinhanh, makv) values (?,?,?,?,?)";
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, phongTro.getTenPT());
            ps.setDouble(2, phongTro.getGiaThue());
            ps.setFloat(3, phongTro.getDienTich());
            ps.setString(4, phongTro.getHinhAnh());
            ps.setInt(5, phongTro.getMaKV().getMaKV());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int maPT) {
        String sql = "delete from phongtro where mapt=?";
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
           ps.setInt(1, maPT);
           return  ps.executeUpdate() >0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}


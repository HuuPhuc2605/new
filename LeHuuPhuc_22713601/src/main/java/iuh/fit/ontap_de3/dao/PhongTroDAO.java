package iuh.fit.ontap_de3.dao;

import iuh.fit.ontap_de3.model.PhongTro;

import java.util.List;

public interface PhongTroDAO {
    List<PhongTro> getAllPhongTro();
    PhongTro getByID(int id);
    List<PhongTro> getByKhuVuc(int maKV);
    List<PhongTro> getByName(String name);
    boolean updatePhongTro(PhongTro phongTro);
    boolean addPhongTro(PhongTro phongTro);
    boolean delete(int maPT);

}

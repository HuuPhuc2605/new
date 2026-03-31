package iuh.fit.ontap_de3.model;

public class PhongTro {
    private int maPT;
    private String tenPT;
    private Double giaThue;
    private float dienTich;
    private String hinhAnh;
    private KhuVuc maKV;

    public PhongTro() {
    }

    public int getMaPT() {
        return maPT;
    }

    public void setMaPT(int maPT) {
        this.maPT = maPT;
    }

    public String getTenPT() {
        return tenPT;
    }

    public void setTenPT(String tenPT) {
        this.tenPT = tenPT;
    }

    public Double getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(Double giaThue) {
        this.giaThue = giaThue;
    }

    public float getDienTich() {
        return dienTich;
    }

    public void setDienTich(float dienTich) {
        this.dienTich = dienTich;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public KhuVuc getMaKV() {
        return maKV;
    }

    public void setMaKV(KhuVuc maKV) {
        this.maKV = maKV;
    }
}

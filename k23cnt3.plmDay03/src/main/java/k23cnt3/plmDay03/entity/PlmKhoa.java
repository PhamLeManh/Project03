package k23cnt3.plmDay03.entity;


public class PlmKhoa {
    private String maKH;
    private String tenKH;

    public PlmKhoa() {
    }

    public PlmKhoa(String maKH, String tenKH) {
        this.maKH = maKH;
        this.tenKH = tenKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }
}

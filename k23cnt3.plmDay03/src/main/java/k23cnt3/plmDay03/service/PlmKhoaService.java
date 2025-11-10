package k23cnt3.plmDay03.service;

import k23cnt3.plmDay03.entity.PlmKhoa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PlmKhoaService {
    private List<PlmKhoa> khoaList = new ArrayList<>();

    public PlmKhoaService() {
        khoaList.addAll(Arrays.asList(
                new PlmKhoa("KH01", "Công nghệ thông tin"),
                new PlmKhoa("KH02", "Điện tử viễn thông"),
                new PlmKhoa("KH03", "Cơ khí"),
                new PlmKhoa("KH04", "Xây dựng"),
                new PlmKhoa("KH05", "Kinh tế")
        ));
    }

    public List<PlmKhoa> getAllKhoa() {
        return khoaList;
    }

    public PlmKhoa getKhoaByMaKH(String maKH) {
        return khoaList.stream()
                .filter(khoa -> khoa.getMaKH().equals(maKH))
                .findFirst()
                .orElse(null);
    }

    public PlmKhoa addKhoa(PlmKhoa khoa) {
        khoaList.add(khoa);
        return khoa;
    }

    public PlmKhoa updateKhoa(String maKH, PlmKhoa khoa) {
        PlmKhoa existingKhoa = getKhoaByMaKH(maKH);
        if (existingKhoa == null) {
            return null;
        }

        existingKhoa.setTenKH(khoa.getTenKH());
        return existingKhoa;
    }

    public boolean deleteKhoa(String maKH) {
        PlmKhoa khoa = getKhoaByMaKH(maKH);
        if (khoa != null) {
            return khoaList.remove(khoa);
        }
        return false;
    }
}
package k23cnt3.plmDay03.service;

import k23cnt3.plmDay03.entity.PlmMonHoc;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PlmMonHocService {
    private List<PlmMonHoc> monHocList = new ArrayList<>();

    public PlmMonHocService() {
        monHocList.addAll(Arrays.asList(
                new PlmMonHoc("MH01", "Lập trình Java", 45),
                new PlmMonHoc("MH02", "Cơ sở dữ liệu", 30),
                new PlmMonHoc("MH03", "Mạng máy tính", 40),
                new PlmMonHoc("MH04", "Toán cao cấp", 60),
                new PlmMonHoc("MH05", "Tiếng Anh chuyên ngành", 35)
        ));
    }

    public List<PlmMonHoc> getAllMonHoc() {
        return monHocList;
    }

    public PlmMonHoc getMonHocByMaMH(String maMH) {
        return monHocList.stream()
                .filter(monHoc -> monHoc.getMaMH().equals(maMH))
                .findFirst()
                .orElse(null);
    }

    public PlmMonHoc addMonHoc(PlmMonHoc monHoc) {
        monHocList.add(monHoc);
        return monHoc;
    }

    public PlmMonHoc updateMonHoc(String maMH, PlmMonHoc monHoc) {
        PlmMonHoc existingMonHoc = getMonHocByMaMH(maMH);
        if (existingMonHoc == null) {
            return null;
        }

        existingMonHoc.setTenMH(monHoc.getTenMH());
        existingMonHoc.setSoTiet(monHoc.getSoTiet());
        return existingMonHoc;
    }

    public boolean deleteMonHoc(String maMH) {
        PlmMonHoc monHoc = getMonHocByMaMH(maMH);
        if (monHoc != null) {
            return monHocList.remove(monHoc);
        }
        return false;
    }
}
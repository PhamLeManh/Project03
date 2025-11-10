package k23cnt3.plmDay03.controller;

import k23cnt3.plmDay03.entity.PlmMonHoc;
import k23cnt3.plmDay03.service.PlmMonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monhoc")
public class PlmMonHocController {

    @Autowired
    private PlmMonHocService monHocService;

    @GetMapping
    public List<PlmMonHoc> getAllMonHoc() {
        return monHocService.getAllMonHoc();
    }

    @GetMapping("/{maMH}")
    public PlmMonHoc getMonHocByMaMH(@PathVariable String maMH) {
        return monHocService.getMonHocByMaMH(maMH);
    }

    @PostMapping
    public PlmMonHoc addMonHoc(@RequestBody PlmMonHoc monHoc) {
        return monHocService.addMonHoc(monHoc);
    }

    @PutMapping("/{maMH}")
    public PlmMonHoc updateMonHoc(@PathVariable String maMH, @RequestBody PlmMonHoc monHoc) {
        return monHocService.updateMonHoc(maMH, monHoc);
    }

    @DeleteMapping("/{maMH}")
    public boolean deleteMonHoc(@PathVariable String maMH) {
        return monHocService.deleteMonHoc(maMH);
    }
}
package k23cnt3.plmDay03.controller;

import k23cnt3.plmDay03.entity.PlmKhoa;
import k23cnt3.plmDay03.service.PlmKhoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/khoa")
public class PlmKhoaController {

    @Autowired
    private PlmKhoaService khoaService;

    @GetMapping
    public List<PlmKhoa> getAllKhoa() {
        return khoaService.getAllKhoa();
    }

    @GetMapping("/{maKH}")
    public PlmKhoa getKhoaByMaKH(@PathVariable String maKH) {
        return khoaService.getKhoaByMaKH(maKH);
    }

    @PostMapping
    public PlmKhoa addKhoa(@RequestBody PlmKhoa khoa) {
        return khoaService.addKhoa(khoa);
    }

    @PutMapping("/{maKH}")
    public PlmKhoa updateKhoa(@PathVariable String maKH, @RequestBody PlmKhoa khoa) {
        return khoaService.updateKhoa(maKH, khoa);
    }

    @DeleteMapping("/{maKH}")
    public boolean deleteKhoa(@PathVariable String maKH) {
        return khoaService.deleteKhoa(maKH);
    }
}
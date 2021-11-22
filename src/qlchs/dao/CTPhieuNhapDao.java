/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlchs.dao;

import EduSys.entity.CTPhieuNhap;
import java.util.ArrayList;
import java.util.List;
import qlchs.utils.JDBCHelper;
import java.sql.ResultSet;

/**
 *
 * @author tachi
 */
public class CTPhieuNhapDao extends QLNSDAO<CTPhieuNhap, String> {

    final String INSERT_SQL = "INSERT INTO CTPHIEUNHAP(MaCTPN,MaPN,MaSach,SoLuong,GiaNhap) values(?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE CTPHIEUNHAP set MaPN=?, MaSach =?, SoLuong=?,GiaNhap=? WHERE MaCTPN=?";
    final String DELETE_SQL = "DELETE FROM CTPHIEUNHAP WHERE MaCTPN=?";
    final String SELECT_ALL_SQL = "SELECT * FROM CTPHIEUNHAP";
    final String SELECT_BY_ID_SQL = "SELECT * FROM CTPHIEUNHAP WHERE MaCTPN= ?";

    @Override
    public void insert(CTPhieuNhap entity) {
        JDBCHelper.update(INSERT_SQL, entity.getMaCTPN(), entity.getMaPN(), entity.getMaSach(), entity.getSoLuong(), entity.getGiaNhap());
    }

    @Override
    public void update(CTPhieuNhap entity) {
        JDBCHelper.update(UPDATE_SQL, entity.getMaPN(), entity.getMaSach(), entity.getSoLuong(), entity.getGiaNhap(), entity.getMaCTPN());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<CTPhieuNhap> selectAll() {
       return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public CTPhieuNhap selectById(String id) {
            List<CTPhieuNhap> list = selectBySql(SELECT_BY_ID_SQL,id);
        if(list.isEmpty()){
            return  null;
        } 
        return list.get(0);
    }

    @Override
    public List<CTPhieuNhap> selectBySql(String sql, Object... args) {
       List<CTPhieuNhap> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {                
                CTPhieuNhap entity = new CTPhieuNhap();
                entity.setMaCTPN(rs.getString("MaCTPN"));
                entity.setMaPN(rs.getString("MaPN"));
                entity.setMaSach(rs.getString("MaSach"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setGiaNhap(rs.getDouble("GiaNhap"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
        return list;
    }

}

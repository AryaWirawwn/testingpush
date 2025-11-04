/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author acer
 */
public class Buku implements CRUD {

    private int idBuku;
    private String namaBuku;
    private String namaPenulis;
    private int jumlahHalaman;

    public Buku(String namaBuku, String namaPenulis, int jumlahHalaman) {
        this.namaBuku = namaBuku;
        this.namaPenulis = namaPenulis;
        this.jumlahHalaman = jumlahHalaman;
    }

    public int getIdBuku() {
        return idBuku;
    }

    public String getNamaBuku() {
        return namaBuku;
    }

    public void setNamaBuku(String namaBuku) {
        this.namaBuku = namaBuku;
    }

    public String getNamaPenulis() {
        return namaPenulis;
    }

    public void setNamaPenulis(String namaPenulis) {
        this.namaPenulis = namaPenulis;
    }

    public int getJumlahHalaman() {
        return jumlahHalaman;
    }

    public void setJumlahHalaman(int jumlahHalaman) {
        this.jumlahHalaman = jumlahHalaman;
    }

    public void setIdBuku(int idBuku) {
        this.idBuku = idBuku;
    }
    
    

    @Override
    public void create() {
        String sql = "INSERT INTO buku (nama, penulis, halaman) VALUES (?, ?, ?)";
        try (Connection conn = Database.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, getNamaBuku());
            ps.setString(2, getNamaPenulis());
            ps.setInt(3, getJumlahHalaman());
            ps.executeUpdate();

            System.out.println("Data buku berhasil ditambahkan!");
        } catch (Exception e) {
            System.out.println("❌Gagal menambah data: " + e.getMessage());
        }
    }

    @Override
    public void read() {
        String sql = "SELECT * FROM buku";
        try (Connection conn = Database.connect(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | "
                        + rs.getString("nama") + " | "
                        + rs.getString("penulis") + " | "
                        + rs.getInt("halaman")
                );
            }
        } catch (Exception e) {
            System.out.println("Gagal membaca data: " + e.getMessage());
        }
    }

    @Override
    public void update(int id) {
        String sql = "UPDATE buku SET nama=?, penulis=?, halaman=? WHERE id=?";
        try (Connection conn = Database.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, getNamaBuku());
            ps.setString(2, getNamaPenulis());
            ps.setInt(3, getJumlahHalaman());
            ps.setInt(4, id);
            ps.executeUpdate();

            System.out.println("Data buku berhasil diperbarui!");
        } catch (Exception e) {
            System.out.println("Gagal update data: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM buku WHERE id=?";
        try (Connection conn = Database.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Data buku berhasil dihapus!");
        } catch (Exception e) {
            System.out.println("Gagal hapus data: " + e.getMessage());
        }
    }

}

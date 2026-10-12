/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Acer
 */
public class LapanganDAO {
    public ArrayList<Lapangan> findAll() throws SQLException {
        ArrayList<Lapangan> listLapangan = new ArrayList<>();

        String sql = "SELECT * FROM lapangan";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int nomorLapangan = rs.getInt("nomor_lapangan");
                String jenisKarpet = rs.getString("jenis_karpet");
                int hargaPerJam = rs.getInt("harga_per_jam");

                Lapangan lapangan = new Lapangan(
                    nomorLapangan, jenisKarpet, hargaPerJam
                );

                listLapangan.add(lapangan);
            }
        }

        return listLapangan;
    }

    public Lapangan findById(int nomorLapangan) throws SQLException {
        String sql = "SELECT * FROM lapangan WHERE nomor_lapangan = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, nomorLapangan);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int nomor = rs.getInt("nomor_lapangan");
                    String jenisKarpet = rs.getString("jenis_karpet");
                    int hargaPerJam = rs.getInt("harga_per_jam");

                    return new Lapangan(
                        nomor, jenisKarpet, hargaPerJam
                    );
                }
            }
        }

        return null;
    }
}

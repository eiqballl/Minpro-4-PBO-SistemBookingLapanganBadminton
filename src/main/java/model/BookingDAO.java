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
public class BookingDAO {
       // Mengubah hasil query menjadi objek Booking
    private Booking mapBooking(ResultSet rs) throws SQLException {
        Booking booking = new Booking(
            rs.getString("kode_booking"),
            rs.getString("id_customer"),
            rs.getInt("nomor_lapangan"),
            rs.getInt("durasi_jam"),
            rs.getInt("jam_mulai"),
            rs.getString("tanggal")
        );

        booking.setHargaTotal(rs.getDouble("harga_total"));
        return booking;
    }

    // READ - Menampilkan seluruh data booking
    public ArrayList<Booking> findAll() throws SQLException {
        ArrayList<Booking> listBooking = new ArrayList<>();

        String sql = "SELECT * FROM booking";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                listBooking.add(mapBooking(rs));
            }
        }

        return listBooking;
    }

    // READ - Mencari booking berdasarkan kode
    public Booking findById(String kodeBooking) throws SQLException {
        String sql = "SELECT * FROM booking WHERE kode_booking = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, kodeBooking);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapBooking(rs);
                }
            }
        }

        return null;
    }

    // CREATE - Menambah booking baru
    public boolean insert(Booking booking) throws SQLException {
        String sql = "INSERT INTO booking "
                + "(kode_booking, id_customer, nomor_lapangan, "
                + "durasi_jam, jam_mulai, tanggal, harga_total) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, booking.getKodeBooking());
            ps.setString(2, booking.getIdCustomer());
            ps.setInt(3, booking.getNomorLapangan());
            ps.setInt(4, booking.getDurasiJam());
            ps.setInt(5, booking.getJamMulai());
            ps.setString(6, booking.getTanggal());
            ps.setDouble(7, booking.getHargaTotal());

            return ps.executeUpdate() > 0;
        }
    }

    // UPDATE - Memperbarui data booking
    public boolean update(Booking booking) throws SQLException {
        String sql = "UPDATE booking SET "
                + "id_customer = ?, "
                + "nomor_lapangan = ?, "
                + "durasi_jam = ?, "
                + "jam_mulai = ?, "
                + "tanggal = ?, "
                + "harga_total = ? "
                + "WHERE kode_booking = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, booking.getIdCustomer());
            ps.setInt(2, booking.getNomorLapangan());
            ps.setInt(3, booking.getDurasiJam());
            ps.setInt(4, booking.getJamMulai());
            ps.setString(5, booking.getTanggal());
            ps.setDouble(6, booking.getHargaTotal());
            ps.setString(7, booking.getKodeBooking());

            return ps.executeUpdate() > 0;
        }
    }

    // DELETE - Menghapus booking berdasarkan kode
    public boolean delete(String kodeBooking) throws SQLException {
        String sql = "DELETE FROM booking WHERE kode_booking = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, kodeBooking);

            return ps.executeUpdate() > 0;
        }
    }
}

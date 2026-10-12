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
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.format.DateTimeParseException;
/**
 *
 * @author Acer
 */
public class BookingDAO {
//     Format tanggal untuk input dan tampilan program
    private static final DateTimeFormatter FORMAT_TANGGAL =
            DateTimeFormatter.ofPattern("dd/MM/uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);

    private Date konversiTanggal(String tanggal) {
        LocalDate localDate = LocalDate.parse(tanggal, FORMAT_TANGGAL);
        return Date.valueOf(localDate);
    }

    private String formatTanggal(Date tanggal) {
        return tanggal.toLocalDate().format(FORMAT_TANGGAL);
    }

    private Booking mapBooking(ResultSet rs) throws SQLException {
        String kodeBooking = rs.getString("kode_booking");
        String idCustomer = rs.getString("id_customer");
        int nomorLapangan = rs.getInt("nomor_lapangan");
        int durasiJam = rs.getInt("durasi_jam");
        int jamMulai = rs.getInt("jam_mulai");

        Date tanggalSQL = rs.getDate("tanggal");
        String tanggal = formatTanggal(tanggalSQL);

        Booking booking = new Booking(
                kodeBooking,
                idCustomer,
                nomorLapangan,
                durasiJam,
                jamMulai,
                tanggal
        );

        booking.setHargaTotal(rs.getDouble("harga_total"));

        return booking;
    }

//     READ 
    public ArrayList<Booking> findAll() throws SQLException {
        ArrayList<Booking> daftarBooking = new ArrayList<>();

        String sql = "SELECT * FROM booking ORDER BY tanggal, jam_mulai";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                daftarBooking.add(mapBooking(rs));
            }

        }

        return daftarBooking;
    }

//     READ 
    public Booking findById(String kodeBooking) throws SQLException {
        String sql = "SELECT * FROM booking WHERE kode_booking = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, kodeBooking);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return mapBooking(rs);
                }
            }

        }

        return null;
    }

//     CREATE 
    public boolean insert(Booking booking) throws SQLException {
        String sql = """
                INSERT INTO booking
                (kode_booking, id_customer, nomor_lapangan,
                 durasi_jam, jam_mulai, tanggal, harga_total)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, booking.getKodeBooking());
            pst.setString(2, booking.getIdCustomer());
            pst.setInt(3, booking.getNomorLapangan());
            pst.setInt(4, booking.getDurasiJam());
            pst.setInt(5, booking.getJamMulai());

            // Konversi tanggal DD/MM/YYYY ke DATE MySQL
            pst.setDate(6, konversiTanggal(booking.getTanggal()));

            pst.setDouble(7, booking.getHargaTotal());

            return pst.executeUpdate() > 0;

        } catch (DateTimeParseException e) {
            System.out.println("Format tanggal tidak valid! Gunakan DD/MM/YYYY.");
            return false;
        }
    }

//     UPDATE 
    public boolean update(Booking booking) throws SQLException {
     String sql = """
             UPDATE booking SET
                 id_customer = ?,
                 nomor_lapangan = ?,
                 durasi_jam = ?,
                 jam_mulai = ?,
                 tanggal = ?,
                 harga_total = ?
             WHERE kode_booking = ?
             """;

     try (Connection conn = Koneksi.getConnection();
          PreparedStatement pst = conn.prepareStatement(sql)) {

         pst.setString(1, booking.getIdCustomer());
         pst.setInt(2, booking.getNomorLapangan());
         pst.setInt(3, booking.getDurasiJam());
         pst.setInt(4, booking.getJamMulai());
         pst.setDate(5, konversiTanggal(booking.getTanggal()));
         pst.setDouble(6, booking.getHargaTotal());
         pst.setString(7, booking.getKodeBooking());

         int hasil = pst.executeUpdate();

         System.out.println("Jumlah data yang diperbarui: " + hasil);

         return hasil > 0;
        }
    }

//     DELETE 
    public boolean delete(String kodeBooking) throws SQLException {
        String sql = "DELETE FROM booking WHERE kode_booking = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, kodeBooking);

            return pst.executeUpdate() > 0;

        }
    }
}


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
public class CustomerDAO {
      
    private Customer mapCustomer(ResultSet rs) throws SQLException {
        String id = rs.getString("id_customer");
        String nama = rs.getString("nama_customer");
        String noHp = rs.getString("no_hp");
        String jenis = rs.getString("jenis_customer");

        if (jenis.equals("Member")) {
            double diskon = rs.getDouble("diskon");
            return new Member(id, nama, noHp, diskon);
        } else {
            int biayaTambahan = rs.getInt("biaya_tambahan");
            return new NonMember(id, nama, noHp, biayaTambahan);
        }
    }

    public ArrayList<Customer> findAll() throws SQLException {
        ArrayList<Customer> listCustomer = new ArrayList<>();

        String sql = "SELECT * FROM customer";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                listCustomer.add(mapCustomer(rs));
            }
        }

        return listCustomer;
    }

    public Customer findById(String idCustomer) throws SQLException {
        String sql = "SELECT * FROM customer WHERE id_customer = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idCustomer);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapCustomer(rs);
                }
            }
        }

        return null;
    }


    public boolean insert(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer "
                + "(id_customer, nama_customer, no_hp, jenis_customer, diskon, biaya_tambahan) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, customer.getIdCustomer());
            ps.setString(2, customer.getNamaCustomer());
            ps.setString(3, customer.getNoHp());
            ps.setString(4, customer.getStatus());

            if (customer instanceof Member) {
                Member member = (Member) customer;
                ps.setDouble(5, member.getDiskon());
                ps.setInt(6, 0);
            } else {
                NonMember nonMember = (NonMember) customer;
                ps.setDouble(5, 0);
                ps.setInt(6, nonMember.getBiayaTambahan());
            }

            return ps.executeUpdate() > 0;
        }
    }
}

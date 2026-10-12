/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Customer;
import model.Member;
import model.NonMember;
import model.Lapangan;
import model.Booking;
import model.BookingDAO;
import model.CustomerDAO;
import model.LapanganDAO;


/**
 *
 * @author Acer
 */
public class BookingController {
     private final CustomerDAO customerDAO;
    private final LapanganDAO lapanganDAO;
    private final BookingDAO bookingDAO;

//     Constructor
    public BookingController() {
        customerDAO = new CustomerDAO();
        lapanganDAO = new LapanganDAO();
        bookingDAO = new BookingDAO();
    }

//     READ 
    public ArrayList<Customer> getListCustomer() {
        try {
            return customerDAO.findAll();
        } catch (SQLException e) {
            System.out.println("Gagal mengambil customer: " + e.getMessage());
            return new ArrayList<>();
        }
    }

//     READ 
    public ArrayList<Booking> getListBooking() {
        try {
            return bookingDAO.findAll();
        } catch (SQLException e) {
            System.out.println("Gagal mengambil booking: " + e.getMessage());
            return new ArrayList<>();
        }
    }

//     READ
    public ArrayList<Lapangan> getListLapangan() {
        try {
            return lapanganDAO.findAll();
        } catch (SQLException e) {
            System.out.println("Gagal mengambil lapangan: " + e.getMessage());
            return new ArrayList<>();
        }
    }

//     READ 
    public Customer cariCustomer(String idCustomer) {
        try {
            return customerDAO.findById(idCustomer);
        } catch (SQLException e) {
            System.out.println("Gagal mencari customer: " + e.getMessage());
            return null;
        }
    }

//     READ
    public Lapangan cariLapangan(int nomorLapangan) {
        try {
            return lapanganDAO.findById(nomorLapangan);
        } catch (SQLException e) {
            System.out.println("Gagal mencari lapangan: " + e.getMessage());
            return null;
        }
    }

//     READ
    public Booking cariBooking(String kodeBooking) {
        try {
            return bookingDAO.findById(kodeBooking);
        } catch (SQLException e) {
            System.out.println("Gagal mencari booking: " + e.getMessage());
            return null;
        }
    }

//     CREATE
    public boolean tambahCustomer(Customer customer) {
        try {
            if (customerDAO.findById(customer.getIdCustomer()) != null) {
                return false;
            }

            return customerDAO.insert(customer);

        } catch (SQLException e) {
            System.out.println("Gagal menambah customer: " + e.getMessage());
            return false;
        }
    }

    // CREATE - Menambahkan booking
    public boolean tambahBooking(Booking booking) {
        try {
            if (bookingDAO.findById(booking.getKodeBooking()) != null) {
                return false;
            }
            if (customerDAO.findById(booking.getIdCustomer()) == null) {
                return false;
            }
            if (lapanganDAO.findById(booking.getNomorLapangan()) == null) {
                return false;
            }
            if (booking.getDurasiJam() <= 0) {
                return false;
            }
            if (booking.getJamMulai() < 0 || booking.getJamMulai() > 21) {
                return false;
            }
            if (booking.getTanggal() == null
                    || booking.getTanggal().isBlank()) {
                return false;
            }

//             Menghitung harga total
            double total = hitungHargaTotal(booking);
            booking.setHargaTotal(total);
            return bookingDAO.insert(booking);

        } catch (SQLException e) {
            System.out.println("Gagal menambah booking: " + e.getMessage());
            return false;
        }
    }

    // UPDATE - Memperbarui booking
    public boolean updateBooking(
            String kodeBooking,
            String idCustomer,
            int nomorLapangan,
            int durasiJam,
            int jamMulai,
            String tanggal) {

        try {
            Booking booking = bookingDAO.findById(kodeBooking);
            if (booking == null) {
                return false;
            }
            if (customerDAO.findById(idCustomer) == null) {
                return false;
            }
            if (lapanganDAO.findById(nomorLapangan) == null) {
                return false;
            }
            if (durasiJam <= 0) {
                return false;
            }
            if (jamMulai < 0 || jamMulai > 23) {
                return false;
            }
            if (tanggal == null || tanggal.isBlank()) {
                return false;
            }

//             update booking
            booking.setIdCustomer(idCustomer);
            booking.setNomorLapangan(nomorLapangan);
            booking.setDurasiJam(durasiJam);
            booking.setJamMulai(jamMulai);
            booking.setTanggal(tanggal);
            double total = hitungHargaTotal(booking);
            booking.setHargaTotal(total);
//            masukkin ke databse
            return bookingDAO.update(booking);

        } catch (SQLException e) {
            System.out.println("Gagal update booking: " + e.getMessage());
            return false;
        }
    }

    // DELETE - Menghapus booking
    public boolean hapusBooking(String kodeBooking) {
        try {
            return bookingDAO.delete(kodeBooking);

        } catch (SQLException e) {
            System.out.println("Gagal menghapus booking: " + e.getMessage());
            return false;
        }
    }

    // Menghitung harga booking
    public double hitungHargaTotal(Booking booking) {
        Lapangan lapangan = cariLapangan(booking.getNomorLapangan());
        Customer customer = cariCustomer(booking.getIdCustomer());

        if (lapangan == null || customer == null) {
            return 0;
        }

        double total = booking.getDurasiJam()
                * lapangan.getHargaPerJam();
        total = total - (total * customer.hitungDiskon());

        // Biaya tambahan Non Member
        if (customer instanceof NonMember) {
            NonMember nonMember = (NonMember) customer;
            total += nonMember.getBiayaTambahan();
        }

        return total;
    }

    // Method hitung biaya
    public double hitungHargaTotal(
            Booking booking, double potonganTambahan) {

        double total = hitungHargaTotal(booking);
        return total - potonganTambahan;
    }
    
}

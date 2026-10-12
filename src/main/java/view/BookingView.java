/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.BookingController;
import java.util.Scanner;
import model.Booking;
import model.Customer;
import model.Member;
import model.NonMember;
import model.Lapangan;

/**
 *
 * @author Acer
 */
public class BookingView {

    private BookingController controller;
    private Scanner input;

    public BookingView(BookingController controller) {
        this.controller = controller;
        this.input = new Scanner(System.in);
    }

    public void menu() {
        int pilihan = 0;
        
        while (pilihan != 8) {
            System.out.println("\n=========================================");
            System.out.println("=== SISTEM BOOKING LAPANGAN BADMINTON ===");
            System.out.println("=========================================");
            System.out.println("1. Tambah Customer");
            System.out.println("2. Tampilkan Customer");
            System.out.println("3. Tampilkan Lapangan");
            System.out.println("4. Tambah Booking");
            System.out.println("5. Tampilkan Booking");
            System.out.println("6. Update Booking");
            System.out.println("7. Hapus Booking");
            System.out.println("8. Keluar");
            System.out.print("Pilih Menu (1-8): ");

            if (input.hasNextInt()) {
                pilihan = input.nextInt();
                input.nextLine();
            } else {
                System.out.println("Input harus berupa angka (1-8)");
                input.nextLine();
                continue;
            }

            switch (pilihan) {
                case 1:
                    tambahCustomer();
                    break;
                case 2:
                    tampilkanCustomer();
                    break;
                case 3:
                    tampilkanLapangan();
                    break;
                case 4:
                    tambahBooking();
                    break;
                case 5:
                    tampilkanBooking();
                    break;
                case 6:
                    updateBooking();
                    break;
                case 7:
                    hapusBooking();
                    break;
                case 8:
                    System.out.println("Program selesai, terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
    
    private void tambahCustomer() {
        System.out.println("\n======================");
        System.out.println("=== TAMBAH CUSTOMER ===");
        System.out.println("======================");

        System.out.print("ID Customer (Contoh C1) : ");
        String idCustomer = input.nextLine();
        if (idCustomer.isBlank()) {
            System.out.println("ID Customer tidak boleh kosong!");
            return;
        }

        System.out.print("Nama: ");
        String nama = input.nextLine();
        if (nama.isBlank()) {
            System.out.println("Nama tidak boleh kosong!");
            return;
        }
        if (!nama.matches(".*[a-zA-Z].*")) {
            System.out.println("Nama harus mengandung huruf!");
            return;
        }

        System.out.print("Nomor WhatsApp: ");
        String noHp = input.nextLine();
        if (noHp.isBlank()) {
            System.out.println("Nomor HP tidak boleh kosong!");
            return;
        }
        if (!noHp.matches("\\d{10,15}")) {
            System.out.println("Nomor HP harus berupa 10-15 digit angka!");
            return;
        }

        System.out.println("Jenis Customer:");
        System.out.println("1. Member");
        System.out.println("2. Non Member");
        System.out.print("Pilih: ");
        if (!input.hasNextInt()) {
            System.out.println("Pilihan harus berupa angka (1-2)");
            input.nextLine();
            return;
        }

        int jenis = input.nextInt();
        input.nextLine();
        Customer customer;

        if (jenis == 1) {
            System.out.print("Diskon Member (contoh 0,10): ");

            if (!input.hasNextDouble()) {
                System.out.println("Diskon harus berupa angka atau menggunakan koma',' bukan titik");
                input.nextLine();
                return;
            }

            double diskon = input.nextDouble();
            input.nextLine();

            customer = new Member(idCustomer, nama, noHp, diskon);

        } else if (jenis == 2) {
            System.out.print("Biaya Tambahan: ");

            if (!input.hasNextInt()) {
                System.out.println("Biaya tambahan harus berupa angka!");
                input.nextLine();
                return;
            }

            int biayaTambahan = input.nextInt();
            input.nextLine();

            customer = new NonMember(idCustomer,nama,noHp,biayaTambahan);

        } else {
            System.out.println("Jenis customer tidak valid!");
            return;
        }

        boolean berhasil = controller.tambahCustomer(customer);

        if (berhasil) {
            System.out.println("Customer berhasil ditambahkan!");
        } else {
            System.out.println("ID Customer sudah digunakan!");
        }
    }
    
    private void tampilkanCustomer() {
        System.out.println("\n========================");
        System.out.println("=== DAFTAR CUSTOMER ===");
        System.out.println("========================");

        if (controller.getListCustomer().isEmpty()) {
            System.out.println("Belum ada data customer.");
            return;
        }

        for (Customer customer : controller.getListCustomer()) {

            String status;

            if (customer instanceof Member) {
                status = "Member";
            } else {
                status = "Non Member";
            }
            System.out.println("ID: " + customer.getIdCustomer()+ " | Nama: " + customer.getNamaCustomer()+ " | No HP: " + customer.getNoHp()+ " | Status: " + status);
        }
    }
    
    private void tampilkanLapangan() {
        System.out.println("\n=========================");
        System.out.println("=== DAFTAR LAPANGAN ===");
        System.out.println("=========================");

        if (controller.getListLapangan().isEmpty()) {
            System.out.println("Belum ada data lapangan.");
            return;
        }

        for (Lapangan lapangan : controller.getListLapangan()) {
            System.out.println("No Lapangan: " + lapangan.getNomorLapangan()+ " | Karpet: " + lapangan.getJenisKarpet()+ " | Harga/Jam: Rp" + lapangan.getHargaPerJam());
        }
    }
    
    private void tambahBooking() {
    System.out.println("\n======================");
    System.out.println("=== TAMBAH BOOKING ===");
    System.out.println("======================");

    tampilkanCustomer();
    System.out.print("ID Customer: ");
    String idCustomer = input.nextLine();
    
    if (controller.cariCustomer(idCustomer) == null) {
        System.out.println("ID Customer tidak ditemukan!");
        return;
    }
    
    tampilkanLapangan();
    
    System.out.print("Nomor Lapangan: ");

    if (!input.hasNextInt()) {
        System.out.println("Nomor lapangan harus berupa angka!");
        input.nextLine();
        return;
    }

    int nomorLapangan = input.nextInt();
    input.nextLine();

    if (controller.cariLapangan(nomorLapangan) == null) {
        System.out.println("Nomor lapangan tidak ditemukan!");
        return;
    }
    
    String kodeBooking = idCustomer + "0" + nomorLapangan;
    System.out.println("Kode Booking: " + kodeBooking);

    System.out.print("Durasi Main (Contoh: 3): ");
    if (!input.hasNextInt()) {
        System.out.println("Durasi harus berupa angka!");
        input.nextLine();
        return;
    }
    
    int durasiJam = input.nextInt();

    System.out.print("Jam Mulai (Contoh: 17 ): ");
        if (!input.hasNextInt()) {
            System.out.println("Jam mulai harus berupa angka!");
            input.nextLine();
            return;
        }
        int jamMulai = input.nextInt();
        input.nextLine();

        System.out.print("Tanggal (Contoh: 24/09/2026): ");
        String tanggal = input.nextLine();

        Booking booking = new Booking(kodeBooking,idCustomer,nomorLapangan,durasiJam,jamMulai,tanggal);

        boolean berhasil = controller.tambahBooking(booking);

        if (berhasil) {
            System.out.println("Booking berhasil ditambahkan!");
        } else {
            System.out.println("Booking gagal ditambahkan!");
            System.out.println("Pastikan kode booking unik, customer dan lapangan terdaftar, serta input valid.");
        }
    }
    
    private void tampilkanBooking() {
        System.out.println("\n===========================");
        System.out.println("=== DAFTAR BOOKING ===");
        System.out.println("===========================");

        if (controller.getListBooking().isEmpty()) {
            System.out.println("Belum ada data booking.");
            return;
        }

        for (Booking booking : controller.getListBooking()) {
            System.out.println(
                    "Kode Booking: " + booking.getKodeBooking()+ " | ID Customer: " + booking.getIdCustomer()+ " | No Lapangan: " + booking.getNomorLapangan()+ " | Durasi: " + booking.getDurasiJam() + " Jam"+ " | Jam Mulai: " + booking.getJamMulai() + ":00"+ " | Tanggal: " + booking.getTanggal()+ " | Total Harga: Rp" + booking.getHargaTotal());
        }
    }
    
    private void updateBooking() {
        System.out.println("\n======================");
        System.out.println("=== UPDATE BOOKING ===");
        System.out.println("======================");

        tampilkanBooking();
        
        System.out.print("Kode Booking yang ingin diubah: ");
        String kodeBooking = input.nextLine();

        Booking booking = controller.cariBooking(kodeBooking);

        if (booking == null) {
            System.out.println("Kode booking tidak ditemukan");
            return;
        }

        System.out.print("ID Customer Baru : ");
        String idCustomer = input.nextLine();

        System.out.print("Nomor Lapangan Baru : ");
        if (!input.hasNextInt()) {
            System.out.println("Nomor lapangan harus berupa angka");
            input.nextLine();
            return;
        }
        int nomorLapangan = input.nextInt();

        System.out.print("Durasi Main Baru : ");
        if (!input.hasNextInt()) {
            System.out.println("Durasi harus berupa angka");
            input.nextLine();
            return;
        }
        int durasiJam = input.nextInt();

        System.out.print("Jam Mulai Baru : ");
        if (!input.hasNextInt()) {
            System.out.println("Jam mulai harus berupa angka");
            input.nextLine();
            return;
        }
        int jamMulai = input.nextInt();
        input.nextLine();

        System.out.print("Tanggal Baru : ");
        String tanggal = input.nextLine();

        boolean berhasil = controller.updateBooking(kodeBooking,idCustomer,nomorLapangan,durasiJam,jamMulai,tanggal);

        if (berhasil) {
            System.out.println("Booking telah diperbarui!");
        } else {
            System.out.println("Booking gagal diperbarui!");
            System.out.println("Pastikan input Valid!");
        }
    }
    
    private void hapusBooking() {
        System.out.println("\n---------------------");
        System.out.println("--- HAPUS BOOKING ---");
        System.out.println("---------------------");
        
        tampilkanBooking();
        
        System.out.print("Masukkan Kode Booking yang ingin dihapus: ");
        String kodeBooking = input.nextLine();

        boolean berhasil = controller.hapusBooking(kodeBooking);

        if (berhasil) {
            System.out.println("Booking berhasil dihapus!");
        } else {
            System.out.println("Kode booking tidak ditemukan!");
        }
    }
    
}

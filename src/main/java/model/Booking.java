/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Acer
 */
public class Booking {
    private String kodeBooking;
    private String idCustomer;
    private int nomorLapangan;
    private int durasiJam;
    private int jamMulai;
    private String tanggal;
    private double hargaTotal;
    
//    Constructor
    public Booking(String kodeBooking, String idCustomer, int nomorLapangan, int durasiJam, int jamMulai, String tanggal){
        this.kodeBooking = kodeBooking;
        this.idCustomer = idCustomer;
        this.nomorLapangan = nomorLapangan;
        this.durasiJam = durasiJam;
        this.jamMulai = jamMulai;
        this.tanggal = tanggal;
    }

//    Getter
    public String getKodeBooking(){
        return kodeBooking;
    }
    public String getIdCustomer(){
        return idCustomer;
    }
    public int getNomorLapangan(){
        return nomorLapangan;
    }
    public int getDurasiJam(){
        return durasiJam;
    }
    public int getJamMulai(){
        return jamMulai;
    }
    public String getTanggal(){
        return tanggal;
    }
    public double getHargaTotal() {
        return hargaTotal;
    }
    
//    Setter
    public void setIdCustomer(String idCustomer){
        this.idCustomer = idCustomer;
    }
    public void setNomorLapangan(int nomorLapangan){
        this.nomorLapangan = nomorLapangan;
    }
    public void setDurasiJam(int durasiJam){
        this.durasiJam = durasiJam;
    }
    public void setJamMulai(int jamMulai){
        this.jamMulai = jamMulai;
    }
    public void setTanggal(String tanggal){
        this.tanggal = tanggal;
    }
    public void setHargaTotal(double hargaTotal) {
        this.hargaTotal = hargaTotal;
    }
    
    
}

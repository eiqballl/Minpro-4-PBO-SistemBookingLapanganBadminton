/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Acer
 */
public class Lapangan {
    private int nomorLapangan;
    private String jenisKarpet;
    private double hargaPerJam;

//    Constructor
    public Lapangan(int nomorLapangan, String jenisKarpet, int hargaPerJam) {
        this.nomorLapangan = nomorLapangan;
        this.jenisKarpet = jenisKarpet;
        this.hargaPerJam = hargaPerJam;
    }

//    Getter
    public int getNomorLapangan(){
        return nomorLapangan;
    }
    public String getJenisKarpet(){
        return jenisKarpet;
    }
    public double getHargaPerJam(){
        return hargaPerJam;
    }
    
// Setter
    public void setJenisKarpet(String jenisKarpet){
        this.jenisKarpet = jenisKarpet;
    }
    public void setHargaPerjam(int hargaPerJam){
        this.hargaPerJam = hargaPerJam;
    }
}

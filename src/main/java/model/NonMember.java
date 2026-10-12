/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Acer
 */
public class NonMember extends Customer {
    private int biayaTambahan;
    
//    Constructor
    public NonMember(String idCustomer, String namaCustomer, String noHp, int biayaTambahan){
        super(idCustomer, namaCustomer, noHp);
        this.biayaTambahan = biayaTambahan;
    }
    
//    Getter
    public int getBiayaTambahan(){
        return biayaTambahan;
    }
    
//    Setter
    public void setBiayaTambahan(int biayaTambahan){
        this.biayaTambahan = biayaTambahan;
    }
    
    @Override
    public double hitungDiskon(){
        return 0;
    }
    
    @Override
    public String getStatus() {
    return "Non Member";
    }
    
}

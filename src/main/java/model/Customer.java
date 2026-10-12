/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Acer
 */
public abstract class Customer implements InfoCustomer {
    private final String idCustomer;
    private String namaCustomer;
    private String noHp;
    
//    Constructor
    public Customer(String idCustomer, String namaCustomer, String noHp){
        this.idCustomer = idCustomer;
        this.namaCustomer = namaCustomer;
        this.noHp = noHp;
    }
    
//    Getter
    public String getIdCustomer(){
        return idCustomer;
    }
    public String getNamaCustomer(){
        return namaCustomer;
    }
    public String getNoHp(){
        return noHp;
    }
    
//    Setter
    public void setNamaCustomer(String namaCustomer){
        this.namaCustomer = namaCustomer;
    }
    public void setNoHp(String NoHp){
        this.noHp = noHp;
    }
    
//    Method diskon
    public abstract double hitungDiskon();
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Acer
 */
public class Member extends Customer {
    private double diskon;
    
//    Constructor
    public Member(String idCustomer, String namaCustomer, String noHp, double diskon){
        super(idCustomer, namaCustomer, noHp);
        this.diskon = diskon;
    }
    
//    Getter
    public double getDiskon(){
        return diskon;
    }
    
//    Setter
    public void setDiskon(double diskon){
        this.diskon = diskon;
    }
    
    @Override
    public double hitungDiskon(){
        return diskon;
    }
    
    @Override
    public String getStatus() {
        return "Member";
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main.sistembookinglapanganempat;
import controller.BookingController;
import view.BookingView;

/**
 *
 * @author Acer
 */
public class SistemBookingLapanganEmpat {

    public static void main(String[] args) {
        BookingController controller = new BookingController();
        BookingView view = new BookingView(controller);

        view.menu();
    }
}


# **SISTEM BOOKING LAPANGAN BADMINTON - Mini Project 4**

**Nama: Iqbal Nurriz Ramadhan**  
**NIM: 2509116067**  
**Kelas: Sistem Informasi (B)**

## 1. Deskripsi Singkat Program
Program ini merupakan pengembangan **Mini Project 3** untuk memenuhi tugas **Mini Project 4 Pemrograman Berorientasi Objek (PBO)**. Sistem Booking Lapangan Badminton dibuat menggunakan Java berbasis **CLI (Command Line Interface)** untuk mengelola data customer, lapangan, dan pemesanan lapangan badminton.

Pada Mini Project 4, penyimpanan data sudah menggunakan **database MySQL** yang diakses melalui **JDBC**, **PreparedStatement**, dan pola **DAO (Data Access Object)**. Data customer, lapangan, dan booking tidak lagi disimpan hanya di `ArrayList` dalam memori. `ArrayList` tetap dapat digunakan untuk menampung hasil pembacaan database yang kemudian ditampilkan ke pengguna.

Program tetap menerapkan **encapsulation, inheritance, polymorphism, abstraction**, dan **interface**, dengan struktur **MVC (Model, View, Controller)**. Operasi CRUD tersedia untuk booking, sedangkan data customer dapat ditambahkan dan ditampilkan, serta data lapangan dapat ditampilkan. Data awal diisi melalui file `.sql`, bukan melalui constructor atau kode Java.

## 2. Penjelasan Struktur Package
Struktur project dibagi berdasarkan MVC, dengan class DAO diletakkan di package **`model`** agar sesuai dengan struktur yang digunakan pada project ini.

<img width="255" height="385" alt="image" src="https://github.com/user-attachments/assets/d6fa6c0a-28a1-4085-a0f6-0689f11ade2a" />

- **Config:** Berisi `Koneksi` untuk menghubungkan aplikasi ke MySQL dan `TesKoneksi` untuk pengujian koneksi.
- **Model:** Berisi class data (`Customer`, `Member`, `NonMember`, `Lapangan`, `Booking`), interface `InfoCustomer`, serta tiga class DAO untuk akses database.
- **View:** Menampilkan menu, menerima input pengguna, melakukan validasi input dasar, dan menampilkan hasil operasi.
- **Controller:** Mengatur validasi bisnis, pencarian dan pengelolaan booking, perhitungan total harga, serta pemanggilan DAO.
- **Main:** Menjalankan `BookingController` dan `BookingView`.

**Perubahan dari Mini Project 3:** Class `CustomerDAO`, `LapanganDAO`, dan `BookingDAO` ditambahkan pada package `model`. `BookingController` sekarang mengambil dan menyimpan data melalui DAO, bukan mengisi data awal secara manual.

## 3. Penjelasan Alur Program
### 3.1. **Inisialisasi Awal**
   - Program melakukan koneksi ke MySQL melalui `Koneksi.java` ketika DAO mengakses database.
   - Dummy data customer, lapangan, dan booking sudah tersedia setelah file `.sql` diimpor; tidak ada pengisian dummy data dari kode Java.
   - DAO membaca hasil query dari database dan mengubahnya menjadi objek Java untuk diproses oleh controller.
   - Customer dibagi menjadi dua jenis, yaitu Member dan Non Member.
   - Tersedia beberapa lapangan dengan jenis karpet Vinyl dan Wooden.

### 3.2. **Menu Utama**
   - Program menggunakan perulangan while dan percabangan switch-case untuk menampilkan menu pilihan 1–8 sampai user memilih keluar.

   <img width="312" height="207" alt="image" src="https://github.com/user-attachments/assets/0f2af432-8a6b-4a14-9cc2-5fbe82a9357f" />


### 3.3. **Tambah Customer**
   - User memasukkan ID Customer, nama, nomor HP, serta memilih jenis Customer.
   - Data customer baru disimpan ke tabel `customer` melalui `CustomerDAO.insert()`.
   - Jika memilih Member, user memasukkan diskon dengan batas 0% sampai 50%.
   - Jika memilih Non Member, user memasukkan biaya tambahan.
   - ID Customer tidak boleh duplikat, nama harus mengandung huruf, dan nomor HP hanya boleh berisi angka.

   <img width="240" height="242" alt="image" src="https://github.com/user-attachments/assets/b0f400a8-4c67-49eb-8ad1-17ff3346ec42" />


### 3.4. **Tampilkan Customer**
   - Menampilkan data ID, nama, nomor HP, dan status Customer.
   - Data dibaca dari tabel `customer` melalui `CustomerDAO.findAll()`.
   - Status Customer diperoleh dari method `getStatus()` pada `Member` dan `NonMember`.

   <img width="450" height="158" alt="image" src="https://github.com/user-attachments/assets/c8c12e84-e790-4914-833e-b94e2fbed908" />


### 3.5. **Tampilkan Lapangan**
   - Menampilkan nomor lapangan, jenis karpet, dan harga sewa per jam dari tabel `lapangan` melalui `LapanganDAO.findAll()`.

   <img width="390" height="166" alt="image" src="https://github.com/user-attachments/assets/d75bba55-89d2-4bd9-93ac-2f2fbbb94976" />


### 3.6. **Tambah Booking**
   - User memilih Customer dan nomor lapangan yang tersedia.
   - Sistem menerima input durasi bermain, jam mulai, dan tanggal booking.
   - Kode booking dibuat berdasarkan ID Customer dan nomor lapangan.
   - Sistem menghitung total harga berdasarkan durasi, harga lapangan, diskon Member, atau biaya tambahan Non Member.
   - Data booking yang valid disimpan ke tabel `booking` melalui `BookingDAO.insert()`.

   <img width="446" height="463" alt="image" src="https://github.com/user-attachments/assets/93ceb8e6-199a-42f3-912c-ca28cb380f87" />


### 3.7. **Tampilkan Booking**
   - Menampilkan seluruh data booking seperti kode booking, ID Customer, nomor lapangan, durasi, jam mulai, tanggal, dan total harga yang dibaca melalui `BookingDAO.findAll()`.

   <img width="972" height="100" alt="image" src="https://github.com/user-attachments/assets/c23bd8a4-356e-478e-843a-f97c3c77ce43" />


### 3.8. **Update Booking**
   - User memasukkan kode booking yang ingin diubah, lalu memasukkan data booking baru.
   - Jika data valid, sistem menghitung ulang total harga dan menyimpan perubahan ke database melalui `BookingDAO.update()`.

   <img width="970" height="310" alt="image" src="https://github.com/user-attachments/assets/bfbf19b8-71d0-4bfe-86fb-3e00efcb9116" />

   Hasil setelah data diperbarui:

   <img width="969" height="99" alt="image" src="https://github.com/user-attachments/assets/6d2803d7-f6a4-416a-a6af-3fae2699776c" />


### 3.9. **Hapus Booking**
   - User memasukkan kode booking yang ingin dihapus.
   - Jika ditemukan, data booking dihapus dari tabel `booking` melalui `BookingDAO.delete()`.

   <img width="975" height="131" alt="image" src="https://github.com/user-attachments/assets/e8128ceb-a6c6-42b7-adf6-340332fa7ff2" />

   Daftar booking setelah dihapus:

   <img width="969" height="96" alt="image" src="https://github.com/user-attachments/assets/1cf64724-87f2-485c-95b4-43a61e45e9b8" />


### 3.10. **Keluar**
  - Program berhenti ketika user memilih menu 8.

  <img width="377" height="231" alt="image" src="https://github.com/user-attachments/assets/79f027f3-1912-471b-b47e-c4f67a3d6dac" />


## 4. Encapsulation
Encapsulation diterapkan dengan menggunakan access modifier private pada atribut di setiap class. Data diakses melalui getter dan diubah melalui setter agar perubahan data tetap terkontrol.

Contoh:

```java
private final String idCustomer;
private String namaCustomer;
private String noHp;
```

Atribut yang digunakan sebagai identitas utama seperti idCustomer dibuat final dan tidak memiliki setter sehingga tidak dapat diubah setelah object dibuat.

Contoh getter dan setter:

```java
public String getNamaCustomer() {
    return namaCustomer;
}

public void setNamaCustomer(String namaCustomer) {
    this.namaCustomer = namaCustomer;
}
```

Validasi input juga diterapkan melalui `BookingView`, seperti pengecekan nama agar mengandung huruf serta nomor HP agar terdiri dari angka.


## 5. Inheritance
Inheritance diterapkan pada abstract class Customer sebagai superclass, kemudian diturunkan menjadi dua subclass yaitu Member dan NonMember.

```text
Customer (Abstract Superclass)
|__Member
|__ NonMember
```

Customer menyimpan atribut umum seperti ID Customer, nama Customer, dan nomor HP. Sedangkan subclass memiliki atribut khusus masing-masing:

- Member memiliki atribut diskon.
- NonMember memiliki atribut biayaTambahan.

Contoh:

```java
public class Member extends Customer
```

```java
public class NonMember extends Customer
```

## 6. Polymorphism 

### 6.1. Polymorphism (Overriding)
Polymorphism overriding diterapkan melalui method yang didefinisikan pada superclass/interface kemudian diimplementasikan kembali pada subclass.

Method hitungDiskon() pada Customer dibuat sebagai abstract method:

```java
public abstract double hitungDiskon();
```

Kemudian di-override pada Member:

```java
@Override
public double hitungDiskon() {
    return diskon;
}
```

Sedangkan pada NonMember:

```java
@Override
public double hitungDiskon() {
    return 0;
}
```

Jadi, pemanggilan customer.hitungDiskon() dapat memberikan hasil berbeda sesuai objectnya, apakah Member atau NonMember.

Method getStatus() juga di-override untuk menghasilkan status yang berbeda pada masing-masing jenis Customer.

### 6.2. Polymorphism - Overloading
Method overloading diterapkan pada method hitungHargaTotal() di BookingController dengan nama method yang sama tetapi parameter berbeda.

```java
public double hitungHargaTotal(Booking booking)
```

```java
public double hitungHargaTotal(Booking booking, double potonganTambahan)
```

Method pertama digunakan untuk menghitung harga booking normal, sedangkan method kedua dapat digunakan ketika terdapat potongan tambahan.

## 7. Abstraction
Abstraction diterapkan dengan menjadikan Customer sebagai abstract class.

```java
public abstract class Customer implements InfoCustomer
```

Class Customer tidak dapat dibuat object secara langsung. Object Customer harus dibuat melalui subclass seperti Member atau NonMember.

Selain itu, method hitungDiskon() dibuat sebagai abstract method agar setiap subclass wajib memberikan implementasinya sendiri.

## 8. Interface (Nilai Tambah)
Nilai tambah pada program ini diterapkan melalui interface InfoCustomer yang berada di package model.

<img width="337" height="127" alt="image" src="https://github.com/user-attachments/assets/d39b2d73-8136-4bc9-b75a-15c2327913b5" />

```java
public interface InfoCustomer {
    String getStatus();
}
```

Interface tersebut digunakan oleh abstract class Customer:

```java
public abstract class Customer implements InfoCustomer
```

Kemudian subclass Member dan NonMember memberikan implementasi getStatus() masing-masing.

Contoh pada Member:

```java
@Override
public String getStatus() {
    return "Member";
}
```

Contoh pada NonMember:

```java
@Override
public String getStatus() {
    return "Non Member";
}
```

Dengan interface ini, kedua jenis customer menyediakan method `getStatus()` yang sama meskipun hasilnya berbeda. Pada implementasi View saat ini, pengecekan jenis objek juga masih digunakan ketika menampilkan status.

## 9. Implementasi JDBC, PreparedStatement, dan DAO

### 9.1. JDBC (Java Database Connectivity)
JDBC digunakan untuk menghubungkan aplikasi Java dengan database MySQL. Konfigurasi host, port, nama database, username, dan password berada pada class `config.Koneksi`.

### 9.2. PreparedStatement
Query yang membutuhkan input menggunakan `PreparedStatement` dengan parameter `?`. Contoh pada pencarian customer:

```java
String sql = "SELECT * FROM customer WHERE id_customer = ?";
PreparedStatement ps = conn.prepareStatement(sql);
ps.setString(1, idCustomer);
ResultSet rs = ps.executeQuery();
```

Penggunaan parameter membantu menghindari penggabungan input langsung ke teks SQL dan membuat pemanggilan query lebih terstruktur.

### 9.3. DAO (Data Access Object)
DAO memisahkan operasi SQL dari logika program pada controller:

- **CustomerDAO:** Method `findAll()` untuk mengambil seluruh customer, `findById()` untuk mencari berdasarkan ID, dan `insert()` untuk menambahkan customer.
- **LapanganDAO:** Method `findAll()` untuk menampilkan seluruh lapangan dan `findById()` untuk mencari nomor lapangan tertentu.
- **BookingDAO:** Method `findAll()` dan `findById()` untuk membaca data, `insert()` untuk menambah booking, `update()` untuk mengubah booking, serta `delete()` untuk menghapus booking.

Alur penyimpanan data: **BookingView → BookingController → DAO → JDBC → MySQL**. Hasil query dikembalikan ke controller dan ditampilkan oleh view.

## 10. Database dan Dummy Data
Database bernama **`sistem_booking_lapangan`** dan berisi tiga tabel:

- **`customer`:** ID, nama, nomor HP, jenis customer, diskon, dan biaya tambahan.
- **`lapangan`:** Nomor lapangan, jenis karpet, dan harga per jam.
- **`booking`:** Kode booking, ID customer, nomor lapangan, durasi, jam mulai, tanggal, serta total harga.

Tabel `booking` memiliki foreign key ke `customer` dan `lapangan`.

Dummy data dimasukkan menggunakan perintah `INSERT` pada file **`database/sistem_booking_lapangan.sql`**, bukan dari kode Java. Isi data awal yang disiapkan:

- **Customer:** A, Adam (Member) dan B1, Budi (Non Member).
- **Lapangan:** Empat lapangan awal, yaitu nomor 1–2 berkarpet Vinyl dan nomor 3–4 berkarpet Wooden.
- **Booking:** A101 milik customer A1 pada lapangan nomor 1 dengan durasi 2 jam dan jam mulai 17.00 pada 24 September 2026.

Dengan begitu, menu READ sudah dapat menampilkan data setelah database diimpor, tanpa harus memasukkan data manual terlebih dahulu.

## 11. Pengujian Fitur

Pengujian dilakukan pada seluruh menu untuk memastikan data dapat dibaca dan perubahan tersimpan pada MySQL.

- **Menu 1 Tambah Customer:** Menambahkan customer dengan ID baru. Data yang berhasil ditambahkan dapat dilihat kembali pada tabel `customer`.
- - After: 
- <img width="845" height="174" alt="image" src="https://github.com/user-attachments/assets/65fa25d9-1c0d-4c3f-8e2b-d5165bf9eeb4" />

- **Menu 2 Tampilkan Customer:** Menampilkan customer yang berasal dari database, termasuk dummy data awal.
- **Menu 3 Tampilkan Lapangan:** Menampilkan empat lapangan awal dari tabel `lapangan`.
- **Menu 4 Tambah Booking:** Menambahkan booking baru dengan kode unik, menghitung total harga, dan menyimpan hasilnya ke tabel `booking`.
-Before:
- <img width="919" height="86" alt="image" src="https://github.com/user-attachments/assets/542d630d-43a6-4e37-bcb0-75c16286cdba" />

- After:
  
- <img width="898" height="97" alt="image" src="https://github.com/user-attachments/assets/c08fb756-28e6-4038-ac94-520818e55cb6" />

- **Menu 5 Tampilkan Booking:** Membaca daftar booking langsung dari database.
- **Menu 6 Update Booking:** Mengubah detail booking dan memastikan hasil perubahan tersimpan di database.
- Before:

- <img width="898" height="97" alt="image" src="https://github.com/user-attachments/assets/c08fb756-28e6-4038-ac94-520818e55cb6" />

- After:  
  
- <img width="921" height="111" alt="image" src="https://github.com/user-attachments/assets/aa75fa3a-b4b2-4f59-b975-6ac027845c07" />

- **Menu 7 Hapus Booking:** Menghapus booking berdasarkan kode dan memeriksa bahwa data tersebut tidak lagi ada pada tabel `booking`.

- Before:

- <img width="898" height="97" alt="image" src="https://github.com/user-attachments/assets/c08fb756-28e6-4038-ac94-520818e55cb6" />

After:

- <img width="919" height="86" alt="image" src="https://github.com/user-attachments/assets/542d630d-43a6-4e37-bcb0-75c16286cdba" />


- **Menu 8 Keluar:** Mengakhiri program melalui menu utama.

### **Pada minpro 4 ini saya tidak menerapkan nilai tambah**

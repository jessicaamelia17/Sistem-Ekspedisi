import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SistemEkspedisi{ 
    //Deklarasi
    static String [][] dataEkspedisi = new String [100][50];
    static String  pengirim, penerima, layanan, kotaAsal, kotaTujuan;
    static int  l=1, indeksKotaAsal, indeksKotaTujuan;
    static String[] isi_barang = new String[20];
    static char jawab;
    static double biaya=0, totalBiaya=0.0;
    static long no_hp, no_hp_penerima;
    static double berat;
    static Boolean online = false;
    static double pendapatanHarian = 0;
    static double pendapatanBulanan = 0;
    static int bulanIni = -1;
    static String nomor_resi = "";
    // Membuat array 2D untuk menyimpan biaya ekspedisi antar kota
    static int[][] biayaEkspedisi = {
        // Malang Blitar Kediri Surabaya Pasuruan Tulungagung Madiun
        {0    , 6000 , 8000 , 10000, 6000 , 8000 , 10000},  // Malang
        {6000 , 0    , 6000 , 12000, 7000 , 6000 , 8000 },  // Blitar
        {8000 , 6000 , 0    , 13000, 10000, 7000 , 6000 },  // Kediri
        {10000, 12000, 13000, 0    , 8000 , 14000, 15000},  // Surabaya
        {6000 , 7000 , 10000, 8000 , 0    , 9000 , 10000},  // Pasuruan
        {8000 , 6000 , 6000 , 16000, 10000, 0    , 7000 },  // Tulungagung
        {10000, 8000 , 6000 , 15000, 10000, 7000 , 0    }   // Madiun
    };
    static Scanner ekspedisi = new Scanner(System.in);
    public static void main(String[] args){
        System.out.println("----------------");
        intro();
        menuLogin();
    }
    static void intro(){
        System.out.println("---------------------------------");
        System.out.println("\tSELAMAT DATANG DI ");
        System.out.println("\tEKSPEDISI JLS FAST");
        System.out.println("---------------------------------");
    }
    static String [][] dataPengguna =  {{"Jessica Amelia","J3ss!c4"}, {"Lovelyta", "L0v3"}, {"Syifaul", "12345"}};
    static String [][]dataAdmin = {{"Admin1", "123456"},{"Admin2", "789012"}};
    static String [][] dataKasir = {{"Kasir1", "k4s!r1"},{"Kasir2","k4s!r2"}};
    static void menuLogin() {
        String role;
        do {
            System.out.println("---------------");
            System.out.println("| Menu Login |");
            System.out.println("---------------");
            System.out.println("1. Login sebagai Pengguna");
            System.out.println("2. Login sebagai Kasir");
            System.out.println("3. Login sebagai Admin");
            System.out.println("4. Keluar");
    
            System.out.print("Pilih peran (1-4): ");
            int choice = ekspedisi.nextInt();
            ekspedisi.nextLine();  // Membuang karakter newline dari buffer
    
            switch (choice) {
                case 1:
                    role = "pengguna";
                    break;
                case 2:
                    role = "kasir";
                    break;
                case 3:
                    role = "admin";
                    break;
                case 4:
                    online = false;
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    continue;
            }
    
            boolean loginSuccess = false;
            do {
                System.out.print("Masukkan Username: ");
                String inputUsername = ekspedisi.nextLine();
                System.out.print("Masukkan Password: ");
                String inputPassword = ekspedisi.nextLine();
    
                switch (role) {
                    case "pengguna":
                        if (checkLogin(dataPengguna, inputUsername, inputPassword)) {
                            online = true;
                            loginSuccess = true;
                            System.out.println("Login berhasil sebagai pengguna!");
                            menuPelanggan();
                        } else {
                            System.out.println("Login gagal. Username atau password salah.");
                        }

                        break;
                    case "kasir":
                        if (checkLogin(dataKasir, inputUsername, inputPassword)) {
                            online = true;
                            loginSuccess = true;
                            System.out.println("Login berhasil sebagai kasir!");
                            menuKasir();
                        } else {
                            System.out.println("Login gagal. Username atau password salah.");
                        }
                        break;
                    case "admin":
                        if (checkLogin(dataAdmin, inputUsername, inputPassword)) {
                            online = true;
                            loginSuccess = true;
                            System.out.println("Login berhasil sebagai admin!");
                            menuAdmin();
                        } else {
                            System.out.println("Login gagal. Username atau password salah.");
                        }
                        break;
                }
            } while (!loginSuccess);
        // Tambahkan perulangan untuk meminta penggunaan kembali menu login atau tidak
        char pilihanKembali;
        do {
            System.out.print("Apakah Anda ingin kembali ke Menu Login? (Y/T): ");
            pilihanKembali = ekspedisi.next().charAt(0);
            if (pilihanKembali != 'Y' && pilihanKembali != 'T') {
                System.out.println("Pilihan tidak valid. Silakan masukkan Y atau T.");
            }
            menuLogin();
        } while (pilihanKembali != 'Y' && pilihanKembali != 'T');

        if (pilihanKembali != 'Y') {
            online = false;
        }
        } while (!online);
    }
    

    static boolean checkLogin(String[][] data, String username, String password) {
        for (String[] user : data) {
            if (username.equals(user[0]) && password.equals(user[1])) {
                return true;
            }
        }
        return false;
    }

        //output
        static void menuPelanggan(){
        boolean kondisi = true;
        while (kondisi){
            System.out.println("----------------------------------------");
            System.out.println("|           Menu :                     |");
            System.out.println("|   1. Buat Paket/Tambah Paket         |");
            System.out.println("|   2. Data Ekspedisi                  |");
            System.out.println("|   3. Lacak Pesanan                   |");
            System.out.println("|   4. Keluar                          |");
            System.out.println("----------------------------------------");
            System.out.print("Pilih menu : ");
            int pilihan = ekspedisi.nextInt();

            switch (pilihan){
                case 1:
                buatPaket();
                break;
                
                case 2:
                dataEkspedisi();
                break;
                
                case 3:
                lacakPesanan();
                break;
                
                case 4:
                    kondisi=false;
                     System.out.println("Terima kasih telah menggunakan layanan ekspedisi kami");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
                    break;
        }
    }
}

            static void menuAdmin() {
            boolean menu=true;
            while(menu){
                System.out.println("-----------------------------");
                System.out.println("|        Menu Admin         |");
                System.out.println("-----------------------------");
                System.out.println("1. Laporan Pendapatan Harian");
                System.out.println("2. Laporan Pendapatan Bulanan");
                System.out.println("3. Keluar");

                System.out.print("Pilih menu (1-2): ");
                int pilihan = ekspedisi.nextInt();

                switch (pilihan) {
                    case 1:
                        // Tampilkan laporan pendapatan untuk admin
                        System.out.println("Laporan Pendapatan Harian: Rp " + pendapatanHarian);

                        break;
                    case 2:
                        System.out.print("Masukkan bulan (MM): ");
                        String bulanInput = ekspedisi.next();
                        double pendapatanBulan = hitungPendapatanBulanan(bulanInput);
                        System.out.println("Laporan Pendapatan Bulanan untuk bulan " + bulanInput + ": Rp " + pendapatanBulan);
                        break;
                    case 3:
                        online = false;
                        menu = false;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid");
                        break;
                }
            }
            }

            static void menuKasir() {
            boolean kasir = true;
            while(kasir){
                System.out.println("-----------------------------");
                System.out.println("|        Menu Kasir         |");
                System.out.println("-----------------------------");
                // Tambahkan opsi-opsi menu kasir sesuai kebutuhan
                System.out.println("1. Proses Pembayaran");
                System.out.println("2. Keluar");
            
                boolean validInput = false;
                do {
                    System.out.print("Pilih menu (1-2): ");
                    int pilihan = ekspedisi.nextInt();
            
                    switch (pilihan) {
                        case 1:
                            boolean cetakLabelSuccess = false;
                            do {
                                System.out.print("Masukkan nomor resi yang ingin dicetak label: ");
                                String nomorResiCetakKasir = ekspedisi.next();
                                cetakLabelSuccess = cetakLabel(nomorResiCetakKasir);
                                if (!cetakLabelSuccess) {
                                    System.out.println("Nomor resi tidak ditemukan. Silakan coba lagi.");
                                }
                            } while (!cetakLabelSuccess);
                            break;
                        case 2:
                            online = false;
                            validInput = true;
                            kasir= false;
                            break;
                        default:
                            System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                            break;
                    }
                } while (!validInput);
            }
            }
            
            static void buatPaket (){
                int jml = 0;
                int maxBarang = 10; // Tentukan maksimum barang yang dapat dimasukkan
                
                if (l < dataEkspedisi.length) {
                    System.out.println("--Data Ekspedisi--");
                    System.out.print("Nama Pengirim: ");
                    pengirim = ekspedisi.next();
                    
                    String[][] isi_barang = new String[maxBarang][2]; // Kolom pertama untuk nama barang, kolom kedua untuk jumlah barang
                    
                    do {
                        System.out.print("Isi barang: ");
                        isi_barang[jml][0] = ekspedisi.next();
                        System.out.print("Jumlah barang: ");
                        isi_barang[jml][1] = ekspedisi.next();
                        jml++;
                
                        if (jml < maxBarang) {
                            System.out.print("Apakah anda ingin menambahkan barang (Y/T)? ");
                            jawab = ekspedisi.next().charAt(0);
                        } else {
                            System.out.println("Maksimum barang tercapai. Tidak dapat menambahkan barang lagi.");
                            break;
                        }
                
                    } while (jawab == 'Y' || jawab == 'y');
                
                    System.out.println("-----------------------------");
                    System.out.println("Berikut adalah isi barang yang akan dipaketkan : ");
                    
                    for (int i = 0; i < jml; i++) {
                        System.out.println("Nama Barang: " + isi_barang[i][0] + ", Jumlah: " + isi_barang[i][1]);
                    }
                
                    System.out.println("Jumlah barang yang akan dikirimkan : " + jml);
                }
                
            System.out.println("-----------------------------");
            System.out.print("Masukkan No. HP customer: ");
            no_hp = ekspedisi.nextLong(); 
             do{
            System.out.print("Masukkan kota asal (Malang, Blitar, Kediri, Surabaya, Pasuruan, Tulungagung, Madiun): ");
            kotaAsal = ekspedisi.next();

            System.out.print("Masukkan kota tujuan (Malang, Blitar, Kediri, Surabaya, Pasuruan, Tulungagung, Madiun): ");
            kotaTujuan = ekspedisi.next();

            indeksKotaAsal = -1;
            indeksKotaTujuan = -1;
    
            // Mencari indeks kota asal
            if (kotaAsal.equalsIgnoreCase("Malang")) {
                indeksKotaAsal = 0;
            } else if (kotaAsal.equalsIgnoreCase("Blitar")) {
                indeksKotaAsal = 1;
            } else if (kotaAsal.equalsIgnoreCase("Kediri")) {
                indeksKotaAsal = 2;
            } else if (kotaAsal.equalsIgnoreCase("Surabaya")) {
                indeksKotaAsal = 3;
            } else if (kotaAsal.equalsIgnoreCase("Pasuruan")) {
                indeksKotaAsal = 4;
            } else if (kotaAsal.equalsIgnoreCase("Tulungagung")) {
                indeksKotaAsal = 5;
            } else if (kotaAsal.equalsIgnoreCase("Madiun")) {
                indeksKotaAsal = 6;
            }
    
            // Mencari indeks kota tujuan
            if (kotaTujuan.equalsIgnoreCase("Malang")) {
                indeksKotaTujuan = 0;
            } else if (kotaTujuan.equalsIgnoreCase("Blitar")) {
                indeksKotaTujuan = 1;
            } else if (kotaTujuan.equalsIgnoreCase("Kediri")) {
                indeksKotaTujuan = 2;
            } else if (kotaTujuan.equalsIgnoreCase("Surabaya")) {
                indeksKotaTujuan = 3;
            } else if (kotaTujuan.equalsIgnoreCase("Pasuruan")) {
                indeksKotaTujuan = 4;
            } else if (kotaTujuan.equalsIgnoreCase("Tulungagung")) {
                indeksKotaTujuan = 5;
            } else if (kotaTujuan.equalsIgnoreCase("Madiun")) {
                indeksKotaTujuan = 6;
            }
    
            if (indeksKotaAsal != -1 && indeksKotaTujuan != -1) {
                int biayaEkspedisiAB = biayaEkspedisi[indeksKotaAsal][indeksKotaTujuan];
                
                System.out.print("Berat (kg): ");
                berat= ekspedisi.nextDouble();
                System.out.println("Jenis Layanan (Reguler/Kargo/Hemat/SameDay)");
            layanan = ekspedisi.next();
            if(layanan.equalsIgnoreCase("Reguler")){
                if(berat<=1.0){
                    biaya = 5000;
                }else if(berat <= 5.0){
                    biaya = 10000;
                }else if (berat <= 10.0){
                    biaya = 15000;
                }else{
                    biaya = 20000;
                }
            }else if (layanan.equalsIgnoreCase ("Kargo")){
                if(berat<=1.0){
                    biaya = 13000;
                }else if(berat<=5.0){
                    biaya = 18000;
                }else if(berat<=10){
                    biaya = 25000;
                }else{
                    biaya = 50000;
                }
            }else if (layanan.equalsIgnoreCase ("Hemat")){
                if(berat<=1.0){
                    biaya = 3000;
                }else if(berat <=5.0){
                    biaya = 6000;
                }else if(berat  <= 10.0){
                    biaya = 10000;
                }else{
                    System.out.println("Anda tidak dapat menambahkan barang di atas 10kg");
                }
            }else if (layanan.equalsIgnoreCase ("SameDay")){
                if(berat<=1.0){
                    biaya = 10000;
                }else if(berat <= 5.0){
                    biaya = 20000;
                }else if(berat <= 10.0){
                    biaya = 50000;
                }else{
                    biaya = 70000;
                }
            }
            if (biaya !=0){
                totalBiaya = biayaEkspedisiAB + biaya;
                //Pendapatan Harian
                pendapatanHarian += totalBiaya;
                System.out.println("Ongkos kirim : " + totalBiaya);
                } else {
                    System.out.println("Kota asal atau kota tujuan tidak valid.");
                }

            System.out.print("Nama Penerima: ");
            penerima = ekspedisi.next();
            System.out.print("Masukkan nomor HP penerima: ");
            no_hp_penerima = ekspedisi.nextLong();
            // Buat variabel untuk menyimpan nomor resi

           // Buat fungsi untuk generate nomor resi
            Random random = new Random();
            for (int j = 0; j < 5; j++) {
            nomor_resi += random.nextInt(10);
            }
           // Tampilkan nomor resi
            System.out.println("Nomor resi Anda adalah: " + nomor_resi);
                System.out.println("Data Anda Telah Diproses");
                System.out.println("--------------------");
                
                
                dataEkspedisi[l][0] = nomor_resi;
                dataEkspedisi[l][1] = pengirim;
                dataEkspedisi[l][2] = Long.toString(no_hp);
                dataEkspedisi[l][3] = String.join(", ", isi_barang);
                dataEkspedisi[l][4] = layanan;
                dataEkspedisi[l][5] = Double.toString(totalBiaya);
                dataEkspedisi[l][6] = berat+"";
                dataEkspedisi[l][7] = kotaTujuan;
                dataEkspedisi[l][8] = penerima;
                dataEkspedisi[l][9] = Long.toString(no_hp_penerima)+"";
                dataEkspedisi[l][10] = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            l++;
            System.out.println("Data ekspedisi berhasil ditambahkan.");
            } else {
                System.out.println("Kota asal atau kota tujuan tidak tersedia.");
            }
        }while (indeksKotaAsal == -1 || indeksKotaTujuan == -1);

        }
    

    static void dataEkspedisi(){
        System.out.println("Data Ekspedisi:");
        for (int i = 1; i < l; i++) {
            System.out.println("No Resi: " + dataEkspedisi[i][0]);
            System.out.println("Pengirim: " + dataEkspedisi[i][1]);
            System.out.println("No HP Pengirim: " + dataEkspedisi[i][2]);
            System.out.println("Isi barang: " + dataEkspedisi[i][3]);
            System.out.println("Layanan: " + dataEkspedisi[i][4]);
            System.out.println("Ongkos kirim: " + dataEkspedisi[i][5]);
            System.out.println("Berat Paket: " + dataEkspedisi[i][6] + " Kg");
            System.out.println("Alamat Tujuan: " + dataEkspedisi[i][7]);
            System.out.println("Nama Penerima: " + dataEkspedisi[i][8]);
            System.out.println("No HP Penerima: " + dataEkspedisi[i][9]);
            System.out.println("Tanggal pesanan: " + dataEkspedisi[i][10]);
            System.out.println();
        }
    }
    static void lacakPesanan(){
        System.out.print("Masukkan nomor resi yang ingin dilacak: ");
        String nomorResi = ekspedisi.next();
        boolean ditemukan = false;
    
        for (int i = 1; i < l; i++) {
            if (nomorResi.equals(dataEkspedisi[i][0])) {
                ditemukan = true;
                System.out.println("Status Pengiriman:");
                System.out.println("No Resi: " + dataEkspedisi[i][0]);
                System.out.println("Status: Paket sedang dikirim");
                System.out.println("Pengirim: " + dataEkspedisi[i][1]);
                System.out.println("Alamat Tujuan: " + dataEkspedisi[i][7]);
                System.out.println("Nama Penerima: " + dataEkspedisi[i][8]);
                System.out.println("No HP Penerima: " + dataEkspedisi[i][9]);
                System.out.println("Tanggal pesanan: " + dataEkspedisi[i][10]);
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Nomor resi tidak ditemukan.");
        }
    }

    public static double hitungPendapatanBulanan(String bulan) {
        double pendapatan = 0;
        for (int i = 1; i < l; i++) {
          String tanggalPemesanan = dataEkspedisi[i][10];
          String[] tanggalPemesananSplit = tanggalPemesanan.split("-");
          if (bulan.equals(tanggalPemesananSplit[1])) {
            pendapatan += Double.parseDouble(dataEkspedisi[i][5]);
          }
        }
        return pendapatan;
      }

      static void strukPembayaran(String nomorResi) {
        boolean ditemukan = false;
        for (int i = 1; i < l; i++) {
            if (nomorResi.equals(dataEkspedisi[i][0])) {
                ditemukan = true;
                System.out.println("---------------------------");
                System.out.println("       Struk Pembayaran");
                System.out.println("---------------------------");
                System.out.println("No Resi: " + dataEkspedisi[i][0]);
                System.out.println("Pengirim: " + dataEkspedisi[i][1]);
                System.out.println("No HP Pengirim: " + dataEkspedisi[i][2]);
                System.out.println("Isi Barang: " + dataEkspedisi[i][3]);
                System.out.println("Layanan: " + dataEkspedisi[i][4]);
                System.out.println("Ongkos Kirim: Rp " + dataEkspedisi[i][5]);
                System.out.println("Berat Paket: " + dataEkspedisi[i][6] + " Kg");
                System.out.println("Alamat Tujuan: " + dataEkspedisi[i][7]);
                System.out.println("Nama Penerima: " + dataEkspedisi[i][8]);
                System.out.println("No HP Penerima: " + dataEkspedisi[i][9]);
                System.out.println("---------------------------");
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Nomor resi tidak ditemukan.");
        }
    }
    static boolean cetakLabel(String nomorResi) {
        boolean ditemukan = false;
    
        for (int i = 1; i < l; i++) {
            if (nomorResi.equals(dataEkspedisi[i][0])) {
                ditemukan = true;
                System.out.println("------------------------------");
                System.out.println("           Label Pengiriman");
                System.out.println("------------------------------");
                System.out.println("No Resi: " + dataEkspedisi[i][0]);
                System.out.println("Pengirim: " + dataEkspedisi[i][1]);
                System.out.println("No HP Pengirim: " + dataEkspedisi[i][2]);
                System.out.println("Isi Barang: " + dataEkspedisi[i][3]);
                System.out.println("Layanan: " + dataEkspedisi[i][4]);
                System.out.println("Ongkos Kirim: Rp " + dataEkspedisi[i][5]);
                System.out.println("Berat Paket: " + dataEkspedisi[i][6] + " Kg");
                System.out.println("Alamat Tujuan: " + dataEkspedisi[i][7]);
                System.out.println("Nama Penerima: " + dataEkspedisi[i][8]);
                System.out.println("No HP Penerima: " + dataEkspedisi[i][9]);
                System.out.println("------------------------------");
                break;
            }
        }
    
        if (!ditemukan) {
            System.out.println("Nomor resi tidak ditemukan.");
        }
    
        return ditemukan;
    }
    
    }
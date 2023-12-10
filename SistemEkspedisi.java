import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Date;

public class SistemEkspedisi{ 
    //Deklarasi
    static String [][] dataEkspedisi = new String [100][10];
    static String  pengirim, penerima, layanan, kotaAsal, kotaTujuan;
    static int  jml=0, l=1, indeksKotaAsal, indeksKotaTujuan;
    static String[] isi_barang = new String[50];
    static char jawab;
    static double biaya=0, totalBiaya=0.0;
    static long no_hp, no_hp_penerima;
    static int maxPaket = 15;
    static double [] berat = new double [20];
    static Boolean kondisi = true, online = false;
    static double pendapatanHarian = 0;
    static double pendapatanBulanan = 0;
    static int bulanIni = -1;
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
        menuUtama();
    }
    static void intro(){
        System.out.println("---------------------------------");
        System.out.println("\tSELAMAT DATANG DI ");
        System.out.println("\tEKSPEDISI JLS FAST");
        System.out.println("---------------------------------");
    }
    static String [][] dataPengguna =  {{"Jessica Amelia","J3ss!c4"}, {"Lovelyta", "L0v3"}, {"Syifaul", "12345"}};
    static String [][]dataAdmin = {{"Admin1", "123456"},{"Admin2", "789012"}};
    static boolean isAdmin = false;
    static void menuLogin(){
        do {
            
            System.out.print("\nMasukkan Username: ");
            String inputUsername = ekspedisi.nextLine();
            System.out.print("Masukkan Password: ");
            String inputPassword = ekspedisi.nextLine();
            String role = "";
            while (!role.equals("p") && !role.equals("a")) {
                System.out.print("Masuk sebagai pengguna (p) atau admin (a): ");
                role = ekspedisi.nextLine().toLowerCase();
            }

            if (role.equals("p")) {
                // Cek ke database pengguna untuk login pengguna
                if (checkLogin(dataPengguna, inputUsername, inputPassword)) {
                    online = true;
                    isAdmin = false;
                    System.out.println("Selamat datang, pengguna!");
                    System.out.println("Anda tidak memiliki akses ke laporan pendapatan.");
                } else {
                    System.out.println("Username atau password salah untuk pengguna.");
                }
            } else if (role.equals("a")) {
                if (checkLogin(dataAdmin, inputUsername, inputPassword)) {
                    online = true;
                    isAdmin = true;
                    System.out.println("Selamat datang, admin!");
                } else {
                    System.out.println("Username atau password salah untuk admin.");
                }
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
        static void menuUtama(){
        while (kondisi){
            System.out.println("----------------------------------------");
            System.out.println("|           Menu :                     |");
            System.out.println("|   1. Buat Paket/Tambah Paket         |");
            System.out.println("|   2. Cetak Label                     |");
            System.out.println("|   3. Data Ekspedisi                  |");
            System.out.println("|   4. Lacak Pesanan                   |");
            System.out.println("|   5. Laporan Pendapatan              |");
            System.out.println("|   6. Keluar                          |");
            System.out.println("----------------------------------------");
            System.out.print("Pilih menu : ");
            int pilihan = ekspedisi.nextInt();

            switch (pilihan){
                case 1:
                buatPaket();
                break;
                
                case 2:
                cetakLabel();
                break;
                
                case 3:
                dataEkspedisi();
                break;
                
                case 4:
                lacakPesanan();
                break;
                
                case 5:
                if(isAdmin){
                    laporanPendapatan();
                } else{
                    System.out.println("Anda tidak memiliki akses ke laporan pendapatan");
                }
                break;
                    
                case 6:
                    kondisi=false;
                    System.out.println("Terima kasih telah menggunakan layanan ekspedisi kami");
                    break;
                    
                default:
                    System.out.println("Pilihan tidak valid");
                    break;
        }
    }
}
            static void buatPaket (){

            
            if (l<dataEkspedisi.length){
            System.out.println("--Data Ekspedisi--");
            System.out.print("Nama Pengirim: ");
            pengirim = ekspedisi.next();
            do {
                System.out.print("Isi barang :");
                isi_barang[jml] = ekspedisi.next();
                jml++;
                System.out.print("Apakah anda ingin menambahkan barang (Y/T)?");
                jawab = ekspedisi.next().charAt(0);
            }while (jawab == 'Y' || jawab == 'y');
            System.out.println("-----------------------------");
            System.out.println("Berikut adalah isi barang yang akan dipaketkan : ");
            for (String barang : isi_barang){
                if (barang != null){
                    System.out.println(barang );
                }
            }
            System.out.println("Jumlah barang yang akan dikirimkan : " + jml);

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
                berat [maxPaket]= ekspedisi.nextDouble();
                System.out.println("Jenis Layanan (Reguler/Kargo/Hemat/SameDay)");
            layanan = ekspedisi.next();
            if(layanan.equalsIgnoreCase("Reguler")){
                if(berat[maxPaket]<=1.0){
                    biaya = 5000;
                }else if(berat [maxPaket] <= 5.0){
                    biaya = 10000;
                }else if (berat[maxPaket] <= 10.0){
                    biaya = 15000;
                }else{
                    biaya = 20000;
                }
            }else if (layanan.equalsIgnoreCase ("Kargo")){
                if(berat[maxPaket]<=1.0){
                    biaya = 13000;
                }else if(berat[maxPaket]<=5.0){
                    biaya = 18000;
                }else if(berat[maxPaket]<=10){
                    biaya = 25000;
                }else{
                    biaya = 50000;
                }
            }else if (layanan.equalsIgnoreCase ("Hemat")){
                if(berat[maxPaket]<=1.0){
                    biaya = 3000;
                }else if(berat[maxPaket] <=5.0){
                    biaya = 6000;
                }else if(berat [maxPaket] <= 10.0){
                    biaya = 10000;
                }else{
                    System.out.println("Anda tidak dapat menambahkan barang di atas 10kg");
                }
            }else if (layanan.equalsIgnoreCase ("SameDay")){
                if(berat[maxPaket]<=1.0){
                    biaya = 10000;
                }else if(berat[maxPaket] <= 5.0){
                    biaya = 20000;
                }else if(berat[maxPaket] <= 10.0){
                    biaya = 50000;
                }else{
                    biaya = 70000;
                }
            }
            if (biaya !=0){
                totalBiaya = biayaEkspedisiAB + biaya;
                System.out.println("Ongkos kirim : " + totalBiaya);
                } else {
                    System.out.println("Kota asal atau kota tujuan tidak valid.");
                }

            System.out.print("Nama Penerima: ");
            penerima = ekspedisi.next();
            System.out.print("Masukkan nomor HP penerima: ");
            no_hp_penerima = ekspedisi.nextLong();
            // Buat variabel untuk menyimpan nomor resi
            String nomor_resi = "";

           // Buat fungsi untuk generate nomor resi
            Random random = new Random();
            for (int j = 0; j < 10; j++) {
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
                dataEkspedisi[l][6] = berat[maxPaket]+"";
                dataEkspedisi[l][7] = kotaTujuan;
                dataEkspedisi[l][8] = penerima;
                dataEkspedisi[l][9] = Long.toString(no_hp_penerima)+"";
            l++;
            System.out.println("Data ekspedisi berhasil ditambahkan.");
            } else {
                System.out.println("Kota asal atau kota tujuan tidak tersedia.");
            }
        }while (indeksKotaAsal == -1 || indeksKotaTujuan == -1);

        }
    }
    static void cetakLabel() {
        System.out.print("Masukkan nomor resi untuk mencetak label: ");
        String nomorResi = ekspedisi.next();
        boolean ditemukan = false;

        for (int i = 1; i < l; i++) {
            if (nomorResi.equals(dataEkspedisi[i][0])) {
                ditemukan = true;
                generateLabel(dataEkspedisi[i]);
                break;
            }
        }

        if (!ditemukan) {
            System.out.println("Nomor resi tidak ditemukan.");
        }
    }

    static void generateLabel(String[] packageInfo) {
        System.out.println("---------- LABEL PENGIRIMAN ----------");
        System.out.println("No Resi: " + packageInfo[0]);
        System.out.println("Pengirim: " + packageInfo[1]);
        System.out.println("No HP Pengirim: " + packageInfo[2]);
        System.out.println("Isi barang: " + packageInfo[3]);
        System.out.println("Layanan: " + packageInfo[4]);
        System.out.println("Ongkos kirim: " + packageInfo[5]);
        System.out.println("Berat Paket: " + packageInfo[6] + " Kg");
        System.out.println("Alamat Tujuan: " + packageInfo[7]);
        System.out.println("Nama Penerima: " + packageInfo[8]);
        System.out.println("No HP Penerima: " + packageInfo[9]);

        // Adding timestamp to the label
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date currentDate = new Date();
        System.out.println("Tanggal Cetak: " + sdf.format(currentDate));

        System.out.println("--------------------------------------");
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
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Nomor resi tidak ditemukan.");
        }
    }
    static void laporanPendapatan(){
        Calendar cal = Calendar.getInstance();
        int hariIni = cal.get(Calendar.DAY_OF_MONTH);
        int bulanBaru = cal.get(Calendar.MONTH);
    
        if (hariIni != bulanIni) {
            pendapatanHarian = 0;
        }
    
        if (bulanBaru != bulanIni) {
            pendapatanHarian = 0;
            pendapatanBulanan = 0;
        }
        pendapatanHarian += totalBiaya;
        pendapatanBulanan += totalBiaya;
    
        bulanIni = bulanBaru;
        System.out.println("----------------------------------------");
        System.out.println("|          Laporan Pendapatan          |");
        System.out.println("    Pendapatan Harian:   Rp " + pendapatanHarian);
        System.out.println("    Pendapatan Bulanan:  Rp " + pendapatanBulanan);
        System.out.println("----------------------------------------");
    }

    }
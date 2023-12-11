import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SistemEkspedisi{ 
    //Deklarasi
    static String [][] dataEkspedisi = new String [100][50];
    static String  pengirim, penerima, layanan, kotaAsal, kotaTujuan;
    static int  l=1, indeksKotaAsal, indeksKotaTujuan;
    static int maxBarang = 10; // Tentukan maksimum barang yang dapat dimasukkan
    static String[][] isi_barang = new String[maxBarang][3]; // Kolom pertama untuk nama barang, kolom kedua untuk jumlah barang
    static char jawab;
    static double biaya=0, totalBiaya=0.0;
    static long no_hp, no_hp_penerima;
    static double berat;
    static Boolean online = false;
    static double pendapatanHarian = 0;
    static double pendapatanBulanan = 0;
    static int bulanIni = -1;
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
    public static void main(String[] args) {
        System.out.println("----------------");
        intro();
        menuLogin();
    }
    
    static void intro() {
        System.out.println("---------------------------------");
        System.out.println("\tSELAMAT DATANG DI ");
        System.out.println("\tEKSPEDISI JLS FAST");
        System.out.println("---------------------------------");
    }

    static String[][] dataPengguna = {{"Jessica Amelia", "J3ss!c4"}, {"Lovelyta", "L0v3"}, {"Syifaul", "12345"}};
    static String[][] dataAdmin = {{"Admin1", "123456"}, {"Admin2", "789012"}};
    static String[][] dataKasir = {{"Kasir1", "k4s!r1"}, {"Kasir2", "k4s!r2"}};

    static void menuLogin() {
        char pilihanKembali;
        do {
            System.out.println("---------------");
            System.out.println("| Menu Login |");
            System.out.println("---------------");
            System.out.println("1. Login sebagai Pengguna");
            System.out.println("2. Login sebagai Kasir");
            System.out.println("3. Login sebagai Admin");

            System.out.print("Pilih peran (1-3): ");
            int choice = ekspedisi.nextInt();
            ekspedisi.nextLine();  // Membuang karakter newline dari buffer

            switch (choice) {
                case 1:
                    if (login("pengguna", dataPengguna)) {
                        online = true;
                        System.out.println("Login berhasil sebagai pengguna!");
                        menuPelanggan();
                    } else {
                        System.out.println("Login gagal. Username atau password salah.");
                    }
                    break;
                case 2:
                    if (login("kasir", dataKasir)) {
                        online = true;
                        System.out.println("Login berhasil sebagai kasir!");
                        menuKasir();
                    } else {
                        System.out.println("Login gagal. Username atau password salah.");
                    }
                    break;
                case 3:
                    if (login("admin", dataAdmin)) {
                        online = true;
                        System.out.println("Login berhasil sebagai admin!");
                        menuAdmin();
                    } else {
                        System.out.println("Login gagal. Username atau password salah.");
                    }
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }

            // Setelah keluar dari menu pengguna, kasir, atau admin, tanyakan apakah ingin kembali ke menu login
            do {
                System.out.print("Apakah Anda ingin kembali ke Menu Login? (Y/T): ");
                pilihanKembali = ekspedisi.next().charAt(0);
                if (pilihanKembali != 'Y' && pilihanKembali != 'T') {
                    System.out.println("Pilihan tidak valid. Silakan masukkan Y atau T.");
                }
            } while (pilihanKembali != 'Y' && pilihanKembali != 'T');

        } while (pilihanKembali == 'Y');

        System.out.println("================================");
        System.out.println("|Terima kasih telah menggunakan|\n|layanan sistem ekspedisi kami.|");
        System.out.println("================================");
    }
            

    static boolean login(String role, String[][] userData) {
        int maxAttempts = 3;
        int attempts = 0;

        boolean loginSuccess = false;

        do {
            System.out.print("Masukkan Username: ");
            String inputUsername = ekspedisi.nextLine();
            System.out.print("Masukkan Password: ");
            String inputPassword = ekspedisi.nextLine();

            for (String[] user : userData) {
                if (user != null && user[0].equals(inputUsername) && user[1].equals(inputPassword)) {
                    loginSuccess = true;
                    break;
                }
            }

            if (!loginSuccess) {
                attempts++;
                System.out.println("Login gagal. Username atau password salah. Sisa percobaan: " + (maxAttempts - attempts));

                if (attempts >= maxAttempts) {
                    System.out.println("Anda telah mencapai batas percobaan login. Silakan coba beberapa saat lagi.");
                    break;
                }
            }
        } while (!loginSuccess);

        return loginSuccess;
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

                System.out.print("Pilih menu (1-3): ");
                int pilihan = ekspedisi.nextInt();

                switch (pilihan) {
                    case 1:
                        Date currentDate = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        System.out.println("Laporan Pendapatan Harian (" + dateFormat.format(currentDate) + "): Rp " + pendapatanHarian);
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
                boolean validInput = false;
                do {
                    System.out.println("-----------------------------");
                    System.out.println("|        Menu Kasir         |");
                    System.out.println("-----------------------------");
                    System.out.println("1. Proses Pembayaran");
                    System.out.println("2. Cetal Label");
                    System.out.println("3. Keluar");
                    System.out.print("Pilih menu (1-3): ");
                    int pilihan = ekspedisi.nextInt();
            
                    switch (pilihan) {
                        case 1:
                            boolean cetakstruk = false;
                            do {
                                System.out.print("Masukkan nomor resi yang ingin dibayar: ");
                                String nomorResistruk = ekspedisi.next();
                                cetakstruk = strukPembayaran(nomorResistruk);
                                if (!cetakstruk) {
                                    System.out.println("Nomor resi tidak ditemukan. Silakan coba lagi.");
                                }
                            } while (!cetakstruk);
                            break;
                        case 2:
                        Date currentDate = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        System.out.println("Tanggal dan Waktu saat mencetak label: " + dateFormat.format(currentDate));

                        System.out.print("Masukkan nomor resi untuk mencetak label: ");
                        String nomorResi = ekspedisi.next();
                        boolean ditemukan = printLabel(nomorResi);
                
                        if (!ditemukan) {
                            System.out.println("Nomor resi tidak ditemukan.");
                        }
                        break;
                        case 3:
                            online = false;
                            validInput = true;
                            break;
                        default:
                            System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                            break;
                    }
                } while (!validInput);
            }

            static boolean printLabel(String nomorResi) {
                boolean ditemukan = false;

                        for (int i = 1; i < l; i++) {
                            if (nomorResi.equals(dataEkspedisi[i][0])) {
                                ditemukan = true;
                                generateLabel(dataEkspedisi[i]);
                                break;
                            }
                        }
                        return ditemukan;
                        
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
                
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        Date currentDate = new Date();
                        System.out.println("Tanggal Cetak: " + sdf.format(currentDate));
                
                        System.out.println("--------------------------------------");
                }
            
            
            static void buatPaket (){
                int jml = 0;
                
                if (l < dataEkspedisi.length) {
                    System.out.println("--Data Ekspedisi--");
                    System.out.print("Nama Pengirim: ");
                    pengirim = ekspedisi.next();
                    
                    
                    do {
                        System.out.print("Isi barang: ");
                        isi_barang[jml][0] = ekspedisi.next();
            
                        System.out.print("Jumlah item: ");
                        isi_barang[jml][1] = ekspedisi.next();
                        jml++;
            
                        if (jml < maxBarang) {
                            System.out.print("Apakah anda ingin menambahkan barang (Y/T)? ");
                            jawab = ekspedisi.next().charAt(0);
            
                            // Validasi input hanya 'Y' atau 'T'
                            while (jawab != 'Y' && jawab != 'y' && jawab != 'T' && jawab != 't') {
                                System.out.println("Input tidak valid. Harap masukkan 'Y' atau 'T'.");
                                System.out.print("Apakah anda ingin menambahkan barang (Y/T)? ");
                                jawab = ekspedisi.next().charAt(0);
                            }
                        } else {
                            System.out.println("Maksimum barang tercapai. Tidak dapat menambahkan barang lagi.");
                            break;
                        }
            
                    } while (jawab == 'Y' || jawab == 'y');
            
                    System.out.println("-----------------------------");
                    System.out.println("Berikut adalah isi barang yang akan dipaketkan : ");
            
                    for (int i = 0; i < jml; i++) {
                        System.out.println("Nama Barang: " + isi_barang[i][0] + ", Jumlah item: " + isi_barang[i][1]);
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
                dataEkspedisi[l][3] = " ";
                for (int i = 0; i < jml; i++) {
                    dataEkspedisi[l][3] += isi_barang[i][0] +" " + isi_barang[i][1] + " item" +", ";
                }
                dataEkspedisi[l][4] = layanan;
                dataEkspedisi[l][5] = Double.toString(totalBiaya);
                dataEkspedisi[l][6] = berat+"";
                dataEkspedisi[l][7] = kotaTujuan;
                dataEkspedisi[l][8] = penerima;
                dataEkspedisi[l][9] = Long.toString(no_hp_penerima)+"";
                dataEkspedisi[l][10] = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
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
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Nomor resi tidak ditemukan.");
        }
    }

     static double hitungPendapatanBulanan(String bulan) {
        double pendapatan = 0;
        for (int i = 1; i < l; i++) {
          String tanggalPemesanan = dataEkspedisi[i][10];
          String[] tanggalPemesananSplit = tanggalPemesanan.split("/");
          if (bulan.equals(tanggalPemesananSplit[1])) {
            pendapatan += Double.parseDouble(dataEkspedisi[i][5]);
          }
        }
        return pendapatan;
      }

      static boolean strukPembayaran(String nomorResi) {
        int uangTransfer=0, uangTunai=0;
        boolean ditemukan = false;

        for (int i = 1; i < l; i++) {
            if (nomorResi.equals(dataEkspedisi[i][0])) {
                ditemukan = true;

                // Menampilkan informasi pesanan
                System.out.println("---------------------------");
                System.out.println("       Detail Pesanan");
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

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date currentDate = new Date();
                System.out.println("Tanggal Cetak: " + sdf.format(currentDate));
                System.out.println("---------------------------");

                // Meminta pengguna memilih metode pembayaran
                System.out.println("Pilih metode pembayaran:");
                System.out.println("1. Tunai");
                System.out.println("2. Transfer");
                System.out.print("Masukkan pilihan (1/2): ");
                int pilihanMetode = ekspedisi.nextInt();

                double totalHarga = Double.parseDouble(dataEkspedisi[i][5]);

                switch (pilihanMetode) {
                    case 1:
                        // Metode pembayaran tunai
                        System.out.print("Masukkan nominal uang yang dibayar: Rp ");
                         uangTunai = ekspedisi.nextInt();

                        if (uangTunai >= totalHarga) {
                            double kembalian = uangTunai - totalHarga;
                            System.out.println("Kembalian anda adalah: Rp " + kembalian);
                            dataEkspedisi[i][11] = "Lunas";
                        } else {
                            System.out.println("Uang anda kurang. Pembayaran gagal.");
                            dataEkspedisi[i][11] = "Belum Lunas";
                        }
                        break;
                    case 2:
                        // Metode pembayaran transfer
                        System.out.print("Masukkan nominal uang yang ditransfer: Rp ");
                         uangTransfer = ekspedisi.nextInt();
                        System.out.println("Silakan transfer ke rekening xxx-xxx-xxxx a/n Sistem Ekspedisi.");

                        if (uangTransfer == totalHarga) {
                            System.out.println("Pembayaran berhasil. Pesanan lunas.");
                            dataEkspedisi[i][11] = "Lunas";
                        } else {
                            System.out.println("Jumlah transfer tidak sesuai dengan total harga. Pembayaran gagal.");
                            dataEkspedisi[i][11] = "Belum Lunas";
                        }
                        break;
                    default:
                        System.out.println("Pilihan metode pembayaran tidak valid.");
                        break;
                }

                // Menampilkan informasi metode pembayaran dan status pembayaran
                System.out.println("---------------------------");
                System.out.println("       Struk Pembayaran");
                System.out.println("---------------------------");
                System.out.println("Tanggal Cetak: " + sdf.format(currentDate));
                System.out.println("No Resi: " + dataEkspedisi[i][0]);
                System.out.println("Metode Pembayaran: " + (pilihanMetode == 1 ? "Tunai" : "Transfer"));
                System.out.println("Nominal Uang Dibayar: Rp " + (pilihanMetode == 1 ? uangTunai : uangTransfer));
                System.out.println("Status Pembayaran: " + dataEkspedisi[i][11]);
                System.out.println("---------------------------");
                
                return true; // Pembayaran berhasil
            }
        }

        if (!ditemukan) {
            System.out.println("Nomor resi tidak ditemukan.");
        }
        return false; // Pembayaran gagal
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

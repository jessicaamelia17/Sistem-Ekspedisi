import java.util.Scanner;
import java.util.Calendar;
import java.util.Random;

public class SistemEkspedisi{ 
public static void main(String[] args){
    
    Scanner ekspedisi = new Scanner(System.in);
    
    //Deklarasi
    String [][] dataEkspedisi = new String [100][10];
    String [][] akun = { 
        //Nama
        {"Jessica Amelia"}, { "Lovelyta Sekarayu Krisdiyanti"},
        {"Moh. Syifa'ul Faj Ismunir"}
    };
    int [][] password = {
        {112233}, {445566}, {778899}
    };
    String  pengirim, penerima, layanan, kotaAsal, kotaTujuan;
    int  jml=0, l=1, indeksKotaAsal, indeksKotaTujuan;
    String[] isi_barang = new String[50];
    char jawab;
    double biaya=0, totalBiaya=0.0;
    long no_hp, no_hp_penerima;
    int maxPaket = 15;
    double [] berat = new double [20];
    Boolean kondisi = true, online = false;
    double pendapatanHarian = 0;
    double pendapatanBulanan = 0;
    int bulanIni = -1;
    // Membuat array 2D untuk menyimpan biaya ekspedisi antar kota
    int[][] biayaEkspedisi = {
    // Malang Blitar Kediri Surabaya Pasuruan Tulungagung Madiun
      {0    , 6000 , 8000 , 10000, 6000 , 8000 , 10000},  // Malang
      {6000 , 0    , 6000 , 12000, 7000 , 6000 , 8000 },  // Blitar
      {8000 , 6000 , 0    , 13000, 10000, 7000 , 6000 },  // Kediri
      {10000, 12000, 13000, 0    , 8000 , 14000, 15000},  // Surabaya
      {6000 , 7000 , 10000, 8000 , 0    , 9000 , 10000},  // Pasuruan
      {8000 , 6000 , 6000 , 16000, 10000, 0    , 7000 },  // Tulungagung
      {10000, 8000 , 6000 , 15000, 10000, 7000 , 0    }   // Madiun
        };
        System.out.println("---------------------------------");
        System.out.println("\tSELAMAT DATANG DI ");
        System.out.println("\tEKSPEDISI JLS FAST");
        System.out.println("---------------------------------");
        do {
            System.out.print("\nMasukkan Username      : ");
            String inputNama = ekspedisi.nextLine();
            System.out.print("Masukkan Password      : ");
            int inputPin = ekspedisi.nextInt();
            ekspedisi.nextLine();

            for (int i = 0; i < akun.length; i++) {
                if (inputNama.equals(akun[i][0]) && inputPin == password[i][0]) {
                    online = true;
                    System.out.println("-------------------------------");
                    System.out.println("\tLogin Berhasil!");
                    System.out.println("-------------------------------");
                    break;
                }
            }
            if (online==true){
                continue;
            }else {
                System.out.println("\nUser Name dan Password salah");
            }
        }while (!online);
            
        //output
        System.out.println("----------------");
            
        while (kondisi){
            System.out.println("----------------------------------------");
            System.out.println("|           Menu :                     |");
            System.out.println("|   1. Buat Paket/Tambah Paket         |");
            System.out.println("|   2. Data Ekspedisi                  |");
            System.out.println("|   3. Lacak Pesanan                   |");
            System.out.println("|   4. Laporan Pendapatan              |");
            System.out.println("|   5. Keluar                          |");
            System.out.println("----------------------------------------");
            System.out.print("Pilih menu : ");
            int pilihan = ekspedisi.nextInt();

            switch (pilihan){
                case 1:
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
                break;
                
                case 2:
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
          
                break;
                
                case 3:
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
                break;

                case 4:
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
                    break;

                case 5:
                    kondisi=false;
                     System.out.println("Terima kasih telah menggunakan layanan ekspedisi kami");
                    
                    

                default:
                    System.out.println();
                    break;
        }
    }}}

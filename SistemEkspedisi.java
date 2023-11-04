import java.util.Scanner;
public class SistemEkspedisi{ 
public static void main(String[] args){
        String[] isi_barang = new String[400];
        
        Scanner ekspedisi = new Scanner(System.in);

        //Deklarasi
        String [][] dataEkspedisi = new String [100][10];
        String  pengirim, penerima, layanan, cari, kotaAsal, kotaTujuan;
        int jumlah, jml=0, hari, l=0;
        char jawab;
        double biaya=0, totalBiaya=0.0;
        long no_hp, no_hp_penerima;
        int maxPaket = 15;
        int no_resi, index;
        double [] berat = new double [20];
        Boolean kondisi = true;
        // Membuat array 2D untuk menyimpan biaya ekspedisi antar kota
        int[][] biayaEkspedisi = {
        // Malang Blitar Kediri Surabaya Pasuruan Tulungagung Madiun
            {0   , 6000 , 8000, 10000, 6000, 8000, 10000},  // Malang
            {6000, 0    , 6000, 12000, 7000, 6000, 8000},  // Blitar
            {8000, 6000  , 0   , 13000, 10000, 7000, 6000},  // Kediri
            {10000, 12000, 13000, 0   , 8000, 14000, 15000},  // Surabaya
            {6000, 7000  , 10000, 8000, 0    , 9000, 10000},  // Pasuruan
            {8000, 6000  , 6000, 16000, 10000, 0    , 7000},  // Tulungagung
            {10000, 8000, 6000, 15000, 1000, 7000    , 0}   // Madiun
        };
            
        //output
        System.out.println("----------------");
            
        while (kondisi){
            System.out.println("----------------------------------------");
            System.out.println("|           Menu :                     |");
            System.out.println("|   1. Buat Paket/Tambah Paket         |");
            System.out.println("|   2. Data Ekspedisi                  |");
            System.out.println("|   3. Cari Paket                      |");
            System.out.println("|   4. Riwayat layanan                 |");
            System.out.println("|   5. Keluar                          |");
            System.out.println("----------------------------------------");
            System.out.print("Pilih menu : ");
            int pilihan = ekspedisi.nextInt();

            switch (pilihan){
                case 1:
                        if (l<dataEkspedisi.length){
                        System.out.println("--Data Ekspedisi--");
                        System.out.print("Masukkan No. Resi: " );
                        no_resi = ekspedisi.nextInt();
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
                        for (int i=0; i<jml;i++){
                            System.out.println(isi_barang[i]);
                        }
                        System.out.println("Jumlah barang yang akan dikirimkan : " + jml);
                    // System.out.print("Jumlah barang: ");
                    // jumlah = ekspedisi.nextInt();
                        System.out.println("-----------------------------");
                        System.out.print("Masukkan No. HP customer: ");
                        no_hp = ekspedisi.nextLong(); 
                        
                        System.out.print("Masukkan kota asal (Malang, Blitar, Kediri, Surabaya, Pasuruan, Tulungagung, Madiun): ");
                        kotaAsal = ekspedisi.next();

                        System.out.print("Masukkan kota tujuan (Malang, Blitar, Kediri, Surabaya, Pasuruan, Tulungagung, Madiun): ");
                        kotaTujuan = ekspedisi.next();

                        int indeksKotaAsal = -1;
                        int indeksKotaTujuan = -1;
                
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
                            System.out.println("Data Anda Telah Diproses");
                            System.out.println("--------------------");
                            
                            dataEkspedisi[1][0] = Integer.toString(no_resi);
                            dataEkspedisi[l][1] = pengirim;
                            dataEkspedisi[1][2] = Long.toString(no_hp);
                            dataEkspedisi[1][3] = isi_barang+"";
                            dataEkspedisi[l][4] = layanan;
                            dataEkspedisi[1][5] = Double.toString(totalBiaya);
                            dataEkspedisi[l][6] = berat[maxPaket]+"";
                            dataEkspedisi[l][7] = kotaTujuan;
                            dataEkspedisi[1][8] = penerima;
                            dataEkspedisi[1][9] = Long.toString(no_hp_penerima)+"";

                        l++;
                        System.out.println("Data ekspedisi berhasil ditambahkan.");
                        }
                        break;
                    }
                case 2:
                System.out.println("Data Ekspedisi:");
                for (int i = 0; i < l; i++) {
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
                    //System.out.println();
                }
                break;
                
                case 3:
                String[] dataAlmt = {"Malang", "Blitar", "Kediri", "Surabaya","Pasuruan", "Tulungagung", "Madiun",};  
                System.out.print("Masukkan alamat tujuan paket yang dicari: ");
                cari = ekspedisi.next();
                
                // Mencari data
                index = -1;
                for (int i = 0; i < dataAlmt.length; i++) {
                    if (dataAlmt[i].equalsIgnoreCase(cari)) {
                        index = i;
                        break;
                    }
                }

                // Menampilkan hasil pencarian
                if (index != -1) {
                    System.out.println("Alamat tujuan " + cari + " ditemukan pada indeks ke-" + index);
                } else {
                    System.out.println("Alamat tujuan " + cari + " tidak ditemukan");
                }
            
                    break;
                case 4:
                int riwayatLayanan [][] = new int [4][4];
                String [] layananTersedia = {"Reguler", "Kargo","Hemat", "SameDay" };
                
                for (int i = 0; i < riwayatLayanan.length; i++) {
                    for (int j = 0; j < riwayatLayanan[i].length; j++) {
                        System.out.print("Jumlah penggunaan layanan " + (layananTersedia[i]) + ", minggu ke-" + (j + 1) + ": ");
                        riwayatLayanan[i][j] = ekspedisi.nextInt();
                    }
                }
        
                // Tampilkan array
                for (int i = 0; i < riwayatLayanan.length; i++) {
                    for (int j = 0; j < riwayatLayanan[i].length; j++) {
                        System.out.print(riwayatLayanan[i][j] + " ");
                    }
                    System.out.println();
                }
                        // Hitung total penggunaan layanan pengiriman per minggu
                int[] totalLayananPerHari = new int[4];
                for (int i = 0; i < riwayatLayanan.length; i++) {
                    for (int j = 0; j < riwayatLayanan[0].length; j++) {
                        totalLayananPerHari[j] += riwayatLayanan[i][j];
                    }
                }

                // Tampilkan total penggunaan layanan pengiriman per minggu
                System.out.println("Total penggunaan layanan pengiriman per minggu:");
                for (int i = 0; i < totalLayananPerHari.length; i++) {
                    System.out.println("Minggu ke-" + (i + 1) + ": " + totalLayananPerHari[i]);
                }

                // Hitung total penggunaan layanan pengiriman per layanan
                int[] totalLayananPerLayanan = new int[4];
                for (int i = 0; i < riwayatLayanan.length; i++) {
                    for (int j = 0; j < riwayatLayanan[0].length; j++) {
                        totalLayananPerLayanan[i] += riwayatLayanan[i][j];
                    }
                }

                // Tampilkan total penggunaan layanan pengiriman per layanan
                System.out.println("Total penggunaan layanan pengiriman per layanan:");
                for (int i = 0; i < totalLayananPerLayanan.length; i++) {
                    System.out.println("Layanan " + layananTersedia[i] + ": " + totalLayananPerLayanan[i]);
                }

                    break;
                case 5:
                    kondisi=false;
                    System.out.println("Terima kasih!");
                    
                    

                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
        }
    }}}

            
        





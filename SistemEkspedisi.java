import java.util.Scanner;
public class SistemEkspedisi{ 
public static void main(String[] args){
        String[] isi_barang = new String[400];
        
        Scanner ekspedisi = new Scanner(System.in);

        //Deklarasi
        String [][] dataEkspedisi = new String [100][10];
        String  pengirim, penerima, layanan, cari;
        int jumlah, jml=0, hari, l=0;
        char jawab;
        double biaya=0;
        long no_hp, no_hp_penerima;
        int maxPaket = 15;
        int no_resi, index;
        double [] berat = new double [20];
       
        //output
        System.out.println("----------------");
            
        while (true){
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
                        System.out.print("Masukkan No. Resi: ");
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
                                System.out.println("Biaya pengiriman: "+ biaya + " IDR");
                        System.out.println("Total Biaya: " +biaya);
                        System.out.print("Nama Penerima: ");
                        penerima = ekspedisi.next();
                        System.out.print("Masukkan nomor HP penerima: ");
                        no_hp_penerima = ekspedisi.nextLong();
                        System.out.println("Alamat tujuan yang tersedia :");
                        System.out.println("1. Surabaya");
                        System.out.println("2. Malang");
                        System.out.println("3. Blitar");
                        System.out.println("4. Jakarta");
                        System.out.println("5. Bali");
                        System.out.println("6. Bandung");
                        System.out.println("7. Solo");
                        System.out.print("Pilih alamat tujuan Anda: ");
                        int almtTuju = ekspedisi.nextInt();
                        ekspedisi.nextLine();
                        String alamatTujuan = "";
                        switch (almtTuju) {
                            case 1:
                                alamatTujuan = "Surabaya";
                                break;
                            case 2:
                                alamatTujuan = "Malang";
                                break;
                            case 3:
                                alamatTujuan = "Blitar";
                                break;
                            case 4:
                                alamatTujuan = "Jakarta";
                                break;
                            case 5:
                                alamatTujuan = "Bali";
                                break;
                            case 6:
                                alamatTujuan = "Bandung";
                                break;
                            case 7:
                                alamatTujuan = "Solo";
                                break;
                            default:
                                System.out.println("Pilihan tidak valid");
                        }
                        if (!alamatTujuan.isEmpty()) { // Periksa apakah alamatTujuan telah diisi
                            System.out.println("Data Anda Telah Diproses");
                            System.out.println("--------------------");
                            
                            dataEkspedisi[1][0] = no_resi+"";
                            dataEkspedisi[l][1] = pengirim;
                            dataEkspedisi[1][2] = no_hp+"";
                            dataEkspedisi[1][3] = isi_barang+"";
                            dataEkspedisi[l][4] = layanan;
                            dataEkspedisi[1][5] = biaya+"";
                            dataEkspedisi[l][6] = berat+"";
                            dataEkspedisi[l][7] = alamatTujuan;
                            dataEkspedisi[1][8] = penerima;
                            dataEkspedisi[1][9] = no_hp_penerima+"";

                        l++;
                        System.out.println("Data ekspedisi berhasil ditambahkan.");
                        }
                        break;
                    }}
                case 2:
                System.out.println("Data Ekspedisi:");
                for (int i = 0; i < l; i++) {
                    System.out.println("No Resi: " + dataEkspedisi[i][0]);
                    System.out.println("Pengirim: " + dataEkspedisi[i][1]);
                    System.out.println("No HP Pengirim: " + dataEkspedisi[i][2]);
                    System.out.println("Isi barang: " + dataEkspedisi[i][3]);
                    System.out.println("Layanan: " + dataEkspedisi[i][4]);
                    System.out.println("Biaya: " + dataEkspedisi[i][5]);
                    System.out.println("Berat Paket: " + dataEkspedisi[i][6] + " Kg");
                    System.out.println("Alamat Tujuan: " + dataEkspedisi[i][7]);
                    System.out.println("Nama Penerima: " + dataEkspedisi[i][8]);
                    System.out.println("No HP Penerima: " + dataEkspedisi[i][9]);
                    //System.out.println();
                }
                break;
                
                case 3:
                String[] dataAlmt = {"Surabaya", "Malang", "Blitar", "Jakarta","Bali", "Bandung", "Solo",};  
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
                    System.out.println("Terima kasih!");
                    System.exit(0);
                    

                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
        }
    }}}

            
        





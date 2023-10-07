import java.util.Scanner;

public class ekspedisi{
    public static void main(String[] args){
        Scanner ekspedisi = new Scanner(System.in);

        //Deklarasi
        String isi_barang, namaPenerima, alamatTujuan, pengirim, layanan;
        int no_resi, jumlah;
        double berat, biaya = 0;
        long no_hp;
        
        //output
        System.out.println("--Data Ekspedisi--");
        System.out.print("Masukkan No. Resi: ");
        no_resi = ekspedisi.nextInt();
        System.out.print("isi barang: ");
        isi_barang = ekspedisi.next();
        System.out.print("Jumlah barang: ");
        jumlah = ekspedisi.nextInt();
        System.out.print("Masukkan no HP customer: ");
        no_hp = ekspedisi.nextLong();
        System.out.print("Berat (kg): ");
        berat = ekspedisi.nextDouble();
        System.out.println("Jenis Layanan (Reguler/Kargo/Hemat/SameDay)");
        layanan = ekspedisi.next();

        if(layanan.equals("Reguler")){
            if(berat<=1.0){
                biaya = 5000;
            }else if(berat <= 5.0){
                biaya = 10000;
            }else if (berat <= 10.0){
                biaya = 15000;
            }else{
                biaya = 20000;
            }
        }else if (layanan.equals ("Kargo")){
            if(berat<=1.0){
                biaya = 13000;
            }else if(berat<=5.0){
                biaya = 18000;
            }else if(berat<=10){
                biaya = 25000;
            }else{
                biaya = 50000;
            }
        }else if (layanan.equals ("Hemat")){
            if(berat<=1.0){
                biaya = 3000;
            }else if(berat <=5.0){
                biaya = 6000;
            }else if(berat <= 10.0){
                biaya = 10000;
            }else{
                System.out.println("Anda tidak dapat menambahkan barang di atas 10kg");
            }
        }else if (layanan.equals ("SameDay")){
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
        System.out.println("Biaya pengiriman: Rp"+ biaya);
        System.out.print("Nama Penerima: ");
        namaPenerima = ekspedisi.next();
        System.out.print("Alamat Tujuan: ");
        alamatTujuan = ekspedisi.next();
        System.out.print("Nama Pengirim: ");
        pengirim = ekspedisi.next();
        System.out.println("Data Anda Telah Diproses");
        }
    }
}

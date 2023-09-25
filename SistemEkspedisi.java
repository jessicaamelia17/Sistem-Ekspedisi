import java.util.Scanner;
public class SistemEkspedisi{ 
public static void main(String[] args){
        Scanner ekspedisi = new Scanner(System.in);

        //Deklarasi
        String isi_barang, namaPenerima, alamatTujuan, pengirim;
        int no_resi, jumlah;
        double berat,biaya;
        double biaya1kg=13000; //tarif1kg
        double biayaFlat=200000; //tarifflat
        long no_hp;
        
        //output
        System.out.println("--Data Ekspedisi--");
        System.out.print("Masukkan No. Resi: ");
        no_resi = ekspedisi.nextInt();
        System.out.print("Isi barang: ");
        isi_barang = ekspedisi.next();
        System.out.print("Jumlah barang: ");
        jumlah = ekspedisi.nextInt();
        System.out.print("Masukkan No. HP customer: ");
        no_hp = ekspedisi.nextLong();
        System.out.print("Berat (kg): ");
        berat = ekspedisi.nextInt();
       
        if(berat<=20){
        biaya=berat*biaya1kg;
        }
        else{ biaya=biayaFlat;
        }
        System.out.println("Total Biaya: " +biaya);
        System.out.print("Nama Penerima: ");
        namaPenerima = ekspedisi.next();
        System.out.print("Alamat Tujuan: ");
        alamatTujuan = ekspedisi.next();
        System.out.print("Nama Pengirim: ");
        pengirim = ekspedisi.next();
        System.out.println("Data Anda Telah Diproses");



}


}

import java.util.*;
import java.text.*;
import java.io.*;
import java.sql.*;

public class gaun implements butik {
    //mengkoneksikan ke database
    Connection con;
    String url = "jdbc:mysql://localhost:3306/butik";//nama database nya toko

     Scanner input = new Scanner(System.in);

     //mendeklarasikan variable yang ada diperlukan
     int idGaun, telat;
     String namaGaun, desainer, denda;


    
    @Override
    public void IdGaun() {
        System.out.print("Input ID Gaun \t\t: ");
        idGaun = input.nextInt();
        input.nextLine();
    }

    @Override
    public void NamaGaun() {
        System.out.print("Input Nama Gaun\t\t: ");
        namaGaun = input.nextLine(); 
    }

    @Override
    public void Desainer() {
        System.out.print("Nama Desainer\t\t\t: ");
        desainer = input.nextLine(); 
    }

    @Override
    public void telat() {
        System.out.print("Keterlambatan (hari)\t: ");
        telat = input.nextInt();
        input.nextLine();
        
        
    }

    @Override
    public void denda() {
        int denda = 0;
        denda = telat * 100000;// perhitungan matematika
    }
    @Override
    public void Peminjam() {
    }

    //Menyimpan data varibel kedalam tabel "gaun"
    public void InsertDb1() throws SQLException{
        String sql = "INSERT INTO gaun (id_gaun,nama_gaun,desainer) VALUES ('"+idGaun+"', '"+namaGaun+"','"+desainer+"')";
        con = DriverManager.getConnection(url,"root","");
        Statement statement = con.createStatement();
        statement.execute(sql);   
        System.out.println("\nDATA SUCCESSFULY INPUT !!!"); 
    }

}//ini kurung paling atas yah

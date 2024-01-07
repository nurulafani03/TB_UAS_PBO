import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.zip.DataFormatException;
import java.util.InputMismatchException;
import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class peminjam extends gaun {
    //variabel yang dibutuhkan
    String peminjam;
    int id,id2;
    int x =1;

    @Override
    public void Peminjam() {
        System.out.print("Masukkan Nama Peminjam\t\t: ");
        peminjam = input.nextLine(); 
        System.out.print("Masukkan ID Peminjam\t\t: ");
        id = input.nextInt();
        input.nextLine();
    }
     //Method String Date
    public String tglPengembalian() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
      //Menyimpan data peminjam kedalam tabel "data_peminjam"
    public void InsertDB2() throws SQLException{
        String sql = "INSERT INTO data_peminjam (id_peminjam,nama,id_gaun,denda,tanggal_pengembalian) VALUES ('"+id+"','"+peminjam+"', '"+idGaun+"','"+denda+"','"+tglPengembalian()+"')";
        con = DriverManager.getConnection(url,"root","");
        Statement statement = con.createStatement();
        statement.execute(sql);
        //System.out.println("\nDATA BERHASIL DI INPUT !!!");    
    }
     //Mengambil data pada tabel "gaun" dan "data_peminjam"
    public void ShowAll() throws SQLException{

        String sql = "SELECT gaun.id_gaun, gaun.nama_gaun, gaun.desainer, data_peminjam.nama, data_peminjam.id_peminjam, data_peminjam.denda, data_peminjam.tanggal_pengembalian FROM gaun, data_peminjam  WHERE gaun.id_gaun = '"+idGaun+"' AND data_peminjam.id_gaun = '"+idGaun+"'";
        con = DriverManager.getConnection(url,"root","");
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);

        
        if (result.next()){
        System.out.println("\n\t\tINFO!!!!");
        System.out.print("Nama Peminjam    \t\t: ");
        System.out.println(result.getString("data_peminjam.nama"));
        System.out.print("Nomor ID Peminjam \t: ");
        System.out.println(result.getInt("data_peminjam.id_peminjam"));
        System.out.print("ID Gaun yang dikembalikan  \t: ");
        System.out.println(result.getInt("gaun.id_gaun"));
        System.out.print("Nama Gaun \t\t\t: "); 
        System.out.println(result.getString("gaun.nama_gaun"));
        System.out.print("Nama Desainer\t\t\t: ");
        System.out.println(result.getString("gaun.desainer"));
        System.out.print("Denda Keterlambatan \t\t: ");
        System.out.println(result.getString("data_peminjam.denda"));
        System.out.print("Tanggal Pengembalian \t\t: ");
        System.out.println(result.getDate("data_peminjam.tanggal_pengembalian"));
        }



}
}// kurung paling akhir

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.zip.DataFormatException;
import java.util.InputMismatchException;
import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class CRUD extends gaun{

    //koneksi database
    String url = "jdbc:mysql://localhost:3306/butik";
    Connection con;

    Scanner input = new Scanner(System.in);
    String peminjam;
    int id, id2;

    //Mengedit data yang sudah disimpan kedalam database
     public void Edit() throws SQLException{
        Show();

        //try
        try {
            System.out.print("\nMasukkan Id gaun yang akan di Ubah : ");
            id2 = 0;
            id2 = input.nextInt();
            input.nextLine();
            System.out.println("-----------------------------------------\n");

            String sql = "SELECT gaun.id_gaun, gaun.nama_gaun, gaun.desainer, data_peminjam.nama FROM gaun, data_peminjam  WHERE gaun.id_gaun = '"+id2+"' AND data_peminjam.id_gaun = '"+id2+"'";
            con = DriverManager.getConnection(url,"root","");
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);

             //Percabangan IF
            if (result.next()){
                System.out.print("Nama Peminjam ["+result.getString("data_peminjam.nama")+"]\t : ");
                peminjam = input.nextLine();

                System.out.print("Nama Gaun ["+result.getString("gaun.nama_gaun")+"]\t\t : ");
                namaGaun = input.nextLine();

                System.out.print("Nama Desainer ["+result.getString("gaun.desainer")+"]\t : ");
                desainer = input.nextLine();

                sql = "UPDATE gaun, data_peminjam SET gaun.nama_gaun ='"+namaGaun+"', gaun.desainer='"+desainer+"', data_peminjam.nama='"+peminjam+"' WHERE gaun.id_gaun='"+id2+"' AND data_peminjam.id_gaun='"+id2+"'";
                
                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Berhasil memperbaharui data ");
                }
            }
            statement.close();  

              //exception SQL      
        } catch (SQLException e) {
            System.err.println("Terjadi kesalahan dalam mengedit data");
            System.err.println(e.getMessage());
        }
    }
     //Menampilkan data yang sudah disimpan kedalam database
    public void Show() throws SQLException{
        int x = 1;
        String sql = "SELECT id_gaun, nama_gaun FROM gaun";
        con = DriverManager.getConnection(url,"root","");
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);

        //Perulangan While
        while(result.next()){
            System.out.print(x+". ");
            System.out.print("Id Gaun :");
            System.out.print(result.getString("id_gaun"));
            System.out.print("\t Nama Gaun :");
            System.out.println(result.getString("nama_gaun"));
            x++;
        }
    }
        //menghapus data yang sudah disimpan kedalam database
    public void Delete() throws SQLException{

        String text4 = "\nHapus Data Buku";
		System.out.println(text4.toUpperCase());
		
        //try
		try{
	        Show();
	        System.out.print("\nMasukan Id Gaun : ");
	        Integer Id2= Integer.parseInt(input.nextLine());
	        
            String sql = "DELETE FROM gaun WHERE id_gaun = "+Id2;
            
	        con = DriverManager.getConnection(url,"root","");
	        Statement statement = con.createStatement();

	        if(statement.executeUpdate(sql) > 0){
	            System.out.println("Berhasil menghapus gaun (Id "+Id2+")");
	        }
	   }
        //exception SQL
		catch(SQLException e){
	        System.out.println("Terjadi kesalahan dalam menghapus data");
	    }
        //exception inputan tidak sesuai
        catch(Exception e){
            System.out.println("masukan data yang benar");
        }
	}  

    
}
import java.util.InputMismatchException;
import com.mysql.cj.protocol.Resultset;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.ref.Cleaner;
import java.io.FileReader;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;
import java.sql.*;

public class main {
      //Koneksi database
    static String url = "jdbc:mysql://localhost:3306/butik";
    static Connection con;
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        System.out.println("\n====================================================");
        System.out.println("||  DRESS RETURNING PROGRAM      ||");
        System.out.println("====================================================");
        System.out.println("\n   Please log in first");
        System.out.println("---------------------------------------------------");
        Admin();
        
    }
     private static void menu() throws IOException {
        
        boolean lanjutkan = true;
        int pilihan;
        int x = 0;

        //Exception
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,"root","");
            //System.out.println("Database Terhubung");

            //Perulangan While
            while (lanjutkan){
                System.out.println("");
                System.out.println("=========================================");
                System.out.println("            PROGRAM MENU         ");
                System.out.println("=========================================");
                System.out.println("1. Pengembalian Gaun");
                System.out.println("2. Ganti Data Gaun");
                System.out.println("3. Cek List Pengembalian Gaun"); 
                System.out.println("4. Hapus Gaun Pinjaman");
                System.out.println("5. Keluar dari Program");
                System.out.println("=========================================");
                System.out.println("");
                System.out.print("Pilih Menu : ");
                pilihan = input.nextInt();
                System.out.println("-----------------------------------------\n");


                  //Percabangan Switch Case
            switch (pilihan){

                case 1 :
                    peminjam kembalikan = new peminjam();
                    kembalikan.Peminjam();
                    kembalikan.NamaGaun();
                    kembalikan.IdGaun();
                    kembalikan.Desainer();
                    kembalikan.telat();
                    kembalikan.denda();
                    kembalikan.tglPengembalian();
                    kembalikan.InsertDb1();
                    kembalikan.InsertDB2();
                    kembalikan.ShowAll();
                    
                    Console();
                    clean clear = new clean();
        
                    break;

                    case 2 : 
                    CRUD edit = new CRUD();
                    edit.Edit();

                    Console();
                    clean clear2 = new clean();
                    
                    break;

                    case 3 :
                    CRUD tampilkan = new CRUD();
                    tampilkan.Show();

                    Console();
                    clean clear3 = new clean();

                    break;
                
                case 4 : 
                    CRUD hapus = new CRUD();
                    hapus.Delete();
                    hapus.Show();

                    Console();
                    clean clear4 = new clean();

                    break;
                    case 5 :
                    lanjutkan = false;
                    System.out.println("THANK YOU FOR USING THIS PROGRAM");
                    DateFormat formatTanggal = new SimpleDateFormat("dd MMMM yyyy");
                    DateFormat formatJam = new SimpleDateFormat("HH:mm:ss");
                    Date tanggal = new Date();
                    System.out.println("==========================================");
                    System.out.println("= Was being run on : "+formatTanggal.format(tanggal)+" ");
                    System.out.println("= Closed time      : "+formatJam.format(tanggal)+" WIB      ");
                    System.out.println("==========================================");


                    Console();
                    break;

                      default :
                    System.out.println("MENU TIDAK TERSEDIA!!!!");
                    System.out.println("");
                    break;
                }
            }
        }catch(ClassNotFoundException ex) {
            System.err.println("Driver eror");
            System.exit(0);
        }
        catch(SQLException e){
            System.err.println("Connection Unsuccessful");
        }
    }

     //methode Admin
    private static void Admin() throws SQLException, IOException{

        //Membuat objek HashMap baru
        Map<String, String> Login = new HashMap<String, String>();

        //Mengambil data dari database butik pada tabel admin
       
        String inputUsername, inputPassword;
        String sql = "SELECT * FROM admin";
        boolean relogin = true;
        con = DriverManager.getConnection(url,"root","");
        Statement statement = con.createStatement();
		ResultSet result = statement.executeQuery(sql);

        //Perulangan While
        while (result.next())
        {
            //mengambil nilai di database dan menyimpannya ke dalam variable
            String username = result.getString("username");
            String password = result.getString("password");

            //input key dan value 
            Login.put(username, password);
        }

        //perulangan while
        while (relogin)
        {
            
            System.out.print("Username : ");
            inputUsername = input.nextLine();
            System.out.print("Password : ");
            inputPassword = input.nextLine();
            System.out.println("---------------------------------------------------");
            menu();//code baru

            //percabangan if
            if (Login.containsValue(inputUsername)==true) //method bawaan HashMap
            {
                //percabangan if
                if (Login.get(inputUsername).equals(inputPassword)) //method bawaan HashMap dan method string
                {
                    System.out.println("Log in successful");
                    Console();
                    clean clear5 = new clean();
                    menu();
                    relogin = false;
                }
                else
                {
                    relogin = true;
                      }
        }
    }
    statement.close();
    }

    //Memberikan inputan untuk ke sesi selanjutnya
    private static void Console() throws IOException{
        System.out.print("\nEnter a character to next: ");
        // Read the char
        char ch = (char) System.in.read();
    }



  
}//kurung terakhir
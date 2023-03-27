import java.sql.Connection;
import java.sql.Statement;

public class Runner {

    public static void main(String[] args) {

        //1. Adım: Driver'a kaydol
        //2. Adım: Datbase'e bağlan
        Connection connection = JdbcUtils.connectionToDataBase("localhost","techproed","postgres","Kuzey.123456");

        //3. Adım: Statement oluştur.
        Statement statement = JdbcUtils.createStatement();


        //4. Adim: Query calistir.
        //JdbcUtils.execute("Create Table students (name varchar(20), id int, address varchar(80))");

        JdbcUtils.createTable("def","classes varchar(20)","teacher_name varchar(20)","id int");

        //5. Adim: Baglanti ve Statement'i kapat.
        JdbcUtils.closeConnectionAndStatement();

    }


}

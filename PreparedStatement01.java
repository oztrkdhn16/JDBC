import java.sql.*;

public class PreparedStatement01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","Kuzey.123456");
        Statement st = con.createStatement();

        /*
        PreparedStatement interface, birden cok kez calistirilabilen onceden derlenmis bir SQL kodunu temsil eder.
        Parametrelendirilmis SQL sorgulari(query) ile calisir. Bu sorguyu 0 veya daha fazla parametre ile kullanabiliriz.
        */

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

        //1. ADIM: PreparedStatement query'sini olustur.
        String sql1 = "Update companies Set number_of_employees = ? Where company = ? ";

        //2. ADIM: PreparedStatement objesini olustur.
        PreparedStatement pst1 = con.prepareStatement(sql1);

        //3.ADIM: setInt(), setString(), ... methodlarini kullanarak soru isaretleri yerlerine deger atar.
        pst1.setInt(1, 9999);
        pst1.setString(2, "IBM");

        //4.ADIm
        int guncellenenSatirSayisi = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);

        String sql2 = "Select * From companies";
        ResultSet rs1 =  st.executeQuery(sql2);

        while (rs1.next()){
            System.out.println(rs1.getInt(1) + "-" + rs1.getString(2)+"-"+rs1.getInt(3));
        }

        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.
        pst1.setInt(1, 5555);
        pst1.setString(2, "GOOGLE");

        int guncelenenStairSayisi1 = pst1.executeUpdate();
        System.out.println("guncelenenStairSayisi = " + guncelenenStairSayisi1);

        ResultSet rs2 = st.executeQuery(sql2);
        while (rs2.next()){
            System.out.println(rs2.getInt(1)+"-"+rs2.getString(2)+ "-"+ rs2.getInt(3));
        }
        con.close();
        st.close();
        rs1.close();
        rs2.close();
        pst1.close();



    }

}

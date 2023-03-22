import java.sql.*;

public class ExecuteQuery02 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","Kuzey.123456");
        Statement st = con.createStatement();

        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan
        //          company ve number_of_employees değerlerini çağırın.

        String sql1 = "SELECT company, number_of_employees from companies Order By number_of_employees DESC offset 1 Fetch Next 1 Row Only";

        ResultSet resultSet1 = st.executeQuery(sql1);

        while (resultSet1.next()){

            System.out.println(resultSet1.getString("company") + "--" + resultSet1.getInt("number_of_employees"));
        //  System.out.println(resultSet1.getString(1) + "--" + resultSet1.getInt(2));

        }

        //2. Yol: Subquery kullanarak.
        String sql2 = "SELECT company, number_of_employees\n" +
                "FROM companies\n" +
                "WHERE number_of_employees = (SELECT MAX(number_of_employees)\n" +
                "                            FROM companies\n" +
                "                            WHERE number_of_employees < (SELECT MAX(number_of_employees)\n" +
                "                                                         FROM companies))";

        ResultSet resultSet2 = st.executeQuery(sql2);

        while (resultSet2.next()){

            System.out.println(resultSet2.getString("company") + "--" + resultSet2.getInt("number_of_employees"));

        }

        con.close();
        st.close();
        resultSet1.close();
        resultSet2.close();

    }

}

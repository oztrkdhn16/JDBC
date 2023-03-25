import java.sql.*;

public class JdbcUtils {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet rst1;

    //1. Adım: Driver'a kaydol
    //2. Adım: Datbase'e bağlan
    public static Connection connectionToDataBase(String hostName, String dbName, String username, String password){

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://"+hostName+":5432/"+dbName,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(connection!=null){
            System.out.println("Connection Success");
        }else {
            System.out.println("Connection Fail");
        }

        return connection;

    }

    //3. Adım: Statement oluştur.
    public static Statement createStatement(){

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return statement;

    }

    //4. Adim: Query calistir.
    public static boolean execute(String sql){

        boolean isExecute;

        try {
            isExecute = statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return isExecute;

    }


    //ExecuteQuery
    public static ResultSet executeQuery(String sql){

        try {
            rst1 = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rst1;

    }

    //ExecuteUpdate
    public static int executeUpdate(String sql ){
        int updateLine;
        try {
            updateLine = statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return updateLine;

    }

    //5. Adim: Baglanti ve Statement'i kapat.
    public static void closeConnectionAndStatement(){

        try {
            connection.close();
            statement.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        try {
            if(connection.isClosed()&&statement.isClosed()){
                System.out.println("Connection and Statement Closed");
            }else {
                System.out.println("Connection and Statement NOT Closed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Table olusturan method
    public static void createTable(String tableName, String ... columnName_dataType){

        StringBuilder columnName_dataValue = new StringBuilder("");


        for(String w: columnName_dataType){

            columnName_dataValue.append(w).append(",");

        }

        columnName_dataValue.deleteCharAt(columnName_dataValue.length()-1);
        //System.out.println(columnName_dataValue);


        try {
            statement.execute("Create Table "+tableName+"("+columnName_dataValue+")");
            System.out.println("Table "+tableName+" successfully created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }









}

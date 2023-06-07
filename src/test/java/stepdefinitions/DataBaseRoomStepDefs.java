package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pojos.RoomPojo;

import java.sql.*;

import static stepdefinitions.MedunnaRoomStepDefs.roomNumberFaker;

public class DataBaseRoomStepDefs {

    Connection connection;
    Statement statement;
    @Given("connect to DataBase")
    public void connect_to_data_base() throws SQLException {
        //1. Adım : Connection oluştur
        connection = DriverManager.getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2","select_user","Medunna_pass_@6");

        //2.Adım : Statement oluştur
        statement = connection.createStatement();

    }
    @Then("read room and validate")
    public void read_room_and_validate() throws SQLException {

        //3.Adım : query çalıştır
        String sqlQuery = "SELECT * FROM room WHERE room_number = "+roomNumberFaker;

        ResultSet resultSet = statement.executeQuery(sqlQuery);//Query ile çağrılan data resultSet içerisinde yer alacak.
        resultSet.next();//next() methodu pointer'ı sıradaki satıra alır.

        RoomPojo expectedData = new RoomPojo(roomNumberFaker,"SUITE",true,123.00,"Yaniyosun Fuat Abi");

        String roomType = resultSet.getString("room_type");
        Boolean status = resultSet.getBoolean("status");
        Double price = resultSet.getDouble("price");
        String description = resultSet.getString("description");
        Integer roomNumber = resultSet.getInt("room_number");

        Assert.assertEquals(expectedData.getRoomType(),roomType);
        Assert.assertEquals(expectedData.getStatus(),status);
        Assert.assertEquals(expectedData.getPrice(),price);
        Assert.assertEquals(expectedData.getDescription(),description);
        Assert.assertEquals(expectedData.getRoomNumber(),roomNumber);

    }
}

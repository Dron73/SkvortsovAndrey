package haos;

import java.sql.*;

public class Main {

    static DBWorker worker = new DBWorker();

    public static void result() {
	String query = "select * from book";

	try {
	    Statement statement = worker.getConnection().createStatement();
	    ResultSet resultSet = statement.executeQuery(query);

	    while (resultSet.next()) {
		UserName userName = new UserName();
		userName.setName(resultSet.getString("name"));
		userName.setEvaluation(resultSet.getString("evaluation"));
		userName.setComment(resultSet.getString("comment"));
		userName.setId(resultSet.getInt("id"));
	    }

	    statement.close();
	    resultSet.close();

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public static void addPost(String name, String evaluation, String comment)
	    throws SQLException, ClassNotFoundException {
	try {
	    String add = "INSERT INTO book (name, evaluation, comment) VALUES (?, ?, ?)";
	    PreparedStatement ps = worker.getConnection().prepareStatement(add);
	    ps.setString(1, name);
	    ps.setString(2, evaluation);
	    ps.setString(3, comment);
	    ps.execute();
	    ps.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}

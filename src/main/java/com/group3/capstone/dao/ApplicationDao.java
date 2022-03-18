package com.group3.capstone.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.group3.capstone.beans.Bulletin;
import com.group3.capstone.beans.Post;
import com.group3.capstone.services.ApplicationService;
import com.group3.capstone.user.User;
import com.group3.capstone.usersession.UserSession;

public class ApplicationDao implements ApplicationService {
	
	private Connection connection = null;

	// Make connection upon DAO instantiation.
    public ApplicationDao() {
    	this.connection = DBConnection.getConnectionToDatabase();;

    }

	@Override
	public Post readPost(String id) {
		
		return null;
	
	}

	@Override
	public void writePost(Post post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePost(String id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Bulletin getBulletin(UUID bulletinId) {
		Bulletin retrievedBulletin = null;
		
		try {
			//Search for bulletin
			String sql = "select * from bulletin where bulletinId = \'" + bulletinId.toString() +"\';";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			//execute query
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				//Retrieve bulletin if exists.
				retrievedBulletin = new Bulletin(UUID.fromString(set.getString("bulletinId")), set.getString("bulletinName"));
				String test = retrievedBulletin.getBulletinName();
				System.out.println(test +"TTtttt");
			}
		}catch (SQLException exception) {
			exception.printStackTrace();	
		}
		
		return retrievedBulletin;
	}

	//view a single bulletins registry
	@Override
	public List<Post> getBulletinPosts(UUID bulletinId) {
		
		List<Post> postRegistry = new ArrayList<Post>();
		
		try {
			//write select query
			String sql = "select * from post where bulletinId = \'" + bulletinId.toString() +"\'"
					+ "order by postDate desc;";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			//execute query
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				//Add new post object if exists.
				postRegistry.add(
						new Post(UUID.fromString(set.getString("postId")),
								set.getString("title"),set.getString("description"),set.getString("url"),
								Date.valueOf(set.getString("postDate")),
								UUID.fromString(set.getString("bulletinId")),
								UUID.fromString(set.getString("authorId"))
								));
			}
		}catch (SQLException exception) {
			exception.printStackTrace();	
		}
		
		return postRegistry;
	}	
	
	//all CRUD operations for USER table
	@Override
	public void createUser(User user) {
		try {
			//write insert query for new user
			String sql = "INSERT INTO user (userId, userName, password, firstName, lastName, email, role) values (?,?,?,?,?,?,?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getUserID().toString());
			statement.setString(2, user.getUserName() );
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getFirstName());
			statement.setString(5, user.getLastName());
			statement.setString(6, user.getEmail());
			statement.setString(7, user.getRole());
			
			statement.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override	
	public User getUser(UUID userId) {
	User retrievedUser = null;
	String sql = "SELECT * FROM user WHERE userId= '"+userId.toString()+"';";
	try {
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet set = statement.executeQuery();
		if (set.next()) {
			retrievedUser = new User(set.getString("userId"), set.getString("firstName"), set.getString("lastName"), set.getString("userName"), set.getString("email"), set.getString("password"));
		} else {			
			retrievedUser = new User("NullFirsName", "NullLastName", "NullUserName", "NullEmail", "NullPassword");
		}

	} catch (SQLException e) {
		e.printStackTrace();
	}
	return retrievedUser;
	}
	
	public User getUser(String userName) {
	User retrievedUser = null;
	String sql = "SELECT * FROM user WHERE username= '"+userName+"';";
	try {
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet set = statement.executeQuery();
		if (set.next()) {
			retrievedUser = new User(set.getString("userId"), set.getString("firstName"),set.getString("lastName"),set.getString("userName"), set.getString("email"), set.getString("password"));
		} else {			
			retrievedUser = new User("NullFirsName", "NullLastName", "NullUserName", "NullEmail", "NullPassword");
		}

		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return retrievedUser;
	}
	
	//method to verify if user is in database of registered users
	@Override
	public boolean verifyUser(UUID userId) {
	boolean match = false;
	String sql = "SELECT * FROM user WHERE userId = '"+userId.toString()+"';";
	try {
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet set = statement.executeQuery();
		if (set.next()) {
			match = true;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return match ;
	}

	public boolean verifyUserEmail(String email) {
	boolean match = false;
	String sql = "SELECT * FROM user WHERE email=?;";
	try {
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, email);
		
		ResultSet set = statement.executeQuery();
		if (set.next()) {
			match = true;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return match ;
	}
	
	public boolean verifyUserName(String userName) {
	boolean match = false;
	String sql = "SELECT * FROM user WHERE username=?;";
	try {
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, userName);
		
		ResultSet set = statement.executeQuery();
		if (set.next()) {
			match = true;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return match ;
	}

	public boolean verifyUserNamePassword(String userName, String password) {
	boolean match = false;
	String sql = "SELECT * FROM user WHERE username=? AND password=?;";
	try {
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, userName);
		statement.setString(2, password);
		
		ResultSet set = statement.executeQuery();
		if (set.next()) {
			match = true;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return match ;
	}
	
	@Override
	public boolean updateUser(User user) {
		boolean updateSuccess = false;
		
		
		
		return updateSuccess;
	}
	
	@Override
	public void createSession(UserSession session) {
		try {
			//write insert query for new session
			String sql = "INSERT INTO session (sessionId, userId) values (?,?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, session.getSessionId().toString());
			statement.setString(2, session.getUser().getUserID().toString());
			
			statement.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public UserSession getSession(UUID sessionId) {
		UserSession retrievedSession = null;
		String sql = "SELECT * FROM session WHERE sessionId= '"+sessionId.toString()+"';";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet set = statement.executeQuery();
			
			//Return a UserSession either way - if session didn't exist, we can return a session with a default constructed User.
			retrievedSession = new UserSession();
			if (set.next()) {
				retrievedSession.setSessionId(UUID.fromString(set.getString("sessionId")));
				User retrievedUser = this.getUser(UUID.fromString(set.getString("userId")));
				retrievedSession.setUser(retrievedUser);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrievedSession;
	}
	
	@Override
	public boolean verifySession(UUID sessionId) {
	boolean match = false;
	String sql = "SELECT * FROM session WHERE sessionId = '"+sessionId.toString()+"';";
	try {
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet set = statement.executeQuery();
		if (set.next()) {
			match = true;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return match ;
	}
	
}

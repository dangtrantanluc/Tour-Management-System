package model;

import java.util.HashSet;
import java.util.Set;

public class UserManager {
	private Set<String> userIDs;
	private Set<User> users;

	public UserManager() {
		this.userIDs = new HashSet<>();
		this.users = new HashSet<>();
	}

	// Thêm người dùng mới
	public boolean addUser(User user) {
		if (this.userIDs.contains(user.getUserId())) {
			System.out.println("Error: User ID already exists!");
			return false;
		}
		this.userIDs.add(user.getUserId());
		this.users.add(user);
		return true;
	}

	public boolean deleteUser(String userId) {
		for (User user : users) {
			if (user.getUserId().equals(userId)) {
				users.remove(user);
				userIDs.remove(userId);
				System.out.println("User deleted successfully!");
				return true;
			}
		}
		System.out.println("Error: User ID not found!");
		return false;
	}

	public User findUser(String userId) {
		for (User user : users) {
			if (user.getUserId().equals(userId)) {
				return user;
			}
		}
		System.out.println("Error: User ID not found!");
		return null;
	}

	// Đăng nhập
	public boolean login(String userId, String password) {
		for (User user : users) {
			if (user.getUserId().equals(userId) && user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

}

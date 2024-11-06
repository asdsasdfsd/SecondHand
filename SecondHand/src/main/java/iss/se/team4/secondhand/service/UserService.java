package iss.se.team4.secondhand.service;

import iss.se.team4.secondhand.common.Result;
import iss.se.team4.secondhand.common.dto.RegisterDto;
import iss.se.team4.secondhand.model.User;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Service interface for managing {@link User} entities.
 */
public interface UserService {

    /**
     * Adds a new user.
     *
     * @param user the user to be added
     */
    void add(User user);

    /**
     * Deletes a user by its ID.
     *
     * @param id the ID of the user to be deleted
     */
    void deleteById(Integer id);

    /**
     * Deletes a batch of users by their IDs.
     *
     * @param ids the list of IDs of users to be deleted
     */
    void deleteBatch(List<Integer> ids);

    /**
     * Updates an existing user by its ID.
     *
     * @param user the user with updated information
     */
    void updateById(User user);

    /**
     * Retrieves a user by its ID.
     *
     * @param id the ID of the user to be retrieved
     * @return the user with the specified ID
     */
    User selectById(Integer id);

    /**
     * Retrieves all users.
     *
     * @return a list of all users
     */
    List<User> selectAll();

    /**
     * Retrieves a paginated list of users.
     *
     * @param pageNum the page number (starting from 1)
     * @param pageSize the number of users per page
     * @return a paginated list of users
     */
    Page<User> selectPage(Integer pageNum, Integer pageSize);

    // Placeholder methods for login, register, and update password functionalities.

    // others
    // User login(String username, String password);
    // void register(User user);
    // void updatePassword(Integer id, String newPassword);
    /**
     * user register
     *
     * @param registerDto the user to be registered
     */
    Result register(RegisterDto registerDto);

    /**
     * user login
     *
     * @param username
     * @param password
     */
    Result login(String username, String password);

    /**
     * get user's security question
     *
     * @param username
     */
    Result getUserSecurityQuestion(String username);

    Result checkSecurityQuestion(String username, String question, String answer);

    Result changePassword(String username, String newPassword);

    Result getUserAddress(String username);

    Result setUserAddress(String username, String address1, String address2);

    Result getUserByUsername(String username);


}

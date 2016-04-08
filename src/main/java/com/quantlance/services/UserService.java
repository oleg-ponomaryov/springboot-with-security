package com.quantlance.services;

import java.util.Collection;

import com.quantlance.domain.User;
import com.quantlance.forms.UserCreateForm;

public interface UserService {

    User getUserById(long id);

    Collection<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

	User update(UserCreateForm form);

	void delete(Long id);

}
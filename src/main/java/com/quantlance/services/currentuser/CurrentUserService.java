package com.quantlance.services.currentuser;

import com.quantlance.domain.CurrentUser;

public interface CurrentUserService {
	boolean canAccessUser(CurrentUser currentUser, Long userId);
}

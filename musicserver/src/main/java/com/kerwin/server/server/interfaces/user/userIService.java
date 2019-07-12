package com.kerwin.server.server.interfaces.user;



import com.kerwin.server.utils.requestAPI;

public interface userIService {


    requestAPI findUser(String username, String password);


    requestAPI addUser(String username, String password, String email);

    requestAPI confirmUser(String username);
}

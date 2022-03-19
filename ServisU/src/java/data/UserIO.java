/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.*;
import java.util.*;
import models.User;

/**
 *
 * @author daniel
 */
public class UserIO {
    
    //Returns User object based on username in specified file
    public static User getUser(String filepath, String uNameInput) {
        //read file
        //if usernames match, return User object
        //else, return null
        try {
            File file = new File(filepath);
            BufferedReader in = new BufferedReader(
                                new FileReader(file));

            String line = in.readLine();
            while (line != null) {
//                Each line in file is read, data separated by comma
                StringTokenizer t = new StringTokenizer(line, ",");
                String uName = t.nextToken();
                
//              check if username on file matches username from input
                if (uName.equalsIgnoreCase(uNameInput)) {
                    String pWord = t.nextToken();
                    User user = new User();
                    user.setUsername(uName);
                    user.setPassword(pWord);
                    in.close();
                    return user;
                }
//                Repeat for next line until end of file
                line = in.readLine();
            }
            in.close();
            return null;
        } catch (IOException e) {
            System.err.println(e);
            return null;
        }
    }
    
}

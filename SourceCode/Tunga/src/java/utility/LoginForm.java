/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.prefs.Preferences;

/**
 *
 * @author notte
 */
public class LoginForm extends DataProcess {

    public boolean login(String u, String p) {
        Preferences login = this.readConfig("login");
        return (u.equals(login.get("username", null)) && p.equals(login.get("password", null)));
    }
}

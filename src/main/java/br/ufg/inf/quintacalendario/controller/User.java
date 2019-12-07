package br.ufg.inf.quintacalendario.controller;

/**
 * User entity
 *
 * @author Hyago Souza
 */
public class User {
    private String name;
    private String password;

    /**
     * Returns username attributed
     *
     * @return username
     */
    public String getName() {
        return name;
    }

    /**
     * Assigns username received in parameters
     *
     * @param name username to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns password attributed
     *
     * @return user password
     * @see String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Assigns password received in parameters
     *
     * @param password password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

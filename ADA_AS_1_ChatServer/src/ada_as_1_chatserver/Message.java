/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada_as_1_chatserver;

import java.io.Serializable;

/**
 *
 * @author Bedi
 */
public abstract class Message implements Serializable{
    public String message;
        
    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }    
    
    @Override
    public String toString() {
        return  message;
    }
}

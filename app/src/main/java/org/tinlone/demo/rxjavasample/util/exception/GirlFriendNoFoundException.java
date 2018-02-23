package org.tinlone.demo.rxjavasample.util.exception;

/**
 * Created by Administrator on 2017/7/19 0019.
 */

public class GirlFriendNoFoundException extends Exception {

    public GirlFriendNoFoundException() {
        super();
    }

    public GirlFriendNoFoundException(String message) {
        super(message);
    }

    public GirlFriendNoFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GirlFriendNoFoundException(Throwable cause) {
        super(cause);
    }

}

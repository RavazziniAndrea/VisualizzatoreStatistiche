package Exceptions;

import java.io.IOException;

public class LetturaConfigException extends IOException {
    public LetturaConfigException(){
        super();
    }
    public LetturaConfigException(String msg){
        super(msg);
    }
    public LetturaConfigException(Throwable t){
        super(t);
    }

    public LetturaConfigException(String msg, Throwable t){
        super(msg, t);
    }
}

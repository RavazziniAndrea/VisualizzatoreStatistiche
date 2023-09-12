package Exceptions;

import java.io.IOException;

public class CampiNonUguali  extends Exception {
    public CampiNonUguali(){
        super();
    }
    public CampiNonUguali(String msg){
        super(msg);
    }
    public CampiNonUguali(Throwable t){
        super(t);
    }

    public CampiNonUguali(String msg, Throwable t){
        super(msg, t);
    }
}

package com.gimenez.danielgga.dihi;

/**
 * Created by j3sus on 13/03/2017.
 */

public class product {

    private String invitacion;

    public product(){
        super();
    }



    public product(String invitacion) {
        super();
        this.invitacion = invitacion;

    }

    @Override
    public String toString() {
        return this.invitacion;
    }


}

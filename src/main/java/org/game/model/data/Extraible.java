
package org.game.model.data;

import java.util.ArrayList;

//Interfaz para extraer información de un objeto
public interface Extraible {
    ArrayList<String> getInfo();
    Extraible getObj(ArrayList<String> info);
    void validar() throws ValidacionException;
    void edit(Extraible e)throws ValidacionException;
}

package fiuba.algo3.algochess.model;

import java.util.HashMap;
import java.util.Map;

public class ParserObjeto {
    Map<String, Object> estado;

    public ParserObjeto() {
        estado = new HashMap<>();
    }

    public void put(String clave, Object valor) {
        estado.put(clave, valor);
    }

    public Object get(String clave) {
        return estado.get(clave);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        estado.forEach((clave, valor) -> {
            if (valor != null) {
                sb.append(clave);
                sb.append(": ");
                sb.append(valor.toString());
                sb.append("\n");
            }
        });
        return sb.toString();
    }
}

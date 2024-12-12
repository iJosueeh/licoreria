/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios.paises;

import java.util.HashMap;
import java.util.Map;

public class CodigoPais {

    private static final Map<String, String> CODIGOS_PAIS = new HashMap<>();

    static {
        CODIGOS_PAIS.put("Argentina", "+54");
        CODIGOS_PAIS.put("Bolivia", "+591");
        CODIGOS_PAIS.put("Brasil", "+55");
        CODIGOS_PAIS.put("Chile", "+56");
        CODIGOS_PAIS.put("Colombia", "+57");
        CODIGOS_PAIS.put("Ecuador", "+593");
        CODIGOS_PAIS.put("España", "+34");
        CODIGOS_PAIS.put("Mexico", "+52");
        CODIGOS_PAIS.put("Peru", "+51");
        CODIGOS_PAIS.put("Uruguay", "+598");
        CODIGOS_PAIS.put("Venezuela", "+58");
        CODIGOS_PAIS.put("Estados Unidos", "+1");
    }

    public static String obtenerCodigo(String pais) {
        if (pais == null || pais.isBlank()) {
            return "No debe haber un valor vacio.";
        }

        String codigo = CODIGOS_PAIS.get(pais);

        if (codigo != null) {
            return codigo;
        } else {
            return "No se encontro el codigo telefonico";
        }

    }

}

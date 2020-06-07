package br.com.massariol.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.awt.*;

public class GeradorSenha {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("massariolMaster"));
        System.out.println(encoder.encode("empresa"));
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for(String font:e.getAvailableFontFamilyNames()) {
            System.out.println(font);
        }
    }
}

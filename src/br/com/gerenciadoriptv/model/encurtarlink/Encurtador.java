package br.com.gerenciadoriptv.model.encurtarlink;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Encurtador {

    public static void linkCurto(String nome, String telefone, String vencimento) {        
        try {
            try {
                java.awt.Desktop.getDesktop().browse(new java.net.URI("https://api.whatsapp.com/send?phone="+telefone+"&text=Ol%C3%A1%20"+nome+"%2C%20gostaria%20de%20lembrar%20o%20vencimento%20do%20seu%20iptv%20dia%20"+vencimento+".%20Para%20renovar%2C%20basta%20me%20avisar%20por%20aqui%20mesmo%20que%20eu%20irei%20atualizar%20o%20seu%20usu%C3%A1rio%20!"));
            } catch (URISyntaxException ex) {
                Logger.getLogger(Encurtador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(Encurtador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

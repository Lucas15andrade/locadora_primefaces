/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import com.sun.javafx.logging.PulseLogger;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.CestaLocadora;
import model.Filme;

/**
 *
 * @author lucas
 */
@ManagedBean
@ApplicationScoped
public class Locadora {

    List<CestaLocadora> cestaLocadora;
    List<Filme> listaFilme;
    Filme filme;
    CestaLocadora locadora;
    Double precoTotal = 0.0;

    public Locadora() {

        //Calendar c = Calendar.getInstance(Locale.ITALY)
        //SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        //formato.
        Date data1 = new GregorianCalendar(2018, Calendar.JANUARY, 20).getTime();
        Date data2 = new GregorianCalendar(2018, Calendar.JULY, 15).getTime();
        Date data3 = new GregorianCalendar(2018, Calendar.MARCH, 10).getTime();
        //formato.format(data1);
        Filme filme1 = new Filme("Vingadores", data1, 5, "Filme da Marvel", 10);
        Filme filme2 = new Filme("Tadszão", data2, 5, "Filme do TADS-EAJ", 5);
        Filme filme3 = new Filme("Homem de Ferro", data3, 4, "Filme do Homem de Ferro com Tony Stark", 5);

        filme = new Filme();

        listaFilme = new ArrayList<>();
        listaFilme.add(filme1);
        listaFilme.add(filme2);
        listaFilme.add(filme3);

        cestaLocadora = new ArrayList<>();

    }

    public void cadastrarFilme() {
        //filme.
        this.listaFilme.add(filme);
        addMessage("Filme cadastrado com sucesso! :)");
        filme = new Filme();
    }

    public void finalizarCesta(){
        for (CestaLocadora cl : cestaLocadora) {
            
        }
        cestaLocadora.clear();
        precoTotal = 0.0;
    }
    
    public void cancelar(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(Locadora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void removerCesta(CestaLocadora loc) {

        int quant;
        if (loc.getQuantidade() > 0) {
            quant = loc.getQuantidade();
            quant--;
            if (quant == 0) {
                cestaLocadora.remove(loc);
                System.out.println("remover quant = 0");
                precoTotal -= 2.0;

            } else {
                loc.setQuantidade(quant);
                System.out.println("removeu no último");
                precoTotal -= 2.0;
            }

        }

        for (Filme lf : listaFilme) {
            if (lf.getIdFilme() == loc.getFilme().getIdFilme()) {
                int qtdFilme = lf.getQuantidade();
                qtdFilme++;
                lf.setQuantidade(qtdFilme);
            }
        }

        addMessage("Filme removido!");

    }

    public Double calcularPreco() {
        Double preco = 0.0;

        for (CestaLocadora cl : cestaLocadora) {
            preco += cl.getFilme().getPreco();
        }

        return preco;
    }

    public void adicionarCesta(Filme filme) {
        CestaLocadora cestinha = null;
        //CestaLocadora cesta = null;
        int quantidade;
        for (CestaLocadora cl : cestaLocadora) {
            if (cl.getFilme().getIdFilme() == filme.getIdFilme()) {
                cestinha = cl;
                break;
            }
        }

        if (cestinha != null) {
            quantidade = cestinha.getQuantidade();
            quantidade++;
            this.cestaLocadora.remove(cestinha);
            locadora = new CestaLocadora(filme, quantidade);
            this.cestaLocadora.add(locadora);
        } else {
            locadora = new CestaLocadora(filme, 1);
            this.cestaLocadora.add(locadora);
        }
        precoTotal += 2.0;
        filme.decrementaQuantidade();
        addMessage("Adicionado na cesta!");
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<CestaLocadora> getCestaLocadora() {
        return cestaLocadora;
    }

    public void setCestaLocadora(List<CestaLocadora> cestaLocadora) {
        this.cestaLocadora = cestaLocadora;
    }

    public List<Filme> getListaFilme() {
        return listaFilme;
    }

    public void setListaFilme(List<Filme> listaFilme) {
        this.listaFilme = listaFilme;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

}

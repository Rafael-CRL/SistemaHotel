/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel.controller;

/**
 *
 * @author LabLa
 */
import sistemahotel.dao.HospedeDAO;
import sistemahotel.model.Hospede;
import java.sql.SQLException;
import sistemahotel.dao.ReservaDAO;


public class HospedeController {
    private HospedeDAO hospedeDAO;
    
    public HospedeController(){
        this.hospedeDAO = new HospedeDAO();
    }
    
    public Hospede buscarPorCPF(String cpf){
        try {
            return hospedeDAO.buscarPorCPF(cpf);
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao buscar hóspede por CPF", e);
        }
    }
    }



package reto5.controller;

import java.sql.SQLException;

import reto5.model.dao.ListaLideresDao;
import reto5.model.dao.ComprasProveedorDao;
import reto5.model.dao.ProyectosDao;
import reto5.model.vo.*;
import java.util.List;


public class ReportesController {
    private ProyectosDao proyectosDao;
    private ComprasProveedorDao comprasProveedorDao;
    private ListaLideresDao listaLideresDao;

    public ReportesController(){
        proyectosDao = new ProyectosDao();
        comprasProveedorDao = new ComprasProveedorDao();
        listaLideresDao = new ListaLideresDao();
    }
    public List<ProyectosVo> listarProyectos() throws SQLException{
        return proyectosDao.listar();
    }
    public List<ComprasProveedorVo> listarProveedores() throws SQLException{
        return comprasProveedorDao.listar();
    }

    public List<ListaLideresVo> listarLideres() throws SQLException{
        return listaLideresDao.listar();
    }
}
package jl.conferenciasapp.daos;

import jl.conferenciasapp.interfaces.ConferenciasInt;
import jl.conferenciasapp.models.Ponente;
import jl.conferenciasapp.utils.DbConnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PonenteDAO extends BaseDAO implements ConferenciasInt<Ponente> {
    public PonenteDAO() { }

    @Override
    public void update(Ponente p) throws SQLException, IOException {
        String sql = "UPDATE ponente SET nombre = ?, apellido1 = ?, apellido2 = ?, especialidad = ? WHERE codigo = ?";
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql)) {
            ps.setString(5, p.getCodigo());
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido1());
            ps.setString(3, p.getApellido2());
            ps.setString(4, p.getEspecialidad());
            ps.executeUpdate();
        }
    }

    @Override
    public void insert(Ponente p) throws SQLException, IOException {
        String sql = "INSERT INTO ponente(codigo, nombre, apellido1, apellido2, especialidad) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getApellido1());
            ps.setString(4, p.getApellido2());
            ps.setString(5, p.getEspecialidad());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(String codigo) throws SQLException, IOException {
        String sql = "DELETE FROM ponente WHERE codigo = ?";
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.executeUpdate();
        }
    }

    private Ponente getPonente(ResultSet rs) throws SQLException {
        Ponente p;
        p = new Ponente();
        p.setCodigo(rs.getString("codigo").trim());
        p.setNombre(rs.getString("nombre").trim());
        p.setApellido1(rs.getString("apellido1").trim());
        // "apellido2" puede tomar valores NULL
        p.setApellido2(getStringOrNull(rs, "apellido2"));
        // "especialidad" puede tomar valores NULL
        p.setEspecialidad(getStringOrNull(rs, "especialidad"));
        return p;
    }

    @Override
    public List<Ponente> getAll(String filter) throws SQLException, IOException {
        List<Ponente> lista = new ArrayList<>();
        Ponente p;
        String sql = "SELECT * FROM ponente";

        if (!filter.isEmpty())
            sql = "SELECT * FROM ponente WHERE codigo LIKE ? OR nombre LIKE ? OR apellido1 LIKE ? OR apellido2 LIKE ? OR especialidad LIKE ?";

        ResultSet rs;
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql)) {
            if (!filter.isEmpty()) {
                for (int i = 1; i <= 5; i++)
                    ps.setString(i, "%" + filter + "%");
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                p = getPonente(rs);
                lista.add(p);
            }
        }
        return lista;
    }

    @Override
    public Ponente findByCodigo(String codigo) throws SQLException, IOException {
        Ponente p;

        String sql = "SELECT * FROM ponente WHERE codigo = ?";
        ResultSet rs;
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            rs.next();
            p = getPonente(rs);
        }

        return p;
    }
}

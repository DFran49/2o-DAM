package jl.conferenciasapp.daos;

import jl.conferenciasapp.interfaces.ConferenciasInt;
import jl.conferenciasapp.models.Sala;
import jl.conferenciasapp.utils.DbConnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO extends BaseDAO implements ConferenciasInt<Sala> {
    public SalaDAO() { }

    @Override
    public void update(Sala s) throws SQLException, IOException {
        String sql = "UPDATE sala SET capacidad = ? WHERE nombre = ?";
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql)) {
            ps.setString(2, s.getNombre());
            ps.setInt(1, s.getCapacidad());
            ps.executeUpdate();
        }
    }

    @Override
    public void insert(Sala s) throws SQLException, IOException {
        String sql = "INSERT INTO sala(nombre, capacidad) VALUES (?, ?)";
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, s.getNombre());
            ps.setInt(2, s.getCapacidad());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(String nombre) throws SQLException, IOException {
        String sql = "DELETE FROM sala WHERE nombre = ?";
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.executeUpdate();
        }
    }

    private Sala getSala(ResultSet rs) throws SQLException {
        Sala s;
        s = new Sala();
        s.setNombre(rs.getString("nombre").trim());
        s.setCapacidad(rs.getInt("capacidad"));
        return s;
    }

    @Override
    public List<Sala> getAll(String filter) throws SQLException, IOException {
        List<Sala> lista = new ArrayList<>();
        Sala s;
        String sql = "SELECT * FROM sala ORDER BY nombre";

        if (!filter.isEmpty())
            sql = "SELECT * FROM sala WHERE nombre LIKE ? ORDER BY nombre";

        ResultSet rs;
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql)) {
            if (!filter.isEmpty()) {
                ps.setString(1, "%" + filter + "%");
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                s = getSala(rs);
                lista.add(s);
            }
        }

        return lista;
    }

    @Override
    public Sala findByCodigo(String nombre) throws SQLException, IOException {
        Sala s;

        String sql = "SELECT * FROM sala WHERE nombre = ?";
        ResultSet rs;
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            rs.next();
            s = getSala(rs);
        }

        return s;
    }

    public List<String> getSalas() throws SQLException, IOException {
        List<String> lista = new ArrayList<>();

        lista.add("sala");
        String sql = "SELECT * FROM sala ORDER BY nombre";
        ResultSet rs;
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("nombre").trim());
            }
        }

        return lista;
    }
}

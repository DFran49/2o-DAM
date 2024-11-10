package jl.conferenciasapp.daos;

import jl.conferenciasapp.interfaces.ConferenciasInt;
import jl.conferenciasapp.utils.DbConnection;
import jl.conferenciasapp.models.Conferencia;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConferenciaDAO extends BaseDAO implements ConferenciasInt<Conferencia> {
    public ConferenciaDAO() { }

    @Override
    public void update(Conferencia c) throws SQLException, IOException {
        String sql = "UPDATE conferencia SET tema = ?, precio = ?, fecha = ?, turno = ?, sala = ? WHERE referencia = ?";
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql)) {
            ps.setString(6, c.getReferencia());
            ps.setString(1, c.getTema());
            ps.setDouble(2, c.getPrecio());
            ps.setDate(3, Date.valueOf(c.getFecha()));
            ps.setString(4, c.getTurno());
            ps.setString(5, c.getSala());
            ps.executeUpdate();
        }
    }

    @Override
    public void insert(Conferencia c) throws SQLException, IOException {
        String sql = "INSERT INTO conferencia(referencia, tema, precio, fecha, turno, sala) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, c.getReferencia());
            ps.setString(2, c.getTema());
            ps.setDouble(3, c.getPrecio());
            ps.setDate(4, Date.valueOf(c.getFecha()));
            ps.setString(5, c.getTurno());
            ps.setString(6, c.getSala());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(String referencia) throws SQLException, IOException {
        String sql = "DELETE FROM conferencia WHERE referencia = ?";
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, referencia);
            ps.executeUpdate();
        }
    }

    private Conferencia getConferencia(ResultSet rs) throws SQLException {
        Conferencia c;
        c = new Conferencia();
        c.setReferencia(rs.getString("referencia").trim());
        // "tema" puede tomar valores NULL
        c.setTema(getStringOrNull(rs, "tema"));
        // "precio" puede tomar valores NULL
        c.setPrecio(rs.getDouble("precio"));
        c.setFecha(rs.getDate("fecha").toLocalDate());
        // "turno" puede tomar valores NULL
        c.setTurno(getStringOrNull(rs, "turno"));
        c.setSala(rs.getString("sala").trim());
        return c;
    }

    @Override
    public List<Conferencia> getAll(String filter) throws SQLException, IOException {
        List<Conferencia> lista = new ArrayList<>();
        Conferencia c;
        String sql = "SELECT * FROM conferencia";

        if (!filter.isEmpty())
            sql = "SELECT * FROM conferencia WHERE referencia LIKE ? OR tema LIKE ? OR sala LIKE ?";

        ResultSet rs;
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql)) {
            if (!filter.isEmpty()) {
                for (int i = 1; i <= 3; i++)
                    ps.setString(i, "%" + filter + "%");
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                c = getConferencia(rs);
                lista.add(c);
            }
        }

        return lista;
    }

    @Override
    public Conferencia findByCodigo(String referencia) throws SQLException, IOException {
        Conferencia c;

        String sql = "SELECT * FROM conferencia WHERE referencia = ?";
        ResultSet rs;
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, referencia);
            rs = ps.executeQuery();
            rs.next();
            c = getConferencia(rs);
        }

        return c;
    }
}

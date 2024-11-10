package jl.conferenciasapp.daos;

import jl.conferenciasapp.interfaces.ConferenciasInt;
import jl.conferenciasapp.models.Asistente;
import jl.conferenciasapp.utils.DbConnection;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AsistenteDAO extends BaseDAO implements ConferenciasInt<Asistente> {
    public AsistenteDAO() { }

    @Override
    public void update(Asistente a) throws SQLException, IOException {
        String sql = "UPDATE asistente SET nombre = ?, apellido1 = ?, apellido2 = ?, sexo = ?, fechaNac = ?, empresa = ? WHERE codigo = ?";
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql)) {
            ps.setString(7, a.getCodigo());
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido1());
            ps.setString(3, a.getApellido2());
            ps.setString(4, a.getSexo());
            ps.setDate(5, Date.valueOf(a.getFechaNac()));
            ps.setString(6, a.getEmpresa());
            ps.executeUpdate();
        }
    }

    @Override
    public void insert(Asistente a) throws SQLException, IOException {
        String sql = "INSERT INTO asistente(codigo, nombre, apellido1, apellido2, sexo, fechaNac, empresa) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, a.getCodigo());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getApellido1());
            ps.setString(4, a.getApellido2());
            ps.setString(5, a.getSexo());
            ps.setDate(6, Date.valueOf(a.getFechaNac()));
            ps.setString(7, a.getEmpresa());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(String codigo) throws SQLException, IOException {
        String sql = "DELETE FROM asistente WHERE codigo = ?";
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.executeUpdate();
        }
    }

    private Asistente getAsistente(ResultSet rs) throws SQLException {
        Asistente a;
        a = new Asistente();
        a.setCodigo(rs.getString("codigo").trim());
        a.setNombre(rs.getString("nombre").trim());
        a.setApellido1(rs.getString("apellido1").trim());
        // "apellido2" puede tomar valores NULL
        a.setApellido2(getStringOrNull(rs, "apellido2"));
        // "sexo" puede tomar valores NULL
        a.setSexo(getStringOrNull(rs, "sexo"));
        a.setFechaNac(rs.getDate("fechaNac").toLocalDate());
        // "empresa" puede tomar valores NULL
        a.setEmpresa(getStringOrNull(rs, "empresa"));
        return a;
    }

    @Override
    public List<Asistente> getAll(String filter) throws SQLException, IOException {
        List<Asistente> lista = new ArrayList<>();
        Asistente a;
        String sql = "SELECT * FROM asistente";

        if (!filter.isEmpty())
            sql = "SELECT * FROM asistente WHERE codigo LIKE ? OR nombre LIKE ? OR apellido1 LIKE ? OR apellido2 LIKE ? OR empresa LIKE ?";

        ResultSet rs;
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql)) {
            if (!filter.isEmpty()) {
                for (int i = 1; i <= 5; i++)
                    ps.setString(i, "%" + filter + "%");
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                a = getAsistente(rs);
                lista.add(a);
            }
        }
        return lista;
    }

    @Override
    public Asistente findByCodigo(String codigo) throws SQLException, IOException {
        Asistente a;

        String sql = "SELECT * FROM asistente WHERE codigo = ?";
        ResultSet rs;
        try (PreparedStatement ps = DbConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            rs.next();
            a = getAsistente(rs);
        }

        return a;
    }
}

package jl.conferenciasapp.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ConferenciasInt<T> {
    void update(T elem) throws SQLException, IOException;
    void insert(T elem) throws SQLException, IOException;
    void delete(String ref) throws SQLException, IOException;
    List<T> getAll(String filter) throws SQLException, IOException;
    T findByCodigo(String ref) throws SQLException, IOException;
}

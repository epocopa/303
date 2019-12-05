package negocio.grupo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrupoSAImpTest {
    private static Connection conn;
    private TGrupo grupo1;
    private TGrupo grupo2;
    private GrupoSAImp grupoSAImpl;
    @BeforeAll
    static void beforeAll() {

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trescerotres", "empleado", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void BeforeEach() {
        try(Statement st=conn.createStatement()){
            st.execute("DELETE FROM grupo");
        }catch(SQLException e) {
            e.printStackTrace();
        }

        grupo1 = new TGrupo(1,"textil",true);
        grupo2 = new TGrupo(2,"calzado",true);

    }
    @Test
    void testInsertar() {
        try{
            grupoSAImpl.insertar(grupo1);
            TGrupo auxiliar = grupoSAImpl.mostrar(grupo1.getId());
            assertTrue(iguales(grupo1,auxiliar));
        }catch (Exception e) {
            fail("Excepcion al insertar");
        }
    }

    @Test
    void testMostrarTodos() {
        ArrayList<TGrupo> lista = new ArrayList<>();
        lista.add(grupo1);
        lista.add(grupo2);
        try{
            for (TGrupo tGrupo : lista) {
                grupoSAImpl.insertar(tGrupo);
            }
            List<TGrupo> te = grupoSAImpl.mostrarTodos();

            for (int i = 0; i < lista.size(); i++) {
                if (!iguales(lista.get(i), te.get(i))) {
                    fail("El grupo leido no se corresponde con el insertado");
                }
            }
        }catch (Exception e){
            fail("Excepcion al mostrar todos");
        }
    }

    @Test
    void testModificar() {
        try{
            grupoSAImpl.insertar(grupo1);
            grupo1.setSeccion("optica");
            grupoSAImpl.modificar(grupo1);
            TGrupo auxiliar = grupoSAImpl.mostrar(grupo1.getId());
            assertTrue(iguales(grupo1,auxiliar));

        }catch (Exception e){
            fail("Excepcion al modificar");
        }
    }

    @Test
    void testEliminar() {
        try{
            grupoSAImpl.insertar(grupo1);
            grupoSAImpl.eliminar(grupo1.getId());
            assertFalse(grupoSAImpl.mostrar(grupo1.getId()).isActivo());
        }catch (Exception e){
            fail("Excepcion al eliminar");
        }
    }

    @Test
    void testInsertarEmpleado() {
    }

    @Test
    void testEliminarEmpleado() {
    }

    @AfterAll
    static void afterAll() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean iguales(TGrupo tGrupo1, TGrupo auxiliar) {
        return tGrupo1.getId() == auxiliar.getId() &&
                tGrupo1.getSeccion() == auxiliar.getSeccion();
    }
}
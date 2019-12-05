package negocio.turno;

import negocio.empleado.TEmpleado;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

class TurnoSAImpTest {
    private static Connection conn;
    private TTurno turno1;
    private TTurno turno2;
    private TurnoSAImp turnoSAImp;
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
            st.execute("DELETE FROM turno");
        }catch(SQLException e) {
            e.printStackTrace();
        }

        turnoSAImp = new TurnoSAImp();
        turno1 = new TTurno(1,"mañana", LocalTime.of(8,00),LocalTime.of(14,00),true);
        turno2 = new TTurno(2,"tarde", LocalTime.of(14,00),LocalTime.of(20,00),true);
    }
    @Test
    void testInsertar() {
        try{
            turnoSAImp.insertar(turno1);
            TTurno auxiliar = turnoSAImp.mostrar(turno1.getId());
            assertTrue(iguales(turno1,auxiliar));
        }catch (Exception e) {
            fail("Excepcion al insertar");
        }
    }

    @Test
    void testMostrarTodos() {
        ArrayList<TTurno> lista = new ArrayList<>();
        lista.add(turno1);
        lista.add(turno2);
        try{
            for (TTurno tTurno : lista) {
                turnoSAImp.insertar(tTurno);
            }
            List<TTurno> te = turnoSAImp.mostrarTodos();

            for (int i = 0; i < lista.size(); i++) {
                if (!iguales(lista.get(i), te.get(i))) {
                    fail("El empleado leido no se corresponde con el insertado");
                }
            }
        }catch (Exception e){
            fail("Excepcion al mostrar todos");
        }
    }

    @Test
    void testModificar() {
        try{
            turnoSAImp.insertar(turno1);
            turno1.setNombre("noche");
            turnoSAImp.modificar(turno1);
            TTurno auxiliar = turnoSAImp.mostrar(turno1.getId());
            assertTrue(iguales(turno1,auxiliar));

        }catch (Exception e){
            fail("Excepcion al modificar");
        }
    }

    @Test
    void testEliminar() {
        try{
            turnoSAImp.insertar(turno1);
            turnoSAImp.eliminar(turno1.getId());
            assertFalse(turnoSAImp.mostrar(turno1.getId()).isActivo());
        }catch (Exception e){
            fail("Excepcion al eliminar");
        }
    }

    @Test
    void testAnadirEmpleado() {
    }

    @Test
    void testBorrarEmpleado() {
    }

    @AfterAll
    static void afterAll() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean iguales(TTurno turno1, TTurno auxiliar) {
        return turno1.getHoraFin() == auxiliar.getHoraFin() &&
                turno1.getHoraInicio() == auxiliar.getHoraInicio() &&
                turno1.getId() == auxiliar.getId() &&
                turno1.getNombre() == auxiliar.getNombre();
    }
}
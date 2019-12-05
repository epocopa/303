package negocio.empleado;

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

class EmpleadoSAImpTest {
    private static Connection conn;
    private EmpleadoSAImp empleadoSAImp;
    private TEmpleado empleado1;
    private TEmpleado empleado2;
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
            st.execute("DELETE FROM empleado");
        }catch(SQLException e) {
            e.printStackTrace();
        }

        empleadoSAImp = new EmpleadoSAImp();
        //Empleado 1
        empleado1 = new TEmpleado();
        empleado1.setActivo(true);
        empleado1.setDNI("842582382H");
        empleado1.setEncargado(true);
        empleado1.setNombre("Olegario");
        empleado1.setSalarioBase(650);
        empleado1.setId(1);
        //Empleado 2
        empleado2 = new TEmpleado();
        empleado2.setActivo(true);
        empleado2.setDNI("256256256F");
        empleado2.setEncargado(false);
        empleado2.setNombre("AlmeidaCP");
        empleado2.setSalarioBase(30);
        empleado2.setId(2);
    }
    @Test
    void testInsertar() {
        try{
            empleadoSAImp.insertar(empleado1);
            TEmpleado auxiliar = empleadoSAImp.mostrar(empleado1.getId());
            assertTrue(iguales(empleado1,auxiliar));
        }catch (Exception e) {
            fail("Excepcion al insertar");
        }
    }

    @Test
    void testMostrarTodos() {
        ArrayList<TEmpleado> lista = new ArrayList<>();
        lista.add(empleado1);
        lista.add(empleado2);
        try{
            for (TEmpleado tEmpleado : lista) {
                empleadoSAImp.insertar(tEmpleado);
            }
            List<TEmpleado> te = empleadoSAImp.mostrarTodos();

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
            empleadoSAImp.insertar(empleado1);
            empleado1.setSalarioBase(1919);
            empleado1.setNombre("Rodolfo");
            empleadoSAImp.modificar(empleado1);
            TEmpleado auxiliar = empleadoSAImp.mostrar(empleado1.getId());
            assertTrue(iguales(empleado1,auxiliar));

        }catch (Exception e){
            fail("Excepcion al modificar");
        }
    }

    @Test
    void testEliminar() {
        try{
            empleadoSAImp.insertar(empleado1);
            empleadoSAImp.eliminar(empleado1.getId());
            assertFalse(empleadoSAImp.mostrar(empleado1.getId()).isActivo());
        }catch (Exception e){
            fail("Excepcion al eliminar");
        }
    }

    @AfterAll
    static void afterAll() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean iguales(TEmpleado empleado1, TEmpleado auxiliar) {
        return empleado1.getDNI() == auxiliar.getDNI() &&
                empleado1.getId() == auxiliar.getId() &&
                empleado1.getNombre() == auxiliar.getNombre() &&
                empleado1.getSalarioBase() == auxiliar.getSalarioBase();
    }
}
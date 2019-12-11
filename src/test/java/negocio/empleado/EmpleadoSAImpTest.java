package negocio.empleado;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmpleadoSAImpTest {
    private static Connection conn;
    private EmpleadoSAImp empleadoSAImp;
    private TEmpleado tEmpleado1;
    private TEmpleado tEmpleado2;
    private Empleado empleado1;
    private Empleado empleado2;


    @BeforeEach
    void BeforeEach() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
        EntityManager em = emf.createEntityManager();
       // em.getTransaction().begin();

        Query query = em.createNamedQuery("Empleado.DELETEALL", Empleado.class);

        empleadoSAImp = new EmpleadoSAImp();
        //Empleado 1
        tEmpleado1 = new TEmpleado();
        tEmpleado1.setActivo(true);
        tEmpleado1.setDNI("842582382H");
        tEmpleado1.setEncargado(true);
        tEmpleado1.setNombre("Olegario");
        tEmpleado1.setSalarioBase(650);
        tEmpleado1.setId(1);



        //Empleado 2
        tEmpleado2 = new TEmpleado();
        tEmpleado2.setActivo(true);
        tEmpleado2.setDNI("256256256F");
        tEmpleado2.setEncargado(false);
        tEmpleado2.setNombre("AlmeidaCP");
        tEmpleado2.setSalarioBase(30);
        tEmpleado2.setId(2);

    }
    @Test
    void testInsertar() {
        try{
            empleadoSAImp.insertar(tEmpleado1);
            TEmpleado auxiliar = empleadoSAImp.mostrar(tEmpleado1.getId());
            assertTrue(iguales(tEmpleado1,auxiliar));
        }catch (Exception e) {
            fail("Excepcion al insertar");
        }
    }

    @Test
    void testMostrarTodos() {
        ArrayList<TEmpleado> lista = new ArrayList<>();
        lista.add(tEmpleado1);
        lista.add(tEmpleado2);
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
            empleadoSAImp.insertar(tEmpleado1);
            tEmpleado1.setSalarioBase(1919);
            tEmpleado1.setNombre("Rodolfo");
            empleadoSAImp.modificar(tEmpleado1);
            TEmpleado auxiliar = empleadoSAImp.mostrar(tEmpleado1.getId());
            assertTrue(iguales(tEmpleado1,auxiliar));

        }catch (Exception e){
            fail("Excepcion al modificar");
        }
    }

    @Test
    void testEliminar() {
        try{
            empleadoSAImp.insertar(tEmpleado1);
            empleadoSAImp.eliminar(tEmpleado1.getId());
            assertFalse(empleadoSAImp.mostrar(tEmpleado1.getId()).isActivo());
        }catch (Exception e){
            fail("Excepcion al eliminar");
        }
    }

    @AfterEach
    void afterEach(){
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("303");
        EntityManager em = emf.createEntityManager();

        em.close();
        emf.close();*/
    }

    private boolean iguales(TEmpleado empleado1, TEmpleado auxiliar) {
        return empleado1.getDNI() == auxiliar.getDNI() &&
                empleado1.getId() == auxiliar.getId() &&
                empleado1.getNombre() == auxiliar.getNombre() &&
                empleado1.getSalarioBase() == auxiliar.getSalarioBase();
    }
}
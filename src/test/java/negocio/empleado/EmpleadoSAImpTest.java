package negocio.empleado;

import negocio.grupo.Grupo;
import negocio.turno.Turno;
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
        em.getTransaction().begin();

        Query query = em.createNamedQuery("AsignacionGrupo.DELETEALL", AsignacionGrupo.class);
        query.executeUpdate();
        query = em.createNamedQuery("Grupo.DELETEALL", Grupo.class);
        query.executeUpdate();
        query = em.createNamedQuery("Empleado.DELETEALL", Empleado.class);
        query.executeUpdate();
        query = em.createNamedQuery("Turno.DELETEALL", Turno.class);
        query.executeUpdate();

        empleadoSAImp = new EmpleadoSAImp();
        //Empleado 1
        tEmpleado1 = new TEncargado(1,"Olegario","842582382H",650,true,0.3,-1);

        //Empleado 2
        tEmpleado2 = new TDependiente(2,"Pedro","256256256F",30,true,20,-1);
        em.getTransaction().commit();
        em.close();
        emf.close();
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
        return empleado1.getDNI().equals(auxiliar.getDNI()) &&
                empleado1.getId() == auxiliar.getId() &&
                empleado1.getNombre().equals(auxiliar.getNombre()) &&
                empleado1.getSalarioBase() == auxiliar.getSalarioBase();
    }
}
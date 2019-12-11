package negocio.turno;

import negocio.empleado.*;
import negocio.grupo.Grupo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Connection;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TurnoSAImpTest {
    private static Connection conn;
    private TTurno turno1;
    private TTurno turno2;
    private TurnoSAImp turnoSAImp;
    private EmpleadoSAImp empleadoSAImp;
    private TEmpleado empleado1;

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

        turnoSAImp = new TurnoSAImp();
        turno1 = new TTurno(1,"manana", LocalTime.of(8,0),LocalTime.of(14,0),true);
        turno2 = new TTurno(2,"tarde", LocalTime.of(14, 0),LocalTime.of(20,0),true);

        empleadoSAImp = new EmpleadoSAImp();
        empleado1 = new TDependiente(1,"Jose","578344400S",1000,true,60,1);

        em.getTransaction().commit();
        em.close();
        emf.close();
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
        try{
            turnoSAImp.insertar(turno1);
            empleadoSAImp.insertar(empleado1);
            turnoSAImp.insertarEmpleado(turno1.getId(),empleado1);
            empleado1 = empleadoSAImp.mostrar(empleado1.getId());
            assertEquals(empleado1.getIdTurno(), turno1.getId());
        }catch (Exception e){
            fail("Excepcion al anadir empleado");
        }
    }

    @Test
    void testBorrarEmpleado() {
        try{
            turnoSAImp.insertar(turno1);
            empleadoSAImp.insertar(empleado1);
            turnoSAImp.insertarEmpleado(turno1.getId(),empleado1);
            turnoSAImp.eliminarEmpleado(turno1.getId(),empleado1);
            empleado1 = empleadoSAImp.mostrar(empleado1.getId());
            assertNotEquals(empleado1.getIdTurno(), turno1.getId());
        }catch (Exception e){
            fail("Excepcion al eliminar empleado");
        }
    }

    private boolean iguales(TTurno turno1, TTurno auxiliar) {
        return turno1.getHoraFin() == auxiliar.getHoraFin() &&
                turno1.getHoraInicio() == auxiliar.getHoraInicio() &&
                turno1.getId() == auxiliar.getId() &&
                turno1.getNombre().equals(auxiliar.getNombre());
    }
}
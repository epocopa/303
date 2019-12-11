package negocio.grupo;

import negocio.empleado.*;
import negocio.turno.Turno;
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

class GrupoSAImpTest {
    private static Connection conn;
    private GrupoSAImp grupoSAImpl;
    private TGrupo grupo1;
    private TGrupo grupo2;
    private EmpleadoSAImp empleadoSAImp;
    private TEmpleado empleado1;
    private TTrabaja trabaja1;



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

        grupo1 = new TGrupo(1,"textil",true);
        grupo2 = new TGrupo(2,"calzado",true);

        grupoSAImpl = new GrupoSAImp();
        empleado1 = new TDependiente(1,"Jose","578344400S",1000,true,10,-1);

        empleadoSAImp = new EmpleadoSAImp();


        em.getTransaction().commit();
        em.close();
        emf.close();
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
        try{
           empleadoSAImp.insertar(empleado1);
           grupoSAImpl.insertar(grupo1);
           trabaja1 = new TTrabaja(grupo1.getId(),empleado1.getId(),5);
           grupoSAImpl.insertarEmpleado(trabaja1);
           empleado1 = empleadoSAImp.mostrar(empleado1.getId());
           assertTrue(trabaja1.getIdEmpleado()==empleado1.getId() &&
                   trabaja1.getIdGrupo() == grupo1.getId());
        }catch(Exception e){
            fail("Excepcion al insertar empleado en turno");
        }
    }

    @Test
    void testEliminarEmpleado() {
        try{
            empleadoSAImp.insertar(empleado1);
            grupoSAImpl.insertar(grupo1);
            trabaja1 = new TTrabaja(grupo1.getId(),empleado1.getId(),5);
            grupoSAImpl.insertarEmpleado(trabaja1);
            grupoSAImpl.eliminarEmpleado(trabaja1);
            empleado1 = empleadoSAImp.mostrar(empleado1.getId());
            assertTrue(trabaja1.getIdEmpleado()==empleado1.getId() &&
                    trabaja1.getIdGrupo() == grupo1.getId());
        }catch(Exception e){
            fail("Excepcion al eliminar empleado en turno");
        }
    }



    private boolean iguales(TGrupo tGrupo1, TGrupo auxiliar) {
        return tGrupo1.getId() == auxiliar.getId() &&
                tGrupo1.getSeccion().equals(auxiliar.getSeccion());
    }
}
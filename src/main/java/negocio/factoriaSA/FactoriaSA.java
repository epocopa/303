package negocio.factoriaSA;

import negocio.cliente.ClienteSA;
import negocio.empleado.EmpleadoSA;
import negocio.factura.FacturaSA;
import negocio.grupo.GrupoSA;
import negocio.producto.ProductoSA;
import negocio.turno.TurnoSA;

public abstract class FactoriaSA {
	private static FactoriaSA sa;
	
	public static FactoriaSA getInstancia() {
		if(sa == null) {
			sa = new FactoriaSAImp();
		}
		return sa;
	}
	
	public abstract ClienteSA generaClienteSA();
	public abstract FacturaSA generaFacturaSA();
	public abstract ProductoSA generaProductoSA();
	public abstract EmpleadoSA generaEmpleadoSA();
	public abstract GrupoSA generaGrupoSA();
	public abstract TurnoSA generaTurnoSA();
}
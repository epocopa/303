package negocio.factoriaSA;


import negocio.cliente.ClienteSA;
import negocio.cliente.ClienteSAImp;
import negocio.empleado.EmpleadoSA;
import negocio.empleado.EmpleadoSAImp;
import negocio.factura.FacturaSA;
import negocio.factura.FacturaSAImp;
import negocio.grupo.GrupoSA;
import negocio.grupo.GrupoSAImp;
import negocio.producto.ProductoSA;
import negocio.producto.ProductoSAImp;
import negocio.turno.TurnoSA;
import negocio.turno.TurnoSAImp;

public class FactoriaSAImp extends FactoriaSA{

	@Override
	public ClienteSA generaClienteSA(){
		return new ClienteSAImp();
	}

	@Override
	public FacturaSA generaFacturaSA(){
		return new FacturaSAImp();
	}

	@Override
	public ProductoSA generaProductoSA(){
		return new ProductoSAImp();
	}

	@Override
	public EmpleadoSA generaEmpleadoSA() {
		return new EmpleadoSAImp();
	}

	@Override
	public GrupoSA generaGrupoSA() {
		return new GrupoSAImp();
	}

	@Override
	public TurnoSA generaTurnoSA() {
		return new TurnoSAImp();
	}
}
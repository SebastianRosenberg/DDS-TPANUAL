package procesos;

import procesos.AdministradorDeProcesos.EstadoResultado;
import tpanual.usuario.Usuario;

public class ReintentarNVeces extends AccionEnCasoDeError{
	   private int n;
	   public ReintentarNVeces(int n){
		   this.n = n;
	   }
	   
		@Override
		public RespuestaProceso RealizarAccion(Usuario usuario, Proceso proceso, RespuestaProceso respuesta) {
			for(int i = 0; i < n; i++)
			{
				RespuestaProceso resp = proceso.procesar();
				
				if (resp.getEstado() == EstadoResultado.OK)
				{
					return resp;
				}
			}
			return respuesta;
			
		}
	}
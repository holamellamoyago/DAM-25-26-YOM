/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package empresahb26alu_xml;

import LOGICA.GestorEmpresaHB;
import POJOS.Empregado;
import POJOS.Funcion;
import POJOS.Proxecto;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

public class EMPRESAHB26ALU_XML {

   public static void main(String[] args) throws ParseException {
      GestorEmpresaHB.comprobarConexion();
      
      Proxecto p = GestorEmpresaHB.visualizarProxecto(1);
      GestorEmpresaHB.cambiarLugarProxecto(p, "MOña");

      GestorEmpresaHB.obtenerEmpregado("0010010");

      // Functiones
      Funcion f1 = new Funcion();
      f1.setFuncion("Limpiar habitaciones");

      Funcion f2 = new Funcion();
      f2.setFuncion("Limpiar baños");

      Set<String> funciones = new HashSet<>(Set.of("Limpiar Baños", "Limpiar habitaciones"));
      GestorEmpresaHB.anadirFuncionesDepartamento(1, funciones);


      GestorEmpresaHB.anadirTelefonoEmpregado("0010010", "692433876", "MOVIL PARTICULAR");




   }
}

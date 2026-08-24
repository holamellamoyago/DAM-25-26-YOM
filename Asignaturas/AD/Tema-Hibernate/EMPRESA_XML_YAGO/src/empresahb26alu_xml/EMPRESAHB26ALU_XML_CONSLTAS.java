/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package empresahb26alu_xml;

import LOGICA.GestorEmpresaHB;
import POJOS.Departamento;
import POJOS.Empregado;
import POJOS.Funcion;
import POJOS.Proxecto;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

public class EMPRESAHB26ALU_XML_CONSLTAS {

   public static void main(String[] args) throws ParseException {
      GestorEmpresaHB.comprobarConexion();

      System.out.println();
      Proxecto p = GestorEmpresaHB.visualizarProxecto(1);
      Empregado e = GestorEmpresaHB.obtenerEmpregado("0010010");

      GestorEmpresaHB.listarProxectosVigoSantiago();
      GestorEmpresaHB.listarProxectosVigoSantiagoDTO();

   }
}
